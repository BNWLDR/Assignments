import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

class HW0904 {
    public static void main(String[] args) {
        LocalDate n = LocalDate.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String today = n.format(f);
        Month m = n.getMonth();
        int d = n.getDayOfMonth();
        int y = n.getYear();
        System.out.println("Date: " + today);
        System.out.println("Year: " + y);
        System.out.println("Month: " + m);
        System.out.println("Day: " + d);
        System.out.print("Enter your birthdate in YYYY-MM-DD format: ");
        Scanner scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        int bd = Integer.parseInt(res.substring(8));
        int bm = Integer.parseInt(res.substring(5,7));
        int by = Integer.parseInt(res.substring(0,4));

        LocalDate bday = LocalDate.of(by, bm, bd);
        Period p = Period.between(bday, n);
        System.out.println("You are " + p.getYears() + " years old");

        System.out.print("Enter your birthdate in YYYY-MM-DD format: ");
        res = scanner.nextLine();
        bd = Integer.parseInt(res.substring(8));
        bm = Integer.parseInt(res.substring(5,7));
        by = Integer.parseInt(res.substring(0,4));

        bday = LocalDate.of(n.getYear() + 1, bm, bd);
        //System.out.print(bday);
        p = Period.between(n, bday);
        System.out.println("Days until your birthday: " + ChronoUnit.DAYS.between(n, bday));

        scanner.close();
    }
}