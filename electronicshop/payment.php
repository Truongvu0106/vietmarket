<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getAllPayment':
			$func();
			break;
		case 'getPaymentById':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllPayment(){
		global $conn;
		$query = "SELECT * FROM payment";
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"payment\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"name" => $line["name"],
					"image" => $line["image"],
					"price" => $line["price"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getPaymentById(){
		global $conn;
		if (isset($_POST["id"])) {
			$id = $_POST["id"];
		}

		$query = "SELECT * FROM payment WHERE id = ".$id;
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"payment\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"name" => $line["name"],
					"image" => $line["image"],
					"price" => $line["price"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

 ?>