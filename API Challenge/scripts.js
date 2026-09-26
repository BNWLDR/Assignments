const nameInput = document.getElementById("pokemonName");
const getPokemonBtn = document.getElementById("getPokemonBtn");
const pokemonTitle = document.getElementById("pokemonTitle");
const pokemonImage = document.getElementById("pokemonImage");
const pokemonType = document.getElementById("pokemonType");

async function getPokemon() {
    const name = nameInput.value.trim().toLowerCase();

    if (!name) {
        pokemonTitle.textContent = "Please enter a Pokemon name.";
        pokemonImage.style.display = "none";
        pokemonType.textContent = "";
        return;
    }

    try {
        const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);

        if (!response.ok) {
            throw new Error("Pokemon not found");
        }

        const data = await response.json();

        const types = data.types.map(t => t.type.name).join(" / ");

        pokemonTitle.textContent = data.name;
        pokemonImage.src = data.sprites.front_default;
        pokemonImage.alt = data.name;
        pokemonImage.style.display = "block";
        pokemonType.textContent = `Type: ${types}`;
    } catch (error) {
        pokemonTitle.textContent = error.message;
        pokemonImage.style.display = "none";
        pokemonType.textContent = "";
    }
}

getPokemonBtn.addEventListener("click", getPokemon);

