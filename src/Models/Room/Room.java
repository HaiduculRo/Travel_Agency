package Models.Room;

public abstract sealed class Room implements Cloneable permits Suite_Room,Apartment,Delux_Room,Double_Room {
    protected int persons;
    protected String view;
    protected int size_m2;
    protected double price;

    public Room(int persons, String view, int size_m2, double price) {
        this.persons = persons;
        this.view = view;
        this.size_m2 = size_m2;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room {" +
                "persons: " + persons + '\'' +
                ", view: '" + view + '\'' +
                ", size_m2: " + size_m2 + '\'' +
                ", price: " + price +
                '}';
    }

    public int getPersons() {
        return persons;
    }

    public String getView() {
        return view;
    }

    public int getSize_m2() {
        return size_m2;
    }
}
