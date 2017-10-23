package application;


import java.awt.Image;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller implements Initializable{


	@FXML public TableView<ProductData> table;
	@FXML public TableColumn<ProductData, String> name;
	@FXML public TableColumn<ProductData, Double> price;
	@FXML public Label totalLabel;


	@Override
	public void initialize(URL location, ResourceBundle resources){
		if (location.toString().endsWith("Checkout.fxml")) {
			//data = FXCollections.observableArrayList();
			table.getItems().clear();
			table.getColumns().clear();
			name.setCellValueFactory(new PropertyValueFactory("name"));
			price.setCellValueFactory(new PropertyValueFactory("price")); // "name" here is for just to render the column

	        table.getColumns().addAll(name, price);

			for (Product p : Main.products) {

				ProductData pd = new ProductData(p.getName(), p.getPrice());
				table.getItems().add(pd);


			}
			DecimalFormat df = new DecimalFormat("0.00");
			totalLabel.setText("Total: $"+df.format(Main.transactionHandler.getMoneyProcessed()));
		}



	}


	public void snackPress() {
		Main.showSnacks();
	}

	public void drinkPress() {
		Main.showDrinks();
	}

	public void backPress() {
		Main.showMain();
	}


	public void donePress() {
		Main.showCheckout();

	}

	public void alert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(null);
		alert.setContentText("Succesfully purchased item,\nClick done to see final prices.");
		alert.showAndWait();
	}

	public void buyWater() {
		Main.products.add(new Drink("Water", 2.00, -1, "", -1, null, -1));
		Main.transactionHandler.process(2.00);
		alert();
	}
	public void buySoda() {
		Main.products.add(new Drink("Soda", 4.00, -1, "", -1, null, -1));
		Main.transactionHandler.process(4.00);
		alert();
	}
	public void buyTea() {
		Main.products.add(new Drink("Tea", 3.00, -1, "", -1, null, -1));
		Main.transactionHandler.process(3.00);
		alert();
	}

	public void buyChips() {
		Main.products.add(new Chips("Chips", 5.00, -1, "", -1, null, false, false));
		Main.transactionHandler.process(5.00);
		alert();
	}
	public void buyCandy() {
		Main.products.add(new Chips("Candy", 4.00, -1, "", -1, null, false, false));
		Main.transactionHandler.process(4.00);
		alert();
	}
	public void buyGum() {
		Main.products.add(new Chips("Gum", 2.00, -1, "", -1, null, false, false));
		Main.transactionHandler.process(2.00);
		alert();
	}



}
