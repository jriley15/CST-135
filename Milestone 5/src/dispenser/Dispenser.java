package dispenser;

import java.util.ArrayList;

import dispenser.product.Product;

/**
 * Trevor Moore
 * Jordan Riley
 * Antonio Jabrail
 * CST-135
 * Milestone 3
 * 9/24/2017
 */

public class Dispenser {

	//member variables

	public TransactionHandler transactions;

	public Inventory inventory;

	private ArrayList<Product> purchasedProducts;

	//default constructor that instantiates inventory and transactions
	public Dispenser() {

		inventory = new Inventory();

		//stocks inventory with 5 of every item
		inventory.stock(5);

		//initializes transaction class
		transactions = new TransactionHandler();


		//initializes purchased products arraylist
		purchasedProducts = new ArrayList<Product>();


	}


	//returns boolean if item is in stock
	public boolean checkStock(int id) {
		if (inventory.itemCount(id) > 0) {
			return true;
		}
		return false;
	}

	//buys item
	public void buyItem(int id) {
		transactions.process(inventory.getProduct(id).getPrice());
		purchasedProducts.add(inventory.getProduct(id));
		inventory.remove(id);
	}



	//getter/setter
	public ArrayList<Product> getPurchasedProducts() {
		return purchasedProducts;
	}


	public void setPurchasedProducts(ArrayList<Product> purchasedProducts) {
		this.purchasedProducts = purchasedProducts;
	}







}