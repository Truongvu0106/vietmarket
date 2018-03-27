<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getAllTransport':
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

 ?>