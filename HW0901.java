

/**
 * InnerHW0901
 */
public class HW0901 {
    public static void main(String[] args) {
        //Challenge: Print Hello World
        System.out.println("Hello World"); 


        //Challenge: Print Variables
        int age = 33;
        double height = 5.9;
        String name = "Boone Wilder";

        System.out.printf("Age: %d\nHeight: %f\nName: %s\n", age, height, name);


        //Challenge: Operators
        int a = 20;
        int b = 10;

        System.out.println("Addition: " + (a+b));
        System.out.println("Subtraction: " + (a-b));
        System.out.println("Multiplication: " + (a*b));
        System.out.println("Division: " + (a/b));
        Boolean c = a > b;
        System.out.println("Is a greater than b? " + c);
        Boolean d = a > b && b > 0; 
        System.out.println("Is a > b and b > 0? " + d);


        //Challenge: Grading
        int score = 75;
        char grade = 'F';
        
        if (score < 50) {
            System.out.println("failed");
        }
        else if (score >=50 && score < 60) {
            System.out.println("passed");
            grade = 'E';

        }
        else if (score >=60 && score < 70) {
            grade = 'D';

        }
        else if (score >=70 && score < 80) {
            grade = 'D';

        }
        else if (score >=80 && score < 90) {
            grade = 'B';

        }
        else if (score >=90) {
            grade = 'A';

        }
        System.out.println("Grade: " + grade);



        //Challenge: Loops
        System.out.print("For Loop: ");
        for (int i = 1; i < 6; i++) {
            System.out.print(i);

        }

        System.out.print("\nWhile Loop:");
        int cond = 1;
        while (cond < 6) {
            System.out.print(" " + cond);
            cond++;
        }

        System.out.print("\nDo-While Loop:");
        cond = 1;
        do {
            System.out.print(" " + cond);
            cond++;
        } while (cond < 5);
         System.out.println("");


        //Challenge: Calculator
        double num1 = 4;
        double num2 = 8;
        char operator = '+';
        String again = "y";

        while (again == "y") {
            if (operator == '+') {
                System.out.println("Result: " + (num1+num2));
                again = "n";
            }
            else if (operator == '-') {
                System.out.println("Result: " + (num1+num2));
                again = "n";

            }
            else if (operator == '*') {
                System.out.println("Result: " + (num1+num2));
                again = "n";
                
            }
            else if (operator == '/') {
                if (num2 == 0) {
                    System.out.println("Cannot divide by Zero");
                    again = "n";
                }
                else {
                    System.out.println("Result: " + (num1/num2));
                    again = "n";
                }
                
            }
        }
         System.out.println("Thank you for using the calculator.");


    }
    
} 