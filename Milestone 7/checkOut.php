<?php



include 'autoLoader.php';
include 'securePage.php';


    if(isset($_SESSION['cart'])){


        $connection = new Connection();
        $productOperations = new ProductOperations($connection);
        $accountOperations = new AccountOperations($connection);


        //load cart items into array
        $cart = new ShoppingCart($productOperations);
        $cart_items = $cart->getCartItems();

        //load account info into object
        $account = $accountOperations->getAccountAsAccount($_SESSION['userID']);


        //close connection
        $connection->getConnection()->close();


    }

    include "checkOut.html";



