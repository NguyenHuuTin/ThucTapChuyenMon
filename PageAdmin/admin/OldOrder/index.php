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
				    <a class="nav-link" href="../Food/">Món Ăn</a>
				  </li>
			    <li class="nav-item">
			        <a class="nav-link active" href="">Đơn Hàng</a>
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
					<div class="panel-body">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>MaDH</th>
									<th>SDT</th>
									<th>Tên Khách Hàng</th>
									<th>Thời Gian</th>
									<th>Địa chỉ</th>
									<th>Tổng Tiền</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
<?php
$limit =7;
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
$additional = 'and MaDH like "%'.$s.'%"';
}

$sql = "select MaDH, donhang.SDT, TenDangNhap, ThoiGian, DiaChi, ThanhTien from donhang, khachhang where donhang.SDT = khachhang.SDT and TinhTrang = 0 $additional limit $firrtIndex , $limit";
$DHList = executeResult($sql);
$sqlCount = "select count(MaDH) as total from donhang where TinhTrang = 0 $additional";
$CountResult = executeResult($sqlCount);
$itemDH = $CountResult[0];
$count = $itemDH['total'];
$number = ceil($count/$limit);
foreach ($DHList as $dh) {
	echo '<tr>
<td>'.$dh['MaDH'].'</td>
<td>'.$dh['SDT'].'</td>
<td>'.$dh['TenDangNhap'].'</td>
<td>'.$dh['ThoiGian'].'</td>
<td>'.$dh['DiaChi'].'</td>
<td>'.$dh['ThanhTien'].'</td>
<td>
<a href="DetailOldOrder.php?id='.$dh['MaDH'].'"><button class="btn btn-warning">Chi Tiết</button></td></a>
<td><button class="btn btn-danger" onclick="deleteOrder('.$dh['MaDH'].')">Xóa</button></td>
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
		function deleteOrder(id){
			var option = confirm('Bạn có chác muốn xóa món ăn này không?');
			if (!option) {
				return;
			}
			console.log(id);
			// ajax
			$.post('ajaxOldOrder.php', {
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