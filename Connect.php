<?php
    $host = "localhost";
    $username = "root";
    $password = "";
    $database = "db_ttcm";

    $conn = mysqli_connect($host,$username,$password,$database);
    mysqli_query($conn,"SET NAMES 'utf8'");

?>