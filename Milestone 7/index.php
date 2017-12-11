
<html>


    <head>

        <?php include 'navBar.php'; ?>
        <title>Home</title>
    </head>



    <body>

        <br>

        <div class="welcome">
            <div class="wrapper">

                <?php if (isset($welcome)) {
                    echo $welcome."<br><br>";
                }
                ?>
                <?php if (isset($msg)) {
                    echo $msg;
                }
                ?>
                Welcome to our milestone home page!<br><br>
                Created by Jordan Riley, Antonio Jabrail, Joey Stott<br><br>
                Currently working modules: Login, Register, Edit Products, <br>
                View Products, Edit Profile, and Shopping Cart
            </div>
        </div>
    </body>
</html>
