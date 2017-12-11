<?php

    //if user isn't logged in, redirect them to login page
    if (!isset($_SESSION["principle"]) || !$_SESSION["principle"] || $_SESSION["principle"] == null) {
        header("Location: login.php");
    }




