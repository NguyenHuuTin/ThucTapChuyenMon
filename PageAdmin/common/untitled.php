<?php
function pageination($number, $page,$addition){
	if ($number > 1) {
	echo '<ul class="pagination">';

	if ($page >1) {
		echo '<li class="page-item"><a class="page-link" href="?page='.($page -1).''.$addition.'">Trang trước</a></li>';
	}
	

$avaiblePage = [1,$page-1, $page, $page+1,$number];
$isFirt = $isLast = false;
	for ($i=0; $i < $number; $i++) { 
		if (!in_array($i +1, $avaiblePage)) {
			if (!$isFirt && $page >3) {
				echo '<li class="page-item"><a class="page-link" href="?page='.($page-2).''.$addition.'">...</a></li>';
				$isFirt = true;
			}
			if (!$isLast && $i > $page) {
				echo '<li class="page-item"><a class="page-link" href="?page='.($page+2).''.$addition.'">...</a></li>';
				$isLast = true;
			}
			continue;
		}


		if ($page == ($i + 1)) {
			echo '<li class="page-item active"><a class="page-link" href="#">'.($i +1) .'</a></li>';
		}else{
			echo '<li class="page-item"><a class="page-link" href="?page='.($i +1).''.$addition.'">'.($i +1) .'</a></li>';
		}
		
	}

	if ($page  < $number) {
		echo '<li class="page-item"><a class="page-link" href="?page='.($page +1).''.$addition.'">Trang sau</a></li>';
	}
	
	
	echo '</ul>';
}
}
?>

