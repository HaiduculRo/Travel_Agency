package Repository;

import Models.Plane.Plane;
import App.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightsRepositoryService {
    private static FlightsRepositoryService instance;
    private DatabaseConnection databaseConnection;

    private FlightsRepositoryService() throws SQLException {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static FlightsRepositoryService getInstance() throws SQLException {
        if (instance == null) {
            instance = new FlightsRepositoryService();
        }
        return instance;
    }

    public void insertPlane(Plane plane) throws SQLException {
        String query = "INSERT INTO flights (price, moment_of_flight, moment_of_arrival, airport_city, airport_arrival) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, plane.getPrice());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(plane.getMomentOfFlight()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(plane.getMomentOfArrival()));
            preparedStatement.setString(4, plane.getAirportCity());
            preparedStatement.setString(5, plane.getAirportArrival());
            preparedStatement.executeUpdate();
        }
    }

    public List<Plane> getAllPlanes() throws SQLException {
        List<Plane> planes = new ArrayList<>();
        String query = "SELECT * FROM flights";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                double price = resultSet.getDouble("price");
                LocalDateTime momentOfFlight = resultSet.getTimestamp("moment_of_flight").toLocalDateTime();
                LocalDateTime momentOfArrival = resultSet.getTimestamp("moment_of_arrival").toLocalDateTime();
                String airportCity = resultSet.getString("airport_city");
                String airportArrival = resultSet.getString("airport_arrival");

                Plane plane = new Plane(price, momentOfFlight, momentOfArrival, airportCity, airportArrival);
                planes.add(plane);
            }
        }

        return planes;
    }

    public void deleteFlight(Plane flight) throws SQLException {
        String query = "DELETE FROM flights WHERE airport_city = ? AND airport_arrival = ? AND moment_of_flight = ? AND moment_of_arrival = ? AND price = ?";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, flight.getAirportCity());
            preparedStatement.setString(2, flight.getAirportArrival());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(flight.getMomentOfFlight()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(flight.getMomentOfArrival()));
            preparedStatement.setDouble(5, flight.getPrice());
            preparedStatement.executeUpdate();
        }
    }

    public void updatePlane(Plane flight, LocalDateTime oldMomentOfFlight, LocalDateTime oldMomentOfArrival, String oldAirportCity, String oldAirportArrival) throws SQLException {
        String query = "UPDATE flights SET price = ?, moment_of_flight = ?, moment_of_arrival = ? WHERE  moment_of_flight = ? and moment_of_arrival = ? and airport_city = ? and airport_arrival= ?";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, flight.getPrice());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(flight.getMomentOfFlight()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(flight.getMomentOfArrival()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(oldMomentOfFlight));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(oldMomentOfArrival));
            preparedStatement.setString(6, oldAirportCity);
            preparedStatement.setString(7, oldAirportArrival);
            preparedStatement.executeUpdate();
        }
    }


}
