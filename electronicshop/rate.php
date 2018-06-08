<?php 
	include_once("config.php");
	$func = $_POST["func"];
	switch ($func) {
		case 'isRated':
			$func();
			break;
		case 'addRate':
			$func();
			break;
		case 'getRateByProduct':
			$func();
			break;
		case 'getRateByShopAndUser':
			$func();
			break;
		case 'addShopRate':
			$func();
			break;
		case 'getRateByShop':
			$func();
			break;
		case 'updateShopRate':
			$func();
			break;
		default:
			# code...
			break;
	}

	function isRated(){
		global $conn;
		if (isset($_POST["user_rate"]) || isset($_POST["id_product"])) {
			$user = $_POST["user_rate"];
			$product = $_POST["id_product"];
		}

		$query = "SELECT * FROM rate WHERE id_product='".$product."' AND user_rate='".$user."'";
		$results = mysqli_query($conn, $query);
		$count = mysqli_num_rows($results);
		if ($count >= 1) {
			echo "{result : true}";
		}else{
			echo "{result : false}";
		}
		mysqli_close($conn);
	}

	function addRate(){
		global $conn;
		$date = date("d/m/Y");
		if (isset($_POST["id_product"]) || isset($_POST["user_rate"]) || isset($_POST["title"]) || isset($_POST["content"]) || isset($_POST["star"])) {
			$id_product = $_POST["id_product"];
			$user_rate = $_POST["user_rate"];
			$title = $_POST["title"];
			$content = $_POST["content"];
			$star = $_POST["star"];
		}

		$query = "INSERT INTO rate (id_product,user_rate,title,content,star,date_rate) 
		VALUES ('".$id_product."', 
				'".$user_rate."', 
				'".$title."',
				'".$content."',
				'".$star."',
				'".$date."')";
		if (mysqli_query($conn, $query)) {
			echo "{result : true}";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}
		mysqli_close($conn);
	}

	function getRateByProduct(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_product"])) {
			$id = $_POST["id_product"];
		}
		$query = "SELECT * FROM rate WHERE id_product = ".$id;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"rate\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_product"], 
					"user" => $line["user_rate"],
					"title" => $line["title"],
					"content" => $line["content"],
					"star" => $line["star"],
					"date" => $line["date_rate"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getRateByShopAndUser(){
		global $conn;
		if (isset($_POST["user_rate"]) || isset($_POST["id_shop"])) {
			$user = $_POST["user_rate"];
			$idShop = $_POST["id_shop"];
		}

		$query = "SELECT * FROM rate_shop WHERE id_shop='".$idShop."' AND id_user='".$user."'";
		$results = mysqli_query($conn, $query);
		$count = mysqli_num_rows($results);
		if ($count >= 1) {
			$line = mysqli_fetch_array($results);
			echo json_encode([
				"result" => "rated",
				"number" => $line["rate"]
			]);
		}else{
			echo json_encode([
				"result" => "not rate",
				"number" => 0
			]);
		}
		mysqli_close($conn);
	}

	function addShopRate(){
		global $conn;
		if (isset($_POST["id_shop"]) || isset($_POST["user_rate"]) || isset($_POST["rate"])) {
			$idShop = $_POST["id_shop"];
			$user_rate = $_POST["user_rate"];
			$rate = $_POST["rate"];
		}

		$query = "INSERT INTO rate_shop (id_shop,id_user,rate) 
		VALUES ('".$idShop."', 
				'".$user_rate."', 
				'".$rate."')";
		if (mysqli_query($conn, $query)) {
			echo "{result : true}";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}
		mysqli_close($conn);
	}

	function getRateByShop(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_shop"])) {
			$id = $_POST["id_shop"];
		}
		$query = "SELECT * FROM rate_shop WHERE id_shop = ".$id;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"rate\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array("rate" => $line["rate"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function updateShopRate(){
		global $conn;
		if (isset($_POST["id_shop"]) || isset($_POST["id_user"]) || isset($_POST["rate"])) {
			$idShop = $_POST["id_shop"];
			$idUser = $_POST["id_user"];
			$mRate = $_POST["rate"];
		}

		$query = "UPDATE rate_shop SET rate = ".$mRate." WHERE id_shop = ".$idShop." AND id_user = ".$idUser;
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "update shop successful"
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