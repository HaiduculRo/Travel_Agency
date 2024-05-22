package App.Service;

import App.Reader;
import Models.CityTax.CityTax;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;
import Repository.CityTaxRepositoryService;


public class Service_CityTax {
    static List<CityTax> citytaxList = new ArrayList<>();
    private final Reader reader = Reader.getInstance();

//    CityTaxRepositoryService cityTaxService = CityTaxRepositoryService.getInstance();


    private Service_CityTax() {
        try {
            CityTaxRepositoryService cityTaxService = CityTaxRepositoryService.getInstance();
            citytaxList = cityTaxService.getAllCityTaxes();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to initialize citytaxList from database.");
        }
    }

    private static Service_CityTax instance;

    static {
        try {
            instance = new Service_CityTax();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating Service singleton instance");
        }
    }

    public static Service_CityTax getInstance() {
        if (instance == null) {
            instance = new Service_CityTax();
        }
        return instance;
    }

    public static void listCityTax() {
        int i = 0;
        System.out.println("__CITY TAXES__ \n");
        for (CityTax cityTax : citytaxList) {
            i++;
            System.out.println("NR." + i);
            System.out.println(cityTax);
        }
    }

    public void addCityTax() {
        try {
            CityTax new_obj = reader.readCityTax();


            citytaxList.add(new_obj);

            CityTaxRepositoryService cityTaxService = CityTaxRepositoryService.getInstance();
            cityTaxService.insertCityTax(new_obj);

            System.out.println("CityTax added successfully to list and database.");
            AuditService.writeAudit("add_city_tax");
        } catch (SQLException e) {
            System.out.println("Failed to add CityTax to database.");
        }
    }
    public void deleteCityTax() {
        Scanner read = new Scanner(System.in);
        listCityTax();
        System.out.println("_______________________________________");
        System.out.println("Alege ce vrei sa stergi");
        int select_option = read.nextInt();
        read.nextLine();  // Consumă linia nouă rămasă

        if (select_option <= 0 || select_option > citytaxList.size()) {
            System.out.println("Invalid selection!");
            return;
        }

        CityTax cityTaxToDelete = citytaxList.get(select_option - 1);

        try {
            CityTaxRepositoryService cityTaxService = CityTaxRepositoryService.getInstance();
            cityTaxService.deleteCityTax(cityTaxToDelete);
            citytaxList.remove(select_option - 1);
            System.out.println("CityTax deleted successfully from list and database.");
            AuditService.writeAudit("deleteCityTax");
        } catch (SQLException e) {
            System.out.println("Failed to delete CityTax from database.");
        }
    }

    public void editCityTask() {
        Scanner read = new Scanner(System.in);
        listCityTax();
        int select_city, select_option = -1;
        System.out.println("\nAlegeti taxa de oras pe care doriti sa o editati:");
        select_city = read.nextInt();
        CityTax cityTaxToEdit = citytaxList.get(select_city - 1);
        String old_city_name = cityTaxToEdit.getCity();
                System.out.println("\n --------------------- \n EDIT MENU \n ---------------------");
        while (select_option != 0) {
            System.out.println("1 -> Name");
            System.out.println("2 -> Country");
            System.out.println("3 -> Tax per night");
            System.out.println("0 -> EXIT");
            select_option = read.nextInt();
            read.nextLine();

            switch (select_option) {
                case 1:
                    System.out.print("Enter new city name: ");
                    String name = read.nextLine();
                    cityTaxToEdit.setCity(name);
                    break;

                case 2:
                    System.out.print("Enter new country name: ");
                    String country = read.nextLine();
                    cityTaxToEdit.setCountry(country);
                    break;

                case 3:
                    System.out.print("Enter new tax price per night: ");
                    int tax = read.nextInt();
                    read.nextLine(); // Consumă linia goală rămasă din buffer
                    cityTaxToEdit.setTax_price_per_night(tax);
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }

            try {
                CityTaxRepositoryService cityTaxService = CityTaxRepositoryService.getInstance();
                cityTaxService.updateCityTax(cityTaxToEdit, old_city_name);
                System.out.println("City tax updated successfully in the database.");
            } catch (SQLException e) {
                System.out.println("Failed to update city tax in the database.");
            }
        }
    }

    public static boolean verifyCity(String city) {
        for (CityTax city_tax : citytaxList) {
            if (city_tax.getCity().equals(city))
                return true;
        }
        return false;
    }
}
