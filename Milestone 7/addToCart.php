<?php

    include 'session.php';
    include 'securePage.php';

    if(!isset($_SESSION['cart'])){

        $_SESSION['cart'] = array();
    }
    if (isset($_POST['id'], $_POST['quantity'])) {

        $productID = $_POST['id'];
        $quantity = $_POST['quantity'];

        if (!isset($_SESSION['cart'][$productID])) {
            $_SESSION['cart'][$productID] = 0;
        }


        if ($_SESSION['cart'][$productID] + $quantity <= 5) {
            $_SESSION['cart'][$productID] += $quantity;
            $addedToCart = "Successfully added product to cart.";
        } else {
            $addedToCart = "<div class='error'>The max quantity you can have of an item is 5.</div>";
        }




    } else {
        $addedToCart = "Error adding product to cart.";
    }


    include "viewProduct.php";

?>


