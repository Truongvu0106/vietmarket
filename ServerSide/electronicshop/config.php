<?php  
	define('DB_SERVER', 'localhost');
	define('DB_USERNAME', 'root');
	define('DB_PASSWORD', '');
	define('DB_NAME', 'vietmarket');

	date_default_timezone_get("Asia/Ho_Chi_Minh");
	$conn = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);
	mysqli_set_charset($conn, "utf8");
	if (!$conn) {
		die("Connect error: ".mysqli_connect_errno());
	}
	
	
	
	
?>