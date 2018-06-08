<?php 
	include_once("../config.php");
	$func = $_POST["action"];
	switch ($func) {
		case 'checkLogin':
			$func();
			break;
		default:
			# code...
			break;
	}

	function checkLogin(){
		global $conn;
		session_start();
		$username = $_POST["username"];
		$pass = $_POST["pass"];
		$query = "SELECT * FROM user WHERE username='".$username."' AND password='".$pass."'";
		$results = mysqli_query($conn, $query);
		$count = mysqli_num_rows($results);

		if ($count >= 1) {
			while ($line = mysqli_fetch_array($results)) {
				$_SESSION["id_user"] = $line["id_user"];
				$_SESSION["username"] = $line["username"];
				$_SESSION["fullname"] = $line["fullname"];
				$_SESSION["avatar"] = $line["img_avatar"];
				$_SESSION["id_type"] = $line["id_type"];
			}
			echo 1;
		}else{
			echo 0;
		}
		// mysqli_close($conn);
	}
 ?>