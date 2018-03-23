<?php 
	include_once("config.php");

	$func = $_POST["func"];

	switch ($func) {
		case 'registerUser':
			$func();
			break;
		case 'checkLogin':
			$func();
			break;
		case 'getUserByUsername':
			$func();
			break;
		default:
			# code...
			break;
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
			while ($line = mysqli_fetch_array($results)) {
				$username = $line["username"];
			}
			echo "{result : true, username : \"".$username."\" }";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}
		mysqli_close($conn);
	}

	function registerUser(){
		global $conn;
		if (isset($_POST["fullname"]) || isset($_POST["username"]) || isset($_POST["password"]) || isset($_POST["typeid"])) {
			$fullname = $_POST["fullname"];
			$username = $_POST["username"];
			$password = $_POST["password"];
			$typeid = $_POST["typeid"];
		}
		
		$query = "INSERT INTO user (fullname, username, password, id_type) 
		VALUES ('".$fullname."',
				'".$username."',
				'".$password."',
				'".$typeid."')"; 
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
 ?>