package ui;


import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JOptionPane;


import dispenser.Dispenser;
import dispenser.product.Product;
import dispenser.product.ProductData;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import simulation.CustomerQueuePane;
import simulation.ProcessCustomerQueue;
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



	//member vars
	private Stage stage;
	private BorderPane pane;
	private Dispenser dispenser;
	private Animation animation;
	private ProcessCustomerQueue customerProcess;
	public static final int LOW_STOCK = 5;
	public static final int BASE_STOCK = 10;
	private static boolean simulationMode = false;

	//initializes application
	@Override
	public void start(Stage primaryStage) {

		//instantiates class member variables
		stage = primaryStage;
		setDispenser(new Dispenser());
		setAnimation(new Animation());

		//stops child threads of app when exited
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

		//check if simulation mode active
		if (simulationMode) {
			customerProcess = new ProcessCustomerQueue(this);
			showCustomerQueue();
		} else {
			//sets default screen
			showMain();
		}


	}

	//main function
	public static void main(String[] args) {

		//check if args were passed in for simulation mode
		try {
			if (Boolean.parseBoolean(args[0])) {
				simulationMode = true;
			}
		} catch (Exception e) {

		}

		launch(args);

	}

	//view simulation pane
	public void showCustomerQueue() {
		pane = new BorderPane();
		pane.setPrefSize(800, 500);
		pane.setPadding(new Insets(0, 10, 0, 10));

		CustomerQueuePane customerInterface = new CustomerQueuePane(this);
		this.getCustomerProcess().setCustomerPane(customerInterface);


		pane.setTop(customerInterface.getHeader());

	    pane.setCenter(customerInterface.getCenter());


		stage.setScene(new Scene(pane));
		stage.show();

		this.getCustomerProcess().start();

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
	    Button admin = new Button("Admin Panel");
	    admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	showInventory(false, false, "", false);
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
	public void showInventory(boolean sortName, boolean sortQuantity, String search, boolean restock) {
		pane = new BorderPane();
		pane.setPrefSize(800, 500);
		pane.setPadding(new Insets(0, 10, 0, 10));

		InventoryPane inventoryInterface = new InventoryPane(this, sortName, sortQuantity, search, restock);

		pane.setTop(inventoryInterface.getHeader());

	    pane.setCenter(inventoryInterface.getCenter());


		stage.setScene(new Scene(pane));
		stage.show();

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

	public String formatMoney(double value) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		return nf.format(value);
	}


	public Dispenser getDispenser() {
		return dispenser;
	}

	public void setDispenser(Dispenser dispenser) {
		this.dispenser = dispenser;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public ProcessCustomerQueue getCustomerProcess() {
		return customerProcess;
	}


}
