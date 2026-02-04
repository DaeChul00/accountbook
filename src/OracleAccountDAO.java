import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OracleAccountDAO implements AccountbookDAO {

    private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String user = "system";
    private final String password = "1234";

    private Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public int ChangeAmount(Accountbook ab) {

        int result = 0;
        String sql = "UPDATE accountbook SET amount = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ab.getAmount());
            ps.setInt(2, ab.getId());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int ChangeCategory(Accountbook ab) {

        int result = 0;
        String sql = "UPDATE accountbook SET category = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ab.getCategory());
            ps.setInt(2, ab.getId());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
