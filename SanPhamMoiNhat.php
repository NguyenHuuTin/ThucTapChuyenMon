<?php
    include "Connect.php";
    $mangmonanmoinhat = array();
    $query = "select * from monan order by  MaMonAn DESC limit 6";

    $data = mysqli_query($conn,$query);
    while($row = mysqli_fetch_assoc($data)){
        array_push($mangmonanmoinhat, new Monanmoinhat(
            $row['MaMonAn'],
            $row['TenMonAn'],
            $row['Gia'],
            $row['HinhAnh'],
            $row['MaLoai']
        ));
    }
    echo json_encode($mangmonanmoinhat);

     class Monanmoinhat {
         function __construct($id, $tenmonan, $gia, $hinhanh, $loaimonan){
             $this->id=$id;
             $this->tenmonan=$tenmonan;
             $this->gia=$gia;
             $this->hinhanh=$hinhanh;
             $this->loaimonan=$loaimonan;
         }
     } 

?>