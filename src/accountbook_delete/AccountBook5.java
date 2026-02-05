package accountbook_delete;

import java.util.List;

public interface AccountBook5 {
	//삭제
	public int delete(int id);
	public AccountBook findById(int id);
	
	//입력
	public int insert(AccountBook ab);
	public int count();
	
	//선택조회
	public AccountBook findByCategory(String category);
	
	//전체 조회
	public List<AccountBook> findAll();
	public int getTotalIncome();
	public int getTotalExpense();
	
	//수정
	int ChangeAmount(AccountBook ab);
    int ChangeCategory(AccountBook ab);
}
