<html>
    <head>

        <?php include 'navBar.php'; ?>
        <title>Home</title>
    </head>

    <body>

    <div class="welcome">
        <div class="wrapper">

            <div class="error">
                <?php
                if ($_GET['type'] == "secure") {
                    echo "You must be logged in to view this page.";
                } else if ($_GET['type'] == "404") {

                    echo "Page doesn't exist!";
                }?>
            </div>
        </div>
    </div>



    </body
</html>
