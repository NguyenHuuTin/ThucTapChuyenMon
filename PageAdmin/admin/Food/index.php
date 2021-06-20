<?php
	require_once('../../db/dbhelp.php');
	require_once('../../common/untitled.php');
?>
<!DOCTYPE html>
<html>
<head>
	<title>Home Admin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
</head>
<body class="body">
	<div class="row">
	    <div class="col-3">
	    	<div class="logo">
	    		<h2>Food Order</h1>
	    	</div>
	        <ul class="nav flex-column nav-pills" role="tablist">
	        		<li class="nav-item">
				    <a class="nav-link" href="../Order/">Đơn Hàng Mới</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link active" href="">Món Ăn</a>
				  </li>
			    <li class="nav-item">
			        <a class="nav-link" href="../OldOrder/">Đơn Hàng</a>
			    </li>
	        </ul>
	    </div>
	    <div class="col-9">
	    	<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
	    		<div class="header">
	    		<form method="GET">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Tìm kiếm..." id="s" name="s">
					</div>
				</form>
	    	</div>
	    	</nav>
		        <div class="container">
					<div class="panel panel-primary">
						<div><a href="input.php">
								<button class="btn btn-success" style="margin-top: 15px;">Thêm Món Mới</button>
							</a></div>
						<div class="panel-body">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>ID</th>
										<th>Tên món ăn</th>
										<th>Giá tiền</th>
										<th>Hình ảnh</th>
										<th>Loại</th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
<?php
$limit =4;
$page = 1;
if (isset($_GET['page'])) {
$page = $_GET['page'];
}
if ($page <= 0) {
$page = 1; 
}
$firrtIndex = ($page-1)*$limit;
$s='';
if (isset($_GET['s'])) {
$s = $_GET['s'];
}
$additional = '';
if (!empty($s)) {
 $additional = 'and TenMonAn like "%'.$s.'%"';
}
$sql = "select MaMonAn, TenMonAn,Gia,HinhAnh,TenLoai from monan, loaimonan where monan.MaLoai = loaimonan.MaLoai $additional order by MaMonAn limit $firrtIndex , $limit";
$DHList = executeResult($sql);
$sqlCount = "select count(MaMonAn) as total from monan where 1 $additional";
$CountResult = executeResult($sqlCount);
$itemDH = $CountResult[0];
$count = $itemDH['total'];
$number = ceil($count/$limit);
foreach ($DHList as $dh) {
	echo '<tr>
<td>'.$dh['MaMonAn'].'</td>
<td>'.$dh['TenMonAn'].'</td>
<td>'.$dh['Gia'].'</td>
<td><img src="'.$dh['HinhAnh'].'" style="width: 100px;height:70px"/></td>
<td>'.$dh['TenLoai'].'</td>
<td>
<a href="input.php?id='.$dh['MaMonAn'].'"><button class="btn btn-warning">Sửa</button></td></a>
<td><button class="btn btn-danger" onclick="deleteFood('.$dh['MaMonAn'].')">Xóa</button></td>
</tr>';
}
?>
										
								</tbody>
							</table>
							<?=pageination($number,$page,'&s='.$s)?>
						</div>
					</div>
				</div>
	    </div>
	</div>
	<script type="text/javascript">
		function deleteFood(id){
			var option = confirm('Bạn có chác muốn xóa món ăn này không?');
			if (!option) {
				return;
			}
			console.log(id);
			// ajax
			$.post('ajaxFood.php', {
				'id':id,
				'action':'delete'
			}, function (data){
				alert(data)
				location.reload()

			})

		}
	</script>
</body>
</html>