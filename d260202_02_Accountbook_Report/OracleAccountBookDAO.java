package d260202_02_Accountbook_Report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OracleAccountBookDAO implements AccountbookDAO {
    Connection conn;
    Accountbook ab = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public OracleAccountBookDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@172.30.1.59:1521:xe";
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
    public List<Accountbook> findByCategory(String category) {
        List<Accountbook> list = new ArrayList<>();

        try {
            String sql = "select * from accountbook where category=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Accountbook(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("amount"),
                        rs.getString("category"),
                        rs.getDate("adate"),
                        rs.getString("memo")
                ));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Accountbook> findByType(String type) {
        List<Accountbook> list = new ArrayList<>();

        try {
            String sql = "select * from accountbook where type=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Accountbook(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("amount"),
                        rs.getString("category"),
                        rs.getDate("adate"),
                        rs.getString("memo")
                ));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}