package App;
import Models.Plane.Plane;
import Models.Room.*;
import Models.Hotel.*;
import Models.CityTax.*;
import Models.Vacation.Vacation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.*;

public final class Reader {
    private static Reader instance;
    private Reader() {}
    static {
        try {
            instance = new Reader();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }

    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }
    private void typeOfHotel(String op) {
        System.out.println("What type of hotel do you want to " + op + "?");
        System.out.println("1. Normal Hotel");
        System.out.println("2. Resort");
    }

    public Hotel readHotel() {
        Scanner read = new Scanner(System.in);
        typeOfHotel("add");

        try {
            int operation = read.nextInt();
            read.nextLine(); // Consumă newline

            switch (operation) {
                case 1:
                    return readNormalHotelDetails();
                case 2:
                    return readResortDetails();
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }

        // Return null if an invalid option is selected
        return null;
    }


    private Hotel readNormalHotelDetails() {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter details for Normal Hotel:");
        System.out.print("Name: ");
        String name = read.nextLine();
        System.out.print("Number of stars: ");
        int stars = read.nextInt();
        read.nextLine(); // Consumă newline
        System.out.print("Country: ");
        String country = read.nextLine();
        System.out.print("City: ");
        String city = read.nextLine();
        System.out.print("all_inclusive or breakfast or half_board");
        System.out.print("Type of meal: ");
        String mealType = read.nextLine();

        return new Hotel(name,stars, country, city, null, mealType);
    }


    private Hotel readResortDetails() {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter details for Resort:");
        System.out.println("Number of pools: ");
        String name = read.nextLine();
        System.out.print("Number of stars: ");
        int stars = read.nextInt();
        read.nextLine(); // Consumă newline
        System.out.print("Country: ");
        String country = read.nextLine();
        System.out.print("City: ");
        String city = read.nextLine();
        System.out.print("Type of meal: all inclusive \n");
        System.out.print("Number of pools: ");
        int numberOfPools = read.nextInt();

        return new Resort(name, stars, country, city,  null, numberOfPools);
    }
    public Plane readPlane() {
        Scanner read = new Scanner(System.in);
        Plane plane = null;
        System.out.println("Enter details for the flight:");
        try {
            System.out.print("Departure City: ");
            String departure = read.nextLine();
            System.out.print("Arrival City: ");
            String arrival = read.nextLine();

            System.out.print("Flight departure time (YYYY-MM-DD HH:mm): ");
            String departureTimeString = read.nextLine();
            LocalDateTime departureTime = LocalDateTime.parse(departureTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Flight arrival time (YYYY-MM-DD HH:mm): ");
            String arrivalTimeString = read.nextLine();
            LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            // Check if departure time is before arrival time
            if (departureTime.isAfter(arrivalTime)) {
                System.out.println("Error: Departure date must be earlier than arrival date.");
                return null;
            }

            System.out.print("Price: ");
            double price = read.nextDouble();
            plane = new Plane(price, departureTime, arrivalTime, departure, arrival);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
        return plane;
    }
    public CityTax readCityTax() {
        Scanner read = new Scanner(System.in);
        CityTax cityTax = null;
        System.out.println("Enter details for the city tax:");
        try {
            System.out.print("City: ");
            String city = read.nextLine();
            System.out.print("Country: ");
            String country = read.nextLine();
            System.out.print("Tax price per night: ");
            double taxPricePerNight = read.nextDouble();
            read.nextLine(); // Consumă newline

            cityTax = new CityTax(city, country, taxPricePerNight);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
        return cityTax;
    }
    public void typeOfRoom(String op) {
        System.out.println("What type of hotel do you want to " + op + "?");
        System.out.println("1. Apartment");
        System.out.println("2. Delux Room");
        System.out.println("3. Suite Room");
        System.out.println("4. Double Room");
        System.out.println("5. EXIT");
    }
    public Suite_Room readSuiteRoomDetails() {
        Scanner read = new Scanner(System.in);
        System.out.print("Number of persons_capacity: ");
        int persons_capacity = read.nextInt();

        System.out.print("Describle your view: ");
        String view = read.nextLine();

        System.out.print("Room size in m^2: ");
        int room_size = read.nextInt();

        System.out.print("PRICE PER NIGHT: ");
        double price = read.nextDouble();

        return new Suite_Room(persons_capacity,view,room_size,price);
    }
    public Double_Room readDoubleRoomDetails() {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter details for Double Room:");

        System.out.print("Describle your view: ");
        String view = read.nextLine();

        System.out.print("Room size in m^2: ");
        int room_size = read.nextInt();
        read.nextLine();

        System.out.print("BED TYPE(matriminial / separat): ");
        String bed = read.nextLine();

        System.out.print("PRICE PER NIGHT: ");
        double price = read.nextDouble();

        return new Double_Room(view,room_size,price,bed);
    }
    public Delux_Room readDeluxRoomDetails() {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter details for Deluxe Room:");
        System.out.print("Number of persons_capacity: ");
        int persons_capacity = read.nextInt();
        read.nextLine(); // Consumă newline
        System.out.print("Describe your view: ");
        String view = read.nextLine();
        System.out.print("Room size in m^2: ");
        int size_m2 = read.nextInt();
        System.out.print("Price per night: ");
        double price = read.nextDouble();
        System.out.print("Jacuzzi size: ");
        int jacuzzi_size = read.nextInt();
        System.out.print("Is local transport included? (true/false): ");
        boolean localTransportIncluded = read.nextBoolean();
        return new Delux_Room(persons_capacity,view, size_m2, price, jacuzzi_size, localTransportIncluded);
    }
    public static Apartment readApartmentDetails() {
        Scanner read = new Scanner(System.in);
        System.out.print("Number of persons_capacity: ");
        int persons_capacity = read.nextInt();
        read.nextLine(); // Consume newline
        System.out.print("Describle your view: ");
        String view = read.nextLine();
        System.out.print("Room size in m^2: ");
        int room_size = read.nextInt();
        read.nextLine(); // Consume newline
        System.out.print("PRICE PER NIGHT: ");
        double price = read.nextDouble();
        read.nextLine(); // Consume newline

        Set<String> kitchenFacilities = readKitchenFacilities(read);

        return new Apartment(persons_capacity, view, room_size, price, kitchenFacilities);
    }
    private static Set<String> readKitchenFacilities(Scanner read) {
        Set<String> kitchenFacilities = new HashSet<>();

        System.out.print("Enter kitchen facilities (comma-separated): ");
        String facilitiesInput = read.nextLine();
        String[] facilitiesArray = facilitiesInput.split(",");
        for (String facility : facilitiesArray) {
            kitchenFacilities.add(facility.trim());
        }

        return kitchenFacilities;
    }
}

