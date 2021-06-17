<?php
     include "Connect.php";
     $SDT = $_POST['SDT'];
     $arrayOrder = array();
     if(strlen($SDT) > 0 ){
         $query = "Select MaDH,ThanhTien, ThoiGian, DiaChi from donhang where SDT = '$SDT'";
         $data = mysqli_query($conn,$query);
         while($row = mysqli_fetch_assoc($data)){
             array_push($arrayOrder, new Order(
                 $row['MaDH'],
                 $row['ThanhTien'],
                 $row['ThoiGian'],
                 $row['DiaChi']
             ));

         }
         echo json_encode($arrayOrder);
     }else{
         echo "0";
     }
     class Order {
         function __construct($id, $Total, $Time, $address){
             $this->id = $id;
             $this->total = $Total;
             $this->time = $Time;
             $this->address = $address;
         }
     }
?>