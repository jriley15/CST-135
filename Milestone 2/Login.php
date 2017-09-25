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

        //creates new account instance with submitted session variables
        $account = new Account($_POST["email"], $_POST["password"], "", "");

        //instantiating error array
        $errors = array();

        //new db connection instance
        $connection = new Connection();

        //check if connection worked
        if (!$connection->getConnection()->connect_error) {

            //email and username length checks
            if (empty($account->getEmail()) || empty($account->getPassword())) {
                $errors[] = "Complete all required fields.";
            }
            if (strlen($account->getEmail()) > 30) {
                $errors[] = "Email can only be 30 characters long.";
            }
            if (strlen($account->getPassword()) > 15) {
                $errors[] = "Password can only be 15 characters long.";
            }

            //if no errors
            if (empty($errors)) {
                //$response = $connection->getConnection()->query("SELECT * FROM accounts WHERE emailAddress = '" . $account->getEmail() . "' AND password = '" . $account->getPassword() . "'");

                $response = fetchAccount($connection, $account->getEmail(), $account->getPassword());
                $rows = $response->num_rows;

                //if account with user and pass exists
                if ($rows > 0) {
                    $account->setUserId(mysqli_fetch_array($response)['ID']);
                    //echo "Successfully logged in as: " . $account->getEmail();
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