import java.util.*;

public class MainLibrary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryItem current = null;

        System.out.println("=== Library Management System ===");
        System.out.println("1. Add Book");
        System.out.println("2. Add DVD");
        System.out.println("3. Borrow");
        System.out.println("4. Show borrow duration");
        System.out.println("0. Exit");

        while (true) {
            try {
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        System.out.print("Book title: ");
                        String bTitle = sc.nextLine();
                        System.out.print("Pages: ");
                        int bPages = sc.nextInt();
                        if (bPages <= 0) throw new IllegalArgumentException("Pages must be positive");
                        current = new Book(bTitle, bPages);
                        System.out.println("Book added");
                        break;
                    case 2:
                        System.out.print("DVD title: ");
                        String dTitle = sc.nextLine();
                        System.out.print("Duration (min): ");
                        int dDur = sc.nextInt();
                        if (dDur <= 0) throw new IllegalArgumentException("Duration must be positive");
                        current = new DVD(dTitle, dDur);
                        System.out.println("DVD added");
                        break;
                    case 3:
                        if (current == null) {
                            System.out.println("No item loaded");
                        } else {
                            current.borrow();
                        }
                        break;
                    case 4:
                        if (current == null) {
                            System.out.println("No item loaded");
                        } else {
                            System.out.println("Borrow duration: " + current.getBorrowDuration() + " days");
                        }
                        break;
                    case 0:
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter numbers only.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}