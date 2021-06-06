<?php
    include "Connect.php";
    $SDT = $_POST['SDT'];
    $UserName = $_POST['UserName'];
    $Password = $_POST['Password'];
    $Email =  $_POST['Email'];

    $query = "insert into taikhoang values('$SDT','$Email','$UserName','$Password',2)";
    if(mysqli_query($conn,$query)){
        //successful
        echo "success";
    }
    else{
        echo "loi";
    }
?>