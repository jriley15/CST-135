<?php
    /**
     *  Milestone
     *  Register Page
     *  Jordan Riley and Antonio Jabrail
     *  9-9-2017
     */
    include "autoLoader.php";

    //checks for session variables
    if (isset($_POST["email"]) && isset($_POST["password"])) {

        //instantiates new variables with passed in post vars trimmed
        $email = trim($_POST['email']);
        $password = trim($_POST['password']);
        $password2 = trim($_POST['password2']);
        $firstName = trim($_POST['firstname']);
        $lastName = trim($_POST['lastname']);
        $rights = 0;
        $ccNumber = trim($_POST['ccNumber']);
        $ccName = trim($_POST['ccName']);
        $ccExpiration = trim($_POST['ccExpirationDate']);
        $ccCCV = trim($_POST['ccCCV']);

        //new db connection instance
        $connection = new Connection();

        //new account operations instance
        $accountOperations = new AccountOperations($connection);

        //check if connection worked
        if (!$connection->getConnection()->connect_error) {

            //instantiating error array
            $errors = array();

            //registration field checks
            $errors = $accountOperations->checkRegisterFields($email, $password, $password2, $firstName, $lastName);

            //check if errors
            if (empty($errors)) {

                //check if account with email already exists
                if (!$accountOperations->verifyRegister($email)) {
                    $errors[] = "Email already in use.";
                    include('registerForm.html');

                } else {

                    //mysql query to insert data into db and create new account
                    $result = $accountOperations->createAccount($email, $password, $firstName, $lastName, 0, $ccName, $ccNumber, $ccExpiration, $ccCCV);


                    //check if query worked
                    if ($result) {

                        //registration successful
                        $_SESSION["principle"] = true;
                        $_SESSION["userID"] = mysqli_insert_id($connection->getConnection());
                        $_SESSION["rights"] = 0;

                        $welcome = "You have been succesfully registered, welcome ".$firstName.".";
                        include 'index.php';

                    } else {

                        //back to form with db error
                        $errors[] = "Database connection error.";
                        include('registerForm.html');
                    }
                }

            } else {
                //back to form with errors
                include('registerForm.html');
            }


        } else {
            //back to form with errors
            $errors[] = "DB error.";
            include('registerForm.html');
        }
        $connection->getConnection()->close();
    } else {
        //show register form
        require_once "registerForm.html";
    }








?>