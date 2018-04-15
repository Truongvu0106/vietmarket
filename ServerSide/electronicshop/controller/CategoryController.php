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
		case 'deleteCategoryById':
			$func();
			break;
		case 'searchCategoryByName':
			$func();
			break;
		case 'updateChildCategory':
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
		if (isset($_POST["image_category"])) {
			$path = $_POST["image_category"];
		}
		
		if ($name == "") {
			echo "<p style='color:red'>Chưa nhập tên</p>";
		}elseif ($path == "") {
			echo "<p style='color:red'>Chưa chọn hình ảnh</p>";
		}else{
			$full_path = "/img/category/".$path;
			$query = "INSERT INTO type_child(name_type_child,id_type_parent,image_cate) VALUES('".$name."', '".$idParent."', '".$full_path."')";
			$results = mysqli_query($conn, $query);
			if ($results) {
				echo "<p style='color:green'>Thêm thành công</p>";
			}else{
				echo "<p style='color:red'>Thêm thất bại</p>";
			}
		}
	}

	function updateChildCategory(){
		global $conn;
		$name = $_POST["name"];
		$idParent = $_POST["parent"];
		$idChild = $_POST["idChild"];
		
		if ($name == "") {
			echo "<p style='color:red'>Chưa nhập tên</p>";
		}else{
			$query = "UPDATE type_child SET name_type_child='".$name."', id_type_parent='".$idParent."' WHERE id_type_child='".$idChild."'";
			$results = mysqli_query($conn, $query);
			if ($results) {
				echo "<p style='color:green'>Cập nhật thành công</p>";
			}else{
				echo "<p style='color:red'>Cập nhật thất bại</p>";
			}
		}
	}

	function getListCategoryLimit(){
		global $conn;
		$pageNumber = $_POST["pageNumber"];
		$limit = ($pageNumber-1)*6;
		$query = "SELECT * FROM type_child tc, type_parent tp WHERE tc.id_type_parent = tp.id_type_parent LIMIT ".$limit.", 6";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				echo "<tr>";
				echo '<td data-name-child="'.$line["name_type_child"].'" class="name-type-child">'.$line["name_type_child"].'</td>';
				echo '<td data-name-parent="'.$line["name_type_parent"].'">'.$line["name_type_parent"].'</td>';
				echo '<td data-id="'.$line["id_type_child"].'"><a class="btn btn-success btn-edit-category">Sửa</a> <a class="btn btn-danger btn-delete-category">Xóa</a></td>';
				echo '<td data-image="'.$line["image_cate"].'"></td>';
				echo '<td data-id-parent="'.$line["id_type_parent"].'"></td>';
				echo "</tr>";
			}
		}else{
			echo "Null";
		}
	}

	function deleteCategoryById(){
		global $conn;
		// $id = $_POST["idCategory"];
		// $query = "DELETE FROM type_child WHERE id_type_child = ".$id;
		// $results = mysqli_query($conn, $query);
		// if ($results) {
		// 	echo 1;
		// }else{
		// 	echo 0;
		// }
		echo 1;
	}

	function searchCategoryByName(){
		global $conn;
		$content = $_POST["content"];
		
		$query = "SELECT * FROM type_child WHERE name_type_child LIKE '%".$content."%'";
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