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

//Candy class extends Snack and inherits all superclass properties
//Candy IS-A Snack which IS-A Product
public class Candy extends Snack {

	//private member variables
	private boolean hardCandy;
	private boolean isSweet;
	private boolean isSour;

	//constructor with no arguments that instantiates variables
	public Candy() {
		//instantiates variables from super class
		super();
		//instantiates Candy member variables
		this.hardCandy = false;
		this.isSweet = false;
		this.isSour = false;
	}

	//overloaded copy constructor that initializes the fields with a copy of another objects fields
	public Candy(Candy c) {
		super(c.getName(), c.getPrice(), c.getStock(), c.getDescription(), c.getId(), c.getImage(), c.isVegan());
		this.hardCandy = c.hardCandy;
		this.isSweet = c.isSweet;
		this.isSour = c.isSour ;
	}

	//overloaded constructor that initializes the fields with the parameters
	public Candy(String n, Double p, int s, String d, int id, Image i, boolean v, boolean hard, boolean sweet, boolean sour) {
		super(n, p, s, d, id, i, v);
		this.hardCandy = hard;
		this.isSweet = sweet;
		this.isSour = sour;
	}


	//getters and setters
	public boolean isHardCandy() {
		return hardCandy;
	}

	public void setHardCandy(boolean hardCandy) {
		this.hardCandy = hardCandy;
	}

	public boolean isSweet() {
		return isSweet;
	}

	public void setSweet(boolean isSweet) {
		this.isSweet = isSweet;
	}

	public boolean isSour() {
		return isSour;
	}

	public void setSour(boolean isSour) {
		this.isSour = isSour;
	}

	//toString that returns the toString from the super class
	public String toString() {
		return "Candy: "+super.toString();
	}

}
