package Models.Hotel;

import Models.Room.Room;

public final class Resort extends Hotel {
    private final int nr_piscine;

    public Resort(String name, int nrStele, String tara, String city, Room[] camere, int nr_piscine) {
        super(name, nrStele, tara, city, camere, "all_inclusive");
        this.nr_piscine = nr_piscine;
    }

    public int getNrPiscine() {
        return nr_piscine;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resort{\n");
        sb.append("\tnr_piscine=").append(nr_piscine).append("\n");
        sb.append("\tName=").append(getName()).append("\n");
        sb.append("\tNr Stele=").append(getNrStele()).append("\n");
        sb.append("\tTara='").append(getTara()).append("'\n");
        sb.append("\tCity='").append(getCity()).append("'\n");
        sb.append("\tTip Masa='").append(getTipMasa()).append("'\n");
        sb.append("\tRooms:\n");

        // Iterăm prin lista de camere și le adăugăm la șirul de caractere
        for (Room room : getAllRooms()) {
            sb.append("\t\t").append(room.toString()).append('\n');
        }

        sb.append("}");
        return sb.toString();
    }
}
