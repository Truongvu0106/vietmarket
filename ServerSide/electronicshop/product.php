<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getAllProduct':
			$func();
			break;
		case 'getProductById':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllProduct(){
		global $conn;
		$query = "SELECT * FROM product";
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"products\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_product"], 
					"name" => $line["name_product"],
					"price" => $line["price"],
					"image" => $line["image"],
					"information" => $line["information"],
					"weight" => $line["weight"],
					"type_product" => $line["type_product"],
					"brand" => $line["brand"],
					"rate" => $line["rate"],
					"amount" => $line["amount"],
					"highlight" => $line["hightlight"],
					"discount" => $line["discount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getProductById(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_product"])) {
			$id = $_POST["id_product"];
		}
		$query = "SELECT * FROM product WHERE id_product = ".$id;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"product\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_product"], 
					"name" => $line["name_product"],
					"price" => $line["price"],
					"image" => $line["image"],
					"information" => $line["information"],
					"weight" => $line["weight"],
					"type_product" => $line["type_product"],
					"brand" => $line["brand"],
					"rate" => $line["rate"],
					"amount" => $line["amount"],
					"highlight" => $line["hightlight"],
					"discount" => $line["discount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

?>