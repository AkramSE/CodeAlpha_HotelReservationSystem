import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HotelReservationApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelReservationService hotelService = new HotelReservationService();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("==================================================");
        System.out.println("     ENTERPRISE HOTEL RESERVATION SYSTEM");
        System.out.println("==================================================");

        while (running) {
            printMenu();
            int choice = getIntInput("Enter your choice (1-6): ");

            switch (choice) {
                case 1:
                    displayAvailableRooms();
                    break;
                case 2:
                    handleBooking();
                    break;
                case 3:
                    viewBookingDetails();
                    break;
                case 4:
                    displayBookingDashboard();
                    break;
                case 5:
                    handleCancellation();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using our Reservation System. Goodbye!");
                    break;
                default:
                    System.out.println("Error: Invalid choice. Please select from 1 to 6.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Search Available Rooms");
        System.out.println("2. Make a Reservation");
        System.out.println("3. View Specific Booking Details");
        System.out.println("4. View All Bookings (Admin Dashboard)");
        System.out.println("5. Cancel a Reservation");
        System.out.println("6. Exit");
    }

    private static void displayAvailableRooms() {
        System.out.println("\n============= AVAILABLE ROOMS =============");
        List<Room> availableRooms = hotelService.getAvailableRooms();

        if (availableRooms.isEmpty()) {
            System.out.println("Sorry, the hotel is fully booked!");
        } else {
            for (Room room : availableRooms) {
                System.out.println(room.toString());
            }
        }
        System.out.println("===========================================");
    }

    private static void handleBooking() {
        // Enforcing strict alphabetical validation for guest names
        String guestName = getValidNameInput("Enter Guest Name: ");

        System.out.println("\n--- Room Categories ---");
        System.out.println("1. STANDARD ($100/night)");
        System.out.println("2. DELUXE   ($200/night)");
        System.out.println("3. SUITE    ($500/night)");
        int categoryChoice = getIntInput("Select Category (1-3): ");

        Room.Category category;
        switch (categoryChoice) {
            case 1: category = Room.Category.STANDARD; break;
            case 2: category = Room.Category.DELUXE; break;
            case 3: category = Room.Category.SUITE; break;
            default:
                System.out.println("Error: Invalid category selection.");
                return;
        }

        int nights = getIntInput("Enter number of nights to stay: ");
        if (nights <= 0) {
            System.out.println("Error: Nights must be at least 1.");
            return;
        }

        Reservation reservation = hotelService.bookRoom(guestName, category, nights);

        if (reservation != null) {
            System.out.println("\nSuccess: Room booked successfully!");
            reservation.displayReservationDetails();
            System.out.println(" PLEASE SAVE YOUR BOOKING ID FOR FUTURE REFERENCE.");
        } else {
            System.out.println("\nFailed: Sorry, no rooms available in the " + category + " category.");
        }
    }

    private static void viewBookingDetails() {
        String bookingId = getValidStringInput("Enter your Booking ID: ").toUpperCase();
        Reservation reservation = hotelService.getReservation(bookingId);

        if (reservation != null) {
            reservation.displayReservationDetails();
        } else {
            System.out.println("Error: No booking found with ID " + bookingId);
        }
    }

    private static void displayBookingDashboard() {
        Map<String, Reservation> allReservations = hotelService.getAllReservations();

        System.out.println("\n=================== ADMIN BOOKING DASHBOARD ===================");
        System.out.println("Total Active Bookings: " + allReservations.size());
        System.out.println("---------------------------------------------------------------");
        System.out.println(String.format("%-12s | %-15s | %-10s | %s", "BOOKING ID", "GUEST NAME", "ROOM NO", "ROOM TYPE"));
        System.out.println("---------------------------------------------------------------");

        if (allReservations.isEmpty()) {
            System.out.println("No active reservations found in the system.");
        } else {
            double totalRevenue = 0;
            for (Reservation res : allReservations.values()) {
                System.out.println(String.format("%-12s | %-15s | %-10s | %s",
                        res.getBookingId(),
                        res.getGuestName(),
                        res.getRoom().getRoomNumber(),
                        res.getRoom().getCategory()));
                totalRevenue += res.getTotalAmount();
            }
            System.out.println("---------------------------------------------------------------");
            System.out.printf("TOTAL SYSTEM REVENUE GENERATED : $%.2f\n", totalRevenue);
        }
        System.out.println("===============================================================");
    }

    private static void handleCancellation() {
        String bookingId = getValidStringInput("Enter your Booking ID to cancel: ").toUpperCase();

        boolean isCancelled = hotelService.cancelReservation(bookingId);
        if (isCancelled) {
            System.out.println("Success: Booking ID " + bookingId + " has been cancelled successfully.");
        } else {
            System.out.println("Error: No active booking found with ID " + bookingId);
        }
    }

    //  Regex Validation: Strictly letters and single spaces allowed
    private static String getValidNameInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("^[a-zA-Z\\s]+$")) {
                return input;
            } else {
                System.out.println("Error: Invalid name! Numbers or symbols are not allowed. Please use alphabets only.");
            }
        }
    }

    private static String getValidStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: Input cannot be empty.");
            } else {
                return input;
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter a valid integer number.");
                scanner.nextLine(); // Clear scanner buffer
            }
        }
    }
}