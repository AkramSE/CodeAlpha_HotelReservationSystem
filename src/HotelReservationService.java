import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class HotelReservationService {
    private final List<Room> rooms;
    private final Map<String, Reservation> reservations;

    public HotelReservationService() {
        this.rooms = new ArrayList<>();
        this.reservations = new HashMap<>();
        initializeRooms();
    }

    // Default Hotel Inventory Data setup
    private void initializeRooms() {
        rooms.add(new Room("101", Room.Category.STANDARD, 100.0));
        rooms.add(new Room("102", Room.Category.STANDARD, 100.0));
        rooms.add(new Room("201", Room.Category.DELUXE, 200.0));
        rooms.add(new Room("202", Room.Category.DELUXE, 200.0));
        rooms.add(new Room("301", Room.Category.SUITE, 500.0));
    }

    public List<Room> getAvailableRooms() {
        return rooms.stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Room> getAvailableRoomsByCategory(Room.Category category) {
        return rooms.stream()
                .filter(room -> room.isAvailable() && room.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Reservation bookRoom(String guestName, Room.Category category, int nights) {
        List<Room> availableRooms = getAvailableRoomsByCategory(category);

        if (availableRooms.isEmpty()) {
            return null; // Category mein koi room khali nahi hai
        }

        // Pahla available room allot kar do
        Room roomToBook = availableRooms.get(0);
        roomToBook.setAvailable(false); // Room status change to Booked

        double totalAmount = roomToBook.getPricePerNight() * nights;

        // Custom 8-character unique alphanumeric booking ID generation
        String bookingId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Reservation newReservation = new Reservation(bookingId, guestName, roomToBook, nights, totalAmount);
        reservations.put(bookingId, newReservation); // Memory database (HashMap) mein save karein

        System.out.println("\n[SYSTEM] Processing secure payment of $" + totalAmount + "...");
        System.out.println("[SYSTEM] Payment Successful!");

        return newReservation;
    }

    public boolean cancelReservation(String bookingId) {
        if (reservations.containsKey(bookingId)) {
            Reservation res = reservations.get(bookingId);
            res.getRoom().setAvailable(true); // Room ko wapis khali karein
            reservations.remove(bookingId); // Database se booking urayein
            return true;
        }
        return false;
    }

    public Reservation getReservation(String bookingId) {
        return reservations.get(bookingId);
    }

    public Map<String, Reservation> getAllReservations() {
        return reservations;
    }
}
