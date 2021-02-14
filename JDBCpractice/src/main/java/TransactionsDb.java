import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionsDb {

    public static void main(String[] args) {
        TransactionsDb m = new TransactionsDb();

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/contactsListGui";
            String login = "";
            String password = "";
            Connection con = DriverManager.getConnection(url, login, password);


            try {
                // Switch off autocommit
                con.setAutoCommit(false);

                for (int i = 0; i < 5; i++) {
                    long contactId = m.insert(con, "FirstName_" + i, "LastName_" + i, "phone", "email");
                    System.out.println("CONTACT_ID:" + contactId);
                }
                // Completes the operations
                con.commit();

                // command cancels all the changes
                //con.rollback();

                con.setAutoCommit(true);
                

                m.select(con);
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long insert(Connection con, String firstName, String lastName, String phone, String email) throws SQLException {
        long contactId = -1;
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO jc_contact (first_name, last_name, phone, email) VALUES (?, ?, ?, ?)",
                new String[]{"contact_id"});
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, phone);
        stmt.setString(4, email);
        stmt.executeUpdate();

        ResultSet gk = stmt.getGeneratedKeys();
        while (gk.next()) {
            contactId = gk.getLong("CONTACT_ID");
        }
        stmt.close();

        return contactId;
    }

    private void select(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM jc_contact");
        while (rs.next()) {
            String str = rs.getString("contact_id") + ":" + rs.getString(2);
            System.out.println("Contact:" + str);
        }
        rs.close();
        stmt.close();
    }
}
