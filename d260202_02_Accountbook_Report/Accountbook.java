package d260202_02_Accountbook_Report;

import java.util.Date;
// 기능 설계
// 기능을 가지고 메뉴를 작성 (수입, 지출 입력, 월 사용금액, 월 수입, 지출) 문서로 작성하면서 각각의 입력깂과 출력값을 상세히 명세
//인터페이스를 이용해서 각자 맡은 코드를 나눠서 작성, 나눠서 작성한 것을 다시 결합 (인터페이스의 상속)
//팀장의 역할은 모든 프로그램을 합쳐서 조율하기
//Oracle, H2에 대해 처리
// 어떤값을 어떻게 입력하고 출력할지 
public class Accountbook {
	private int id; //아이디
	private String type; //수입 또는 지출
	private int amount; //금액
	private String category; //분류
	private java.util.Date date; //사용날짜 or java.sql.Date
	private String memo;

	public Accountbook() {
	}

	public Accountbook(int id, String type, int amount, String category, Date date, String memo) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.memo = memo;
	}

//	public Accountbook(int id, String type, int amount, String category, String date, String memo) {
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Accountbook{" +
				"id=" + id +
				", type='" + type + '\'' +
				", amount=" + amount +
				", category='" + category + '\'' +
				", date=" + date +
				", memo=" + memo +
				'}';
	}
}