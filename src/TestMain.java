public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountBookDAO2 abd = new OracleAccountBookDAO2();
		
		System.out.println(abd.findAll());
		
		int Total = abd.getTotalIncome() - abd.getTotalExpense();
		System.out.println("	총 수입금액 : " + abd.getTotalIncome());
		System.out.println("-	총 지출금액 : " + abd.getTotalExpense());
		System.out.println("--------------------------");
		System.out.println("	순수익 : " + Total);
	}

}
