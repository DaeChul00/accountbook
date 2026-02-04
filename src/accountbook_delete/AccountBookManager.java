package accountbook_delete;

import java.util.Date;

public class AccountBookManager {
	
	AccountBook5 dao;
	
	public AccountBookManager() {
		
	}
	
	public AccountBookManager(AccountBook5 dao) {
		this.dao=dao;
	}
	
	public void delete(int id) {
		dao.delete(id);
	}

	public boolean isExist(int id) {
		if(dao.findById(id)==null) {
			return false;
		}
		return true;
	}
	
	public void insert(String type, int amount, String category, String date, String memo) {
		int id=dao.count()+1;
		dao.insert(new AccountBook(id, type, amount, category, date, memo));
	}
	
}
