<?php
	require_once('../../db/dbhelp.php');
	require_once('../../common/untitled.php');
	// $id =0;
	// if ($_SERVER["REQUEST_METHOD"] == "POST") {
	// 	if (isset($_POST['action'])) {
	// 		$action = $_POST['action'];
	// 		switch ($action) {
	// 			case 'login':
	// 				if (isset($_POST['id'])) {
	// 					$id = $_POST['id'];
	// 				}
	// 				break;
				
	// 			default:
	// 				$id = '';
	// 				break;
					
	// 		}
	// 	}
	// }
	// if ($id == 0) {
	// 	header('location : ../index.php');
	// 	die();
	// }
?>
<!DOCTYPE html>
<html>
<head>
	<title>Home Admin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style.css">
	 <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
	  <!-- Ionicons -->
	  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

	<!-- Popper JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script>
	    function Refresh()
	    {
	        var t=setTimeout("location.reload(true)",10000);
	    }
	</script>
</head>
</head>
<body class="body" onload="Refresh()">
	<div class="row">
	    <div class="col-3">
	    	<div class="logo">
	    		<h2>Food Order</h1>
	    	</div>
	        <ul class="nav flex-column nav-pills" role="tablist">
	        		<li class="nav-item">
	        			<i class="nav-icon fas fa-tachometer-alt"></i>
				    <a class="nav-link active" href="">Đơn Hàng Mới</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="../Food/">Món Ăn</a>
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

$sql = "select MaDH, donhang.SDT, TenDangNhap, ThoiGian, DiaChi, ThanhTien, TinhTrang from donhang, khachhang where donhang.SDT = khachhang.SDT and TinhTrang = 1 $additional limit $firrtIndex , $limit";
$DHList = executeResult($sql);
$sqlCount = "select count(MaDH) as total from donhang where TinhTrang = 1 $additional";
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
<a href="DetailOrder.php?id='.$dh['MaDH'].'"><button class="btn btn-warning">Chi Tiết</button></td></a>
<td><button class="btn btn-danger" onclick="deleteOrder('.$dh['MaDH'].')">Hoàn Thành</button></td>
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
			var option = confirm('Bạn đã hoàn thành đơn hàng này?');
			if (!option) {
				return;
			}
			console.log(id);
			// ajax
			$.post('ajaxOrder.php', {
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