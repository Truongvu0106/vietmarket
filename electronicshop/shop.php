<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getAllShop':
			$func();
			break;
		case 'getTopShop':
			$func();
			break;
		case 'getShopById':
			$func();
			break;
		case 'isFollowing':
			$func();
			break;
		case 'follow':
			$func();
			break;
		case 'unFollow':
			$func();
			break;
		case 'getListShopFollow':
			$func();
			break;
		case 'getShopByOwner':
			$func();
			break;
		case 'registerShop':
			$func();
			break;
		case 'getNumberUserFollowing':
			$func();
			break;
		case 'updateRateShop':
			$func();
			break;
		default:
			# code...
			break;
	}

	function registerShop(){
		global $conn;
		if (isset($_POST["name_shop"]) || isset($_POST["slogan"]) || isset($_POST["img_avatar"]) || isset($_POST["img_cover"]) || isset($_POST["id_owner"]) || isset($_POST["address"]) || isset($_POST["phone"]) || isset($_POST["website"]) || isset($_POST["rate"]) || isset($_POST["highlight"])) {
			$name = $_POST["name_shop"];
			$slogan = $_POST["slogan"];
			$avatar = $_POST["img_avatar"];
			$cover = $_POST["img_cover"];
			$owner = $_POST["id_owner"];
			$address = $_POST["address"];
			$phone = $_POST["phone"];
			$website = $_POST["website"];
			$rate = $_POST["rate"];
			$highlight = $_POST["highlight"];
		}
		
		$query = "INSERT INTO shop (name_shop, slogan, img_avatar, img_cover, id_owner, address, phone, website, rate, highlight) 
		VALUES ('".$name."',
				'".$slogan."',
				'".$avatar."',
				'".$cover."',
				'".$owner."',
				'".$address."',
				'".$phone."',
				'".$website."',
				'".$rate."',
				'".$highlight."')"; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "insert product successful"
			]);
		}else{
			echo json_encode([
			"result" => "false",
			"message" => "error : ".$query."</br>".$conn->error
			]);
		}

		mysqli_close($conn);
	}

	function getShopByOwner(){
		global $conn;
		if (isset($_POST["id_owner"])) {
			$idOwner = $_POST["id_owner"];
		}

		$query = "SELECT * FROM shop WHERE id_owner = ".$idOwner;
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"shop\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_shop"], 
					"name" => $line["name_shop"],
					"slogan" => $line["slogan"],
					"avatar" => $line["img_avatar"],
					"cover" => $line["img_cover"],
					"owner" => $line["id_owner"],
					"address" => $line["address"],
					"phone" => $line["phone"],
					"website" => $line["website"],
					"rate" => $line["rate"],
					"highlight" => $line["highlight"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);

	}

	function getAllShop(){
		global $conn;
		$query = "SELECT * FROM shop";
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"shops\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_shop"], 
					"name" => $line["name_shop"],
					"slogan" => $line["slogan"],
					"avatar" => $line["img_avatar"],
					"cover" => $line["img_cover"],
					"owner" => $line["id_owner"],
					"address" => $line["address"],
					"phone" => $line["phone"],
					"website" => $line["website"],
					"rate" => $line["rate"],
					"highlight" => $line["highlight"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getTopShop(){
		global $conn;
		$query = "SELECT * FROM shop ORDER BY rate DESC";
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"shops\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_shop"], 
					"name" => $line["name_shop"],
					"slogan" => $line["slogan"],
					"avatar" => $line["img_avatar"],
					"cover" => $line["img_cover"],
					"owner" => $line["id_owner"],
					"address" => $line["address"],
					"phone" => $line["phone"],
					"website" => $line["website"],
					"rate" => $line["rate"],
					"highlight" => $line["highlight"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getShopById(){
		global $conn;
		if (isset($_POST["id"])) {
			$idShop = $_POST["id"];
		}

		$query = "SELECT * FROM shop WHERE id_shop = ".$idShop;
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"shops\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_shop"], 
					"name" => $line["name_shop"],
					"slogan" => $line["slogan"],
					"avatar" => $line["img_avatar"],
					"cover" => $line["img_cover"],
					"owner" => $line["id_owner"],
					"address" => $line["address"],
					"phone" => $line["phone"],
					"website" => $line["website"],
					"rate" => $line["rate"],
					"highlight" => $line["highlight"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function isFollowing(){
		global $conn;
		if (isset($_POST["idUser"]) || isset($_POST["idShop"])) {
			$idUser = $_POST["idUser"];
			$idShop = $_POST["idShop"];
		}

		$query = "SELECT * FROM shop_follow WHERE id_user='".$idUser."' AND id_shop='".$idShop."'";
		$results = mysqli_query($conn, $query);
		$count = mysqli_num_rows($results);
		if ($count >= 1) {
			echo json_encode([
				"result" => "true"
			]);
		}else{
			echo json_encode([
				"result" => "false"
			]);
		}
		mysqli_close($conn);
	}

	function follow(){
		global $conn;
		if (isset($_POST["idShop"]) || isset($_POST["idUser"])) {
			$idShop = $_POST["idShop"];
			$idUser = $_POST["idUser"];
		}

		$query = "INSERT INTO shop_follow (id_shop, id_user) 
		VALUES ('".$idShop."',
				'".$idUser."')"; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "follow successful"
			]);
		}else{
			echo json_encode([
				"result" => "false",
				"message" => "error : ".$query."</br>".$conn->error.""
			]);
		}

		mysqli_close($conn);
	}

	function unFollow(){
		global $conn;
		if (isset($_POST["idShop"]) || isset($_POST["idUser"])) {
			$idShop = $_POST["idShop"];
			$idUser = $_POST["idUser"];
		}

		$query = "DELETE FROM shop_follow WHERE id_shop = ".$idShop." AND id_user =".$idUser.""; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "unfollow successful"
			]);
		}else{
			echo json_encode([
				"result" => "false",
				"message" => "error : ".$query."</br>".$conn->error.""
			]);
		}
	}

	function getListShopFollow(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["idUser"])) {
			$idUser = $_POST["idUser"];
		}
		$query = "SELECT * FROM shop_follow WHERE id_user = ".$idUser;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"shops\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id_shop" => $line["id_shop"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getNumberUserFollowing(){
		global $conn;
		if (isset($_POST["id_shop"])) {
			$idShop = $_POST["id_shop"];
		}

		$query = "SELECT * FROM shop_follow WHERE id_shop = ".$idShop;
		$results = mysqli_query($conn, $query);
		$count = mysqli_num_rows($results);
		echo json_encode([
			"number" => $count
			]);
		mysqli_close($conn);
	}

	function updateRateShop(){
		global $conn;
		if (isset($_POST["id_shop"]) || isset($_POST["rate"])) {
			$id = $_POST["id_shop"];
			$rate = $_POST["rate"];
		}

		$query = "UPDATE shop SET rate = '".$rate."' WHERE id_shop = '".$id."'";
		if (mysqli_query($conn, $query)) {
			echo json_encode([
			"result" => "true",
			"message" => "update product successful"
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