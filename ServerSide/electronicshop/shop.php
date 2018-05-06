<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getAllShop':
			$func();
			break;
		case 'getShopById':
			$func();
			break;
		default:
			# code...
			break;
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
	
?>