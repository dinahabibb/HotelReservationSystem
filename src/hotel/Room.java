package hotel;
import java.text.NumberFormat;
import java.util.Locale;
//Class for room details
public class Room {
    //Variables
    int roomNumber;
    String roomType;
    double roomPrice;
    boolean isBooked;
    //Class constructor
    public Room(int number, String type, double price){
        this.roomNumber = number;
        this.roomType = type;
        this.roomPrice = price;
        isBooked = false;
    }
    //Function to display room
    public void displayRoom(int number, String type, double price){
        System.out.println("Room Number: " + number);
        System.out.println("Room Type: " + type);
        this.formatCurrency(price);
    }
    //Method to format price with currency type
    public void formatCurrency(double price){
    NumberFormat priceCurrency = NumberFormat.getCurrencyInstance(Locale.US);
    System.out.println("Room Price: " + priceCurrency.format(price));
}
}
