package Models.Plane;
import java.time.LocalDateTime;

public class Plane {

    private double price;
    private LocalDateTime moment_of_flight;
    private LocalDateTime moment_of_arrival; // YYYY/MM/DD/hh/mm
    private String airport_city;
    private String airport_arrival;

    public Plane(double price, LocalDateTime moment_of_flight, LocalDateTime moment_of_arrival, String airport_city, String airport_arrival) {
        this.price = price;
        this.moment_of_flight = moment_of_flight;
        this.moment_of_arrival = moment_of_arrival;
        this.airport_city = airport_city;
        this.airport_arrival = airport_arrival;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "Price=" + price +
                ", Moment of Flight=" + moment_of_flight +
                ", Moment of Arrival=" + moment_of_arrival +
                ", Airport City='" + airport_city + '\'' +
                ", Airport Arrival='" + airport_arrival + '\'' +
                '}';
    }
    public double getPrice(){
        return price;
    }

    // Getter for moment_of_flight
    public LocalDateTime getMomentOfFlight() {
        return moment_of_flight;
    }

    // Getter for moment_of_arrival
    public LocalDateTime getMomentOfArrival() {
        return moment_of_arrival;
    }
}
