<?php
    include "autoLoader.php";
    include 'securePage.php';



        if ($_SESSION["rights"] > 0) {

            //initialize connection and product operations
            $connection = new Connection();
            $productOperations = new ProductOperations($connection);

            //check if form was submitted
            if (isset($_POST['action'])) {

                //initialize vars sent from form
                $ID = $_POST['productID'];
                $type = $_POST['type'];
                $name = $_POST['name'];
                $description = $_POST['description'];
                $price = $_POST['price'];
                $product = new Product($ID, $type, $name, $description, $price);


                //check if user wants to edit product data
                if ($_POST['action'] == "edit") {

                    if ($_POST['option'] == "Update") {
                        //editproduct
                        if ($productOperations->update($product)) {
                            $operation = "Successfully updated product: " . $product->getName();
                        } else {
                            $operation = "Error updating product: " . $product->getName();
                        }

                        if ($_FILES["image"]["tmp_name"]) {
                            $target_dir = "css/images/products/";
                            $target_file = $target_dir . $product->getID() .".png";
                            $imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);

                            //saves image to directory
                            if (getimagesize($_FILES["image"]["tmp_name"])) {

                                if ($_FILES["image"]["size"] > 500000) {
                                    $operation.="<br><div class='error'>";
                                    $operation.="Image file too large.";
                                    $operation.="</div>";
                                } else {
                                    if($imageFileType != "png") {
                                        $operation.="<br><div class='error'>";
                                        $operation.="Image must be a .png format.".$imageFileType;
                                        $operation.="</div>";
                                    } else {
                                        if (!move_uploaded_file($_FILES["image"]["tmp_name"], $target_file)) {
                                            $operation.="<br><div class='error'>";
                                            $operation.="Error updating image.";
                                            $operation.="</div>";
                                        }
                                    }

                                }
                            } else {
                                $operation.="<br><div class='error'>";
                                $operation.="Incorrect image file type.";
                                $operation.="</div>";
                            }
                        }

                    //check if user wants to delete product data
                    } else if ($_POST['option'] == "Delete") {
                        if ($productOperations->delete($product)) {
                            $operation = "Successfully deleted product: " . $product->getName();
                        } else {
                            $operation = "Error deleting product: " . $product->getName();
                        }
                    }

                    //check if user wants to add new product
                } else if ($_POST['action'] == "add") {

                    if ($productOperations->add($product)) {
                        $operation = "Successfully added product: ".$product->getName();

                    } else {
                        $operation = "Error adding product: ".$product->getName();
                    }

                    //saves image to directory
                    $target_dir = "css/images/products/";
                    $target_file = $target_dir . mysqli_insert_id($connection->getConnection()) .".png";
                    $imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);


                    if (getimagesize($_FILES["image"]["tmp_name"])) {

                        if ($_FILES["image"]["size"] > 500000) {
                            $operation.="<br><div class='error'>";
                            $operation.="Image file too large.";
                            $operation.="</div>";
                        } else {
                            if($imageFileType != "png") {
                                $operation.="<br><div class='error'>";
                                $operation.="Image must be a .png format.".$imageFileType;
                                $operation.="</div>";
                            } else {
                                if (!move_uploaded_file($_FILES["image"]["tmp_name"], $target_file)) {
                                    $operation.="<br><div class='error'>";
                                    $operation.="Error uploading image.";
                                    $operation.="</div>";
                                }
                            }

                        }
                    } else {
                        $operation.="<br><div class='error'>";
                        $operation.="Incorrect image file type.";
                        $operation.="</div>";
                    }
                }


            }
            //load products into an array
            $products = $productOperations->getProducts();

            //close db connection
            $connection->getConnection()->close();

        } else {
            $error = "You don't have permission to view this page.";
        }


    include 'editProducts.html';

?>

