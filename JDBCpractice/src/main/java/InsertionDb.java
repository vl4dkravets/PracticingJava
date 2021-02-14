import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertionDb {

    public static void main(String[] args) {
        InsertionDb m = new InsertionDb();
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/contactsListGui";
            String login = "";
            String password = "";
            Connection con = DriverManager.getConnection(url, login, password);
            try {

                m.insert(con, "FirstName", "LastName", "phone", "email");

                m.select(con);
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insert(Connection con, String firstName, String lastName, String phone, String email) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO JC_CONTACT (FIRST_NAME, LAST_NAME, PHONE, EMAIL) VALUES (?, ?, ?, ?)");
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, phone);
        stmt.setString(4, email);
        stmt.executeUpdate();
        stmt.close();
    }

    private void select(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT");
        while (rs.next()) {
            String str = rs.getString("contact_id") + ":" + rs.getString(2);
            System.out.println("Contact:" + str);
        }
        rs.close();
        stmt.close();
    }
}