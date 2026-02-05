import java.util.List;

public interface AccountBookDAO2 {
	public List<AccountBook> findAll();
	public int getTotalIncome();
	public int getTotalExpense();
}
