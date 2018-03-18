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
		VALUES ('".$fullname."','".$username."','".$password."','".$typeid."')"; 
		if (mysqli_query($conn, $query)) {
			echo "{result : true}";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}

		mysqli_close($conn);
	}
 ?>