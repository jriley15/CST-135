<?php include 'session.php'; ?>
<!DOCTYPE html>

<html lang="en">
    <LINK REL=StyleSheet HREF="css/index.css?<?php echo time(); ?>">
    <h1>Suites</h1>
    <nav>
        <ul>
            <li><a href="index.php">HOME</a></li>
            <li><a href='viewProducts.php'>VIEW PRODUCTS</a></li>

            <?php
            if (isset($_SESSION["principle"])) {?>

                <li><a href='logout.php'>LOGOUT</a></li>

            <?php } else {

                    if ($_SERVER["PHP_SELF"] != '/Milestone/Login.php') { ?>
                        <li><a href='Login.php'>LOGIN</a></li>
                    <?php }
                    if ($_SERVER["PHP_SELF"] != '/Milestone/Register.php') { ?>
                        <li><a href='Register.php'>REGISTER</a></li>
                    <?php } ?>


            <?php } ?>

        </ul>
    </nav>
<br>


</html>