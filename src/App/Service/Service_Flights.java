package App.Service;

import App.Reader;
import Models.Plane.Plane;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Service_Flights{
    List<Plane> planeList = new ArrayList<>();
    Reader reader = Reader.getInstance();

    private Service_Flights() {
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

    public void addData(){
        planeList.add(new Plane(1000.0, LocalDateTime.of(2024, 4, 10, 8, 30), LocalDateTime.of(2024, 4, 10, 10, 0), "1", "2"));
        planeList.add(new Plane(1200.0, LocalDateTime.of(2024, 4, 11, 10, 0), LocalDateTime.of(2024, 4, 11, 12, 30), "1", "2"));
        planeList.add(new Plane(1500.0, LocalDateTime.of(2024, 4, 12, 12, 0), LocalDateTime.of(2024, 4, 12, 14, 30), "2", "1"));
        planeList.add(new Plane(1100.0, LocalDateTime.of(2024, 4, 17, 14, 0), LocalDateTime.of(2024, 4, 17, 17, 30), "2", "1"));
    }
    public void addFlight() {
        planeList.add(reader.readPlane());
    }

    public void listPlanes() {
        // Sort the planeList by price
        planeList.sort(Comparator.comparingDouble(Plane::getPrice));

        // Print the sorted list
        int i = 0;
        System.out.println("__FLIGHTS ORDERED BY PRICE__\n");
        for (Plane plane : planeList) {
            System.out.println("NR." + i);
            System.out.println(plane);
            i++;
        }
    }
}
