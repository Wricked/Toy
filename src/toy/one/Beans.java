package toy.one;

public class Beans {
	private String name;
	private String type;
	private int stock;
	
	public Beans() {
		super();
	}
	public Beans(String name, String type, int stock) {
		super();
		this.name = name;
		this.type = type;
		this.stock = stock;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}	
}
