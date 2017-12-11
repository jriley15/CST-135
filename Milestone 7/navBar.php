<?php include 'session.php'; ?>
<!DOCTYPE html>

<html lang="en">
    <LINK REL=StyleSheet HREF="css/index.css?<?php echo time(); ?>">

    <img src="css/images/logonew.png" width="300px" height="200px">


    <nav>
        <ul>
            <?php
            if ($_SERVER["PHP_SELF"] != '/Milestone/index.php') { ?>
                <li><a href="index.php">HOME</a></li>
            <?php } ?>




            <?php
            if (isset($_SESSION["principle"])) {?>

                <?php if ($_SESSION["rights"] > 0 && $_SERVER["PHP_SELF"] != '/Milestone/editProducts.php') {?>
                    <li><a href='editProducts.php'>EDIT PRODUCTS</a></li>
                <?php } ?>

                <?php
                if ($_SERVER["PHP_SELF"] != '/Milestone/viewProducts.php') { ?>
                    <li><a href='viewProducts.php'>VIEW PRODUCTS</a></li>
                <?php } ?>

                <?php
                if ($_SERVER["PHP_SELF"] != '/Milestone/editProfile.php') { ?>
                    <li><a href='editProfile.php'>EDIT PROFILE</a></li>
                <?php } ?>

                <?php
                if ($_SERVER["PHP_SELF"] != '/Milestone/viewCart.php') {
                    if (!isset($_SESSION["cart"])) { ?>

                        <li><a href='viewCart.php'>VIEW CART (0)</a></li>

                    <?php } else { ?>
                        <li ><a href = 'viewCart.php' > VIEW CART (<?php echo count($_SESSION["cart"]); ?>)</a ></li >
                    <?php }
                }?>



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