
<html>


    <head>

        <?php include 'navbar.php'; ?>
        <title>Home</title>
    </head>



    <body>

        <br>

        <div>
            <?php if (isset($welcome)) {
                echo $welcome."<br>";
            }?>
        </div>

        <div>
            Welcome to our milestone home page <br>
            Created by Jordan Riley, Antonio Jabrail, Joey Stott<br>
            Currently working modules: Login, Register, and Product View

        </div>
    </body>
</html>
