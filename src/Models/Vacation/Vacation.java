package Models.Vacation;
import Models.CityTax.CityTax;
import Models.Hotel.Hotel;
import Models.Plane.Plane;
import Models.Room.Room;
import java.time.LocalDateTime;

import static java.lang.Math.abs;
import static java.time.temporal.ChronoUnit.DAYS;

public final class Vacation {
    private Plane startPlane;
    private Plane endPlane;
    private int roomNr;
    private Hotel hotel;
    private final long days;

    private double price;

    // Constructor
    public Vacation(Plane startPlane, Plane endPlane, int roomNr, Hotel hotel) {
        this.startPlane = startPlane;
        this.endPlane = endPlane;
        this.roomNr = roomNr;
        this.hotel = hotel;
        this.days = calculateDaysDifference(endPlane.getMomentOfFlight(), startPlane.getMomentOfArrival());
        // pret = avion_dus + avion_intors + nr_nopti*pret_camera
        this.price = startPlane.getPrice()+endPlane.getPrice()+hotel.getCamere(roomNr).getPrice() * this.days;
    }

    public static long calculateDaysDifference(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        long daysBetween = abs(DAYS.between(dateTime1, dateTime2));
        System.out.println("Date 1   " + dateTime1);
        System.out.println("Date 2   " + dateTime2);
        System.out.println("Diferența în zile între cele două date este: " + daysBetween);
        return daysBetween;
    }

    public void addCityTaxToPrice(double tax_per_night){
        this.price += this.days*tax_per_night;
    }

    // Getter for startPlane
    public Plane getStartPlane() {
        return startPlane;
    }

    // Setter for startPlane
    public void setStartPlane(Plane startPlane) {
        this.startPlane = startPlane;
    }

    // Getter for endPlane
    public Plane getEndPlane() {
        return endPlane;
    }

    // Setter for endPlane
    public void setEndPlane(Plane endPlane) {
        this.endPlane = endPlane;
    }

    // Getter for roomNr
    public int getRoomNr() {
        return roomNr;
    }

    // Setter for roomNr
    public void setRoomNr(int roomNr) {
        this.roomNr = roomNr;
    }

    // Getter for hotel
    public Hotel getHotel() {
        return hotel;
    }

    // Setter for hotel
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    // Getter for days
    public long getDays() {
        return days;
    }

    @Override
    public String toString() {
        return "Vacation \n" +
                "startPlane=" + startPlane +
                "\n endPlane=" + endPlane +
                "\n hotel=" + hotel +
                "\n room=" + hotel.getAllRooms().get(roomNr) +
                " days=" + days + "      price=" + price;
    }


}
