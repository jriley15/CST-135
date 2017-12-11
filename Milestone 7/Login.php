<?php
    /**
     *  Milestone
     *  Login Page
     *  Jordan Riley and Antonio Jabrail
     *  9-9-2017
     */

    include "autoLoader.php";


    //checks for session variables
    if (isset($_POST["email"], $_POST["password"])) {

        //instantiates new variables with passed in post vars trimmed
        $email = trim($_POST["email"]);
        $password = trim($_POST["password"]);

        //new db connection instance
        $connection = new Connection();

        //new account operations instance
        $accountOperations = new AccountOperations($connection);

        //check if connection worked
        if (!$connection->getConnection()->connect_error) {

            $errors = array();

            //email and username length checks
            $errors = $accountOperations->checkLoginFields($email, $password);

            //if no errors
            if (empty($errors)) {


                //if account with user and pass exists
                if ($accountOperations->verifyLogin($email, $password)) {
                    $row = mysqli_fetch_array($accountOperations->fetchAccount($email, $password));

                    $_SESSION["rights"] = $row["rights"];
                    $_SESSION["userID"] = $row["ID"];

                    include 'loginSuccess.php';

                } else {
                    include 'loginFailed.php';
                }
            } else {
                //back to form with errors
                include('loginForm.html');
            }

        } else {
            //back to form with errors
            $errors[] = "Connection error";
            include('loginForm.html');

        }
        $connection->getConnection()->close();
    } else {
        //show login form
        require_once "loginForm.html";
    }


?>