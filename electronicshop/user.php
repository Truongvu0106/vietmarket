<?php 
	include_once("config.php");

	$func = $_POST["func"];

	switch ($func) {
		case 'getAllMember':
			$func();
			break;
		case 'registerUser':
			$func();
			break;
		case 'checkLogin':
			$func();
			break;
		case 'getUserByUsername':
			$func();
			break;
		case 'getUserById':
			$func();
			break;
		case 'updateUser':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllMember(){
		global $conn;
		$query_parent = "SELECT * FROM user WHERE id_type = 2";
		$results_parent = mysqli_query($conn, $query_parent);
		$my_json_array = array();
		echo "{";
		echo "\"users\":[";
		if ($results_parent) {
			while ($line = mysqli_fetch_array($results_parent)) {
				array_push($my_json_array, array(
					"id_user" => $line["id_user"], 
					"fullname" => $line["fullname"], 
					"username" => $line["username"], 
					"password" => $line["password"], 
					"address" => $line["address"], 
					"birthday" => $line["birthday"], 
					"phone" => $line["phone"], 
					"gender" => $line["gender"], 
					"img_avatar" => $line["img_avatar"], 
					"id_type" => $line["id_type"], 
					"type_login" => $line["type_login"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function checkLogin(){
		global $conn;
		if (isset($_POST["username"]) || isset($_POST["password"])) {
			$username = $_POST["username"];
			$password = $_POST["password"];
		}

		$query = "SELECT * FROM user WHERE username='".$username."' AND password='".$password."'";
		$results = mysqli_query($conn, $query);
		$count = mysqli_num_rows($results);
		if ($count >= 1) {
			$username = "";
			$id = 0;
			$type = 0;
			while ($line = mysqli_fetch_array($results)) {
				$username = $line["username"];
				$id = $line["id_user"];
				$type = $line["id_type"];
			}
			echo json_encode([
				"result" => "true",
				"username" => $username,
				"id" => $id,
				"type" => $type
			]);
		}else{
			echo json_encode([
				"result" => "true",
				"error" => "error : ".$query."</br>".$conn->error."}"
			]);
		}
		mysqli_close($conn);
	}

	function registerUser(){
		global $conn;
		if (isset($_POST["fullname"]) || isset($_POST["username"]) || isset($_POST["phone"]) || isset($_POST["password"]) || isset($_POST["typeid"])) {
			$fullname = $_POST["fullname"];
			$username = $_POST["username"];
			$phone = $_POST["phone"];
			$password = $_POST["password"];
			$typeid = $_POST["typeid"];
		}
		
		$query = "INSERT INTO user (fullname, username, password, phone, id_type) 
		VALUES ('".$fullname."',
				'".$username."',
				'".$password."',
				'".$phone."',
				'".$typeid."')"; 
		if (mysqli_query($conn, $query)) {
			echo "{result : true}";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}

		mysqli_close($conn);
	}

	function updateUser(){
		global $conn;
		if (isset($_POST["fullname"]) || isset($_POST["username"]) 
			|| isset($_POST["password"]) || isset($_POST["address"]) 
			|| isset($_POST["birthday"]) || isset($_POST["phone"]) 
			||isset($_POST["gender"]) || isset($_POST["img_avatar"]) 
			|| isset($_POST["typeid"]) || isset($_POST["type_login"])) {
			$fullname = $_POST["fullname"];
			$username = $_POST["username"];
			$password = $_POST["password"];
			$address = $_POST["address"];
			$birthday = $_POST["birthday"];
			$phone = $_POST["phone"];
			$gender = $_POST["gender"];
			$img_avatar = $_POST["img_avatar"];
			$typeid = $_POST["typeid"];
			$typelogin = $_POST["type_login"];
		}
		
		$query = "UPDATE user SET 
				fullname = '".$fullname."',
				password = '".$password."',
				address = '".$address."',
				birthday = '".$birthday."',
				phone = '".$phone."',
				gender = '".$gender."',
				img_avatar = '".$img_avatar."' WHERE username = '".$username."'"; 
		if (mysqli_query($conn, $query)) {
			echo "{result : true}";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}

		mysqli_close($conn);
	}

	function getUserByUsername(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["username"])) {
			$username = $_POST["username"];
		}
		$query = "SELECT * FROM user WHERE username = '".$username."'";
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"user\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_user"], 
					"fullname" => $line["fullname"],
					"username" => $line["username"],
					"password" => $line["password"],
					"address" => $line["address"],
					"birthday" => $line["birthday"],
					"phone" => $line["phone"],
					"gender" => $line["gender"],
					"img_avatar" => $line["img_avatar"],
					"id_type" => $line["id_type"],
					"type_login" => $line["type_login"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getUserById(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id"])) {
			$id = $_POST["id"];
		}
		$query = "SELECT * FROM user WHERE id_user = '".$id."'";
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"user\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_user"], 
					"fullname" => $line["fullname"],
					"username" => $line["username"],
					"password" => $line["password"],
					"address" => $line["address"],
					"birthday" => $line["birthday"],
					"phone" => $line["phone"],
					"gender" => $line["gender"],
					"img_avatar" => $line["img_avatar"],
					"id_type" => $line["id_type"],
					"type_login" => $line["type_login"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}
 ?>