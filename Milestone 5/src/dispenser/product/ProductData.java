package dispenser.product;

import java.io.Serializable;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductData implements Serializable {
  public enum Type {
    SYSTEM, ENVIRONMENT
  }

  private StringProperty name;
  private DoubleProperty price;




  public ProductData(String name, double price) {
    this.name = new SimpleStringProperty(name);
    this.price= new SimpleDoubleProperty(price);
  }

  public final StringProperty nameProperty() {
    return this.name;
  }

  public final String getName() {
    return this.nameProperty().get();
  }

  public final void setName(String name) {
    this.nameProperty().set(name);
  }

  public final DoubleProperty priceProperty() {
    return this.price;
  }

  public final double getPrice() {
    return this.priceProperty().get();
  }

  public final void setPrice(double price) {
    this.priceProperty().set(price);
  }


}