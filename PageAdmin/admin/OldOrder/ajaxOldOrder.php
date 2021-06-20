<?php
require_once('../../db/dbhelp.php');
	if ($_SERVER["REQUEST_METHOD"] == "POST") {
		if (isset($_POST['action'])) {
			$action = $_POST['action'];
			$tmp = 0;
			switch ($action) {
				case 'delete':
					if (isset($_POST['id'])) {
						$id = $_POST['id'];

						$sqlct = "delete from ctdh where MaDH = $id";
						$status = execute($sqlct);
						$sql = "delete from donhang where MaDH = $id";
						execute($sql);
						echo "Xóa thành công";						
					}
					break;
				
				default:
				
			}

		}
	}
?>