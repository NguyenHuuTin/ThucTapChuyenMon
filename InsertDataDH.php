<?php
    include "Connect.php";
    $SDT = $_POST['SDT'];
    $Money = $_POST['Money'];
    $DateTime = $_POST['DataTime'];
    $Address = $_POST['Address'];
    if(strlen($SDT) > 0 && strlen($Money) > 0 && strlen($DateTime) > 0 && strlen($Address) > 0){
        $query = "insert into donhang(MaDH, SDT, ThanhTien, ThoiGian, DiaChi, TinhTrang) values (null, '$SDT','$Money','$DateTime','$Address',1)";
        if(mysqli_query($conn,$query)){
            $idDH = $conn->insert_id;
            echo $idDH;
        }else {
            echo "Thất bại";
        }
    }else{
        echo "Bạn hãy kiểm tra lại các dữ liệu";
    }

?>