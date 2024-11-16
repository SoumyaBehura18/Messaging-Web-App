import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {
    // Database connection parameters
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database_name";
    private static final String USER = "your_user";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        // SQL query to create the table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS customer_messages (" +
                "id SERIAL PRIMARY KEY, " +
                "user_id INTEGER NOT NULL, " +
                "timestamp TIMESTAMP NOT NULL, " +
                "message_body TEXT NOT NULL" +
                ");";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Execute the SQL statement
            statement.executeUpdate(createTableSQL);
            System.out.println("Table created successfully!");

        } catch (Exception e) {
            System.out.println("Error creating table: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
