package dispenser.product;
/**
 * Trevor Moore
 * Jordan Riley
 * Antonio Jabrail
 * CST-135
 * Milestone 3
 * 9/24/2017
 */

import javafx.scene.image.Image;

//Gum extends Snack and inherits all its properties
//Gum IS-A Snack and IS-A Product
public class Gum extends Snack {

	//private member variables
	private boolean minty;

	//constructor with no arguments that instantiates variables
	public Gum() {
		//instantiates variables from super class
		super();
		//instantiates Gum member variable
		minty = false;
	}

	//overloaded copy constructor that initializes the fields with a copy of another objects fields
	public Gum(Gum g) {
		super(g.getName(), g.getPrice(), g.getStock(), g.getDescription(), g.getId(), g.getImage(), g.isVegan());
		this.minty = g.minty;
	}

	//overloaded constructor that initializes the fields with the parameters
	public Gum(String n, Double p, int s, String d, int id, Image i, boolean v, boolean mint) {
		super(n, p, s, d, id, i, v);
		this.minty = mint;
	}

	//getters and setters
	public boolean isMinty() {
		return minty;
	}

	public void setMinty(boolean minty) {
		this.minty = minty;
	}

	//toString that returns the toString from the super class
	public String toString() {
		return "Gum: "+super.toString();
	}

}