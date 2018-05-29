<?php 
	include_once("config.php");

	$func = $_POST["func"];

	switch ($func) {
		case 'getAllBanner':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllBanner(){
		global $conn;
		$query_parent = "SELECT * FROM banner";
		$results_parent = mysqli_query($conn, $query_parent);
		$my_json_array = array();
		echo "{";
		echo "\"banners\":[";
		if ($results_parent) {
			while ($line = mysqli_fetch_array($results_parent)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"title" => $line["title"],
					"image" => $line["image"],  
					"type" => $line["type"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}


 ?>