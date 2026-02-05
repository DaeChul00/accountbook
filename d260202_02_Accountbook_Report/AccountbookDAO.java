package d260202_02_Accountbook_Report;

import java.util.List;

public interface AccountbookDAO {
    List<Accountbook> findByCategory(String category);
    List<Accountbook> findByType(String type);
}

