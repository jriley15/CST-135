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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {



	private Stage stage;
	private GridPane grid;
	private Dispenser dispenser;
	private Animation animation;


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

		grid = new GridPane();
		grid.setPrefSize(600, 400);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		Label title = new Label("Milestone Dispenser Machine");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));
	    grid.add(title, 3, 3);

	    Label instruction = new Label("Select a category below to view products for purchase");
	    grid.add(instruction, 3, 4);
	    GridPane.setHalignment(instruction, HPos.CENTER);


	    Button snacks = new Button("View Snacks");
	    snacks.setPrefSize(200, 30);
	    snacks.setTranslateX(20);
	    grid.add(snacks, 3, 8);
	    GridPane.setHalignment(snacks, HPos.LEFT);
	    snacks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSnacks();
            }
        });

		Image snackImage = new Image("images/snacks.png");
		ImageView imageView = new ImageView(snackImage);
		imageView.setTranslateX(30);
		grid.add(imageView, 3, 7);
		GridPane.setHalignment(imageView, HPos.LEFT);

	    Button drinks = new Button("View Drinks");
	    drinks.setPrefSize(200, 30);
	    drinks.setTranslateX(-20);
	    grid.add(drinks, 3, 8);
	    GridPane.setHalignment(drinks, HPos.RIGHT);
	    drinks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	showDrinks();
            }
        });

		Image drinkImage = new Image("images/drinks.png");
		ImageView imageView2 = new ImageView(drinkImage);
		imageView2.setTranslateX(-50);
		grid.add(imageView2, 3, 7);
		GridPane.setHalignment(imageView2, HPos.RIGHT);

	    Button inventory = new Button("View Inventory");
	    grid.add(inventory, 3, 11);
	    GridPane.setHalignment(inventory, HPos.CENTER);
	    inventory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	showInventory();
            }
        });


		stage.setTitle("Milestone Dispenser");
		stage.setScene(new Scene(grid));
		stage.show();

	}


	//view inventory pane
	public void showInventory() {

		grid = new GridPane();
		grid.setPrefSize(600, 400);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));


		Image chipsImage = new Image("images/chips.png");
		ImageView imageView1 = new ImageView(chipsImage);
		grid.add(imageView1, 1, 2);
		GridPane.setHalignment(imageView1, HPos.CENTER);

		Label stock1 = new Label(dispenser.inventory.itemCount(0)+"");
	    grid.add(stock1, 1, 2);
	    GridPane.setHalignment(stock1, HPos.RIGHT);

		Image candyImage = new Image("images/candy.png");
		ImageView imageView2 = new ImageView(candyImage);
		grid.add(imageView2, 8, 2);
		GridPane.setHalignment(imageView2, HPos.CENTER);

		Label stock2 = new Label(dispenser.inventory.itemCount(1)+"");
	    grid.add(stock2, 8, 2);
	    GridPane.setHalignment(stock2, HPos.RIGHT);

		Image gumImage = new Image("images/gum.png");
		ImageView imageView3 = new ImageView(gumImage);
		grid.add(imageView3, 12, 2);
		GridPane.setHalignment(imageView3, HPos.CENTER);

		Label stock3 = new Label(dispenser.inventory.itemCount(2)+"");
	    grid.add(stock3, 12, 2);
	    GridPane.setHalignment(stock3, HPos.RIGHT);

		Image waterImage = new Image("images/water.png");
		ImageView imageView6 = new ImageView(waterImage);
		grid.add(imageView6, 1, 3);
		GridPane.setHalignment(imageView6, HPos.CENTER);

		Label stock4 = new Label(dispenser.inventory.itemCount(3)+"");
	    grid.add(stock4, 1, 3);
	    GridPane.setHalignment(stock4, HPos.RIGHT);

		Image sodaImage = new Image("images/soda.png");
		ImageView imageView4 = new ImageView(sodaImage);
		grid.add(imageView4, 8, 3);
		GridPane.setHalignment(imageView4, HPos.CENTER);

		Label stock5 = new Label(dispenser.inventory.itemCount(4)+"");
	    grid.add(stock5, 8, 3);
	    GridPane.setHalignment(stock5, HPos.RIGHT);

		Image teaImage = new Image("images/tea.png");
		ImageView imageView5 = new ImageView(teaImage);
		grid.add(imageView5, 12, 3);
		GridPane.setHalignment(imageView5, HPos.CENTER);

		Label stock6 = new Label(dispenser.inventory.itemCount(5)+"");
	    grid.add(stock6, 12, 3);
	    GridPane.setHalignment(stock6, HPos.RIGHT);

		Label title = new Label("Inventory");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));
	    grid.add(title, 8, 1);
	    GridPane.setHalignment(title, HPos.CENTER);

	    Button back = new Button("< Back");
	    back.setPrefSize(57, 26);
	    back.setTranslateY(-10);
	    back.setTranslateX(-10);
	    grid.add(back, 1, 1);
	    back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMain();
            }
        });

		stage.setTitle("Milestone Dispenser");
		stage.setScene(new Scene(grid));
		stage.show();
	}

	//view receipt pane
	public void showReceipt() {

		grid = new GridPane();
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
		stage.show();

	}

	//show snacks pane
	public void showSnacks() {

		grid = new GridPane();
		grid.setPrefSize(600, 400);
		grid.setHgap(10);

		grid.setPadding(new Insets(0, 10, 0, 10));

		Image basketImage = new Image("images/basket.png");
		ImageView imageView4 = new ImageView(basketImage);
		grid.add(imageView4, 5, 8);
		GridPane.setHalignment(imageView4, HPos.CENTER);
		imageView4.setTranslateY(35);

	    Button back = new Button("< Back");
	    back.setPrefSize(57, 26);
	    back.setTranslateY(10);
	    back.setTranslateX(-20);
	    grid.add(back, 2, 2);
	    back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMain();
            }
        });

		Label title = new Label("Snacks");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));
	    grid.add(title, 5, 3);
	    GridPane.setHalignment(title, HPos.CENTER);

	    Label instruction1 = new Label("Select a product below that you wish to purchase");
	    grid.add(instruction1, 5, 4);
	    GridPane.setHalignment(instruction1, HPos.CENTER);

	    Label instruction2 = new Label("Select the back button to view other products");
	    grid.add(instruction2, 5, 5);
	    GridPane.setHalignment(instruction2, HPos.CENTER);

		Image chipsImage = new Image("images/chips.png");
		ImageView imageView1 = new ImageView(chipsImage);
		grid.add(imageView1, 2, 6);
		GridPane.setHalignment(imageView1, HPos.CENTER);
		imageView1.setTranslateY(10);

		Image candyImage = new Image("images/candy.png");
		ImageView imageView2 = new ImageView(candyImage);
		grid.add(imageView2, 5, 6);
		GridPane.setHalignment(imageView2, HPos.CENTER);
		imageView2.setTranslateY(10);

		Image gumImage = new Image("images/gum.png");
		ImageView imageView3 = new ImageView(gumImage);
		grid.add(imageView3, 6, 6);
		GridPane.setHalignment(imageView3, HPos.CENTER);
		imageView3.setTranslateY(10);

	    Button buyChips = new Button("Buy Chips - $5.00");
	    buyChips.setTranslateY(20);
	    grid.add(buyChips, 2, 7);
	    buyChips.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //buy chips
            	if (dispenser.checkStock(0)) {
            		dispenser.buyItem(0);
            		animation.animate(imageView1, new CubicCurveTo (50, 50, 240, 0, 240, 250), true);
            	} else {
            		Alert alert = new Alert(AlertType.ERROR);
            		alert.setTitle("Error");
            		alert.setHeaderText(null);
            		alert.setContentText("Item is out of stock!");
            		alert.showAndWait();
            	}

            }
        });

	    Button buyCandy = new Button("Buy Candy - $4.00");
	    buyCandy.setTranslateY(20);
	    grid.add(buyCandy, 5,7);
	    buyCandy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //buy chips
            	if (dispenser.checkStock(1)) {
            		dispenser.buyItem(1);
            		animation.animate(imageView2, new CubicCurveTo (55, 50, 55, 50, 50, 250), false);
            	} else {
            		Alert alert = new Alert(AlertType.ERROR);
            		alert.setTitle("Error");
            		alert.setHeaderText(null);
            		alert.setContentText("Item is out of stock!");
            		alert.showAndWait();
            	}

            }
        });
		GridPane.setHalignment(buyCandy, HPos.CENTER);

	    Button buyGum = new Button("Buy Gum - $2.00");
	    buyGum.setTranslateY(20);
	    grid.add(buyGum, 6,7);
	    buyGum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //buy chips
            	if (dispenser.checkStock(2)) {
            		dispenser.buyItem(2);
            		animation.animate(imageView3, new CubicCurveTo (55, 50, 55, 50, -150, 250), false);
            	} else {
            		Alert alert = new Alert(AlertType.ERROR);
            		alert.setTitle("Error");
            		alert.setHeaderText(null);
            		alert.setContentText("Item is out of stock!");
            		alert.showAndWait();
            	}

            }
        });
		GridPane.setHalignment(buyGum, HPos.CENTER);

	    Button receipt = new Button("Receipt >");
	    receipt.setTranslateY(10);
	    receipt.setTranslateX(80);
	    grid.add(receipt, 6, 2);
	    receipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showReceipt();
            }
        });




	    //grid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");

		stage.setTitle("Milestone Dispenser");
		stage.setScene(new Scene(grid));
		stage.show();

	}


	//show drinks pane
	public void showDrinks() {

		grid = new GridPane();
		grid.setPrefSize(600, 400);
		grid.setHgap(5);

		grid.setPadding(new Insets(0, 10, 0, 10));

		Image basketImage = new Image("images/basket.png");
		ImageView imageView4 = new ImageView(basketImage);
		grid.add(imageView4, 5, 8);
		GridPane.setHalignment(imageView4, HPos.CENTER);
		imageView4.setTranslateY(25);

	    Button back = new Button("< Back");
	    back.setPrefSize(57, 26);
	    back.setTranslateY(10);
	    back.setTranslateX(-10);
	    grid.add(back, 2, 2);
	    back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMain();
            }
        });

		Label title = new Label("Drinks");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));
	    grid.add(title, 5, 3);
	    GridPane.setHalignment(title, HPos.CENTER);

	    Label instruction1 = new Label("Select a product below that you wish to purchase");
	    grid.add(instruction1, 5, 4);
	    GridPane.setHalignment(instruction1, HPos.CENTER);

	    Label instruction2 = new Label("Select the back button to view other products");
	    grid.add(instruction2, 5, 5);
	    GridPane.setHalignment(instruction2, HPos.CENTER);

		Image waterImage = new Image("images/water.png");
		ImageView imageView1 = new ImageView(waterImage);
		grid.add(imageView1, 2, 6);
		GridPane.setHalignment(imageView1, HPos.CENTER);
		imageView1.setTranslateY(10);

		Image sodaImage = new Image("images/soda.png");
		ImageView imageView2 = new ImageView(sodaImage);
		grid.add(imageView2, 5, 6);
		GridPane.setHalignment(imageView2, HPos.CENTER);
		imageView2.setTranslateY(10);

		Image teaImage = new Image("images/tea.png");
		ImageView imageView3 = new ImageView(teaImage);
		grid.add(imageView3, 6, 6);
		GridPane.setHalignment(imageView3, HPos.CENTER);
		imageView3.setTranslateY(10);

	    Button buyWater = new Button("Buy Water - $2.00");
	    buyWater.setTranslateY(20);
	    grid.add(buyWater, 2, 7);
	    buyWater.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //buy water
            	if (dispenser.checkStock(3)) {
            		dispenser.buyItem(3);
            		animation.animate(imageView1, new CubicCurveTo (50, 50, 240, 0, 240, 250), true);
            	} else {
            		Alert alert = new Alert(AlertType.ERROR);
            		alert.setTitle("Error");
            		alert.setHeaderText(null);
            		alert.setContentText("Item is out of stock!");
            		alert.showAndWait();
            	}

            }
        });

	    Button buySoda = new Button("Buy Soda - $4.00");
	    buySoda.setTranslateY(20);
	    grid.add(buySoda, 5,7);
	    buySoda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //buy soda
            	if (dispenser.checkStock(4)) {
            		dispenser.buyItem(4);
            		animation.animate(imageView2, new CubicCurveTo (55, 50, 55, 50, 50, 250), true);
            	} else {
            		Alert alert = new Alert(AlertType.ERROR);
            		alert.setTitle("Error");
            		alert.setHeaderText(null);
            		alert.setContentText("Item is out of stock!");
            		alert.showAndWait();
            	}

            }
        });
		GridPane.setHalignment(buySoda, HPos.CENTER);

	    Button buyTea = new Button("Buy Tea - $3.00");
	    buyTea.setTranslateY(20);
	    grid.add(buyTea, 6,7);
	    buyTea.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //buy chips
            	if (dispenser.checkStock(5)) {
            		dispenser.buyItem(5);
            		animation.animate(imageView3, new CubicCurveTo (55, 50, -150, 50, -150, 250), true);
            	} else {
            		Alert alert = new Alert(AlertType.ERROR);
            		alert.setTitle("Error");
            		alert.setHeaderText(null);
            		alert.setContentText("Item is out of stock!");
            		alert.showAndWait();
            	}

            }
        });
		GridPane.setHalignment(buyTea, HPos.CENTER);

	    Button receipt = new Button("Receipt >");
	    receipt.setTranslateY(10);
	    receipt.setTranslateX(80);
	    grid.add(receipt, 6, 2);
	    receipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showReceipt();
            }
        });




	    //grid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");

		stage.setTitle("Milestone Dispenser");
		stage.setScene(new Scene(grid));
		stage.show();

	}


}
