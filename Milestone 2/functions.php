<?php


    function fetchAccount($connection, $email, $pass) {

        return $connection->getConnection()->query("SELECT * FROM accounts WHERE emailAddress = '" . $email . "' AND password = '" . $pass . "'");

    }

    function createAccount($connection, $email, $pass, $firstName, $lastName) {
        return $connection->getConnection()->query("INSERT INTO accounts (emailAddress, password, firstName, lastName)
                  VALUES ('" . $email . "', '" . $pass . "', '" . $firstName . "', '" . $lastName . "')");
    }

    function getProducts($connection){
        $products = array();
        $result = $connection->getConnection()->query("SELECT * FROM products");

        while($row = mysqli_fetch_array($result)){
            $products[] = $row;
        }
        $connection->getConnection()->close();
        return $products;
    }
?>