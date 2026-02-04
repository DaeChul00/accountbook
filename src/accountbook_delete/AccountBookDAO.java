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
	            String date = rs.getString("date");
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

	@Override
	public int insert(AccountBook ab) {
		try {
			String sql="insert into accountbook values(?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, ab.getId());
			ps.setString(2, ab.getType());
			ps.setInt(3, ab.getAmount());
			ps.setString(4, ab.getCategory());
			ps.setString(5, ab.getDate());
			ps.setString(6, ab.getMemo());
			int result=ps.executeUpdate();
			
			ps.close();
			
			return result;
			
			}catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public int count() {
		try {
			String sql="select count(*) as cnt from accountbook";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int cnt=rs.getInt("cnt");
				rs.close();ps.close();
				return cnt;
			}
			return -1;
			
			}catch (Exception e) {
				e.printStackTrace();
				return -1;
			}	
	}

	

}
