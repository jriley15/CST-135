<?php

/**
 * Created by PhpStorm.
 * User: jordan
 * Date: 10/8/2017
 * Time: 8:22 PM
 */
class CreditCard
{


    private $name;
    private $number;
    private $expirationDate;
    private $ccv;


    function __construct($name, $number, $expirationDate, $ccv)
    {
        $this->name = $name;
        $this->number = $number;
        $this->expirationDate = $expirationDate;
        $this->ccv = $ccv;


    }
    /**
     * @return mixed
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @param mixed $name
     */
    public function setName($name)
    {
        $this->name = $name;
    }

    /**
     * @return mixed
     */
    public function getNumber()
    {
        return $this->number;
    }

    /**
     * @param mixed $number
     */
    public function setNumber($number)
    {
        $this->number = $number;
    }

    /**
     * @return mixed
     */
    public function getExpirationDate()
    {
        return $this->expirationDate;
    }

    /**
     * @param mixed $expirationDate
     */
    public function setExpirationDate($expirationDate)
    {
        $this->expirationDate = $expirationDate;
    }

    /**
     * @return mixed
     */
    public function getCcv()
    {
        return $this->ccv;
    }

    /**
     * @param mixed $ccv
     */
    public function setCcv($ccv)
    {
        $this->ccv = $ccv;
    }



}