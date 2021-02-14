import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDb
{
    public static void main(String[] args) {
        TestDb m = new TestDb();
        m.testDatabase();
    }

    private void testDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/contactsListGui";
            String login = "";
            String password = "";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM JC_CONTACT");
                while (rs.next()) {
                    String str = rs.getString("contact_id") + ":" + rs.getString(2);
                    System.out.println("Contact:" + str);
                }
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}