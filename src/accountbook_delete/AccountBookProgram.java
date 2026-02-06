package accountbook_delete;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class AccountBookProgram {
	AccountBookManager am;
    Scanner scan = new Scanner(System.in);
    String type; //수입 또는 지출
	int amount; //금액
	String category; //분류
	String adate; //사용날짜
	String memo; 

    public AccountBookProgram(AccountBookDAO dao) throws IOException {
        am = new AccountBookManager(dao);
        while(true) {
        	switch(displayMenu()) {
   		 case 1: insert(); break;
   		 case 2: findAll(); break;
   		 case 3: selectByCategory(); break;
   		 case 4: selectByType(); break;
   		 case 5: changeAmount(); break;
   		 case 6: changeCategory(); break;
   		 case 7: delete(); break;
   		 case 8: showSummary(); break;
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
        System.out.println("| 2.가계부 전체조회     |");
        System.out.println("| 3.가계부 카테고리검색  |");
        System.out.println("| 4.가계부 수입,지출검색 |");
        System.out.println("| 5.가계부 금액변경     |");
        System.out.println("| 6.가계부 카테고리변경  |");
        System.out.println("| 7.선택 삭제         |");
        System.out.println("| 8.수입/지출 통계보기  |");
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
		 adate=scan.next();
		 System.out.println("가계부에 입력할 세부사항:");
		 memo=scan.next();
		 
		 am.insert(type, amount, category, adate, memo); 
	}
    
    
    
    public void selectByCategory() throws IOException {
    	scan.nextLine();
        System.out.print("찾을 카테고리를 입력하세요 >");
        category = scan.nextLine();
        am.selectByCategory(category);
        System.in.read();
    }
    
    public void selectByType() throws IOException {
    	scan.nextLine();
        System.out.print("수입 또는 지출을 입력하세요 >>>");
        type=scan.nextLine();
        am.selectByType(type);
        System.in.read();
    }
    
    private void findAll() throws IOException {
		am.select();
		System.in.read();
	}
    
    private void changeAmount() {
    	System.out.print("수정할 ID 입력: ");
        int id = scan.nextInt();

        System.out.print("변경할 금액 입력: ");
        int amount = scan.nextInt();

        if (amount < 0) {
            System.out.println("금액은 음수가 될 수 없습니다.");
            return;
        }
        am.changeAmount(id, amount);
		
	}
    
    private void changeCategory() {
    	System.out.print("수정할 ID 입력: ");
        int id = scan.nextInt();

        System.out.print("변경할 카테고리 입력: ");
        String category = scan.next();

        if (category == null || category.trim().isEmpty()) {
            System.out.println("카테고리는 비어있을 수 없습니다.");
            return;
        }
        am.changeCategory(id, category);
		
	}
    
    private void showSummary() throws IOException {
        am.showTotalSummary();
        System.out.println("메뉴로 돌아가려면 Enter를 누르세요...");
        System.in.read(); // 대기 기능
    }

}
