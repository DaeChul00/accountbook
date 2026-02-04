import java.util.Scanner;

public class AccountFc4Manager {

    private AccountbookDAO dao;
    private Scanner sc;

    public AccountFc4Manager() {
        dao = new OracleAccountDAO();
        sc = new Scanner(System.in);
    }

    public void run() {

        boolean running = true;

        while (running) {
            printMenu();
            int menu = sc.nextInt();

            switch (menu) {
                case 1:
                    changeAmount();
                    break;
                case 2:
                    changeCategory();
                    break;
                case 0:
                    System.out.println("프로그램 종료");
                    running = false;
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n메뉴를 선택해주세요>>");
        System.out.println("1. 금액 변경");
        System.out.println("2. 카테고리 변경");
        System.out.println("0. 종료");
        System.out.print("선택 >> ");
    }

    private void changeAmount() {

        System.out.print("수정할 ID 입력: ");
        int id = sc.nextInt();

        System.out.print("변경할 금액 입력: ");
        int amount = sc.nextInt();

        if (amount < 0) {
            System.out.println("금액은 음수가 될 수 없습니다.");
            return;
        }

        Accountbook ab = new Accountbook();
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

    private void changeCategory() {

        System.out.print("수정할 ID 입력: ");
        int id = sc.nextInt();

        System.out.print("변경할 카테고리 입력: ");
        String category = sc.next();

        if (category == null || category.trim().isEmpty()) {
            System.out.println("카테고리는 비어있을 수 없습니다.");
            return;
        }

        Accountbook ab = new Accountbook();
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
}
