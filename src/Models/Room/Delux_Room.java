package Models.Room;

public final class Delux_Room extends Room {
    private int jacuzzi_size;
    private boolean localTransportIncluded;

    public Delux_Room(int persons, String view, int size_m2, double price, int jacuzzi_size, boolean localTransportIncluded) {
        super(persons, view, size_m2, price);
        this.localTransportIncluded = localTransportIncluded;
        this.jacuzzi_size = jacuzzi_size;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", jacuzzi_size=" + jacuzzi_size +
                ", localTransportIncluded=" + localTransportIncluded +
                '}';
    }
}
