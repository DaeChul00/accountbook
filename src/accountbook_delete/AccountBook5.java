package accountbook_delete;

public interface AccountBook5 {
	public int delete(int id);
	public AccountBook findById(int id);
	
	//AccountDAO
	public int insert(AccountBook ab);
	public int count();
}
