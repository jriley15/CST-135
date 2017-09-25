
<html>


    <head>
        <LINK REL=StyleSheet HREF="css/products.css?<?php echo time(); ?>">
        <?php include 'navbar.php'; ?>
        <title>Products</title>
    </head>



    <body>
        <br>
        <div class="product">
            <h1>
                Product List
            </h1>
            <?php include '_getProducts.php'; ?>

        </div>

    </body>
</html>







