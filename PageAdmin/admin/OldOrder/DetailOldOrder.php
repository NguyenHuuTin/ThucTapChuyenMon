<?php
	require_once('../../db/dbhelp.php');
	$MaDH = '';
	if (isset($_GET['id'])) {
		$MaDH = $_GET['id'];
	}
?>
<!DOCTYPE html>
<html>
<head>
	<title>Home Admin</title>
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
		.container{
			margin-top: 20px;
			box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
			padding-top: 25px;
			padding-bottom: 25px;
			padding-left: 10px;
			padding-right: 10px;
			width: 100%;
			height: 570px;
			font-weight: bold;

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
					Chi Tiết Đơn Hàng
				</div>
			</nav>
	<div class="container">
		<div class="panel panel-primary">
			
			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>MaDH</th>
							<th>Tên Món</th>
							<th>SL</th>
							<th>Giá</th>
							<th>Tổng Tiền</th>
						</tr>
					</thead>
					<tbody>
						<?php
							$sql = "select MaDH, monan.TenMonAn, SL, ctdh.Gia, TongTien from ctdh, monan where ctdh.MaMonAn = monan.MaMonAn and ctdh.MaDH = $MaDH";
							$DHList = executeResult($sql);
							foreach ($DHList as $dh) {
									echo '<tr>
								<td>'.$dh['MaDH'].'</td>
								<td>'.$dh['TenMonAn'].'</td>
								<td>'.$dh['SL'].'</td>
								<td>'.$dh['Gia'].'</td>
								<td>'.$dh['TongTien'].'</td>
							</tr>';
							}
							?>
							
					</tbody>
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>