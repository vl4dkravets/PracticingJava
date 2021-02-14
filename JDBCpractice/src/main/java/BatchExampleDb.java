import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BatchExampleDb {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/contactsListGui";
            
            try (Connection con = DriverManager.getConnection(url, "", "")) {
                addBatch(con);
                select(con);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void addBatch(Connection con) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO jc_contact (first_name, last_name, phone, email) "
                        + "VALUES (?, ?, ?, ?)", new String[] {"contact_id"})) {
            
            for (int i = 0; i < 10; i++) {
                // Inserting values in the prepared request
                stmt.setString(1, "FirstName_" + i);
                stmt.setString(2, "LastNAme_" + i);
                stmt.setString(3, "phone_" + i);
                stmt.setString(4, "email_" + i);
                // Statements won't be executed - instead, it'll be added to buffer storage
                stmt.addBatch();
            }
            // After the loop, we execute all of the statements from the buffer
            // the purpose if improved performance
            stmt.executeBatch();

            ResultSet gk = stmt.getGeneratedKeys();
            while(gk.next()) {
                System.out.println("Inserted:" + gk.getString(1));
            }
        }
    }

    private static void select(Connection con) throws SQLException {

        // Java 7 new syntax
        try (PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM jc_contact; DELETE FROM jc_contact")) {

            // true if there was some ResultSet returned
            // false - modification operation was applied OR there's no more ResultSets
            // meant to describe the first result
            boolean test = stmt.execute();

            // getUpdateCount() indicated whether there's more than 1 result
            // & total # of sql operations
            while (test || stmt.getUpdateCount() > -1) {
                if (test) {
                    try (ResultSet rs = stmt.getResultSet()) {
                        while (rs.next()) {
                            String str = rs.getString("contact_id") + ":" + rs.getString(2);
                            System.out.println("Contact:" + str);
                        }
                    }
                } else {
                    System.out.println("Update SQL is executed:" + stmt.getUpdateCount());
                }
                System.out.println("=============================");
                test = stmt.getMoreResults();
            }
        }
    }
}
