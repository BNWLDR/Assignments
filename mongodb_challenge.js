
// Clean slate

db.products.drop();
db.orders.drop();


// Create "products" collection with a JSON Schema validator

db.createCollection("products", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["name", "price", "inStock"],
      properties: {
        name: {
          bsonType: "string",
          description: "must be a string and is required",
        },
        price: {
          bsonType: ["int", "double", "long"],
          description: "must be an integer or double and is required",
        },
        inStock: {
          bsonType: "bool",
          description: "must be a boolean and is required",
        },
        specs: {
          bsonType: "object",
          description: "optional nested sub-document, e.g. { brand: 'Logitech' }",
        },
      },
    },
  },
  validationLevel: "strict",
  validationAction: "error",
});

print("\n--- Step 1: 'products' collection created with JSON Schema validator ---");


// insertMany() - add at least 3 gadgets matching the schema, each includes a nested "specs" sub-document with a "brand" field.

const insertResult = db.products.insertMany([
  {
    name: "Wireless Mouse",
    price: 25.99,
    inStock: true,
    specs: { brand: "Logitech", color: "Black", wireless: true },
  },
  {
    name: "Mechanical Keyboard",
    price: 89,
    inStock: true,
    specs: { brand: "Corsair", switchType: "Cherry MX Blue" },
  },
  {
    name: "Gaming Monitor",
    price: 249.99,
    inStock: false,
    specs: { brand: "Samsung", size: "27in", refreshRate: 144 },
  },
]);

print("\n--- Step 2: insertMany() result ---");
printjson(insertResult.insertedIds);


// Test validation - try to insert a product that VIOLATES the schema

print("\n--- Step 3: Testing schema validation with an invalid document ---");
try {
  db.products.insertOne({
    name: "Broken Webcam",
    inStock: true,
  });
  print("ERROR: insertOne() unexpectedly succeeded (validation did not trigger).");
} catch (e) {
  print("Validation correctly REJECTED the document. Error:");
  print(e.message || e);
}

// Also demonstrate a wrong-type failure
try {
  db.products.insertOne({
    name: "Broken Speaker",
    price: "not-a-number",
    inStock: true,
  });
  print("ERROR: insertOne() unexpectedly succeeded (validation did not trigger).");
} catch (e) {
  print("Validation correctly REJECTED the wrong-type document. Error:");
  print(e.message || e);
}


// Pick one product and run updates on it

const targetProduct = db.products.findOne({ name: "Wireless Mouse" });
print("\n--- Step 4: Target product for updates ---");
printjson(targetProduct);

// $set - add a new top-level field "category"
db.products.updateOne(
  { _id: targetProduct._id },
  { $set: { category: "Accessories" } }
);

// $inc - increase price by 15
db.products.updateOne(
  { _id: targetProduct._id },
  { $inc: { price: 15 } }
);

// $push - add "wireless" to tags array
db.products.updateOne(
  { _id: targetProduct._id },
  { $push: { tags: "wireless" } }
);

// $push again - add "bestseller"
db.products.updateOne(
  { _id: targetProduct._id },
  { $push: { tags: "bestseller" } }
);

print("\n--- After $set/$inc/$push updates ---");
printjson(db.products.findOne({ _id: targetProduct._id }));

// $pull - remove "wireless" from tags array
db.products.updateOne(
  { _id: targetProduct._id },
  { $pull: { tags: "wireless" } }
);

print("\n--- After $pull removes 'wireless' ---");
printjson(db.products.findOne({ _id: targetProduct._id }));


// Queries


// Find all products priced >= a certain amount
print("\n--- Step 5a: Products priced >= $50 ($gte) ---");
db.products.find({ price: { $gte: 50 } }).forEach((doc) => printjson(doc));

// Find all products made by a specific brand via dot notation
print("\n--- Step 5b: Products made by brand 'Logitech' (dot notation) ---");
db.products.find({ "specs.brand": "Logitech" }).forEach((doc) => printjson(doc));

// Find products whose category matches one in a list
print("\n--- Step 5c: Products with category in ['Accessories', 'Peripherals'] ($in) ---");
db.products
  .find({ category: { $in: ["Accessories", "Peripherals"] } })
  .forEach((doc) => printjson(doc));


// Create "orders" collection and link to a product via productId

db.createCollection("orders");

const orderResult = db.orders.insertOne({
  productId: targetProduct._id,
  quantity: 2,
});

print("\n--- Step 6: Inserted order ---");
printjson(orderResult);


// Aggregation pipeline: join orders -> products, output a clean receipt

print("\n--- Step 7: Customer receipt via $lookup + $unwind + $project ---");

const receipt = db.orders.aggregate([
  {
    $lookup: {
      from: "products",
      localField: "productId",
      foreignField: "_id",
      as: "product",
    },
  },
  { $unwind: "$product" },
  {
    $project: {
      _id: 0,
      productName: "$product.name",
      quantity: 1,
    },
  },
]).toArray();

printjson(receipt);

print("\n--- Script complete ---");
