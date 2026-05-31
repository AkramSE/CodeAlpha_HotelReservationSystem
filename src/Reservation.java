import java.time.LocalDate;

public class Reservation {
    private final String bookingId;
    private final String guestName;
    private final Room room;
    private final int nights;
    private final double totalAmount;
    private final LocalDate bookingDate;

    public Reservation(String bookingId, String guestName, Room room, int nights, double totalAmount) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.room = room;
        this.nights = nights;
        this.totalAmount = totalAmount;
        this.bookingDate = LocalDate.now();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void displayReservationDetails() {
        System.out.println("\n================ BOOKING RECEIPT ================");
        System.out.println("Booking ID    : " + bookingId);
        System.out.println("Guest Name    : " + guestName);
        System.out.println("Date Booked   : " + bookingDate);
        System.out.println("-------------------------------------------------");
        System.out.println("Room Number   : " + room.getRoomNumber() + " (" + room.getCategory() + ")");
        System.out.println("Duration      : " + nights + " Night(s)");
        System.out.println("Price/Night   : $" + room.getPricePerNight());
        System.out.println("-------------------------------------------------");
        System.out.printf("TOTAL PAID    : $%.2f\n", totalAmount);
        System.out.println("=================================================");
    }
}