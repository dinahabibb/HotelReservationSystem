package hotel;
import java.text.NumberFormat;
import java.util.Locale;

//Class for reservation details
public class Reservation {
    //Variables
    String GuestName;
    int Capacity;
    int Nights;
    //Class constructor
    public Reservation(String name, int capacity, int nights){
        this.GuestName = name;
        this.Capacity = capacity;
        this.Nights = nights;
    }
    //Method to display reservation details
    public void display(String name, int capacity, int nights){
        System.out.println("Name: " + name.trim());
        System.out.println("Capacity: " + capacity);
        System.out.println("Nights: " + nights);
    }
    //Method to count total price for the stay and print it formatted
    public void countPrice(int nights, double price){
        double totalPrice = nights * price;
        NumberFormat priceCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("Total Price: " + priceCurrency.format(totalPrice));
    }
}
