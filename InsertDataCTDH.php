<?php
    include "Connect.php";
    $json = $_POST['json'];
    $data = json_decode($json,true);
    foreach($data as $value){
        $madonhang = $value['MaDH'];
        $mamonan = $value['MaMonAn'];
        $sl = $value['SL'];
        $gia = $value['Gia'];
        $tongtien = $value['TongTien'];
        $query = "insert into ctdh values('$madonhang','$mamonan','$sl','$gia','$tongtien')";
        $Dta = mysqli_query($conn,$query);
    }
    if($Dta){
        echo "1";
    }
    else{
        echo "0";
    }

?>