package Models.Room;

public final class Double_Room extends Room {
    private final String bedType;

    public Double_Room(String view, int size_m2, double price, String bedType) {
        super(2, view, size_m2, price);
        this.bedType = bedType;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", bedType='" + bedType + '\'' +
                '}';
    }
}
