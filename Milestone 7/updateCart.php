<?php

    include "session.php";

    if (isset($_POST['id'], $_POST['quantity'])) {

        $id = $_POST['id'];
        $quantity = $_POST['quantity'];

        if ($_POST['action']=="Update") {

            if ($quantity > 5) {
                $error = "The max quantity you can have of an item is 5.";
            } else if ($quantity > 0) {
                $_SESSION['cart'][$id] = $quantity;
                $operation = "Succesfully updated cart";
            }

        } else if ($_POST['action']=="Remove") {
            $operation = "Succesfully removed item from cart.";
            unset($_SESSION['cart'][$id]);
        }






    }


    include "viewCart.php";

?>






