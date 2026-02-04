package accountbook_delete;

import java.util.Date;
import java.util.Scanner;

public class AccountBookProgram {
	AccountBookManager am;
    Scanner scan = new Scanner(System.in);
    String type; //수입 또는 지출
	int amount; //금액
	String category; //분류
	String date; //사용날짜
	String memo; 

    public AccountBookProgram(AccountBook5 dao) {
        am = new AccountBookManager(dao);
        while(true) {
        	switch(displayMenu()) {
   		 case 1: insert(); break;
   		 case 5: delete(); break;
   		 case 0: System.out.println("프로그램을 종료합니다.!");
   			 	System.exit(0);
   		 }	
        }
    }

    public int displayMenu() {
        System.out.println("+===================+");
        System.out.println("|    가계부 관리      |");
        System.out.println("+===================+");
        System.out.println("| 1.가계부 입력        |");
        System.out.println("| 5. 내역 삭제        |");
        System.out.println("| 0. 종료            |");
        System.out.println("+===================+");
        System.out.print("선택: ");
        return scan.nextInt();
    }

    public void delete() {
        System.out.println("삭제할 내역의 아이디(ID)를 입력하세요.");
        int id = scan.nextInt();
        
        if(am.isExist(id)) {
            System.out.print("정말 삭제하시겠습니까? (1:확인 / 2:취소): ");
            int confirm = scan.nextInt();
            if(confirm == 1) {
                am.delete(id);
                System.out.println(id + "번 내역이 삭제되었습니다.");
            } else {
                System.out.println("삭제가 취소되었습니다.");
            }
        } else {
            System.out.println("해당 아이디가 존재하지 않습니다.");
        }
    }
    
    public void insert() {
		System.out.println("가계부에 입력할 구분(수입/지출)");
		 type=scan.next();
		 System.out.println("가계부에 입력할 금액 : ");
		 amount=scan.nextInt();
		 System.out.println("가계부에 입력할 카테고리(식비,교통,월급 등) :");
		 category=scan.next();
		 System.out.println("가계부에 입력할 날짜(2000-00-00):");
		 date=scan.next();
		 System.out.println("가계부에 입력할 세부사항:");
		 memo=scan.next();
		 
		 am.insert(type, amount, category, date, memo); 
	}
}
