public class Room {
    public enum Category {
        STANDARD, DELUXE, SUITE
    }

    private final String roomNumber;
    private final Category category;
    private final double pricePerNight;
    private boolean isAvailable;

    public Room(String roomNumber, Category category, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true; // By default room khali hota hai
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Category getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format("Room: %-5s | Type: %-10s | Price: $%.2f/night | Available: %s",
                roomNumber, category, pricePerNight, (isAvailable ? "Yes" : "No"));
    }
}
