package simulation;

public class Customer {

	//class member vars
	private String name;
	private String purchase;



	//default constructor
	public Customer(String name, String purchase) {
		super();
		this.name = name;
		this.purchase = purchase;
	}


	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurchase() {
		return purchase;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}









}
