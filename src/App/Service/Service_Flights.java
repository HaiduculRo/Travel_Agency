package App.Service;

import App.Reader;
import Models.CityTax.CityTax;
import Models.Plane.Plane;
import Repository.FlightsRepositoryService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Service_Flights{
    static List<Plane> planeList = new ArrayList<>();
    Reader reader = Reader.getInstance();

    private Service_Flights() {
        try {
            FlightsRepositoryService flightsService = FlightsRepositoryService.getInstance();
            planeList = flightsService.getAllPlanes();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to initialize citytax List from database.");
        }
    }

    private static Service_Flights instance;

    // static block initialization for exception handling
    static {
        try {
            instance = new Service_Flights();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }
    public static Service_Flights getInstance() {
        if (instance == null) {
            instance = new Service_Flights();
        }
        return instance;
    }
    public void addFlight() {
        try {
            Plane newFlight = reader.readPlane();

            planeList.add(newFlight);
            FlightsRepositoryService flightsRepositoryService = FlightsRepositoryService.getInstance();
            flightsRepositoryService.insertPlane(newFlight);

            System.out.println("Flight added successfully to list and database.");
            AuditService.writeAudit("add Flight");
        } catch (SQLException e) {
            System.out.println("Failed to add flight to database.");
        }
    }

    public void listPlanes() {
        // Sort the planeList by price
        planeList.sort(Comparator.comparingDouble(Plane::getPrice));

        // Print the sorted list
        int i = 0;
        System.out.println("__FLIGHTS ORDERED BY PRICE__\n");
        for (Plane plane : planeList) {
            i++;
            System.out.println("NR." + i);
            System.out.println(plane);
        }
    }

    public void deleteFlight() {
        Scanner read = new Scanner(System.in);
        listPlanes();
        System.out.println("_______________________________________");
        System.out.println("Alege ce vrei sa stergi");
        int select_option = read.nextInt();

        try {
            Plane planeToDelete = planeList.get(select_option - 1);
            System.out.print(planeToDelete.getPrice());
            planeList.remove(select_option - 1);

            FlightsRepositoryService flightRepoService = FlightsRepositoryService.getInstance();
            flightRepoService.deleteFlight(planeToDelete);

            System.out.println("Flight deleted successfully from list and database.");
            AuditService.writeAudit("delete Flight");
        } catch (SQLException e) {
            System.out.println("Failed to delete flight from database.");
        }
    }

    public void editFlight() {
        Scanner read = new Scanner(System.in);
        listPlanes();
        int select_plane, select_option = -1;
        System.out.println("\nAlegeti un zbor pe care doriti sa o editati:");
        select_plane = read.nextInt();
        Plane plane_to_edit = planeList.get(select_plane - 1);
        double oldPrice = plane_to_edit.getPrice();
        LocalDateTime oldMomentOfFlight = plane_to_edit.getMomentOfFlight();
        LocalDateTime oldMomentOfArrival = plane_to_edit.getMomentOfArrival();
        String oldAirportCity = plane_to_edit.getAirportCity();
        String oldAirportArrival = plane_to_edit.getAirportArrival();

        System.out.println("\n---------------------\nEDIT MENU\n---------------------");
        while (select_option != 0) {
            System.out.println("1 -> Price");
            System.out.println("2 -> Moment of flight");
            System.out.println("3 -> Moment of arrival");
            System.out.println("0 -> EXIT");
            System.out.print("Select an option: ");
            select_option = read.nextInt();
            read.nextLine();

            switch (select_option) {
                case 1:
                    System.out.print("Enter new price: ");
                    double price = read.nextDouble();
                    read.nextLine();
                    plane_to_edit.setPrice(price);
                    break;

                case 2:
                    System.out.print("Enter new moment of flight (yyyy-MM-dd HH:mm:ss): ");
                    String momentOfFlightStr = read.nextLine();
                    LocalDateTime momentOfFlight = LocalDateTime.parse(momentOfFlightStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    plane_to_edit.setMomentOfFlight(momentOfFlight);
                    break;

                case 3:
                    System.out.print("Enter new moment of arrival (yyyy-MM-dd HH:mm:ss): ");
                    String momentOfArrivalStr = read.nextLine();
                    LocalDateTime momentOfArrival = LocalDateTime.parse(momentOfArrivalStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    plane_to_edit.setMomentOfArrival(momentOfArrival);
                    break;

                case 0:
                    System.out.println("Exiting edit menu.");
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }

            try {
                FlightsRepositoryService flightService = FlightsRepositoryService.getInstance();
                flightService.updatePlane(plane_to_edit, oldMomentOfFlight, oldMomentOfArrival, oldAirportCity, oldAirportArrival);
                System.out.println("Flight updated successfully in the database.");
                AuditService.writeAudit("edit Flight");
            } catch (SQLException e) {
                System.out.println("Failed to update flight in the database: " + e.getMessage());
            }
        }
    }





}
