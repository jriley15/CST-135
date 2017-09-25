<?php

require_once 'functions.php';
require_once 'session.php';

function my_autoLoader($class_name) {
    if (is_file($class_name . '.php')) {
        require_once $class_name . '.php';
    }

}

spl_autoload_register("my_autoLoader");

