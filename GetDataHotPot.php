<?php
    include "Connect.php";
    $arrayDataFood = array();
    $query = "select * from monan where MaLoai = 7";

    $data = mysqli_query($conn,$query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($arrayDataFood, new Foods(
            $row['MaMonAn'],
            $row['TenMonAn'],
            $row['Gia'],
            $row['HinhAnh'],
            $row['MaLoai']
        ));
    }
    echo json_encode($arrayDataFood);

     class Foods {
         function __construct($id, $tenmonan, $gia, $hinhanh, $loaimonan){
             $this->id=$id;
             $this->tenmonan=$tenmonan;
             $this->gia=$gia;
             $this->hinhanh=$hinhanh;
             $this->loaimonan=$loaimonan;
         }
     } 

?>