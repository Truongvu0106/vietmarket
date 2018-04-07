<?php 
	include_once("../config.php");
	$func = $_POST["action"];
	switch ($func) {
		case 'addChildCategory':
			$func();
			break;
		case 'getListCategoryLimit':
			$func();
			break;
		default:
			# code...
			break;
	}

	function addChildCategory(){
		global $conn;
		$name = $_POST["name"];
		$idParent = $_POST["parent"];
		$path = "abc";

		if ($name == "") {
			echo "<p style='color:red'>Chưa nhập tên</p>";
		}else{
			$query = "INSERT INTO type_child(name_type_child,id_type_parent,image) VALUES('".$name."', '".$idParent."', '".$path."')";
			$results = mysqli_query($conn, $query);
			if ($results) {
				echo "<p style='color:green'>Thêm thành công</p>";
			}else{
				echo "<p style='color:red'>Thêm thất bại</p>";
			}
		}
	}

	function getListCategoryLimit(){
		global $conn;
		$pageNumber = $_POST["pageNumber"];
		$limit = ($pageNumber-1)*10;
		$query = "SELECT * FROM type_child LIMIT ".$limit.", 10";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				echo "<tr>";
				echo '<th><div class="checkbox3 checkbox-inline checkbox-check checkbox-light">
							<input type="checkbox" id="cb-'.$line["id_type_child"].'"/>
							<label for="cb-'.$line["id_type_child"].'"></label>
					 </div></th>';
				echo '<th>'.$line["name_type_child"].'</th>';
				echo '<th>'.$line["id_type_parent"].'</th>';
				echo "</tr>";
			}
		}else{
			echo "Null";
		}
	}
?>