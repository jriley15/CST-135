<?php


class ProductOperations
{

    private $connection;

    function __construct($connection)
    {
        $this->connection = $connection;
    }

    function add($product) {
        return $result = $this->connection->getConnection()->query("INSERT INTO products (type, name, description, price)
                  VALUES ('" . $product->getType() . "', '" . $product->getName() . "', '" . $product->getDescription() . "', 
                  '" . $product->getPrice() . "')");
    }

    function update($product) {

        return $this->connection->getConnection()->query("UPDATE products SET type='".$product->getType()."', name='".$product->getName()."', 
        description='".$product->getDescription()."', price='".$product->getPrice()."' WHERE ID = '".$product->getID()."'");
    }

    function delete($product) {

        $image = "css/images/products/".$product->getID().".png";
        if (file_exists($image)) {
            unlink($image);
        }

        return $this->connection->getConnection()->query("DELETE FROM products WHERE ID = '".$product->getID()."'");
    }

    function getProducts() {
        $products = array();
        $result = $this->connection->getConnection()->query("SELECT * FROM products");

        while($row = mysqli_fetch_array($result)){
            $products[] = new Product($row["ID"], $row["type"],$row["name"],$row["description"],$row["price"]);
        }
        return $products;
    }


    function searchProducts($search) {
        $products = array();

        if (empty($search)) {
            return $products;
        }
        $result = $this->connection->getConnection()->query("SELECT * FROM products WHERE name OR description LIKE '%".$search."%' LIMIT 20");


        while($row = mysqli_fetch_array($result)){
            $products[] = new Product($row["ID"], $row["type"],$row["name"],$row["description"],$row["price"]);
        }
        return $products;
    }

    function getProduct($id){

        return $this->connection->getConnection()->query("SELECT * FROM products WHERE ID = '" . $id . "'");
    }

    function getProductAsProduct($id){

        $result = $this->connection->getConnection()->query("SELECT * FROM products  WHERE ID = '" . $id . "'");
        $row = mysqli_fetch_array($result);

        return new Product($row["ID"], $row["type"],$row["name"],$row["description"],$row["price"]);
    }

    function getProductCount() {
        $result = $this->connection->getConnection()->query("SELECT MAX(ID) as max_id FROM products");
        $row = mysqli_fetch_array($result);
        return $row["max_id"];

    }


}