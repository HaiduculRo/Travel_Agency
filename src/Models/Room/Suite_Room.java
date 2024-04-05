package Models.Room;
import java.util.Scanner;

public final class Suite_Room extends Room {
    public Suite_Room(int persons, String view, int size_m2, double price) {
        super(persons, view, size_m2, price);
        while (persons < 3) {
            System.out.println("The number of persons in a Suite Room must be greater than 3.");
            System.out.println("Please enter a valid number of persons (more than 3): ");
            Scanner scanner = new Scanner(System.in);
            persons = scanner.nextInt();
        }
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
