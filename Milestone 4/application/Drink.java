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
 *class is not abstract, so it can be instantiated, and extends Product
 *and therefore inherits its properties.
 *Drink IS-A Product and implements Comparable interface that compares Product objects.
 *Trevor Moore worked on this class last.
 */
public class Drink extends Product implements Comparable<Product> {

	//private member variable (cannot be accessed outside this class)
	private int liquidVolume;

	/**
	 * constructor with no arguments that instantiates variables
	 */
	public Drink() {
		//instantiates variables from super class
		super();
		liquidVolume = 0;
	}

	/**
	 * @param d
	 * overloaded copy constructor that initializes the fields with a copy of another objects fields
	 */
	public Drink(Drink d) {
		super(d.getName(), d.getPrice(), d.getStock(), d.getDescription(), d.getId(), d.getImage());
		this.liquidVolume = d.liquidVolume;
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
	public Drink(String n, Double p, int s, String d, int id, Image i, int v) {
		super(n, p, s, d, id, i);
		liquidVolume = v;
	}

	//getters and setters
	public int getLiquidVolume() {
		return liquidVolume;
	}

	public void setLiquidVolume(int liquidVolume) {
		this.liquidVolume = liquidVolume;
	}

	/**
	 * toString that returns the toString from the super class
	 */
	public String toString() {
		return "Drink: "+super.toString();
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