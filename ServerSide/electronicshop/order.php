<?php 
	include_once("config.php");
	$func = $_POST["func"];
	
	switch ($func) {
		case 'getListOrderByShopId':
			$func();
			break;
		case 'getDetailsOrderById':
			$func();
			break;
		case 'addNewOrder':
			$func();
			break;
		default:
			# code...
			break;
	}

	function addNewOrder(){
		global $conn;
		if (isset($_POST["list_details"]) || isset($_POST["full_name"]) || isset($_POST["phone"]) || isset($_POST["status"]) || isset($_POST["type_transport"]) || isset($_POST["type_payment"]) || isset($_POST["value"]) || isset($_POST["address"])) {
			$listDetails = $_POST["list_details"];
			$fullname = $_POST["full_name"];
			$phone = $_POST["phone"];
			$dateOrder = round(microtime(true) * 1000);
			$status = $_POST["status"];
			$typeTransport = $_POST["type_transport"];
			$typePayment = $_POST["type_payment"];
			$value = $_POST["value"];
			$address = $_POST["address"];
		}
		
		$query = "INSERT INTO product_order (fullname, phone, date_order, status, type_transport, type_payment, value, address) 
		VALUES ('".$fullname."',
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

				$mQuery = "INSERT INTO order_details (id_order, id_product, id_shop, number) 
				VALUES ('".$idOrder."',
						'".$midProduct."',
						'".$midShop."',
						'".$mNumber."')"; 
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
					"id_order" => $line["id_customer"],
					"id_product" => $line["id_product"],
					"id_shop" => $line["id_shop"],
					"number" => $line["number"]));
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
					"id_order" => $line["id_customer"],
					"id_product" => $line["id_product"],
					"id_shop" => $line["id_shop"],
					"number" => $line["number"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

 ?>