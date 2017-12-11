<?php


class AccountOperations
{

    private $connection;

    function __construct($connection)
    {
        $this->connection = $connection;

    }


    function update($account) {

        return $this->connection->getConnection()->query("UPDATE accounts SET emailAddress='".$account->getEmail()."', password='".$account->getPassword()."', 
        firstName='".$account->getFirstname()."', lastName='".$account->getLastname()."', rights='".$account->getRights()."',ccNumber='".$account->getCc()->getNumber()."', 
        ccName='".$account->getCc()->getName()."', ccExpiration='".$account->getCc()->getExpirationDate()."', ccCCV='".$account->getCc()->getCcv()."' WHERE ID = '".$account->getUserId()."'");


    }

    function getAccount($id) {
        return $this->connection->getConnection()->query("SELECT * FROM accounts WHERE ID = '" . $id . "'");

    }
    function getAccountAsAccount($id) {
        $result = $this->connection->getConnection()->query("SELECT * FROM accounts WHERE ID = '" . $id . "'");
        $row = mysqli_fetch_array($result);
        $cc = new CreditCard($row["ccName"], $row["ccNumber"], $row["ccExpiration"], $row["ccCCV"]);
        return new Account($row["ID"], $row["emailAddress"], $row["password"], $row["firstName"], $row["lastName"], $row["rights"], $cc);
    }

    function accountExists($email) {
        return $this->connection->getConnection()->query("SELECT * FROM accounts WHERE emailAddress = '" . $email . "'");

    }

    function fetchAccount($email, $pass) {
        return $this->connection->getConnection()->query("SELECT * FROM accounts WHERE emailAddress = '" . $email . "' AND password = '" . $pass . "'");
    }


    
    function createAccount($email, $pass, $firstName, $lastName, $rights, $ccname, $ccnumber, $ccdate, $ccCCV) {
        return $this->connection->getConnection()->query("INSERT INTO accounts (emailAddress, password, firstName, lastName, rights, ccNumber, ccName, ccExpiration, ccCCV)
                  VALUES ('" . $email . "', '" . $pass . "', '" . $firstName . "', '" . $lastName . "', '" . $rights . "', '" . $ccname . "', '" . $ccnumber . "', '" . $ccdate . "', '" . $ccCCV . "')");
    }

    function getAccounts(){
        $accounts = array();
        $result = $this->connection->getConnection()->query("SELECT * FROM accounts");

        while($row = mysqli_fetch_array($result)){
            $cc = new CreditCard($row["ccName"], $row["ccNumber"], $row["ccExpiration"], $row["ccCCV"]);
            $accounts[] =  new Account($row["ID"], $row["emailAddress"], $row["password"], $row["firstName"], $row["lastName"], $row["rights"], $cc);

        }
        return $accounts;
    }

    function checkLoginFields($email, $password) {

        $errors = array();
        if (empty($email) || empty($password)) {
            $errors[] = "Complete all required fields.";
        }
        if (strlen($email) > 30) {
            $errors[] = "Email can only be 30 characters long.";
        }
        if (strlen($password) > 15) {
            $errors[] = "Password can only be 15 characters long.";
        }

        return $errors;
    }

    function verifyLogin($email, $password) {
        $response = $this->fetchAccount($email, $password);
        $rows = $response->num_rows;
        //if account with user and pass exists
        if ($rows > 0) {
            return true;
        }
        return false;
    }

    function checkRegisterFields($email, $password,$password2, $firstName, $lastName) {

        $errors = array();
        //check submitted variable lengths and password matches
        if (empty($email) || empty($password)
            || empty($firstName) || empty($lastName)
        ) {
            $errors[] = "Complete all required fields.";
        }
        if (strlen($email) > 30) {
            $errors[] = "Email can only be 30 characters long.";
        }
        if (strlen($password) > 15) {
            $errors[] = "Password can only be 15 characters long.";
        }
        if ($password != $password2) {
            $errors[] = "Passwords must match.";
        }
        return $errors;
    }

    function verifyRegister($email) {

        $response = $this->accountExists($email);
        $rows = $response->num_rows;
        //if account with email already exists
        if ($rows == 0) {
            return true;
        }
        return false;


    }






}