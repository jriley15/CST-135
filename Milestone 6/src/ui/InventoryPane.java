package ui;

import java.util.ArrayList;

import dispenser.product.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InventoryPane {


	/**
	 * Jordan Riley
	 * This is my own work.
	 * CST-135
	 * 9/24/2017
	 *
	 *	Inventory UI class
	 *
	 */



	//main application reference var
	private Main m;

	//param vars for search/sort
	private String search;
	private boolean sortName;
	private boolean sortQuantity;


	//default constructor
	public InventoryPane(Main m, boolean sortName, boolean sortQuantity, String s) {
		this.m = m;
		this.search = s;
		this.sortName = sortName;
		this.sortQuantity = sortQuantity;
	}

	//returns vbox for header on application borderpane
	public VBox getHeader() {

		VBox header = new VBox();

		HBox navButtons = new HBox();
		navButtons.setSpacing(5);
	    Button back = new Button("< Back");

	    //button event for going back to main screen
	    back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                m.showMain();
            }
        });



	    navButtons.setAlignment(Pos.CENTER);
	    navButtons.getChildren().addAll(back);


		header.setSpacing(5);
		header.setPadding(new Insets(20));
		Label title = new Label("Inventory");
		title.setFont(Font.font("Verdana", FontWeight.NORMAL, 36));
		title.setTextFill(Paint.valueOf("#9354c3"));

		HBox sortButtons = new HBox();
		sortButtons.setSpacing(5);

		//nodes to be added to the hbox
	    Label instruction = new Label("Modify Product stock below, Sort by: ");
	    CheckBox name = new CheckBox("Name");
	    CheckBox quantity = new CheckBox("Quantity");
	    Button searchButton = new Button("Search");
	    TextField searchField = new TextField(search);
	    Button cancelButton = new Button("Cancel");

	    //set search/sort to passed in params
	    name.setSelected(sortName);
	    quantity.setSelected(sortQuantity);
	    searchField.setText(this.search);

	    //enter key press event on search field
	    searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {

	        @Override
	        public void handle(KeyEvent event) {
	            if(event.getCode().equals(KeyCode.ENTER)) {
	            	//calls main class to show inventory with search args
	            	m.showInventory(false, false, searchField.getText());
	            }
	        }
	    });

	    //name sort checkbox event
	    name.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (name.isSelected()) {
					//calls main class to show inventory with sort args
					m.dispenser.inventory.sortByName();
					m.showInventory(name.isSelected(), false, "");
				} else {
					//calls main class to show default inventory
					m.showInventory(false, false, "");
				}
			}
		});

	    //quantity sort checkbox event
	    quantity.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (quantity.isSelected()) {
					//calls main class to show inventory with search/sort args
					m.dispenser.inventory.sortByQuantity();
					name.setSelected(false);
					m.showInventory(false, quantity.isSelected(), "");
				} else {
					//calls main class to show default inventory
					m.showInventory(false, false, "");
				}
			}
		});
	    searchButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//calls main class to show inventory with search args
				m.showInventory(false, false, searchField.getText());
			}
		});
	    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//calls main class to show default inventory
				m.showInventory(false, false, "");
			}
		});


	    //adds nodes to hbox
	    sortButtons.getChildren().addAll(instruction, name, quantity, searchButton, searchField, cancelButton);
	    sortButtons.setAlignment(Pos.CENTER);
		header.getChildren().addAll(navButtons, title, sortButtons);
		header.setAlignment(Pos.CENTER);


		//returns final vbox
		return header;
	}



	public ScrollPane getCenter() {

		//create scroll pane instance
		ScrollPane scroll = new ScrollPane();

		scroll.setFitToWidth(true);

		FlowPane center = new FlowPane(Orientation.HORIZONTAL);
		center.setVgap(20);
		center.setHgap(20);
		center.setPrefWrapLength(400);

		//loop through products
		for (Product p : m.dispenser.inventory.getProducts()) {

			//if current product meets search criteria
			if (p.getName().toLowerCase().contains(search.toLowerCase())) {

				//load product data for display
				StringProperty stockValue = new SimpleStringProperty("Stock: "+p.getStock());
				VBox productBox = new VBox();
				productBox.setSpacing(10);
				productBox.setPrefSize(100, 100);
				Label productName = new Label(p.getName());
				ImageView productImage = new ImageView(p.getImage());
				Label productStock = new Label();
				HBox stockButtons = new HBox();
				stockButtons.setSpacing(10);
				Button addStock = new Button(" + ");

				//button for adding stock to product
				addStock.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//updates stock value
						m.dispenser.inventory.add(p.getId());
						stockValue.setValue("Stock: "+p.getStock());
					}
				});

				//button for removing stock from product
				Button removeStock = new Button(" - ");
				removeStock.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						//updates stock value
						m.dispenser.inventory.remove(p.getId());
						stockValue.setValue("Stock: "+p.getStock());

					}
				});
				productStock.textProperty().bind(stockValue);
				stockButtons.getChildren().addAll(addStock, removeStock);
				stockButtons.setAlignment(Pos.CENTER);

				productBox.getChildren().addAll(productName, productImage, productStock, stockButtons);
				center.getChildren().add(productBox);
				productBox.setAlignment(Pos.CENTER);
			}
		}

		center.setAlignment(Pos.CENTER);
		scroll.setContent(center);


		//returns finished scrollpane
		return scroll;
	}



}


