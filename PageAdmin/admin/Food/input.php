<?php
require_once('../../db/dbhelp.php');
	$nameFood = '';
	$price = '';
	$image = '';
	$typeOfFood = '';

//Lấy giá trị POST từ form vừa submit
if ($_SERVER["REQUEST_METHOD"] == "POST") {
	$id = '';
    if(isset($_POST["nameFood"])) { $nameFood = $_POST['nameFood']; }
    if(isset($_POST["price"])) { $price = $_POST['price']; }
    if(isset($_POST["image"])) { $image = $_POST['image']; }
    if(isset($_POST["id_typeOfFood"])) { $typeOfFood = $_POST['id_typeOfFood']; }
     if(isset($_POST["id"])) { $id = $_POST['id']; }
    $nameFood = str_replace('\'', '\\\'', $nameFood);
	$price      = str_replace('\'', '\\\'', $price);
	$image  = str_replace('\'', '\\\'', $image);
	$typeOfFood       = str_replace('\'', '\\\'', $typeOfFood);
	$id = str_replace('\'', '\\\'', $id);
	if ($id != '') {
		//update
		$sql = "update monan set TenMonAn = '$nameFood', Gia = $price, HinhAnh = '$image', MaLoai = $typeOfFood where MaMonAn = $id";
	} else{
		//insert
		 $sql = "insert into monan(TenMonAn,Gia,HinhAnh,MaLoai) values('$nameFood', '$price', '$image', '$typeOfFood')";
	}

    //Code xử lý, insert dữ liệu vào table
  

    execute($sql);
    header('location: index.php');
    die();
}
$MaMonAn = $name = '';
if (isset($_GET['id'])) {
	$MaMonAn = $_GET['id'];
	$sql1 = "select * from monan where MaMonAn = $MaMonAn";
	$Food = executeResult($sql1);
	if ($Food != null && count($Food) > 0) {
		$monan = $Food[0];
		$nameFood = $monan['TenMonAn'];
		$price = $monan['Gia'];
		$image = $monan['HinhAnh'];
		$typeOfFood = $monan['MaLoai'];
	}else{
		$MaMonAn = '';
	}
}
?>
<!DOCTYPE html>
<html>
<head>
	<title>Thông Tin Món Ăn</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<style type="text/css">
		.navbar{
			height: 75px;
			width: 100%;
			background-color: #1e88e5;
		}
		.panel-heading{
			font-size: 1.5rem;
			color: white;
		}
		.bg-white {
		    background-color: white!important;
		}
		.container{
			margin-top: 20px;
			box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
			padding-top: 25px;
			padding-bottom: 25px;
			padding-left: 10px;
			padding-right: 10px;
			width: 100%;
			height: 570px;

		}
		label{
			color: #1e88e5;
		}
	</style>

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	 <nav class="navbar navbar-expand navbar-light topbar mb-4 static-top shadow">
			<div class="panel-heading">
				Thông Tin Món Ăn
			</div>
	</nav>
		<div class="container">
		<div class="panel panel-primary">
			<div class="panel-body">
				<form action="" method="post">
					<div class="form-group">
						<label for="ur">Tên Món Ăn:</label>
					  <input type="number" name="id" value="<?=$MaMonAn?>" style="display: none;">
					  <input required="true" type="text" class="form-control" id="nameFood" name="nameFood" value="<?=$nameFood?>">
					</div>
					<div class="form-group">
					  <label for="Price">Giá:</label>
					  <input type="number" class="form-control" id="price" name="price" value="<?=$price?>">
					</div>
					<div class="form-group">
					  <label for="image">Hình Ảnh:</label>
					  <input type="text" class="form-control" id="image" name="image" value="<?=$image?>"      onchange="updateImage()">
					  <img src="<?=$image?>" style="width: 200px;max-height:200px" id="imgFood">
					</div>
					<div class="form-group">
					  <label for="typeOfFood">Loại Món Ăn:</label>
					  <select class="form-control" name="id_typeOfFood" id="id_typeOfFood" value="<?=$typeOfFood?>">
					  	<option>--Lựa chọn loại món ăn--</option>
<?php
	$sql = "select * from loaimonan";
	$listTypeOfFood = executeResult($sql);
	foreach ($listTypeOfFood as $item) {
		if ($item['MaLoai'] == $typeOfFood) {
			echo '<option selected value="'.$item['MaLoai'].'">'.$item['TenLoai'].'</option>';
		}else{
			echo '<option value="'.$item['MaLoai'].'">'.$item['TenLoai'].'</option>';
		}
		
	}
?>					  	
					  </select>
					</div>
					<button class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function updateImage(){
			$('#imgFood').attr('src', $('#image').val())
		}
	</script>
</body>
</html>