package Models.Room;
import java.util.Set;
import java.util.HashSet;

public final class Apartment extends Room {
    private final Set<String> kitchenFacilities;

    public Apartment(int persons, String view, int size_m2, double price,Set<String> kitchenFacilities) {
        super(persons, view, size_m2, price);
        this.kitchenFacilities = kitchenFacilities;
    }

    public void addKitchenFacility(String facility) {
        kitchenFacilities.add(facility);
    }

    public void removeKitchenFacility(String facility) {
        kitchenFacilities.remove(facility);
    }

    public Set<String> getKitchenFacilities() {
        return kitchenFacilities;
    }

    public static Apartment compareApartments(Apartment apartment1, Apartment apartment2) {
        int size1 = apartment1.getKitchenFacilities().size();
        int size2 = apartment2.getKitchenFacilities().size();

        if (size1 > size2) {
            return apartment1;
        } else if (size1 < size2) {
            return apartment2;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                ", kitchenFacilities=" + kitchenFacilities +
                '}';
    }
}
