package ui;


import java.text.DecimalFormat;

import javax.swing.JOptionPane;


import dispenser.Dispenser;
import dispenser.product.Product;
import dispenser.product.ProductData;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {



	private Stage stage;
	private BorderPane pane;
	public Dispenser dispenser;
	public Animation animation;


	//initializes application
	@Override
	public void start(Stage primaryStage) {

		//instantiates class member variables
		stage = primaryStage;
		dispenser = new Dispenser();
		animation = new Animation();

		//sets default screen
		showMain();
	}

	//main function
	public static void main(String[] args) {
		launch(args);
	}

	//main screen pane
	public void showMain() {

		//creates borderpane
		pane = new BorderPane();
		pane.setPrefSize(800, 500);
		pane.setPadding(new Insets(0, 10, 0, 10));

		//header vbox for main screen
		VBox header = new VBox();
		header.setSpacing(10);
		header.setPadding(new Insets(50));
		Label title = new Label("Milestone Dispenser Machine");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));
	    Label instruction = new Label("Select a category below to view products for purchase");
		header.getChildren().addAll(title, instruction);
		pane.setTop(header);
		header.setAlignment(Pos.CENTER);


		FlowPane center = new FlowPane(Orientation.HORIZONTAL);
		center.setVgap(20);
		center.setHgap(50);

		center.setPrefWrapLength(400);
		center.setMaxWidth(500);
		center.setAlignment(Pos.TOP_CENTER);
		Image snackImage = new Image("images/snacks.png");
		ImageView imageView = new ImageView(snackImage);
	    Button snacks = new Button("View Snacks");
	    snacks.setPrefSize(200, 30);

	    //snack view button event
	    snacks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSnacks();
            }
        });

		Image drinkImage = new Image("images/drinks.png");
		ImageView imageView2 = new ImageView(drinkImage);
	    Button drinks = new Button("View Drinks");
	    drinks.setPrefSize(200, 30);

	    //drink view button event
	    drinks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	showDrinks();
            }
        });

	    //inventory view button event
	    Button admin = new Button("View Inventory");
	    admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	showInventory(false, false, "");
            }
        });

	    //adds all nodes to flowpane
	    center.getChildren().addAll(imageView, imageView2, snacks, drinks, admin);
	    pane.setCenter(center);

		stage.setTitle("Milestone Dispenser");
		stage.setScene(new Scene(pane));
		stage.show();


	}


	//view inventory pane
	public void showInventory(boolean sortName, boolean sortQuantity, String search) {
		pane = new BorderPane();
		pane.setPrefSize(800, 500);
		pane.setPadding(new Insets(0, 10, 0, 10));

		InventoryPane inventoryInterface = new InventoryPane(this, sortName, sortQuantity, search);

		pane.setTop(inventoryInterface.getHeader());

	    pane.setCenter(inventoryInterface.getCenter());


		stage.setScene(new Scene(pane));
		stage.show();

	}

	//view receipt pane
	public void showReceipt() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Module not added yet!");
		alert.showAndWait();


		/*grid = new GridPane();
		grid.setPrefSize(600, 400);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

	    Button back = new Button("< Back");
	    back.setPrefSize(57, 26);
	    back.setTranslateX(-10);
	    grid.add(back, 1, 1);
	    back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMain();
            }
        });

		Label title = new Label("Purchased Items");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));
	    grid.add(title, 7, 2);
	    GridPane.setHalignment(title, HPos.CENTER);

		TableView table = new TableView();
		table.setPrefSize(325, 158);
		TableColumn name = new TableColumn("Product");
		name.setPrefWidth(236);
        TableColumn price = new TableColumn("Price");
        price.setPrefWidth(85);
		name.setCellValueFactory(new PropertyValueFactory("name"));
		price.setCellValueFactory(new PropertyValueFactory("price"));
        table.getColumns().addAll(name, price);
		for (Product p : dispenser.getPurchasedProducts()) {
			ProductData pd = new ProductData(p.getName(), p.getPrice());
			table.getItems().add(pd);
		}
		grid.add(table, 7, 3);



		DecimalFormat df = new DecimalFormat("0.00");

		Label total = new Label("Total: $"+df.format(dispenser.transactions.getMoneyProcessed()));
		total.setFont(Font.font("Verdana", FontWeight.NORMAL, 24));
		total.setTextFill(Paint.valueOf("#85bb65"));
	    grid.add(total, 7, 4);
	    GridPane.setHalignment(total, HPos.CENTER);


		stage.setTitle("Milestone Dispenser");
		stage.setScene(new Scene(grid));
		stage.show();*/

	}

	//show snacks pane
	public void showSnacks() {
		pane = new BorderPane();
		pane.setPrefSize(800, 500);
		pane.setPadding(new Insets(0, 10, 0, 10));

		SnackPane snackInterface = new SnackPane(this);

		pane.setTop(snackInterface.getHeader());

	    pane.setCenter(snackInterface.getCenter());


		stage.setScene(new Scene(pane));
		stage.show();

	}


	//show drinks pane
	public void showDrinks() {

		pane = new BorderPane();
		pane.setPrefSize(800, 500);
		pane.setPadding(new Insets(0, 10, 0, 10));

		DrinkPane drinkInterface = new DrinkPane(this);

		pane.setTop(drinkInterface.getHeader());

	    pane.setCenter(drinkInterface.getCenter());


		stage.setScene(new Scene(pane));
		stage.show();


	}


}
