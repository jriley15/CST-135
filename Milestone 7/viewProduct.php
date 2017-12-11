<?php


    include 'autoLoader.php';
    include 'securePage.php';


    if (isset($_GET["id"])) {

        $id = $_GET["id"];

        $connection = new Connection();
        $productOperations = new ProductOperations($connection);


        $product_data = mysqli_fetch_array($productOperations->getProduct($id));

        $product = new Product($product_data['ID'], $product_data['type'], $product_data['name'], $product_data['description'],
            $product_data['price']);


        $connection->getConnection()->close();

    } else if (isset($addedToCart)) {

        $connection = new Connection();
        $productOperations = new ProductOperations($connection);

        $product_data = mysqli_fetch_array($productOperations->getProduct($productID));

        $product = new Product($product_data['ID'], $product_data['type'], $product_data['name'], $product_data['description'],
            $product_data['price']);


        $connection->getConnection()->close();
    }


    include "viewProduct.html";


?>


