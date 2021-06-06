<?php
    include "Connect.php";
    $Password = $_POST['Password'];
    $SDT = $_POST['SDT'];

    $query = "update taikhoang set MatKhau = '$Password' where SDT = '$SDT'";
    if(mysqli_query($conn,$query)){
        //successful
        echo "success";
    }
    else{
        echo "loi";
    }
?>