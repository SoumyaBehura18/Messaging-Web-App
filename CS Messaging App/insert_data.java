import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class InsertData {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database_name";
    private static final String USER = "your_user";
    private static final String PASS = "your_password";

    public static void insertData(String csvFilePath) {
        String insertQuery = "INSERT INTO customer_messages (user_id, timestamp, message_body) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery);
             Reader in = new FileReader(csvFilePath)) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("User ID", "Timestamp (UTC)", "Message Body")
                    .withFirstRecordAsHeader()
                    .parse(in);

            for (CSVRecord record : records) {
                pstmt.setString(1, record.get("User ID"));
                pstmt.setString(2, record.get("Timestamp (UTC)"));
                pstmt.setString(3, record.get("Message Body"));
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            System.out.println("Data inserted successfully!");
        } catch (Exception e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        insertData("/path/to/GeneralistRails_Project_MessageData.csv");
    }
}
