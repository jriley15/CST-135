package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {

	public static Stage stage;
	public static GridPane mainLayout;
	public static ArrayList<Product> products;
	public static TransactionHandler transactionHandler;


	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		showMain();
	}


	public static void main(String[] args) {
		products = new ArrayList<Product>();
		transactionHandler = new TransactionHandler();
		launch(args);
	}



	public static void showMain() {
		try {
			mainLayout = FXMLLoader.load(Main.class.getResource("MainScreen.fxml"));
			stage.setTitle("Dispenser GUI");
			stage.setScene(new Scene(mainLayout));
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void showSnacks() {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Snacks.fxml"));
			GridPane snacks = loader.load();
			mainLayout = snacks;
			stage.setScene(new Scene(mainLayout));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void showDrinks() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Drinks.fxml"));
			GridPane drinks = loader.load();
			mainLayout = drinks;
			stage.setScene(new Scene(mainLayout));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void showCheckout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Checkout.fxml"));
			GridPane checkout = loader.load();
			mainLayout = checkout;
			stage.setScene(new Scene(mainLayout));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
