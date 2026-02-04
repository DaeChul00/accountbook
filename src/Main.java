import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "1234"
            );

            System.out.println("DB 접속 성공: " + conn);

            Statement stat = conn.createStatement();

            // 기존 테이블 있으면 삭제 (없으면 예외 무시)
            try {
                stat.execute("DROP TABLE accountbook");
            } catch (Exception e) {}

            // 테이블 생성
            String ctable = "CREATE TABLE accountbook("
                    + " id NUMBER PRIMARY KEY,"
                    + " type VARCHAR2(20),"
                    + " amount NUMBER,"
                    + " category VARCHAR2(40),"
                    + " adate VARCHAR2(40),"
                    + " memo VARCHAR2(40)"
                    + ")";

            stat.execute(ctable);

            // 초기 데이터 입력
            String sql = "INSERT INTO accountbook VALUES"
                    + "(1, '지출', 40000, '식비', '2026-02-02', '치킨')";

            stat.execute(sql);

            System.out.println("테이블 생성 및 데이터 삽입 완료");

            conn.close();

            // 프로그램 실행
            new AccountFc4Manager().run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
