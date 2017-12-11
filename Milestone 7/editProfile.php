<?php
include "autoLoader.php";
include 'securePage.php';

if (isset($_SESSION["principle"])) {


        $connection = new Connection();

        $accountOperations = new AccountOperations($connection);

        if (isset($_POST['userID']) && $_POST['userID'] == $_SESSION['userID']) {

            $userID = $_POST['userID'];
            $email = $_POST['email'];
            $password = $_POST['password'];
            $firstName = $_POST['firstName'];
            $lastName = $_POST['lastName'];
            $rights = $_POST['rights'];
            $ccNumber = $_POST['ccNumber'];
            $ccName = $_POST['ccName'];
            $ccExpiration = $_POST['ccExpiration'];
            $ccCCV = $_POST['ccCCV'];

            $updateCard = new CreditCard($ccName, $ccNumber, $ccExpiration, $ccCCV);
            $updateAccount = new Account($userID, $email, $password, $firstName, $lastName, $rights, $updateCard);


            if ($accountOperations->update($updateAccount)) {
                $operation = "Successfully updated profile.";
            } else {
                $operation = "Error updating profile.";
            }

        }

        $result = $accountOperations->getAccount($_SESSION["userID"]);
        $account_data = mysqli_fetch_array($result);
        $cc = new CreditCard($account_data["ccName"], $account_data["ccNumber"], $account_data["ccExpiration"], $account_data["ccCCV"]);
        $account =  new Account($account_data["ID"], $account_data["emailAddress"], $account_data["password"], $account_data["firstName"], $account_data["lastName"], $account_data["rights"], $cc);


        $connection->getConnection()->close();


} else {
    $error = "You must be logged in to access this page.";
}

include 'editProfile.html';

?>

