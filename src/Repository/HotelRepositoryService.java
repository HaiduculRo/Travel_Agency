package Repository;

import App.DatabaseConnection;
import Models.Hotel.Hotel;
import Models.Hotel.Resort;
import Models.Room.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRepositoryService {
    private static HotelRepositoryService instance;
    private final DatabaseConnection databaseConnection;

    private HotelRepositoryService() throws SQLException {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static HotelRepositoryService getInstance() throws SQLException {
        if (instance == null) {
            instance = new HotelRepositoryService();
        }
        return instance;
    }

    public void insertHotel(Hotel hotel) throws SQLException {
        String query = "INSERT INTO Hotel (name, nrStele, tara, city, tipMasa) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setInt(2, hotel.getNrStele());
            preparedStatement.setString(3, hotel.getTara());
            preparedStatement.setString(4, hotel.getCity());
            preparedStatement.setString(5, hotel.getTipMasa());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteHotel(String name, String tara, String city) throws SQLException {
        String query = "DELETE FROM Hotel WHERE name = ? AND tara = ? AND city = ?";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, tara);
            preparedStatement.setString(3, city);
            preparedStatement.executeUpdate();
        }
    }


    public List<Hotel> getHotels() throws SQLException {
        String query = "SELECT * FROM Hotel";
        List<Hotel> hotels = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int nrStele = resultSet.getInt("nrStele");
                String tara = resultSet.getString("tara");
                String city = resultSet.getString("city");
                String tipMasa = resultSet.getString("tipMasa");
                int nrPiscine = resultSet.getInt("nr_piscine");
                Room[] camere = new Room[0]; // Initialize an empty array of Room
                if (resultSet.wasNull()) {
                    // Dacă nr_piscine este null, adăugăm un hotel
                    hotels.add(new Hotel(name, nrStele, tara, city, camere, tipMasa));
                } else {
                    // Altfel, adăugăm un resort
                    hotels.add(new Resort(name, nrStele, tara, city, camere, nrPiscine));
                }
            }
        }
        return hotels;
    }


    public List<Hotel> getHotelsByCity(String cityName) throws SQLException {
        String query = "SELECT * FROM Hotel WHERE city = ?";
        List<Hotel> hotels = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cityName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int nrStele = resultSet.getInt("nrStele");
                    String tara = resultSet.getString("tara");
                    String city = resultSet.getString("city");
                    String tipMasa = resultSet.getString("tipMasa");
                    int nrPiscine = resultSet.getInt("nr_piscine");
                    Room[] camere = new Room[0]; // Initialize an empty array of Room
                    if (resultSet.wasNull()) {
                        // Dacă nr_piscine este null, adăugăm un hotel
                        hotels.add(new Hotel(name, nrStele, tara, city, camere, tipMasa));
                    } else {
                        // Altfel, adăugăm un resort
                        hotels.add(new Resort(name, nrStele, tara, city, camere, nrPiscine));
                    }
                }
            }
        }
        return hotels;
    }


    public void insertResort(Resort resort) throws SQLException {
        String query = "INSERT INTO Hotel (name, nrStele, tara, city, tipMasa, nr_piscine) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, resort.getName());
            preparedStatement.setInt(2, resort.getNrStele());
            preparedStatement.setString(3, resort.getTara());
            preparedStatement.setString(4, resort.getCity());
            preparedStatement.setString(5, resort.getTipMasa());
            preparedStatement.setInt(6, resort.getNrPiscine());
            preparedStatement.executeUpdate();
        }
    }

}
