package simulation;

import dispenser.product.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ui.Main;

public class CustomerQueuePane {

	/**
	 * Jordan Riley
	 * This is my own work.
	 * CST-135
	 * 9/24/2017
	 *
	 *	Customer Simulation UI class
	 *
	 *
	 */

	//main application reference var
	private Main main;


	//string properties for updating text
	public StringProperty customerName = new SimpleStringProperty("");
	public StringProperty customerSelection = new SimpleStringProperty("");
	public ImageView productImage = new ImageView();
	public StringProperty productPrice = new SimpleStringProperty("");
	public StringProperty timeLeft = new SimpleStringProperty("");

	//default constructor
	public CustomerQueuePane(Main m) {
		this.main = m;
	}


	//returns vbox for header on application borderpane
	public VBox getHeader() {

		VBox header = new VBox();

		HBox navButtons = new HBox();
		navButtons.setSpacing(20);
	    navButtons.setAlignment(Pos.CENTER);

	    //header labels
		header.setSpacing(10);
		header.setPadding(new Insets(50));
		Label title = new Label("Customer Queue Simulation");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));



		header.getChildren().addAll(navButtons, title);
		header.setAlignment(Pos.CENTER);

		return header;
	}




	public FlowPane getCenter() {

		//creates flowpane that lines objects up in a vertical row
		FlowPane center = new FlowPane(Orientation.VERTICAL);
		center.setVgap(20);
		center.setHgap(20);


		VBox customerData = new VBox();
		customerData.setSpacing(20);


		//labels for displaying customer data
	    Label name = new Label("");
	    name.setFont(Font.font("Verdana", FontWeight.NORMAL, 24));
	    Label selection = new Label("");
	    selection.setFont(Font.font("Verdana", FontWeight.NORMAL, 24));
	    ImageView image = productImage;
	    Label price = new Label("");
	    price.setFont(Font.font("Verdana", FontWeight.NORMAL, 24));
	    price.setTextFill(Color.GREEN);
	    Label time = new Label("");
	    price.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

	    customerData.getChildren().addAll(name, selection, image, price, time);

	    name.textProperty().bind(customerName);
	    selection.textProperty().bind(customerSelection);
	    price.textProperty().bind(productPrice);
	    time.textProperty().bind(timeLeft);

	    customerData.setAlignment(Pos.CENTER);

	    center.getChildren().addAll(customerData);


		center.setAlignment(Pos.TOP_CENTER);


		//returns finished flowpane
		return center;
	}




}


