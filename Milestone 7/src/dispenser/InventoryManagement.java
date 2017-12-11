package dispenser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import dispenser.product.Candy;
import dispenser.product.Chips;
import dispenser.product.Drink;
import dispenser.product.Gum;
import dispenser.product.Product;
import javafx.scene.image.Image;


public class InventoryManagement {

	/**
	 * Jordan Riley
	 * This is my own work.
	 * CST-135
	 * 9/24/2017
	 *
	 *
	 */

	//arraylist for products in inventory
	private ArrayList<Product> products;

	//image file path var
	private static final String imagePath = "images/";


	//default constructor
	public InventoryManagement() {


		//instantiate products arraylist
		products = new ArrayList<Product>();

		//call load function to populate products arraylist
		load();

		//sort products by name
		this.sortByName();

		//convert arraylist to array of strings for simplicity sake of binary recursion search
		String[] productNames = new String[products.size()];
		for (int i = 0; i < productNames.length; i++) {
			productNames[i] = products.get(i).getName();

		}

		//calls recursive function which uses a binary search algorithm on a sorted array of names to find an index
		System.out.println("Call Stack: ");
		int indexOfSearch = recursiveSearch(productNames, "Pringles");
		System.out.println("Recursive Search Index: "+indexOfSearch+", quantity: "+products.get(indexOfSearch).getStock());

	}

	//binary search function that recursively finds index of keyword
	public int recursiveSearch(String[] names, String wordToFind) {

	    int start = 0;
	    int end = names.length;
	    int mid = end / 2;

	    //function stack print
		System.out.println("Start: "+start +", End: "+ end +", Mid: "+ mid+", Array: " + Arrays.toString(names));


	    if(end < start){
	        return -1;
	    } else if(wordToFind.equals(names[mid])) {
	        return mid;
	    } else if(wordToFind.compareTo(names[mid]) < 0) {
	        String[] split = new String[mid];
	        for(int i = 0; i < mid; i++){
	            split[i] = names[i];
	        }
	        return recursiveSearch(split,wordToFind);
	    } else {
	        String[] split = new String[end - mid - 1];

	        for(int i = 0; i < end - mid - 1; i++){
	            split[i] = names[mid + i + 1];
	        }
	        return recursiveSearch(split, wordToFind);
	    }
	}



	//function stocks dispenser with passed in argument amount of every product
	public void load() {

        String csvFile = "src/config/products.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                //create local var for product to add
                Product p = null;

                //load image for product
        		Image i = new Image(imagePath+data[7]);

                if (data[0].equalsIgnoreCase("snack")) {

                	switch (data[1]) {

                	case "Candy":
                		p = new Candy(data[2], Double.parseDouble(data[3]), Integer.parseInt(data[4]), data[5], Integer.parseInt(data[6]), i,
                				Boolean.parseBoolean(data[8]), Boolean.parseBoolean(data[9]), Boolean.parseBoolean(data[10]), Boolean.parseBoolean(data[11]));
                		break;
                	case "Chips":
                		p = new Chips(data[2], Double.parseDouble(data[3]), Integer.parseInt(data[4]), data[5], Integer.parseInt(data[6]), i,
                				Boolean.parseBoolean(data[8]), Boolean.parseBoolean(data[9]));
                		break;
                	case "Gum":
                		p = new Gum(data[2], Double.parseDouble(data[3]), Integer.parseInt(data[4]), data[5], Integer.parseInt(data[6]), i,
                				Boolean.parseBoolean(data[8]), Boolean.parseBoolean(data[9]));
                		break;
                	}
                	p.setType(0);
                } else if (data[0].equalsIgnoreCase("drink")) {

            		p = new Drink(data[2], Double.parseDouble(data[3]), Integer.parseInt(data[4]), data[5], Integer.parseInt(data[6]), i,
            				Integer.parseInt(data[8]));
                	p.setType(1);
                }

                if (p != null) {
                	products.add(p);
                }
            }
            br.close();

        //exception handle
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}


	//function that calls the recursive sort function
	public void sortByName() {
		sortNames(products, products.size());
	}

	//returns an arraylist of sorted products by name
	public ArrayList<Product> sortNames(ArrayList<Product> products, int n) {
        if (n == 1) {
            return products;
        }

        Product temp;
        for (int i = 0; i < n-1; i++) {
            if (products.get(i+1).getName().compareTo(products.get(i).getName()) < 0) {
                temp = products.get(i);
                products.set(i, products.get(i+1));
                products.set(i+1, temp);
            }
        }
        return sortNames(products, n-1);
	}


	public void sortByQuantity() {
		sortQuantities(products, products.size());
	}

	//returns an arraylist of sorted products by quantities
	public ArrayList<Product> sortQuantities(ArrayList<Product> products, int n) {
        if (n == 1) {
            return products;
        }

        Product temp;
        for (int i = 0; i < n-1; i++) {
            if (products.get(i+1).getStock() < products.get(i).getStock()) {
                temp = products.get(i);
                products.set(i, products.get(i+1));
                products.set(i+1, temp);
            }
        }
        return sortQuantities(products, n-1);
	}


	//returns product of certain type based on name
	public Product getProduct(String name) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equalsIgnoreCase(name))
				return products.get(i);
		}
		return null;
	}

	//returns product of certain type
	public Product getProduct(int id) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id)
				return products.get(i);
		}
		return null;
	}

	//returns next available product
	public Product getNextProduct() {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getStock() > 0)
				return products.get(i);
		}
		return null;
	}

	//returns stock for certain item type
	public int itemCount(int id) {
		return getProduct(id).getStock();

	}

	//removes product stock from inventory
	public void remove(int id) {
		getProduct(id).setStock(getProduct(id).getStock()-1);

	}

	//adds product stock to inventory
	public void add(int id) {
		getProduct(id).setStock(getProduct(id).getStock()+1);

	}

	//adds product stock to inventory
	public void add(int id, int amt) {
		getProduct(id).setStock(getProduct(id).getStock()+amt);

	}


	//getters and setters

	public ArrayList<Product> getProducts() {
		return products;
	}



	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}




}
