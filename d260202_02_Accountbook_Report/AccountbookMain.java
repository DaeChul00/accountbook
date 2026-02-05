package d260202_02_Accountbook_Report;

import java.io.IOException;

public class AccountbookMain {

	public static void main(String[] args) throws IOException {
		new AccountbookProgram(new OracleAccountBookDAO());
	}
}
