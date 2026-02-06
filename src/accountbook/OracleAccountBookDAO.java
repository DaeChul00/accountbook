package accountbook;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class OracleAccountBookDAO implements AccountBookDAO{
	
	Connection conn;
	AccountBook ab = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
	
	public OracleAccountBookDAO() {
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
	            String adate = rs.getString("adate");
	            String memo = rs.getString("memo");
	            AccountBook ab = new AccountBook(_id, type, amount, category, adate, memo);
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

	@Override
	public List<AccountBook> findByCategory(String category) {
		List<AccountBook> list = new ArrayList<>();

        try {
            String sql = "select * from accountbook where category=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new AccountBook(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("amount"),
                        rs.getString("category"),
                        rs.getString("adate"),
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
    public List<AccountBook> findByType(String type) {
        List<AccountBook> list = new ArrayList<>();

        try {
            String sql = "select * from accountbook where type=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new AccountBook(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("amount"),
                        rs.getString("category"),
                        rs.getString("adate"),
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
	public List<AccountBook> findAll() {
		try {
			String sql = "select * from AccountBook";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<AccountBook> list = new ArrayList<AccountBook>();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String type = rs.getString("type");
				int amount = rs.getInt("amount");
				String category = rs.getString("category");
				String adate = rs.getString("adate");
				String memo = rs.getString("memo");
				AccountBook ab = new AccountBook(id, type, amount, category, adate, memo);
				list.add(ab);
			}
			rs.close();
			ps.close();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getTotalIncome() {
		int Income = 0;
		
		try {
			String sql = "select sum(amount) as Income from AccountBook where type='수입'";
			//"select sum(amount) as Expense from AccountBook where type='지출'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs =ps.executeQuery();
			
			if(rs.next()) {
				Income = rs.getInt("Income");
			}
			rs.close();
			ps.close();
			return Income;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getTotalExpense() {
	int Expense = 0;
			
			try {
				String sql = "select sum(amount) as Expense from AccountBook where type='지출'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				
				if(rs.next()) {
					Expense = rs.getInt("Expense");
				}
				rs.close();
				ps.close();
				return Expense;
				
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}

	@Override
	public int ChangeAmount(AccountBook ab) {
		int result = 0;
        String sql = "UPDATE accountbook SET amount = ? WHERE id = ?";

        try {

        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ab.getAmount());
            ps.setInt(2, ab.getId());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
	}

	@Override
	public int ChangeCategory(AccountBook ab) {
		int result = 0;
        String sql = "UPDATE accountbook SET category = ? WHERE id = ?";
        
        try {
        	PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ab.getCategory());
            ps.setInt(2, ab.getId());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
	}
	

}
