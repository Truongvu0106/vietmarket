<?php 
	include_once("config.php");
	$func = $_POST["func"];

	switch ($func) {
		case 'getAllProduct':
			$func();
			break;
		case 'getProductById':
			$func();
			break;
		case 'getProductByCategory':
			$func();
			break;
		case 'getProductByBrand':
			$func();
			break;
		case 'getProductByShop':
			$func();
			break;
		case 'addProduct':
			$func();
			break;
		case 'updateProduct':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllProduct(){
		global $conn;
		$query = "SELECT * FROM product";
		$results = mysqli_query($conn, $query);
		$my_json_array = array();
		echo "{";
		echo "\"products\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_product"], 
					"name" => $line["name_product"],
					"price" => $line["price"],
					"image" => $line["image"],
					"information" => $line["information"],
					"weight" => $line["weight"],
					"type_product" => $line["type_product"],
					"brand" => $line["brand"],
					"rate" => $line["rate"],
					"amount" => $line["amount"],
					"id_shop" => $line["id_shop"],
					"highlight" => $line["hightlight"],
					"discount" => $line["discount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getProductById(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_product"])) {
			$id = $_POST["id_product"];
		}
		$query = "SELECT * FROM product WHERE id_product = ".$id;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"product\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_product"], 
					"name" => $line["name_product"],
					"price" => $line["price"],
					"image" => $line["image"],
					"information" => $line["information"],
					"weight" => $line["weight"],
					"type_product" => $line["type_product"],
					"brand" => $line["brand"],
					"rate" => $line["rate"],
					"amount" => $line["amount"],
					"id_shop" => $line["id_shop"],
					"highlight" => $line["hightlight"],
					"discount" => $line["discount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getProductByCategory(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_category"])) {
			$type_product = $_POST["id_category"];
		}
		$query = "SELECT * FROM product WHERE type_product = ".$type_product;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"products\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_product"], 
					"name" => $line["name_product"],
					"price" => $line["price"],
					"image" => $line["image"],
					"information" => $line["information"],
					"weight" => $line["weight"],
					"type_product" => $line["type_product"],
					"brand" => $line["brand"],
					"rate" => $line["rate"],
					"amount" => $line["amount"],
					"id_shop" => $line["id_shop"],
					"highlight" => $line["hightlight"],
					"discount" => $line["discount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getProductByBrand(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_brand"])) {
			$id_brand = $_POST["id_brand"];
		}
		$query = "SELECT * FROM product WHERE brand = ".$id_brand;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"products\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_product"], 
					"name" => $line["name_product"],
					"price" => $line["price"],
					"image" => $line["image"],
					"information" => $line["information"],
					"weight" => $line["weight"],
					"type_product" => $line["type_product"],
					"brand" => $line["brand"],
					"rate" => $line["rate"],
					"amount" => $line["amount"],
					"id_shop" => $line["id_shop"],
					"highlight" => $line["hightlight"],
					"discount" => $line["discount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getProductByShop(){
		global $conn;
		$my_json_array = array();

		if (isset($_POST["id_shop"])) {
			$id_shop = $_POST["id_shop"];
		}
		$query = "SELECT * FROM product WHERE id_shop = ".$id_shop;
		$results = mysqli_query($conn, $query);
		echo "{";
		echo "\"products\":[";
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				array_push($my_json_array, array(
					"id" => $line["id_product"], 
					"name" => $line["name_product"],
					"price" => $line["price"],
					"image" => $line["image"],
					"information" => $line["information"],
					"weight" => $line["weight"],
					"type_product" => $line["type_product"],
					"brand" => $line["brand"],
					"rate" => $line["rate"],
					"amount" => $line["amount"],
					"id_shop" => $line["id_shop"],
					"highlight" => $line["hightlight"],
					"discount" => $line["discount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function addProduct(){
		global $conn;
		if (isset($_POST["name_product"]) || isset($_POST["price"]) || isset($_POST["image"]) || isset($_POST["information"]) || isset($_POST["weight"]) || isset($_POST["type_product"]) || isset($_POST["brand"]) || isset($_POST["rate"]) || isset($_POST["amount"]) || isset($_POST["id_shop"]) || isset($_POST["rate"]) || isset($_POST["hightlight"]) || isset($_POST["discount"])) {
			$name = $_POST["name_product"];
			$price = $_POST["price"];
			$image = $_POST["image"];
			$information = $_POST["information"];
			$weight = $_POST["weight"];
			$type_product = $_POST["type_product"];
			$brand = $_POST["brand"];
			$rate = $_POST["rate"];
			$amount = $_POST["amount"];
			$id_shop = $_POST["id_shop"];
			$highlight = $_POST["hightlight"];
			$discount = $_POST["discount"];
		}
		
		$query = "INSERT INTO product (name_product, price, image, information, weight, type_product, brand, rate, amount, id_shop, hightlight, discount) 
		VALUES ('".$name."',
				'".$price."',
				'".$image."',
				'".$information."',
				'".$weight."',
				'".$type_product."',
				'".$brand."',
				'".$rate."',
				'".$amount."',
				'".$id_shop."',
				'".$rate."',
				'".$highlight."',
				'".$discount."')"; 
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

	function updateProduct(){
		global $conn;
		if (isset($_POST["id_product"]) || isset($_POST["name_product"]) || isset($_POST["price"]) || isset($_POST["image"]) || isset($_POST["information"]) || isset($_POST["weight"]) || isset($_POST["type_product"]) || isset($_POST["brand"]) || isset($_POST["rate"]) || isset($_POST["amount"]) || isset($_POST["id_shop"]) || isset($_POST["rate"]) || isset($_POST["hightlight"]) || isset($_POST["discount"])) {
			$id = $_POST["id_product"];
			$name = $_POST["name_product"];
			$price = $_POST["price"];
			$image = $_POST["image"];
			$information = $_POST["information"];
			$weight = $_POST["weight"];
			$type_product = $_POST["type_product"];
			$brand = $_POST["brand"];
			$rate = $_POST["rate"];
			$amount = $_POST["amount"];
			$id_shop = $_POST["id_shop"];
			$highlight = $_POST["hightlight"];
			$discount = $_POST["discount"];
		}

		$query = "UPDATE product SET 
				name_product = '".$name."',
				price = '".$price."',
				image = '".$image."',
				information = '".$information."',
				weight = '".$weight."',
				type_product = '".$type_product."',
				brand = '".$brand."',
				rate = '".$rate."',
				amount = '".$amount."',
				id_shop = '".$id_shop."',
				hightlight = '".$hightlight."',
				discount = '".$discount."' WHERE id_product = '".$id."'"; 
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