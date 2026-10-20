package Movie_Theater;

import java.util.ArrayList;
import java.util.Random;

public class Theater {

    private final String[][] seatingChart = new String[6][6];
    //private final boolean[][] isSeatTaken = new boolean[6][6];

    // List to keep track of booked seats
    private final ArrayList<String> bookedSeats = new ArrayList<>();

    public Theater() {
        System.out.println("Welcome to the Movie Theater!\n");
        //initialize the seating chart with seat labels (A1, A2, ..., F6)
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                char columnLetter = (char) ('A' + j);
                int rowNumber = i + 1;

                seatingChart[i][j] = String.valueOf(columnLetter) + rowNumber;
            }
        }

        //Randomly mark some seats as taken
        // Random random = new Random();
        // for (int i = 0; i < 6; i++) {
        //     for (int j = 0; j < 6; j++) {
        //         isSeatTaken[i][j] = random.nextBoolean();
        //     }
        // }
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                if (random.nextBoolean()) {
                    bookedSeats.add(seatingChart[i][j]);
                }
            }
        }

        //Display the seating chart with taken seats marked
        System.out.println("Seating Chart (X = Taken):\n");

        displaySeats();
    }

    public void reserveSeat(String selectedSeat) {

        // boolean seatFound = false;

        // for (int i = 0; i < 6; i++) {
        //     for (int j = 0; j < 6; j++) {
        //         if (seatingChart[i][j].equals(selectedSeat)) {
        //             seatFound = true;
        //             if (isSeatTaken[i][j]) {
        //                 System.out.println("\nSorry, that seat is already taken.");
        //             } else {
        //                 Main.clearConsole();
        //                 System.out.println("\nYou have successfully booked seat " + selectedSeat + "!");
        //                 isSeatTaken[i][j] = true;

        //                 System.out.println("\nUpdated Seating Chart:\n");
        //                 displaySeats();

        //                 Main.options();
        //             }
        //             break;
        //         }
        //     }
        //     if (seatFound) {
        //         break;
        //     }
        
        // if (!seatFound) {
        //     System.out.println("\nSeat does not exist.");
        // }
        // }
        if (!isValidSeat(selectedSeat)) {
        System.out.println("Invalid seat.");
        return;
    }
        if (bookedSeats.contains(selectedSeat)) {
            System.out.println("\nSorry, that seat is already taken.");
        } else {
            // Add the seat to the bookedSeats list
            bookedSeats.add(selectedSeat);
            Main.clearConsole();
            System.out.println("\nYou have successfully booked seat " + selectedSeat + "!");
            System.out.println("\nUpdated Seating Chart:\n");
            displaySeats();
            Main.options();
        }

	}

    private boolean isValidSeat(String selectedSeat) {
        if (selectedSeat.length() != 2) {
            return false;
        }

        char column = selectedSeat.charAt(0);
        char row = selectedSeat.charAt(1);

        return (column >= 'A' && column <= 'F') && (row >= '1' && row <= '6');
    }

    public void cancelSeat(String selectedSeat) {

        // boolean seatFound = false;

        // for (int i = 0; i < 6; i++) {
        //     for (int j = 0; j < 6; j++) {
        //         if (seatingChart[i][j].equals(selectedSeat)) {
        //             seatFound = true;
        //             if (!isSeatTaken[i][j]) {
        //                 System.out.println("\nSorry, that seat is not currently booked.");
        //             } else {
        //                 System.out.println("\nYou have successfully cancelled your booking for seat " + selectedSeat + ".");
        //                 isSeatTaken[i][j] = false;

        //                 System.out.println("\nUpdated Seating Chart:\n");
        //                 displaySeats();

        //                 Main.options();
        //             }
        //             break;
        //         }
        //     }
        //     if (seatFound) {
        //         break;
        //     }
        // }
        if (!bookedSeats.contains(selectedSeat)) {
            System.out.println("\nSorry, that seat is not currently booked.");
        } else {
            // Remove the seat from the bookedSeats list
            bookedSeats.remove(selectedSeat);
            System.out.println("\nYou have successfully cancelled your booking for seat " + selectedSeat + ".");
            System.out.println("\nUpdated Seating Chart:\n");
            displaySeats();
            Main.options();
        }
    }

    public void displaySeats() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                // if (isSeatTaken[i][j]) {
                //     System.out.print("XX ");
                // } else {
                //     System.out.print(seatingChart[i][j] + " ");
                // }
                String seat = seatingChart[i][j];

                if (bookedSeats.contains(seat)) {
                    System.out.print("XX ");
                } else {
                    System.out.print(seat + " ");
                }
            }

            System.out.println();
        }
    }
	
}
