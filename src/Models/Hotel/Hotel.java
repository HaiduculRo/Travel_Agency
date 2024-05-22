package Models.Hotel;

import Models.Room.Room;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    // nrStele, Tara, Oras, pret, camere, tip_masa,
    private String name;
    private int nrStele;
    private String tara;
    private String city;
    private String tipMasa;
    private List<Room> roomList;
    private static final String[] VALORI_TIP_MASA = {"all_inclusive", "breakfast", "half_board"};

    public Hotel(String name, int nrStele, String tara, String city, Room[] camere, String tipMasa) {
        this.name = name;
        this.nrStele = nrStele;
        this.tara = tara;
        this.city = city;
        this.roomList = new ArrayList<>();
        if (camere != null) {
            for (Room camera : camere) {
                addRoom(camera);
            }
        }
        if (isValidTipMasa(tipMasa)) {
            this.tipMasa = tipMasa;
        } else {
            throw new IllegalArgumentException("Tipul de masă invalid: " + tipMasa);
        }
    }




    private boolean isValidTipMasa(String tipMasa) {
        for (String valoare : VALORI_TIP_MASA) {
            if (valoare.equalsIgnoreCase(tipMasa)) {
                return true;
            }
        }
        return false;
    }

    public int getNrStele() {
        return nrStele;
    }

    public String getName(){
        return name;
    }

    public String getTara() {
        return tara;
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(roomList);
    }
    public Room getCamere(int i) {
        if (i >= 0 && i < roomList.size()) {
            return roomList.get(i);
        } else {
            return null;
        }
    }

    public String getTipMasa() {
        return tipMasa;
    }

    public String getCity() {
        return city;
    }

    public void addRoom(Room camera) {
        roomList.add(camera);
    }

    public boolean removeRoom(Room camera) {
        return roomList.remove(camera);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hotel{\n");
        sb.append("\tName=").append(name).append('\n');
        sb.append("\tNr Stele=").append(nrStele).append('\n');
        sb.append("\tTara='").append(tara).append("'\n");
        sb.append("\tCity='").append(city).append("'\n");
        sb.append("\tTip Masa='").append(tipMasa).append("'\n");
        sb.append("\tRooms:\n");

        // Iterăm prin lista de camere și le adăugăm la șirul de caractere
        for (Room room : roomList) {
            sb.append("\t\t").append(room.toString()).append('\n');
        }

        sb.append('}');
        return sb.toString();
    }

}
