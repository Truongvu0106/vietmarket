<?php 
	include_once("config.php");
	
	$image = $_POST["image"];
	$name = $_POST["name"];

	$target_dir = "img/category/".$name.".jpeg";

	if (file_put_contents($target_dir, base64_decode($image))) {
		echo json_encode([
			"result" => "true",
			"message" => "upload successful".$name
		]);
	}else{
		echo json_encode([
			"result" => "false",
			"message" => "upload false"
		]);
	}

 ?>