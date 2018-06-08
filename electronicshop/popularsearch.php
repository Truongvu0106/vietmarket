<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getAllPopularSearch':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllPopularSearch(){
		global $conn;
		$query = "SELECT * FROM popular_search";
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"popular_search\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"name" => $line["name"],
					"image" => $line["image"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

 ?>