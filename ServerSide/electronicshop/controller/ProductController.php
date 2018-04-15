<?php 
	function getListProductLimit(){
		global $conn;
		$pageNumber = $_POST["pageNumber"];
		$limit = ($pageNumber-1)*10;
		$query = "SELECT * FROM product pd, type_child tc, brand b WHERE pd.type_product = tc.id_type_child AND pd.brand = b.id_brand LIMIT ".$limit.", 10";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				$path_image = $line["image"];
				$first = strtok($path_image, '@');
				echo "<tr>";
				echo '<th><img style="width:50px; height:50px" src="../..'.$first.'" /></th>';
				echo '<th>'.$line["name_product"].'</th>';
				echo '<th>'.$line["price"].'</th>';
				echo '<th>'.$line["weight"].'</th>';
				echo '<th>'.$line["name_type_child"].'</th>';
				echo '<th>'.$line["name_brand"].'</th>';
				echo '<th>'.$line["rate"].'</th>';
				echo '<th>'.$line["amount"].'</th>';
				echo '<th>'.$line["discount"].'</th>';
				echo "</tr>";
			}
		}else{
			echo "Null";
		}
	}

?>