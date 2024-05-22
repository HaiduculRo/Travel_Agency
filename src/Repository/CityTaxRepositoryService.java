package Repository;

import Models.CityTax.CityTax;
import App.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityTaxRepositoryService {
    private static CityTaxRepositoryService instance;
    private final DatabaseConnection databaseConnection;

    private CityTaxRepositoryService() throws SQLException {
        databaseConnection = DatabaseConnection.getInstance();
    }

    public static CityTaxRepositoryService getInstance() throws SQLException {
        if (instance == null) {
            instance = new CityTaxRepositoryService();
        }
        return instance;
    }

    public void insertCityTax(CityTax cityTax) {
        String query = "INSERT INTO travel_schema.city_tax (country, city, tax) VALUES (?, ?, ?)";
        try
         {
             Connection connection = DriverManager.getConnection(
                     "jdbc:mysql://127.0.0.1:3306/travel_schema",
                     "root",
                     "12345"
             );
             PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cityTax.getCountry());
                preparedStatement.setString(2, cityTax.getCity());
                preparedStatement.setDouble(3, cityTax.getTax_price_per_night());
                preparedStatement.executeUpdate();
                preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<CityTax> getAllCityTaxes() {
        List<CityTax> cityTaxes = new ArrayList<>();
        String query = "SELECT * FROM city_tax";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                double tax = resultSet.getDouble("tax");

                CityTax cityTax = new CityTax(city, country, tax);
                cityTaxes.add(cityTax);
            }
        } catch (SQLException e) {
            System.err.println("Eroare la extragerea datelor despre taxele orașelor: " + e.getMessage());
        }

        return cityTaxes;
    }


    public void deleteCityTax(CityTax cityTax) throws SQLException {
        String query = "DELETE FROM city_tax WHERE city = ? AND country = ? AND tax = ?";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cityTax.getCity());
            preparedStatement.setString(2, cityTax.getCountry());
            preparedStatement.setDouble(3, cityTax.getTax_price_per_night());
            preparedStatement.executeUpdate();
        }
    }

    public void updateCityTax(CityTax cityTax, String old_city_name) throws SQLException {
        String query = "UPDATE city_tax SET country = ?, tax = ?, city = ? WHERE city = ?";
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/travel_schema",
                "root",
                "12345"
        );
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, cityTax.getCountry());
            preparedStatement.setDouble(2, cityTax.getTax_price_per_night());
            preparedStatement.setString(3, cityTax.getCity());
            preparedStatement.setString(4, old_city_name);
            preparedStatement.executeUpdate();
        }
    }



    public void close() throws SQLException {
        databaseConnection.closeConnection();
    }
}
