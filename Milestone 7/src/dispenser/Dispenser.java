package dispenser;

import java.util.ArrayList;

import dispenser.product.Product;

/**
 * Jordan Riley
 * This is my own work.
 * CST-135
 * 9/24/2017
 *
 *
 */

public class Dispenser {

	//member variables

	public TransactionHandler transactions;
	public InventoryManagement inventory;
	//private ArrayList<Product> purchasedProducts;

	//default constructor that instantiates inventory and transactions
	public Dispenser() {

		//initiailizes inventory class
		inventory = new InventoryManagement();


		//initializes transaction class
		transactions = new TransactionHandler();


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
		//purchasedProducts.add(inventory.getProduct(id));
		inventory.remove(id);
	}



}