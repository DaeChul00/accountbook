package accountbook_delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AccountBookDAO implements AccountBook5{
	
	Connection conn;
	
	public AccountBookDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@172.16.15.89:1521:xe", "system", "1234");
			if(conn==null) {
				System.out.println("DB연결을 다시 확인하세요.");
				System.out.println("프로그램을 종료합니다.");
			}
			System.out.println("DB연결 성공!!");
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public int delete(int id) {
		PreparedStatement ps = null;
	    try {
	        String sql = "delete from accountbook where id = ?"; 
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        
	        int result = ps.executeUpdate(); 
	        return result; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    } 
	 
	}

	@Override
	public AccountBook findById(int id) {
		PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        String sql = "select * from accountbook where id = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        rs = ps.executeQuery();
	  
	        if (rs.next()) {
	            int _id = rs.getInt("id");
	            String type = rs.getString("type");
	            int amount = rs.getInt("amount");
	            String category = rs.getString("category");
	            java.util.Date date = rs.getDate("adate");
	            String memo = rs.getString("memo");
	            AccountBook ab = new AccountBook(_id, type, amount, category, date, memo);
	            rs.close(); 
	            ps.close();
	            return ab;
	        }
	        return null; 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	

}
