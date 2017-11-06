package dispenser;

import java.util.ArrayList;


import dispenser.product.Candy;
import dispenser.product.Chips;
import dispenser.product.Drink;
import dispenser.product.Gum;
import dispenser.product.Product;

public class Inventory {



	//arraylist for products in inventory
	private ArrayList<Product> products;


	//default constructor
	public Inventory() {

		products = new ArrayList<Product>();

	}


	//function stocks dispenser with passed in argument amount of every product
	public void stock(int amt) {

		//snacks
		Candy candy = new Candy("Candy", 4.00, -1, "", 1, null, false, false, true, false);
		for (int i = 0; i < amt; i++) {
			products.add(candy);
		}
		Chips chips = new Chips("Chips", 5.00, -1, "", 0, null, false, false);
		for (int i = 0; i < amt; i++) {
			products.add(chips);
		}
		Gum gum = new Gum("Gum", 2.00, -1, "", 2, null, false, true);
		for (int i = 0; i < amt; i++) {
			products.add(gum);
		}

		//drinks
		Drink water = new Drink("Water", 2.00, -1, "", 3, null, -1);
		for (int i = 0; i < amt; i++) {
			products.add(water);
		}
		Drink soda = new Drink("Soda", 4.00, -1, "", 4, null, -1);
		for (int i = 0; i < amt; i++) {
			products.add(soda);
		}
		Drink tea = new Drink("Tea", 3.00, -1, "", 5, null, -1);
		for (int i = 0; i < amt; i++) {
			products.add(tea);
		}

		System.out.println(products.size());
	}


	//returns product of certain type
	public Product getProduct(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id)
				return products.get(i);
		}
		return null;
	}


	//returns stock for certain item type
	public int itemCount(int id) {
		int total = 0;

		for (Product p : products) {
			if (p.getId() == id)
				total++;
		}


		return total;

	}

	//removes product from inventory of certain type
	public void remove(int type) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == type) {
				products.remove(i);
				return;
			}
		}

	}


	//getters and setters

	public ArrayList<Product> getProducts() {
		return products;
	}



	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}









}
