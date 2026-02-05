package d260202_02_Accountbook_Report;

import java.util.List;

public class AccountbookManager {
    AccountbookDAO dao;

    public AccountbookManager(AccountbookDAO dao) {
        this.dao = dao;
    }

    public void selectByCategory(String category) {
        List<Accountbook> list = dao.findByCategory(category);

        if (list == null || list.isEmpty()) {
            System.out.println("해당 카테고리 데이터가 없습니다.");
            return;
        }

        for (Accountbook ab : list) {
            System.out.println(ab);
        }
    }

    public void selectByType(String type) {
        List<Accountbook> list = dao.findByType(type);

        if (list == null || list.isEmpty()) {
            System.out.println("해당 타입 데이터가 없습니다.");
            return;
        }

        for (Accountbook ab : list) {
            System.out.println(ab);
        }
    }
}
