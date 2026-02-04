import java.sql.Date;

public class Accountbook {

    private int id;
    private String type;
    private int amount;
    private String category;
    private Date date;

    public Accountbook() {}

    public Accountbook(int id, String type, int amount, String category, Date date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString() {
        return "Accountbook [id=" + id + ", type=" + type + ", amount=" + amount
                + ", category=" + category + ", date=" + date + "]";
    }
}
