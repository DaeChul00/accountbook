import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OracleAccountBookDAO2 implements AccountBookDAO2{

	Connection conn;
	
	public OracleAccountBookDAO2() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.15.92:1521:xe", "system", "1234");
			if(conn==null) {
				System.out.println("DB연결 실패");
				System.out.println("프로그램 종료");
			}
			System.out.println("DB연결 성공");
			
			
			Statement stat = conn.createStatement();
			stat.execute("drop table AccountBook");
			String ctable = "create table AccountBook("
					+ " id integer,"
					+ " type varchar2(20),"
					+ " amount integer,"
					+ " category varchar2(20),"
					+ " adate varchar2(11)"
					+ ")";
			stat.execute(ctable);
			System.out.println("테이블 생성 완료");
			
			String sql1 = "insert into AccountBook values(1,'지출',10000,'식비','2026-02-01')";
			String sql2 = "insert into AccountBook values(2,'수입',1000000,'월급','2026-02-02')";
			stat.executeUpdate(sql1);
			stat.executeUpdate(sql2);
			System.out.println("수입/지출내역 입력 완료");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				String date = rs.getString("adate");
				AccountBook ab = new AccountBook(id, type, amount, category, date);
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
	

}
