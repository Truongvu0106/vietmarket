<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getAllTransport':
			$func();
			break;
		case 'getTransportById':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllTransport(){
		global $conn;
		$query = "SELECT * FROM transport";
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"transport\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"name" => $line["name"],
					"note" => $line["note"],
					"price" => $line["price"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getTransportById(){
		global $conn;

		if (isset($_POST["id"])) {
			$id = $_POST["id"];
		}
		
		$query = "SELECT * FROM transport WHERE id = ".$id;
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"transport\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"name" => $line["name"],
					"note" => $line["note"],
					"price" => $line["price"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

 ?>