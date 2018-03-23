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

		if (isset($_GET["id_product"])) {
			$id = $_GET["id_product"];
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

 ?>