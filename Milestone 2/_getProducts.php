<?php


        include 'autoLoader.php';
        $connection = new Connection();

        $products = getProducts($connection);


        foreach ($products as $product) { ?>
            <div>
                <table>
                    <tr>
                        <td>
                            <?php echo "Product name: ".$product['name']; ?>
                        </td>
                        <td>
                            <?php echo ", Price: $".$product['price']; ?>
                        </td>
                    </tr>
                </table>
            </div>
        <?php } ?>