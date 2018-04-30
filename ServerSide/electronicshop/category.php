<?php 
	include_once("config.php");

	$func = $_POST["func"];

	switch ($func) {
		case 'getListParentCategory':
			$func();
			break;
		case 'getListChildCategory':
			$func();
			break;
		case 'getChildCategoryById':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getListParentCategory(){
		global $conn;
		$query_parent = "SELECT * FROM type_parent";
		$results_parent = mysqli_query($conn, $query_parent);
		$my_json_array = array();
		echo "{";
		echo "\"parent_category\":[";
		if ($results_parent) {
			while ($line = mysqli_fetch_array($results_parent)) {
				array_push($my_json_array, array("id" => $line["id_type_parent"], "name" => $line["name_type_parent"], "image" => $line["image"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getListChildCategory(){
		global $conn;
		$query = "SELECT * FROM type_child";
		$results = mysqli_query($myConn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"child_category\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_type_child"], 
					"name" => $line["name_type_child"], 
					"parent" => $line["id_type_parent"], 
					"image" => $line["image_cate"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getChildCategoryById(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_child"])) {
			$id = $_POST["id_child"];
		}
		$query = "SELECT * FROM type_child WHERE id_type_child = ".$id;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"child_category\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_type_child"], 
					"name" => $line["name_type_child"], 
					"parent" => $line["id_type_parent"], 
					"image" => $line["image_cate"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

 ?>