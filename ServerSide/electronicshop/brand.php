<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getListBrand':
			$func();
			break;
		case 'getBrandById':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getListBrand(){
		global $conn;
		$quey = "SELECT * FROM brand";
		$results = mysqli_query($conn, $quey);
		$my_json_array = array();
		echo "{";
		echo "\"brand\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_brand"], 
					"name" => $line["name_brand"], 
					"image" => $line["img_brand"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getBrandById(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_brand"])) {
			$id = $_POST["id_brand"];
		}
		$query = "SELECT * FROM brand WHERE id_brand = ".$id;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"brand\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_brand"], 
					"name" => $line["name_brand"], 
					"image" => $line["img_brand"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

 ?>