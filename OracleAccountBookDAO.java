package d260202_02_Accountbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class OracleAccountBookDAO implements AccountbookDAO {
    Connection conn;

    public OracleAccountBookDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@172.31.1.10:1521:xe";
            conn = DriverManager.getConnection(url, "system", "admin");
            if (conn == null) {
                System.out.println("DB연결 실패, 프로그램 종료");
            }
            System.out.println("DB연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Accountbook findByCategory(String category) {
        Accountbook ab = null;
        ResultSet rs = null;
        try {
            String sql = "select * from accountbook where category=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            rs = ps.executeQuery();


            if (!rs.next()) {
                System.out.println("찾는 데이터가 없습니다.");
                return null;
            }

            do{
                int id = rs.getInt("id");
                String type = rs.getString("type");
                int amount = rs.getInt("amount");
                category = rs.getString("category");
                Date date = rs.getDate("adate");
                String memo = rs.getString("memo");
                ab = new Accountbook(id, type, amount, category, date, memo);
                System.out.println(ab);
            }
            while (rs.next());

            rs.close();
            ps.close();
            return ab;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}