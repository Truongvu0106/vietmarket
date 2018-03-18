<?php 
	include_once("config.php");

	$query = "SELECT * FROM type_child";
	$results = mysqli_query($conn, $query);
	$my_json_array = array();
	echo "{";
	echo "\"child_category\":[";
	if ($results) {
		while ($line = mysqli_fetch_array($results)) {
			array_push($my_json_array, array("id" => $line["id_type_child"], "name" => $line["name_type_child"], "parent" => $line["id_type_parent"], "image" => $line["image"]));
		}
		echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
	}
	echo "]}";

 ?>