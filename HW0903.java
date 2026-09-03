import java.util.Scanner;

public class HW0903 {
    static Scanner in;
    
    public static void main(String[] args){

        in = new Scanner(System.in);

        System.out.println("REPL app challenge:");
        REPL();

        System.out.println("Password validation challenge:");
        Validator();

        System.out.println("Word analyzer challenge:");
        Analyzer();

        

        
        in.close();
    }
    public static void REPL() {
        System.out.println("Welcome to my REPL App!");
        
        String replString = "default";

        while (!replString.equals("quit")) {
            System.out.print("Please enter your command: ");
            replString = in.nextLine().trim().toLowerCase();
            System.err.println("c is " + replString);
            
            if (replString.equals("help")) {//help
                System.out.print("Available commands:\nadd\nsubtract\nmultiply\ndivide\nrandom\nreverse\nquit\n");
            }
            else if(replString.equals("add")) {// add
                System.out.print("First number: ");
                int a = in.nextInt();
                System.out.print("Second number: ");
                int b = in.nextInt();

                System.out.println("Result: " + (a+b));

            }
            else if (replString.equals("subtract")) {// subtract
                System.out.print("First number: ");
                int a = in.nextInt();
                System.out.print("Second number: ");
                int b = in.nextInt();

                System.out.println("Result: " + (a-b)); 
                
            }
            else if (replString.equals("multiply")) { // multiply
                System.out.print("First number: ");
                int a = in.nextInt();
                System.out.print("Second number: ");
                int b = in.nextInt();

                System.out.println("Result: " + (a*b)); 

            }
            else if (replString.equals("divide")){// divide
                System.out.print("First number: ");
                int a = in.nextInt();
                System.out.print("Second number: ");
                int b = in.nextInt();

                if (b != 0) {
                   System.out.println("Result: " + (a/b)); 
                }
                else {
                    System.out.println("Cannot divide by zero"); 
                }

            }
            else if (replString.equals("random")) {// random
                System.out.print("First number: ");
                int a = in.nextInt();
                System.out.print("Second number: ");
                int b = in.nextInt();

                int r = (int)(Math.random() * ((b - a) + 1)) + a;

                System.out.println("Result: " + r); 
            }
            else if (replString.equals("reverse")) {// reverse
                System.out.print("Please enter a string: ");
                String a = in.nextLine();
                String b = new StringBuilder(a).reverse().toString();
                System.out.println("Result: " + b); 

            }
            else if (replString.equals("quit")) {// quit
                System.out.println("Goodbye! "); 

            }
            else {//invalid entry
                System.out.println("Sorry, that was not a valid command."); 

            }
            
        }

    }
    

    public static void Validator() {
        boolean uc = false;
        boolean lc = false;
        boolean n = false;
        
        
        System.out.print("Please enter a password to validate: ");
        
        String p = in.nextLine();

        char[] arr = p.toCharArray();

        for (char c : arr) {
            if (Character.isUpperCase(c)) {
                uc = true;
            }
            else if (Character.isLowerCase(c)) {
                lc = true;
            }
            else if (Character.isDigit(c)) {
                n = true;
            }
        }
        
        if (uc == true && lc == true && n == true) {
            System.out.println("This password is valid");
        }
        else {
            System.out.println("Password Rejected:");
            if (uc == false) {
                System.out.println("- Must contain an uppercase character");

            }
            if (lc == false) {
                System.out.println("- Must contain a lowercase character");
                
            }
            if (n == false) {
                System.out.println("- Must contain a number");
                
            }
        }
    }

    public static void Analyzer() {
        System.out.print("Please enter a word to analyze: ");
        Scanner in = new Scanner(System.in);
        char[] arr = in.nextLine().toLowerCase().toCharArray();
        int charCount = 0;
        int vCount = 0;
        int cCount = 0;
        int dCount = 0;
        int sCount = 0;
        String consonants = "bcdfghjklmnpqrstuvwxyz";
        String vowels = "aeiou";
        String digits = "1234567890";
        for (char c : arr) {
            charCount++;
            if (consonants.indexOf(c) != -1) {
                cCount++;
            }
            else if (vowels.indexOf(c) != -1) {
                vCount++;
            }
            else if (digits.indexOf(c) != -1) {
                dCount++;
            }
            else if (c == ' ') {
                sCount++;
            }

        }
        System.out.println("Characters - " + charCount);
        System.out.println("Consonants - " + cCount);
        System.out.println("Vowels - " + vCount);
        System.out.println("Digits - " + dCount);
        System.out.println("Spaces - " + sCount);

        
    }
}
