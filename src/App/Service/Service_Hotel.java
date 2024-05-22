package App.Service;

import App.Reader;
import Models.Hotel.Hotel;
import Models.Hotel.Resort;
import Models.Room.Room;
import Repository.HotelRepositoryService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service_Hotel {
    static  List<Hotel> hotelList = new ArrayList<>();
    Reader reader = Reader.getInstance();
    Service_CityTax serviceCityTax = Service_CityTax.getInstance();
    Service_Flights serviceFlights = Service_Flights.getInstance();

    private Service_Hotel() {
        try {
            HotelRepositoryService hotelRepositoryService = HotelRepositoryService.getInstance();
            hotelList = hotelRepositoryService.getHotels();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to initialize hotel List from database.");
        }
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

    public void addHotel() {
        Hotel new_hotel = reader.readHotel();

        // Check if new_hotel is null
        if (new_hotel == null) {
            System.out.println("Invalid hotel details. Please try again.");
            return; // Exit the method
        }

        if (Service_CityTax.verifyCity(new_hotel.getCity())) {
            hotelList.add(new_hotel); // adaug in reader
        }
        else {
            // Dacă nu există taxă pentru oraș, afișăm un mesaj și ștergem hotelul din baza de date (dacă a fost adăugat)
            System.out.println("Nu există încă taxă pentru acest oraș. Te rog să adaugi această taxă în CityTask.");

            // Ștergem hotelul din baza de date (dacă a fost adăugat)
            try {
                HotelRepositoryService hotelRepositoryService = HotelRepositoryService.getInstance();
                hotelRepositoryService.deleteHotel(new_hotel.getName(), new_hotel.getTara(), new_hotel.getCity());
                System.out.println("Hotel deleted from the database.");
            } catch (SQLException e) {
                System.out.println("Failed to delete hotel from the database.");
                e.printStackTrace();
            }
        }
    }
    public void listHotels() {
        System.out.println("__HOTEL LIST__\n");
        int i = 0;
        for (Hotel hotel : hotelList) {
            System.out.println("NR." + i+1);
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
                AuditService.writeAudit("add Apartment");
                break;
            case 2:
                hotelList.get(hotel_index).addRoom(reader.readDeluxRoomDetails());
                AuditService.writeAudit("add DeluxRoom");
                break;

            case 3:
                hotelList.get(hotel_index).addRoom(reader.readSuiteRoomDetails());
                AuditService.writeAudit("add SuiteRoom");
                break;
            case 4:
                hotelList.get(hotel_index).addRoom(reader.readDoubleRoomDetails());
                AuditService.writeAudit("add DoubleRoom");

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
        try {
            List<Hotel> hotels = HotelRepositoryService.getInstance().getHotelsByCity(cityName);
            for (Hotel hotel : hotels) {
                System.out.println(hotel);
                foundHotels = true;
            }
            if (!foundHotels) {
                System.out.println("No hotels available in " + cityName + ".");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching hotels from the database.");
            e.printStackTrace();
        }
    }

    public void deleteHotel() {
        Scanner read = new Scanner(System.in);
        listHotels();
        System.out.println("_______________________________________");
        System.out.println("Selecteaza hotelul pe care doresti sa-l stergi:");
        int select_option = read.nextInt();
        read.nextLine();

        if (select_option <= 0 || select_option > hotelList.size()) {
            System.out.println("Selectie invalida!");
            return;
        }

        Hotel hotelToDelete = hotelList.get(select_option - 1);

        try {
            HotelRepositoryService hotelService = HotelRepositoryService.getInstance();
            hotelService.deleteHotel(hotelToDelete.getName(), hotelToDelete.getTara(), hotelToDelete.getCity());
            hotelList.remove(select_option - 1);
            System.out.println("Hotelul a fost sters cu succes din lista si din baza de date.");
            AuditService.writeAudit("delete Hotel");
        } catch (SQLException e) {
            System.out.println("A esuat stergerea hotelului din baza de date.");
        }
    }

}
