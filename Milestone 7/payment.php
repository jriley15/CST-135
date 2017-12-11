<?php



include 'autoLoader.php';
include 'securePage.php';


    if(isset($_SESSION['cart'])){


        //check if payment info is valid
        //submit order form to database



        unset($_SESSION['cart']);
        $msg = "<div class='op'>Purchase complete!</div><br><br>";

        include "index.php";


    }
