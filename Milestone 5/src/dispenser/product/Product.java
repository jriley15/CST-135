package dispenser.product;
/**
 * Trevor Moore
 * Jordan Riley
 * Antonio Jabrail
 * CST-135
 * Milestone 3
 * 9/24/2017
 */

import java.awt.Image;
import java.text.DecimalFormat;

//class is abstract and cannot be instantiated, only inherited from
//this is the super class
public abstract class Product {

	//private member variables (cannot be accessed outside this class)
	private String name;
	private double price;
	private int stock;
	private String description;
	private int id;
	private Image image;
	
	//constructor with no arguments that instantiates variables
	public Product() {
		name = "";
		price = 0.0;
		stock = 0;
		description = "";
		id = 0;
		image = null;
	}
	
	//overloaded copy constructor that initializes the fields with a copy of another objects fields
	public Product(Product p) {
		this.name = p.getName();;
		this.price = p.getPrice();
		this.stock = p.getStock();
		this.description = p.getDescription();
		this.id = p.getId();
		this.image = p.getImage();
	}

	//overloaded constructor that initializes the fields with the parameters
	public Product(String n, Double p, int s, String d, int id, Image i) {
		this.name = n;
		this.price = p;
		this.stock = s;
		this.description = d;
		this.id = id;
		this.image = i;
	}
	
	
	//getters and setters for getting and setting member variables
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}
	
	//toString that returns the name, description, and decimal format of the price
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return name + ": " + description + ", $" + df.format(price);
	}
	
}