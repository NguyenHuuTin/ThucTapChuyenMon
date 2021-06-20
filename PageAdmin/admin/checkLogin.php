<?php
	 $u = $_POST['username'];
	 $p = $_POST['password'];

	require_once('../db/dbhelp.php');
	$sql = "select * from khachhang where TenDangNhap = '$u' and MatKhau = '$p'";
	$dataLogin = executeResult($sql);
	if ($dataLogin != null && count($dataLogin) > 0) {
		echo "thành công";
		header('location: Order');
	}
?>