package hotel;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        //Greetings
        System.out.println("Hotel Reservation: " + "\n");

        //Scanner to read input
        Scanner scanner = new Scanner(System.in);
        char choice;

        //Small hotel database :)
        Room[] rooms = new Room[5];
        rooms[0] = new Room(1, "Standard", 50);
        rooms[1] = new Room(2, "Superior", 100);
        rooms[2] = new Room(3, "Deluxe", 125);
        rooms[3] = new Room(4, "Executive", 150);
        rooms[4] = new Room(5, "Suite", 200);

        //Menu
        while(true) {
            System.out.printf("A. View available rooms. \n" +
                    "B. Book room. \n" +
                    "C. Cancel booking. \n" +
                    "D. Exit. \n");
            System.out.print("Your choice: ");
            choice = scanner.next().charAt(0);
            choice = Character.toUpperCase(choice);

            if (choice == 'A'){
                //Display available rooms.
                System.out.printf("---Hotel Rooms---" + "\n");
                for(Room room : rooms){
                    if (room.isBooked == false){
                        room.displayRoom(room.roomNumber, room.roomType, room.roomPrice);
                        System.out.println("-----" + "\n");
                    }
                }
            }
            else if (choice == 'B') {
                //Book room
                int roomNumber;
                while (true) {
                    System.out.print("Choose Room Number: ");
                    //Input validation
                    if (scanner.hasNextInt()) {
                        Scanner scanner1 = new Scanner(System.in);
                        roomNumber = scanner.nextInt();

                        //Check if input is within rooms array range.
                        if (roomNumber < 1 || roomNumber > 5) {
                            System.out.println("There is no such room!");
                            continue;
                        }
                        if (rooms[roomNumber - 1].isBooked) {
                            System.out.println("Room Unavailable, Please select another room.");
                        } else {
                            //Get reservation details
                            String guestName;
                            while(true){
                                System.out.print("Booking guest name: ");
                                guestName = scanner1.nextLine().trim();
                                if (guestName.isEmpty()) {
                                    System.out.println("Name can not be empty!");
                                }
                                else if( !guestName.matches("[a-zA-Z ]+")){
                                    System.out.println("Name can contain letters only!");
                                }
                                else {
                                    break;
                                }
                            }

                            int capacity = validateIntInput(scanner1, "Enter the number of people for this booking: ");
                            int nights = validateIntInput(scanner1, "Enter the number of nights for this booking: ");

                            Reservation reservation = new Reservation(guestName, capacity, nights);

                            System.out.println("---Reservation Details---");
                            reservation.display(guestName, capacity, nights);
                            double price = rooms[roomNumber - 1].roomPrice;
                            reservation.countPrice(nights, price);

                            System.out.println("--------------");
                            System.out.println("| Booking confirmed! |");
                            System.out.println(("---------------"));

                            //Updating room availability
                            rooms[roomNumber - 1].isBooked = true;
                            break;
                        }
                    }
                    else{
                        System.out.println("Invalid input! Please enter a valid room number.");
                        scanner.next();
                    }
                }
            }
            else if (choice == 'C') {
                //Cancel booking
                int temp = 0;
                for(int i = 0; i < 5; i++){
                    if (rooms[i].isBooked == false){
                        temp++;
                    }
                }
                if (temp == 5){
                    System.out.println("No bookings made previously!");
                    continue;
                }
                int roomNumber;
                while (true) {
                    System.out.print("Choose Room Number: ");

                    if (scanner.hasNextInt()) {
                        roomNumber = scanner.nextInt();
                        //Check if input is within rooms array range.
                        if (roomNumber < 1 || roomNumber > 5) {
                            System.out.println("There is no such room!");
                            continue;
                        }

                        if (rooms[roomNumber - 1].isBooked) {
                            System.out.println("Booking cancelled.");
                            rooms[roomNumber - 1].isBooked = false;
                            break;
                        }
                        else {
                            System.out.println("No booking for this room");

                        }
                    }
                    else {
                        System.out.println("Invalid input! Please enter a valid room number.");
                        scanner.next(); //Clear previous input
                    }

                }
            }

            else if (choice == 'D')
                //Exit menu
                break;

            else
                //Raise an exception
                System.out.println("\n" + "Invalid choice! Please select one of the following options: A, B, C, or D." + "\n");
            continue;
        }
    }
    //Function to validate integer input
    public static int validateIntInput(Scanner scanner, String outputMessage){
        int variable;
        while(true){
            System.out.print(outputMessage);
            if(scanner.hasNextInt()){
                variable = scanner.nextInt();

                if (variable > 0){
                    return variable;
                }
                else{
                    System.out.println("Input must be grater than 0!");
                }
            }
            else{
                System.out.println("Invalid input! Enter a number.");
                scanner.next();
            }
        }
    }
}