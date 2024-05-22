import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/travel_schema",
                    "root",
                    "12345"
            );

            // Comanda SQL pentru inserarea unei taxe de oraș
            String query = "INSERT INTO travel_schema.city_tax (country, city, tax) VALUES (?, ?, ?)";

            // Se folosește un PreparedStatement pentru a evita SQL injection
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Romania"); // Setează țara
            preparedStatement.setString(2, "Bucuresti"); // Setează orașul
            preparedStatement.setDouble(3, 10.0); // Setează taxa

            // Execută comanda pentru a insera în baza de date
            preparedStatement.executeUpdate();

            // Închide conexiunea și resursele
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
