<?php


    include 'autoLoader.php';
    include 'securePage.php';


    if(isset($_SESSION['cart'])){


        $connection = new Connection();
        $productOperations = new ProductOperations($connection);
        $cart = new ShoppingCart($productOperations);
        $cart_items = $cart->getCartItems();

        $connection->getConnection()->close();
    }


    include "viewCart.html";


?>


