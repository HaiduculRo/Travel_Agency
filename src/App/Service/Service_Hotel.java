package App.Service;

import App.Reader;
import Models.Hotel.Hotel;
import Models.Hotel.Resort;
import Models.Room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service_Hotel {
    List<Hotel> hotelList = new ArrayList<>();
    Reader reader = Reader.getInstance();
    Service_CityTax serviceCityTax = Service_CityTax.getInstance();
    Service_Flights serviceFlights = Service_Flights.getInstance();

    private Service_Hotel() {
    }

    private static Service_Hotel instance;

    // Static block initialization for exception handling
    static {
        try {
            instance = new Service_Hotel();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service_Hotels singleton instance");
        }
    }

    // Get the singleton instance of Service_Hotels
    public static Service_Hotel getInstance() {
        if (instance == null) {
            instance = new Service_Hotel();
        }
        return instance;
    }

    public void listRooms(Hotel hotel){
        // Print the sorted list
        int i = 0;
        System.out.println("ROOMS \n");
        for (Room room : hotel.getAllRooms()) {
            System.out.println("NR." + i);
            System.out.println(room);
            i++;
        }
    }

    public void addData(){
        hotelList.add(new Resort("Babilon",5,"Spain","Barcelona",null,2));
        hotelList.add(new Resort("Samba",5,"Spain","Madrid",null,20));
    }
    public void addHotel() {
        Hotel new_hotel = reader.readHotel();

        // Check if new_hotel is null
        if (new_hotel == null) {
            System.out.println("Invalid hotel details. Please try again.");
            return; // Exit the method
        }

        if (Service_CityTax.verifyCity(new_hotel.getCity())) {
            hotelList.add(new_hotel);
        } else {
            System.out.println("Nu exista inca taxa pentru acest oras. Te rog sa adaugi acest oras in CityTask");
        }
    }
    public void listHotels() {
        System.out.println("__HOTEL LIST__\n");
        int i = 0;
        for (Hotel hotel : hotelList) {
            System.out.println("NR." + i);
            if (hotel instanceof Resort) {
                Resort resort = (Resort) hotel;
                System.out.println(resort);
            } else {
                System.out.println(hotel);
            }
            i++;
        }
    }
    public void addRoomToHotel() {
        Scanner read = new Scanner(System.in);
        System.out.println("CHOOSE YOUR HOTEL FROM THIS LIST");
        listHotels();
        int hotel_index = read.nextInt();
        reader.typeOfRoom("add");
        int operation = read.nextInt();
        switch (operation) {
            case 1:
                hotelList.get(hotel_index).addRoom(reader.readApartmentDetails());
                break;
            case 2:
                hotelList.get(hotel_index).addRoom(reader.readDeluxRoomDetails());
                break;

            case 3:
                hotelList.get(hotel_index).addRoom(reader.readSuiteRoomDetails());
                break;
            case 4:
                hotelList.get(hotel_index).addRoom(reader.readDoubleRoomDetails());
                break;
        }
        System.out.println("ROOM WAS ADDED");
    }

    public void listHotelsByCity() {
        System.out.println("CITY NAME: ");
        Scanner read = new Scanner(System.in);
        String cityName = read.nextLine();
        boolean foundHotels = false;
        System.out.println("__HOTEL LIST FOR " + cityName.toUpperCase() + "__\n");
        for (Hotel hotel : hotelList) {
            if (hotel.getCity().equalsIgnoreCase(cityName)) {
                System.out.println(hotel);
                foundHotels = true;
            }
        }
        if (!foundHotels) {
            System.out.println("No hotels available in " + cityName + ".");
        }
    }

}
