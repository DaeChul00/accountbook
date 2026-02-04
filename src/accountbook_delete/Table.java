package accountbook_delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.15.89:1521:xe","system","1234");
	      System.out.println(conn);
	      Statement stat=conn.createStatement();
	      
	      String dt="drop table accountbook";
	      stat.execute(dt);
	      
	      String ctable="create table accountbook("
	            + " id int,"
	            + " type varchar2(20),"
	            + " amount int,"
	            + " category varchar2(40),"
	            + " adate varchar2(40),"
	            + " memo varchar2(40)"
	            + ")";
	      stat.execute(ctable);
	      
	      String sql="insert into accountbook values"
	            + "(1,'수입', 40000,'식비','2026-02-02','치킨')";
	      
	      System.out.println(stat.executeUpdate(sql));
	      

	   }
}
