package Movie_Theater;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static Reservation reservation;
    private static Cancellation cancellation;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        // ONE theater for the whole program
        Theater theater = new Theater();

        // Reservation uses the same theater
        reservation = new Reservation(theater);
        cancellation = new Cancellation(theater);

        System.out.println("\nWould you like to reserve a seat? (Y/N)");
        String response1 = scanner.nextLine().toUpperCase();

        if (response1.equals("Y")) {
            System.out.println("\nPlease select a seat:");
            String selectedSeat = scanner.nextLine().toUpperCase();
            reservation.bookSeat(selectedSeat);
        } else {
            scanner.close();
            return;
        }
    }

    public static void options() {
        System.out.println("\nWould you like to cancel(C) or reserve a new(N) seat? (C/N) else type (X) to exit.");
        String response2 = scanner.nextLine().toUpperCase();
        String selectedSeat;
        switch (response2) {
            case "N":
                System.out.println("\nPlease select a seat:");
                selectedSeat = scanner.nextLine().toUpperCase();
                reservation.bookSeat(selectedSeat);
                break;
            case "C":
                System.out.println("\nPlease select a seat to cancel (row and column): ");
                selectedSeat = scanner.nextLine().toUpperCase();
                cancellation.cancelSeat(selectedSeat);
                break;
            case "X":
                System.out.println("\nExiting the program.");
                break;
            default:
                System.out.println("\nInvalid input. Please enter 'N', 'C', or 'X'.");
                break;
        }

        // Close ONLY when the whole program is done
        scanner.close();
    }


    public static void clearConsole() {
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else {
            System.out.print("\033\143");
        }
    } catch (IOException | InterruptedException ex) {}
    }
}

