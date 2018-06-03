<?php 
	include_once("config.php");

	$func = $_POST["func"];

	switch ($func) {
		case 'getAllPromotion':
			$func();
			break;
		case 'addPromotion':
			$func();
			break;
		case 'deletePromotion':
			$func();
			break;
		case 'updateNumber':
			$func();
			break;
		case 'getPromotionByCode':
			$func();
			break;
		default:
			# code...
			break;
	}

	function getAllPromotion(){
		global $conn;
		$query_parent = "SELECT * FROM promotion";
		$results_parent = mysqli_query($conn, $query_parent);
		$my_json_array = array();
		echo "{";
		echo "\"promotions\":[";
		if ($results_parent) {
			while ($line = mysqli_fetch_array($results_parent)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"code" => $line["code"],
					"start" => $line["start"],
					"end" => $line["end"],
					"number" => $line["number"],  
					"amount" => $line["amount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function getPromotionByCode(){
		global $conn;
		if (isset($_POST["code"])) {
			$code = $_POST["code"];
		}

		$query_parent = "SELECT * FROM promotion WHERE code = '".$code."'";
		$results_parent = mysqli_query($conn, $query_parent);
		$my_json_array = array();
		echo "{";
		echo "\"promotions\":[";
		if ($results_parent) {
			while ($line = mysqli_fetch_array($results_parent)) {
				array_push($my_json_array, array(
					"id" => $line["id"], 
					"code" => $line["code"],
					"start" => $line["start"],
					"end" => $line["end"],
					"number" => $line["number"],  
					"amount" => $line["amount"]));
			}
			echo json_encode($my_json_array, JSON_UNESCAPED_UNICODE);
		}
		echo "]}";
		mysqli_close($conn);
	}

	function addPromotion(){
		global $conn;
		if (isset($_POST["code"]) || isset($_POST["start"]) || isset($_POST["end"]) || isset($_POST["number"]) || isset($_POST["amount"])) {
			$code = $_POST["code"];
			$start = $_POST["start"];
			$end = $_POST["end"];
			$number = $_POST["number"];
			$amount = $_POST["amount"];
		}
		
		$query = "INSERT INTO promotion (code, start, end, number, amount) 
		VALUES ('".$code."',
				'".$start."',
				'".$end."',
				'".$number."',
				'".$amount."')"; 
		if (mysqli_query($conn, $query)) {
			echo "{result : true}";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}

		mysqli_close($conn);
	}

	function deletePromotion(){
		global $conn;
		if (isset($_POST["id"])) {
			$id = $_POST["id"];
		}

		$query = "DELETE FROM promotion WHERE id = ".$id; 
		if (mysqli_query($conn, $query)) {
			echo json_encode([
				"result" => "true",
				"message" => "delete successful"
			]);
		}else{
			echo json_encode([
				"result" => "false",
				"message" => "error : ".$query."</br>".$conn->error.""
			]);
		}
	}

	function updateNumber(){
		global $conn;
		if (isset($_POST["id"]) || isset($_POST["number"])) {
			$id = $_POST["id"];
			$number = $_POST["number"];
		}
		
		$query = "UPDATE promotion SET number = ".$number." WHERE id = ".$id; 
		if (mysqli_query($conn, $query)) {
			echo "{result : true}";
		}else{
			echo "{result : false, error : ".$query."</br>".$conn->error."}";
		}

		mysqli_close($conn);
	}


 ?>