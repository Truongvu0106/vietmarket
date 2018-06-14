<?php 
	include_once("config.php");
	$func = $_POST["func"];
	
	switch ($func) {
		case 'getAllOrder':
			$func();
			break;
		case 'getOrderByUser':
			$func();
			break;
		case 'getDetailsOrderByShop':
			$func();
			break;
		case 'getDetailsOrderById':
			$func();
			break;
		case 'addNewOrder':
			$func();
			break;
		case 'countProductInOrder':
			$func();
			break;
		case 'countOrderByTime':
			$func();
			break;
		case 'updateStatusOrder':
			$func();
			break;
		case 'updateStatusDetailsOrder':
			$func();
			break;
		case 'deleteDetailsOrder':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllOrder(){
		global $conn;
		
		$query = "SELECT * FROM product_order";
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"orders\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id_order" => $line["id_order"], 
					"id_user" => $line["id_user"],
					"fullname" => $line["fullname"],
					"phone" => $line["phone"],
					"date_order" => $line["date_order"],
					"status" => $line["status"],
					"type_transport" => $line["type_transport"],
					"type_payment" => $line["type_payment"],
					"value" => $line["value"],
					"address" => $line["address"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getOrderByUser(){
		global $conn;

		if (isset($_POST["id_user"]) || isset($_POST["status"])) {
			$idUser = $_POST["id_user"];
			$status = $_POST["status"];
		}

		if ($idUser == -1) {
			$query = "SELECT * FROM product_order WHERE id_user = ".$idUser;
		}else{
			$query = "SELECT * FROM product_order WHERE id_user = ".$idUser." AND status = ".$status;
		}
		
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"orders\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id_order" => $line["id_order"], 
					"id_user" => $line["id_user"],
					"fullname" => $line["fullname"],
					"phone" => $line["phone"],
					"date_order" => $line["date_order"],
					"status" => $line["status"],
					"type_transport" => $line["type_transport"],
					"type_payment" => $line["type_payment"],
					"value" => $line["value"],
					"address" => $line["address"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}


	function addNewOrder(){
		global $conn;
		if (isset($_POST["list_details"]) || isset($_POST["id_user"]) || isset($_POST["full_name"]) || isset($_POST["phone"]) || isset($_POST["status"]) || isset($_POST["type_transport"]) || isset($_POST["type_payment"]) || isset($_POST["value"]) || isset($_POST["address"])) {
			$listDetails = $_POST["list_details"];
			$idUser = $_POST["id_user"];
			$fullname = $_POST["full_name"];
			$phone = $_POST["phone"];
			$dateOrder = round(microtime(true) * 1000);
			$status = $_POST["status"];
			$typeTransport = $_POST["type_transport"];
			$typePayment = $_POST["type_payment"];
			$value = $_POST["value"];
			$address = $_POST["address"];
		}
		
		$query = "INSERT INTO product_order (id_user, fullname, phone, date_order, status, type_transport, type_payment, value, address) 
		VALUES ('".$idUser."',
				'".$fullname."',
				'".$phone."',
				'".$dateOrder."',
				'".$status."',
				'".$typeTransport."',
				'".$typePayment."',
				'".$value."',
				'".$address."')"; 
		if (mysqli_query($conn, $query)) {
			
			$idOrder = mysqli_insert_id($conn);
			$jsonFromAndroid = json_decode($listDetails);
			$jsonArray = $jsonFromAndroid->list_products;
			for ($i=0; $i < count($jsonArray); $i++) { 
				$jsonObject = $jsonArray[$i];
				$midProduct = $jsonObject->id_product;
				$midShop = $jsonObject->id_shop;
				$mNumber = $jsonObject->number;
				$mStatus = $jsonObject->status;

				$mQuery = "INSERT INTO order_details (id_order, id_product, id_shop, number, date_order_shop, status) 
				VALUES ('".$idOrder."',
						'".$midProduct."',
						'".$midShop."',
						'".$mNumber."',
						'".$dateOrder."',
						'".$mStatus."')"; 
				$mResults = mysqli_query($conn, $mQuery);
			}
			echo "{result : true}";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}

		mysqli_close($conn);
	}

	function getDetailsOrderByShop(){
		global $conn;
		if (isset($_POST["id_shop"])) {
			$idShop = $_POST["id_shop"];
		}

		$query = "SELECT * FROM order_details WHERE id_shop = ".$idShop;
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"orderdetails\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"id_order" => $line["id_order"],
					"id_product" => $line["id_product"],
					"id_shop" => $line["id_shop"],
					"number" => $line["number"],
					"date_order_shop" => $line["date_order_shop"],
					"status" => $line["status"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getDetailsOrderById(){
		global $conn;
		if (isset($_POST["id"])) {
			$idOrder = $_POST["id"];
		}

		$query = "SELECT * FROM order_details WHERE id_order = ".$idOrder;
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"orderdetails\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"id_order" => $line["id_order"],
					"id_product" => $line["id_product"],
					"id_shop" => $line["id_shop"],
					"number" => $line["number"],
					"date_order_shop" => $line["date_order_shop"],
					"status" => $line["status"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function countProductInOrder(){
		global $conn;
		if (isset($_POST["id_shop"]) || isset($_POST["id_product"])) {
			$idShop = $_POST["id_shop"];
			$idProduct = $_POST["id_product"];
		}

		$query = "SELECT * FROM order_details WHERE id_shop = ".$idShop." AND id_product = ".$idProduct;
		$results = mysqli_query($conn, $query);
		$count = mysqli_num_rows($results);
		echo json_encode([
			"number" => $count
			]);
		mysqli_close($conn);
	}

	function countOrderByTime(){
		global $conn;
		if (isset($_POST["id_shop"]) || isset($_POST["start_date"]) || isset($_POST["end_date"])) {
			$idShop = $_POST["id_shop"];
			$startDate = $_POST["start_date"];
			$endDate = $_POST["end_date"];
		}

		$query = "";
		if ($idShop == 0) {
			$query = "SELECT * FROM order_details WHERE date_order_shop >= ".$startDate. " AND date_order_shop <= ".$endDate;
		}else{
			$query = "SELECT * FROM order_details WHERE id_shop = ".$idShop." AND date_order_shop >= ".$startDate. " AND date_order_shop <= ".$endDate;
		}

		$results = mysqli_query($conn, $query);
		$count = mysqli_num_rows($results);
		echo json_encode([
			"number" => $count
			]);
		mysqli_close($conn);
	}

	function updateStatusOrder(){
		global $conn;
		if (isset($_POST["id"]) || isset($_POST["status"])) {
			$id = $_POST["id"];
			$status = $_POST["status"];
		}

		$query = "UPDATE product_order SET status = '".$status."' WHERE id_order = '".$id."'";
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "update staus successful"
			]);
		}else{
			echo json_encode([
				"result" => "false",
				"message" => "error : ".$query."</br>".$conn->error
			]);
		}

		mysqli_close($conn); 
	}

	function updateStatusDetailsOrder(){
		global $conn;
		if (isset($_POST["id"]) || isset($_POST["status"])) {
			$id = $_POST["id"];
			$status = $_POST["status"];
		}

		$query = "UPDATE order_details SET status = '".$status."' WHERE id = '".$id."'";
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "update staus successful"
			]);
		}else{
			echo json_encode([
				"result" => "false",
				"message" => "error : ".$query."</br>".$conn->error
			]);
		}

		mysqli_close($conn); 
	}

	function deleteDetailsOrder(){
		global $conn;
		if (isset($_POST["id"])) {
			$id = $_POST["id"];
		}

		$query = "DELETE FROM order_details WHERE id = ".$id; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "delete successful"
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