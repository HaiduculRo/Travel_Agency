package Models.Plane;

import java.time.LocalDateTime;

public class Plane {
    private double price;
    private LocalDateTime moment_of_flight;
    private LocalDateTime moment_of_arrival;
    private String airport_city;
    private String airport_arrival;

    // Constructor
    public Plane(double price, LocalDateTime moment_of_flight, LocalDateTime moment_of_arrival, String airport_city, String airport_arrival) {
        this.price = price;
        this.moment_of_flight = moment_of_flight;
        this.moment_of_arrival = moment_of_arrival;
        this.airport_city = airport_city;
        this.airport_arrival = airport_arrival;
    }

    // Getters
    public double getPrice() {
        return price;
    }

    public LocalDateTime getMomentOfFlight() {
        return moment_of_flight;
    }

    public LocalDateTime getMomentOfArrival() {
        return moment_of_arrival;
    }

    public String getAirportCity() {
        return airport_city;
    }

    public String getAirportArrival() {
        return airport_arrival;
    }

    // Setters
    public void setPrice(double price) {
        this.price = price;
    }

    public void setMomentOfFlight(LocalDateTime moment_of_flight) {
        this.moment_of_flight = moment_of_flight;
    }

    public void setMomentOfArrival(LocalDateTime moment_of_arrival) {
        this.moment_of_arrival = moment_of_arrival;
    }

    public void setAirportCity(String airport_city) {
        this.airport_city = airport_city;
    }

    public void setAirportArrival(String airport_arrival) {
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
}
