package simulation;

import java.util.LinkedList;


/**
 * Jordan Riley
 * This is my own work.
 * CST-135
 * 9/24/2017
 *
 *
 */

public class CustomerQueue {


	//linked list to hold data of type customer
	private LinkedList<Customer> data;


	//default constructor
	public CustomerQueue() {

		//instantiates linked list
		data = new LinkedList<Customer>();
	}

	//returns the first elements of data and returns null if data is empty
	public Customer first() {

		if (data.isEmpty())
			return null;
		return data.getFirst();
	}

	//returns size of data
	public int length() {
		return data.size();
	}

	//adds element to end of list
	public void in(Customer c) {
		data.add(c);

	}

	//removes element from list if exists
	public void out(Customer c) {
		if (data.contains(c))
			data.remove(c);

	}

	//returns whether data is empty or not
	public boolean isEmpty() {
		return data.isEmpty();
	}




}
