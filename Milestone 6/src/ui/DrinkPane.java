package ui;

import dispenser.product.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DrinkPane {

	/**
	 * Jordan Riley
	 * This is my own work.
	 * CST-135
	 * 9/24/2017
	 *
	 *	Drink UI class
	 *
	 *
	 */

	//main application reference var
	private Main m;


	//default constructor
	public DrinkPane(Main m) {
		this.m = m;
	}


	//returns vbox for header on application borderpane
	public VBox getHeader() {

		VBox header = new VBox();

		HBox navButtons = new HBox();
		navButtons.setSpacing(20);
	    Button back = new Button("< Back");

	    //button event for going back to main screen
	    back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                m.showMain();
            }
        });

	    //unused button for going to receipt screen
	    Button receipt = new Button("Receipt >");
	    receipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	m.showReceipt();
            }
        });
	    navButtons.setAlignment(Pos.CENTER);
	    navButtons.getChildren().addAll(back, receipt);


	    //header labels
		header.setSpacing(10);
		header.setPadding(new Insets(50));
		Label title = new Label("Drinks");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));
	    Label instruction = new Label("Select a product below that you wish to purchase");
		header.getChildren().addAll(navButtons, title, instruction);
		header.setAlignment(Pos.CENTER);


		return header;
	}

	public FlowPane getCenter() {

		//creates flowpane that lines objects up in a horizontal row
		FlowPane center = new FlowPane(Orientation.HORIZONTAL);
		center.setVgap(20);
		center.setHgap(20);

		//loops through every product
		for (Product p : m.dispenser.inventory.getProducts()) {

			//checks if product is a drink type
			if (p.getType() == 1) {

				//displays product data variables
				VBox productBox = new VBox();
				productBox.setSpacing(10);
				productBox.setPrefSize(100, 100);
				Label productName = new Label(p.getName()+": $"+p.getPrice());
				ImageView productImage = new ImageView(p.getImage());
			    Button productButton = new Button("Buy Item");

			    //buy product button event
			    productButton.setOnAction(new EventHandler<ActionEvent>() {
		            @Override
		            public void handle(ActionEvent event) {
		                //buy product
		            	if (m.dispenser.checkStock(p.getId())) {
		            		m.dispenser.buyItem(p.getId());
		            		m.animation.animate(productImage);
		            		//System.out.println(m.dispenser.inventory.getProduct(p.getId()).getStock());
		            	} else {
		            		//alerts user if product is out of stock
		            		Alert alert = new Alert(AlertType.ERROR);
		            		alert.setTitle("Error");
		            		alert.setHeaderText(null);
		            		alert.setContentText("Item is out of stock!");
		            		alert.showAndWait();
		            	}

		            }
		        });
			    //adds all nodes to productbox
				productBox.getChildren().addAll(productName, productImage, productButton);
				center.getChildren().add(productBox);
				productBox.setAlignment(Pos.CENTER);
			}
		}

		center.setAlignment(Pos.TOP_CENTER);


		//returns finished flowpane
		return center;
	}



}


