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

/**
 * 
 * @author Trevor
 *Class is abstract and cannot be instantiated, only inherited from.
 *Class extends Product and therefore inherits its properties (Snack IS-A Product).
 *Class implements Comparable interface that compares Product objects.
 * Trevor Moore worked on this class last.
 */
public class Snack extends Product implements Comparable<Product> {

	//private member variable (cannot be accessed outside of this class)
	private boolean isVegan;
	
	/**
	 * constructor with no arguments that instantiates variables
	 */
	public Snack() {
		//instantiates variables from super class
		super();
		isVegan = false;
	}
	
	/**
	 * @param s
	 * overloaded copy constructor that initializes the fields with a copy of another objects fields
	 */
	public Snack(Snack s) {
		super(s.getName(), s.getPrice(), s.getStock(), s.getDescription(), s.getId(), s.getImage());
		this.isVegan = s.isVegan();
	}
	
	/**
	 * 
	 * @param n
	 * @param p
	 * @param s
	 * @param d
	 * @param id
	 * @param i
	 * @param v
	 * overloaded constructor that initializes the fields with the parameters
	 */
	public Snack(String n, Double p, int s, String d, int id, Image i, boolean v) {
		//variables from super class
		super(n, p, s, d, id, i);
		isVegan = v;
	}

	//getters and setters
	public boolean isVegan() {
		return isVegan;
	}

	public void setVegan(boolean isVegan) {
		this.isVegan = isVegan;
	}
	
	/**
	 * Overridden toString that returns the toString from the super class
	 */
	public String toString() {
		return super.toString();
	}
	
	@Override
	/**
	 * @param o
	 * Overridden compareTo method that compares Snacks by their names
	 * and then their prices if their names are equal.
	 */
	public int compareTo(Product o) {

		int comparing = this.getName().compareTo(o.getName());
		if(comparing == 0) {
			return Double.compare(this.getPrice(), o.getPrice());
		}
		return comparing;
		
	}
	
}