
<?php




    include 'autoLoader.php';
    include 'securePage.php';

    //initialize connection object
    $connection = new Connection();
    $productOperations = new ProductOperations($connection);

    //load products into product object array
    $products = $productOperations->getProducts();

    //close db connection
    $connection->getConnection()->close();


    include "viewProducts.html";

?>





