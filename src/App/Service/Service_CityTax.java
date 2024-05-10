package App.Service;
import App.Reader;
import Models.CityTax.CityTax;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service_CityTax {
    static List<CityTax> citytaxList = new ArrayList<>();
    Reader reader = Reader.getInstance();

    private Service_CityTax() {
    }

    private static Service_CityTax instance;

    // static block initialization for exception handling
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
    public void addData(){
        citytaxList.add(new CityTax("Barcelona","Spain",2));
        citytaxList.add(new CityTax("Madrid","Spain",2));
    }

    public void listCityTax() {
        int i = 0;
        System.out.println("__CITY TAXES__ \n");
        for (CityTax cityTax : citytaxList) {
            i++;
            System.out.println("NR." + i);
            System.out.println(cityTax);

        }
    }
    public void addCityTax() {
        citytaxList.add(reader.readCityTax());
    }
    public void editCityTask(){
        Scanner read = new Scanner(System.in);
        listCityTax();
        int select_city,select_option  = -1;
        System.out.println("\nAlegeti taxa de oras pe care doriti sa o editati:");
        select_city = read.nextInt();
        System.out.println("\n --------------------- \n EDIT MENU \n ---------------------");
        while(select_option != 0) {
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
                    citytaxList.get(select_city).setCity(name);
                    break;

                case 2:
                    System.out.print("Enter new country name: ");
                    String country = read.nextLine();
                    citytaxList.get(select_city).setCountry(country);
                    break;

                case 3:
                    System.out.print("Enter new tax price per night: ");
                    int tax = read.nextInt();
                    read.nextLine(); // Consumă linia goală rămasă din buffer
                    citytaxList.get(select_city).setTax_price_per_night(tax);
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }

        }

    }
    public static boolean verifyCity(String city){
        for(CityTax city_tax: citytaxList){
            if (city_tax.getCity().equals(city))
                return true;
        }
        return false;
    }
}
