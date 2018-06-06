<?php 
	include_once("config.php");

	$func = $_POST["func"];

	switch ($func) {
		case 'getAllBanner':
			$func();
			break;
		case 'addBanner':
			$func();
			break;
		case 'updateBanner':
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


	function addBanner(){
		global $conn;
		if (isset($_POST["title"]) || isset($_POST["image"]) || isset($_POST["type"])) {
			$title = $_POST["title"];
			$image = $_POST["image"];
			$type = $_POST["type"];
		}
		
		$query = "INSERT INTO banner (title, image, type) 
		VALUES ('".$title."',
				'".$image."',
				'".$type."')"; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "insert banner successful"
			]);
		}else{
			echo json_encode([
			"result" => "false",
			"message" => "error : ".$query."</br>".$conn->error
			]);
		}

		mysqli_close($conn);
	}

	function updateBanner(){
		global $conn;
		if (isset($_POST["id"]) || isset($_POST["title"]) || isset($_POST["image"]) || isset($_POST["type"])) {
			$id = $_POST["id"];
			$title = $_POST["title"];
			$image = $_POST["image"];
			$type = $_POST["type"];
		}

		$query = "UPDATE banner SET 
				title = '".$title."',
				image = '".$image."',
				type = '".$type."' WHERE id = '".$id."'"; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
			"result" => "true",
			"message" => "update banner successful"
			]);
		}else{
			echo json_encode([
			"result" => "false",
			"message" => "error : ".$query."</br>".$conn->error
			]);
		}

		mysqli_close($conn);
	}


 ?>