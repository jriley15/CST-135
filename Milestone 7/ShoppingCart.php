
<?php
include "session.php";
/**
 * Created by PhpStorm.
 * User: jordan
 * Date: 10/20/2017
 * Time: 5:09 PM
 */
class ShoppingCart
{

    private $productOperations;

    function __construct($productOperations)
    {
        $this->productOperations = $productOperations;
    }

    function addProduct($id, $quantity) {
        $_SESSION['cart'][$id] = $quantity;
    }

    function removeProduct($id, $quantity) {
        if ($_SESSION['cart'][$id] - $quantity < 0) {
            $_SESSION['cart'][$id] = 0;
        } else {
            $_SESSION['cart'][$id] -= $quantity;
        }

    }

    function getCount() {
        return count($_SESSION['cart']);
    }

    function getItemCount($id) {
        return $_SESSION['cart'][$id];
    }



    function getCartItems() {

        if ($this->getCount() == 0) {
            return;
        }
        $products = array();

        for ($i = 1; $i <= $this->productOperations->getProductCount(); $i++) {
            if (isset($_SESSION['cart'][$i])) {
                $products[] = $this->productOperations->getProductAsProduct($i);

            }


        }

        return $products;

    }


}