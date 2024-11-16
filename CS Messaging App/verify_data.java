import java.sql.*;

public class VerifyData {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database_name";
    private static final String USER = "your_user";
    private static final String PASS = "your_password";

    public static void verifyData() {
        String query = "SELECT * FROM customer_messages LIMIT 5";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Sample Data from Database:");
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String timestamp = rs.getString("timestamp");
                String messageBody = rs.getString("message_body");
                System.out.println(userId + ", " + timestamp + ", " + messageBody);
            }
        } catch (Exception e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        verifyData();
    }
}
