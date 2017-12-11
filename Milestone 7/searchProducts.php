<?php
    include "autoLoader.php";
    include 'securePage.php';


    $connection = new Connection();
    $productOperations = new ProductOperations($connection);


    if (isset($_GET['search'])) {
        $search = $_GET['search'];
        $products = $productOperations->searchProducts($search);
    }


    $connection->getConnection()->close();

    include "viewProducts.html";

?>

