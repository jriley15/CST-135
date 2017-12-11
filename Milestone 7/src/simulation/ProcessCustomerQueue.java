package simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import dispenser.product.Candy;
import dispenser.product.Chips;
import dispenser.product.Drink;
import dispenser.product.Gum;
import dispenser.product.Product;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import ui.Main;

public class ProcessCustomerQueue {

	/**
	 * Jordan Riley
	 * This is my own work.
	 * CST-135
	 * 9/24/2017
	 *
	 *
	 */


	//class member variables
	private CustomerQueue customerQueue;
	private CustomerQueuePane customerPane;
	private Main main;
	private int time;

	//default constructor which loads customer data from text file and adds to queue
	public ProcessCustomerQueue(Main main) {

		this.main = main;
		customerQueue = new CustomerQueue();


		//loads customers from csv file and adds them to queue
        String csvFile = "src/config/customers.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                //add new customer to queue
                customerQueue.in(new Customer(data[0], data[1]));

            }
            br.close();

        //exception handle
        } catch (Exception e) {
        	e.printStackTrace();
        }


	}


	//function which starts the customer queue timeline event
	public void start() {

		//arraylist to hold the queue information for display
		ArrayList<String> customerLog = new ArrayList<String>();

		//timeline animation event with a 5 second delay that cycles through
		//every customer in the queue and processes the data
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {

			//local vars
			Customer c = customerQueue.first();

			//loads product based on customers product string
			Product p = main.getDispenser().inventory.getProduct(c.getPurchase());

			//updates customer name field
			customerPane.customerName.setValue("Current Customer("+customerQueue.length()+"): " + c.getName());

			//checks if product is in stock
			if (p.getStock() > 0) {
				customerPane.customerSelection.setValue("Customer Selected " + p.getName() + ": Available");
				customerLog.add("Customer: "+c.getName()+" purchased: "+p.getName()+" for "+main.formatMoney(p.getPrice())+"\n");
			} else {
				String pName = p.getName();
				p = main.getDispenser().inventory.getNextProduct();
				customerPane.customerSelection.setValue("Customer Selected " + pName + ": Not available\n New Selection: " + p.getName());
				customerLog.add("Customer: "+c.getName()+" couldn't purchase: "+pName+", purchased: "+p.getName()+" instead for "+main.formatMoney(p.getPrice())+"\n");

			}

			System.out.println("Simulation purchase: "+p.getName());

			//starts 5 second timer
			time = 5;
			Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), eve -> {

				customerPane.timeLeft.setValue("Next Customer in: "+time+" seconds");
				time--;
			}));
			timer.setCycleCount(5);
			timer.playFrom(Duration.millis(999));


			//buys item and processes transaction
			main.getDispenser().buyItem(p.getId());
			customerPane.productImage.setOpacity(0.0);

			//updates product image
			customerPane.productImage.setImage(p.getImage());

			//updates price field
			customerPane.productPrice.setValue("Price: " + main.formatMoney(p.getPrice()));

			//starts image animations
			main.getAnimation().animateLong(customerPane.productImage);

			//removes customer from queue
			customerQueue.out(c);

		}));


		//timeline finished event
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				//makes sure queue is empty
				if (customerQueue.isEmpty()) {

					//displays alert dialogue with activity log
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Alert");
					alert.setHeaderText(null);

					String logString = "";
					for (String s : customerLog) {
						logString += s;
					}

					alert.setContentText("Simulation Complete, Activity Log: \n\n"+logString+"\nClick OK to view inventory");

					//shows inventory on alert dialogue closed
					alert.setOnHidden(evt -> main.showInventory(false, false, "", false));
					alert.show();
					timeline.stop();
				}
			}
		});
		//sets cycles of timeline to size of queue
		timeline.setCycleCount(getQueue().length());

		//starts timeline
		timeline.playFrom(Duration.millis(4999));


	}




	//getters and setters
	public CustomerQueue getQueue() {
		return customerQueue;
	}


	public void setCustomerQueue(CustomerQueue customerQueue) {
		this.customerQueue = customerQueue;
	}


	public CustomerQueuePane getCustomerPane() {
		return customerPane;
	}


	public void setCustomerPane(CustomerQueuePane customerPane) {
		this.customerPane = customerPane;
	}






















}

