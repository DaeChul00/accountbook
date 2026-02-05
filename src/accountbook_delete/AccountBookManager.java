package accountbook_delete;

import java.util.Date;
import java.util.List;

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
	
	public void insert(String type, int amount, String category, String adate, String memo) {
		int id=dao.count()+1;
		dao.insert(new AccountBook(id, type, amount, category, adate, memo));
	}
	
	public void selectByCategory(String category) {
		System.out.println(dao.findByCategory(category));
	}

	public void select() {
		List<AccountBook> list = dao.findAll(); // 리스트를 받아옴
	    if (list == null || list.isEmpty()) {
	        System.out.println("조회된 내역이 없습니다.");
	    } else {
	        for (AccountBook ab : list) {
	            System.out.println(ab); // 각 내역 출력
	        }
	    }
	}
	
	 public void changeAmount(int id, int amount) {

	        AccountBook ab = new AccountBook();
	        ab.setId(id);
	        ab.setAmount(amount);

	        int result = dao.ChangeAmount(ab);

	        if (result > 0) {
	            System.out.println("금액 변경 성공");
	            System.out.println(ab.toString());
	        } else {
	            System.out.println("금액 변경 실패 (해당 ID가 존재하지 않습니다)");
	        }
	    }
	 
	 public void changeCategory(int id, String category) {
		 AccountBook ab = new AccountBook();
	        ab.setId(id);
	        ab.setCategory(category);

	        int result = dao.ChangeCategory(ab);

	        if (result > 0) {
	            System.out.println("카테고리 변경 성공");
	            System.out.println(ab.toString());
	        } else {
	            System.out.println("카테고리 변경 실패 (해당 ID가 존재하지 않습니다)");
	        }
	 }
	 
	 public void showTotalSummary() {
		    int income = dao.getTotalIncome();   // DAO의 수입 합계 호출
		    int expense = dao.getTotalExpense(); // DAO의 지출 합계 호출
		    
		    System.out.println("\n========= 가계부 요약 통계 =========");
		    System.out.println(" 총 수입: " + income + "원");
		    System.out.println(" 총 지출: " + expense + "원");
		    System.out.println("----------------------------------");
		    System.out.println(" 현재 잔액: " + (income - expense) + "원");
		    System.out.println("==================================\n");
		}
	
}
