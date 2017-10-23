package application;
/**
 * Trevor Moore
 * Jordan Riley
 * Antonio Jabrail
 * CST-135
 * Milestone 3
 * 9/24/2017
 */

import java.awt.Image;

//Chips extends Snack and therefore inherits its properties
//Chips IS-A Snack and IS-A Product
public class Chips extends Snack {

	//private member variables
	private boolean salty;
	
	//constructor with no arguments that instantiates variables
	public Chips() {
		//instantiates variables from superclass
		super();
		//instantiates Chips member variables
		salty = false;
	}
	
	//overloaded copy constructor that initializes the fields with a copy of another objects fields
	public Chips(Chips c) {
		super(c.getName(), c.getPrice(), c.getStock(), c.getDescription(), c.getId(), c.getImage(), c.isVegan());
		this.salty = c.salty;
	}

	//overloaded constructor that initializes the fields with the parameters
	public Chips(String n, Double p, int s, String d, int id, Image i, boolean v, boolean salt) {
		super(n, p, s, d, id, i, v);
		this.salty = salt;
	}
	
	//getters and setters
	public boolean isSalty() {
		return salty;
	}

	public void setSalty(boolean salty) {
		this.salty = salty;
	}
	
	//toString that returns the toString from the super class
	public String toString() {
		return "Chips: "+super.toString();
	}

}
