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
		case 'getListChildCategoryByParent':
			$func();
			break;
		case 'addChildCategory':
			$func();
			break;
		case 'updateChildCategory':
			$func();
			break;
		case 'addParentCategory':
			$func();
			break;
		case 'updateParentCategory':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getListChildCategoryByParent(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_parent"])) {
			$idParent = $_POST["id_parent"];
		}
		// $id = $_GET["id_parent"];
		$query = "SELECT * FROM type_child WHERE id_type_parent = ".$idParent;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"child_categories\":[";
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

	function getListParentCategory(){
		global $conn;
		$query_parent = "SELECT * FROM type_parent";
		$results_parent = mysqli_query($conn, $query_parent);
		$my_json_array = array();
		echo "{";
		echo "\"parent_category\":[";
		if ($results_parent) {
			while ($line = mysqli_fetch_array($results_parent)) {
				array_push($my_json_array, array(
					"id" => $line["id_type_parent"], 
					"name" => $line["name_type_parent"], 
					"image" => $line["image"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getListChildCategory(){
		global $conn;
		$query = "SELECT * FROM type_child";
		$results = mysqli_query($conn, $query);
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

	function addChildCategory(){
		global $conn;
		if (isset($_POST["name_type_child"]) || isset($_POST["id_type_parent"]) || isset($_POST["image_cate"])) {
			$name = $_POST["name_type_child"];
			$idParent = $_POST["id_type_parent"];
			$image = $_POST["image_cate"];
		}
		
		$query = "INSERT INTO type_child (name_type_child, id_type_parent, image_cate) 
		VALUES ('".$name."',
				'".$idParent."',
				'".$image."')"; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "insert category successful"
			]);
		}else{
			echo json_encode([
			"result" => "false",
			"message" => "error : ".$query."</br>".$conn->error
			]);
		}

		mysqli_close($conn);
	}

	function updateChildCategory(){
		global $conn;
		if (isset($_POST["id_type_child"]) || isset($_POST["name_type_child"]) || isset($_POST["id_type_parent"]) || isset($_POST["image_cate"])) {
			$id = $_POST["id_type_child"];
			$name = $_POST["name_type_child"];
			$idParent = $_POST["id_type_parent"];
			$image = $_POST["image_cate"];
		}

		$query = "UPDATE type_child SET 
				name_type_child = '".$name."',
				id_type_parent = '".$idParent."',
				image_cate = '".$image."' WHERE id_type_child = '".$id."'"; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
			"result" => "true",
			"message" => "update child successful"
			]);
		}else{
			echo json_encode([
			"result" => "false",
			"message" => "error : ".$query."</br>".$conn->error
			]);
		}

		mysqli_close($conn);
	}

	function addParentCategory(){

	}

	function updateParentCategory(){

	}

 ?>