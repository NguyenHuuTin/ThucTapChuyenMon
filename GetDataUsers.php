<?php
    include "Connect.php";
    $arrayDataUsers = array();
    $query = "select * from taikhoang where Permission = 2";

    $data = mysqli_query($conn,$query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrayDataUsers, new Users(
            $row['SDT'],
            $row['TenDangNhap'],
            $row['MatKhau'],
            $row['Email'],
            $row['Permission']
        ));
    }
    echo json_encode($arrayDataUsers);

     class Users {
         function __construct($SDT, $UserName, $Password, $Email, $Permission){
             $this->SDT=$SDT;
             $this->UserName=$UserName;
             $this->Password=$Password;
             $this->Email=$Email;
             $this->Permission=$Permission;
         }
     } 

?>