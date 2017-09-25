<?php

class Connection
{
    /**
     *  Milestone
     *  Connection Class
     *  Jordan Riley and Antonio Jabrail
     *  9-9-2017
     */

    private $connection;


    function __construct()
    {
        $ini = parse_ini_file("configuration/config.ini", true);


        $this->connection = new mysqli($ini['database']['servername'], $ini['database']['username'], $ini['database']['password'], $ini['database']['name']);


    }

    /**
     * @return mysqli
     */
    public function getConnection()
    {
        return $this->connection;
    }




}