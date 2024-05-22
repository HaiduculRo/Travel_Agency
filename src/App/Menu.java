package App;

import App.Service.*;

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
        System.out.println("13: Sterge ZBOR");
        System.out.println("14: Sterge Taxa ");
        System.out.println("15: Sterge Unitate de Cazare ");
        System.out.println("16: EDIT ZBOR ");
        System.out.println("0 : EXIT ");
    }

    public void runMenu() {
        int op;
        Scanner scanner = new Scanner(System.in);
        do {
            textMenu();
            System.out.print("Please insert your option: ");
            op = scanner.nextInt();
            System.out.println("-----------------------------------------------");
            switch (op){
                case 1 -> serviceFlights.addFlight();
                case 2 -> {     serviceFlights.listPlanes();  AuditService.writeAudit("list_Planes");}
                case 3 -> serviceCityTax.addCityTax();
                case 4 -> {     serviceCityTax.listCityTax(); AuditService.writeAudit("list_CityTax");}
                case 5 -> serviceCityTax.editCityTask();
                case 6 -> serviceHotel.addHotel();
                case 7 -> {     serviceHotel.listHotels(); AuditService.writeAudit("list Hotels");}
                case 8 -> {     serviceHotel.listHotelsByCity(); AuditService.writeAudit("list Hotels by City");}
                case 9 -> serviceHotel.addRoomToHotel();
                case 10 -> serviceVacation.createVacation();
                case 11 -> {    serviceVacation.listVacation(); AuditService.writeAudit("listVacation");}
                case 12 -> {serviceVacation.lastMinute(); AuditService.writeAudit("list last minute"); }
                case 13 -> serviceFlights.deleteFlight();
                case 14 -> serviceCityTax.deleteCityTax();
                case 15 -> serviceHotel.deleteHotel();
                case 16 -> serviceFlights.editFlight();
                case 0 -> {
                    System.out.println("Va mai asteptam !");
                    return;
                }
                default -> {
                    System.out.println("Optiune Invalida !!!");
                }
            }
        }
        while (op != 0);
    }
}
