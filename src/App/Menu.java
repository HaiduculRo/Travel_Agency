package App;

import App.Service.Service_CityTax;
import App.Service.Service_Flights;
import App.Service.Service_Hotel;
import App.Service.Service_Vacation;

import java.util.Scanner;

public final class Menu {
    private static Menu instance;
    Service_CityTax serviceCityTax = Service_CityTax.getInstance();
    Service_Flights serviceFlights = Service_Flights.getInstance();
    Service_Hotel serviceHotel = Service_Hotel.getInstance();
    Service_Vacation serviceVacation = Service_Vacation.getInstance();
    Reader objReader = Reader.getInstance();
    private Menu() {}

    // static block initialization for exception handling
    // singleton
    static {
        try {
            instance = new Menu();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Menu singleton instance");
        }
    }
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }
    private void textMenu(){
        System.out.println("--------------------------------------");
        System.out.println("Travel Agency");
        System.out.println("--------------------------------------");
        System.out.println("1: Add Flight");
        System.out.println("2: List all Flights by price");
        System.out.println("3: Add a City Taxes");
        System.out.println("4: Show all Cities Taxes");
        System.out.println("5: Edit City Taxes");
        System.out.println("6: Add a Hotel");
        System.out.println("7: Show All Hotels");
        System.out.println("8: Find Hotel in a City");
        System.out.println("9: Create a room in a Hotel");
        System.out.println("10: Add a Vacations");
        System.out.println("11: SHOW all Vacation");
        System.out.println("12: LAST MINUTE OFFERT");

    }

    public void runMenu() {
        int op;
        Scanner scanner = new Scanner(System.in);
        serviceCityTax.addData();
        serviceFlights.addData();
        serviceHotel.addData();
        do {
            textMenu();
            System.out.print("Please insert your option: ");
            op = scanner.nextInt();
            System.out.println("-----------------------------------------------");
            switch (op){
                case 1 -> serviceFlights.addFlight();
                case 2 -> serviceFlights.listPlanes();
                case 3 -> serviceCityTax.addCityTax();
                case 4 -> serviceCityTax.listCityTax();
                case 5 -> serviceCityTax.editCityTask();
                case 6 -> serviceHotel.addHotel();
                case 7 -> serviceHotel.listHotels();
                case 8 -> serviceHotel.listHotelsByCity();
                case 9 -> serviceHotel.addRoomToHotel();
                case 10 -> serviceVacation.createVacation();
                case 11 -> serviceVacation.listVacation();
                case 12 -> serviceVacation.lastMinute();
                default -> {
                }

            }
        }
        while (op != 0);
    }
}
