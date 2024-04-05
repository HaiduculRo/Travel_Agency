package App.Service;

import App.Reader;
import Models.CityTax.CityTax;
import Models.Vacation.Vacation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service_Vacation {
    List<Vacation> vacationList = new ArrayList<>();
    Reader reader = Reader.getInstance();

    Service_CityTax serviceCityTax = Service_CityTax.getInstance();
    Service_Flights serviceFlights = Service_Flights.getInstance();
    Service_Hotel serviceHotel = Service_Hotel.getInstance();

    private Service_Vacation() {
    }

    private static Service_Vacation instance;

    // static block initialization for exception handling
    static {
        try {
            instance = new Service_Vacation();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }

    public static Service_Vacation getInstance() {
        if (instance == null) {
            instance = new Service_Vacation();
        }
        return instance;
    }

    public void listVacation() {
        // Print the sorted list
        int i = 0;
        System.out.println("__ALL VACATIONS__\n");
        for (Vacation vacation : vacationList) {
            System.out.println("NR." + i);
            System.out.println(vacation);
            i++;
        }
    }
    public void createVacation() {
        Scanner read = new Scanner(System.in);
        int start_flight, end_flight, hotel_index, room_index;
        double  city_tax_per_night = 0;
        System.out.println("-------------------------------");
        System.out.println("CREATE YOUR PERFECT VACATION:");
        System.out.println("--------------------------------");
        System.out.println();
        serviceFlights.listPlanes();
        // zboruri
        System.out.println("CHOOSE YOUR START FLIGHT FROM THIS LIST");
        start_flight = read.nextInt();
        while (start_flight < 0 || start_flight >= serviceFlights.planeList.size()) {
            System.out.println("THIS FLIGHT DOESN'T EXIST");
            start_flight = read.nextInt();
        }

        System.out.println("CHOOSE YOUR FINAL FLIGHT FROM THIS LIST");
        end_flight = read.nextInt();
        while (end_flight < 0 || end_flight >= serviceFlights.planeList.size()) {
            System.out.println("THIS FLIGHT DOESN'T EXIST");
            end_flight = read.nextInt();
        }

        while (serviceFlights.planeList.get(end_flight).getMomentOfFlight().minusDays(1).isBefore(serviceFlights.planeList.get(start_flight).getMomentOfArrival())) {
            System.out.println("START FLIGHT MUST BE AFTER END FLIGHT BY AT LEAST ONE DAY");
            System.out.println("CHOOSE YOUR END FLIGHT AGAIN FROM THIS LIST");
            end_flight = read.nextInt();
        }

        //hotel
        System.out.println("CHOOSE YOUR HOTEL FROM THIS LIST");
        serviceHotel.listHotels();
        hotel_index = read.nextInt();
        while (hotel_index < 0 || hotel_index >= serviceHotel.hotelList.size()) {
            System.out.println("THIS Hotel dosen't exist");
            hotel_index = read.nextInt();
        }

        // room
        System.out.println("CHOOSE YOUR ROOM FROM THIS LIST");
        serviceHotel.listRooms(serviceHotel.hotelList.get(hotel_index));
        room_index = read.nextInt();
        while (room_index < 0 || room_index >= serviceHotel.hotelList.size()) {
            System.out.println("THIS ROOM number dosen't exist");
            room_index = read.nextInt();
        }
        Vacation vac = new Vacation(serviceFlights.planeList.get(start_flight), serviceFlights.planeList.get(end_flight),room_index, serviceHotel.hotelList.get(hotel_index));
        String hotel_city = serviceHotel.hotelList.get(hotel_index).getCity();

        for(CityTax city_tax: serviceCityTax.citytaxList){
            if (city_tax.getCity().equals(hotel_city))
                city_tax_per_night = city_tax.getTax_price_per_night();
        }
        System.out.println(city_tax_per_night);
        vac.addCityTaxToPrice(city_tax_per_night);
        vacationList.add(vac);
    }
}
