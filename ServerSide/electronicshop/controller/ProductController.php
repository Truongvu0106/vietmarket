<?php 
	include_once("../config.php");
	$func = $_POST["action"];
	switch ($func) {
		case 'getListProductLimit':
			$func();
			break;
		case 'searchProductByName':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getListProductLimit(){
		global $conn;
		$pageNumber = $_POST["pageNumber"];
		$limit = ($pageNumber-1)*6;
		$query = "SELECT * FROM product pd, type_child tc, brand b WHERE pd.type_product = tc.id_type_child AND pd.brand = b.id_brand LIMIT ".$limit.", 6";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				$path_image = $line["image"];
				$first = strtok($path_image, '@');
				echo "<tr>";
				echo '<td><img style="width:50px; height:50px" src="../..'.$first.'" /></td>';
				echo '<td>'.$line["name_product"].'</td>';
				echo '<td>'.$line["price"].'</td>';
				echo '<td>'.$line["weight"].'</td>';
				echo '<td>'.$line["name_type_child"].'</td>';
				echo '<td>'.$line["name_brand"].'</td>';
				echo '<td>'.$line["rate"].'</td>';
				echo '<td>'.$line["amount"].'</td>';
				echo '<td>'.$line["discount"].'</td>';
				echo "</tr>";
			}
		}else{
			echo "Null";
		}
	}

	function searchProductByName(){
		global $conn;
		$content = $_POST["content"];
		
		$query = "SELECT * FROM product WHERE name_product LIKE '%".$content."%'";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				$path_image = $line["image"];
				$first = strtok($path_image, '@');
				echo "<tr>";
				echo '<td><img style="width:50px; height:50px" src="../..'.$first.'" /></td>';
				echo '<td>'.$line["name_product"].'</td>';
				echo '<td>'.$line["price"].'</td>';
				echo '<td>'.$line["weight"].'</td>';
				echo '<td>'.$line["name_type_child"].'</td>';
				echo '<td>'.$line["name_brand"].'</td>';
				echo '<td>'.$line["rate"].'</td>';
				echo '<td>'.$line["amount"].'</td>';
				echo '<td>'.$line["discount"].'</td>';
				echo "</tr>";
			}
		}else{
			echo "Null";
		}
	}

?>