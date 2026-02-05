package d260202_02_Accountbook_Report;

import java.io.IOException;
import java.util.Scanner;

public class AccountbookProgram {

    AccountbookManager ab;
    Scanner scan = new Scanner(System.in);
    String category;
    String type;

    public AccountbookProgram(AccountbookDAO dao) throws IOException {
        ab = new AccountbookManager(dao);

        while (true) {
            switch (menu()) {
                case 1:
                    selectByCategory();
                    break;
                case 2:
                    selectByType();
                    break;

            }
        }
    }

    public int menu() {
        System.out.println("1. 카테고리 검색");
        System.out.println("2. 수입 또는 지출 검색");
        System.out.print(">>>");
        return Integer.parseInt(scan.nextLine());
    }

    public void selectByCategory() throws IOException {
        System.out.print("카테고리를 입력하세요 >>>");
        category = scan.nextLine();
        ab.selectByCategory(category);
        System.in.read();
    }

    public void selectByType() throws IOException {
        System.out.print("수입 또는 지출을 입력하세요 >>>");
        type=scan.nextLine();
        ab.selectByType(type);
        System.in.read();
    }
}
