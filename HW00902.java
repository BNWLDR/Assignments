import java.util.*;

public class HW00902 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Challenge: Grading stats

        int[] arr = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Please enter a grade between 0 and 100: ");
            arr[i] = scanner.nextInt();
        }
        int total = 0;
        int ave = 0;
        int high = 0;
        int low = 101;
        
        for ( int a : arr) {
            if (a > high) {
                high = a;
            }
            if (a < low) {
                low = a;
            }
            total += a;
        }
        System.out.println("Total: " + total);
        System.out.println("Average: " + (total/5));
        System.out.println("Highest: " + high);
        System.out.println("Lowest: " + low);

        char grade = 'p';
        for (int score : arr) {
            if (score < 50) {
            System.out.println("failed");
            grade = 'F';
            }
            else if (score >=50 && score < 60) {
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
            System.out.println(score + " - " + grade);
        }


        //Challenge: REPL Bank sim
        int fn = 0;
        int balance = 0;
        int wd = 0;

        while (fn != 4){
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Please enter your selection: ");
            fn = scanner.nextInt();
            if (fn == 1) {
                //check balance
                System.out.println("Your current balance is: " + balance + "\n");
            }
            else if (fn == 2) {
                //deposit
                System.out.print("Please enter the amount that you would like to deposit: ");
                wd = scanner.nextInt();
                if (wd > 0) {
                    balance += wd;
                    wd = 0;
                }
                else {
                    System.out.print("I'm sorry, that's not a valid amount.\n");
                }
            }
            else if (fn == 3) {
                //withdraw
                System.out.print("Please enter the amount that you would like to withdraw: ");
                wd = scanner.nextInt();
                if (balance - wd >= 0) {
                    balance -= wd;
                    wd = 0;
                }
                else {
                    System.out.println("I'm sorry, that's more than your current balance...Brokie.\n");
                }
            }
            else if (fn == 4) {
                //exit
                System.out.print("Thank you and have a good day!");
                
            }
            else {
                //invalid
                System.out.println("I'm sorry, that is not a valid request. Please try again.\n");
            }
        }
    }
}
