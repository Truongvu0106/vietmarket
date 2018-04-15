<div id="col-right">
	<div class="card">
		<div id="col-right">
			<table class="table-search">
				<tr>
					<td><input id="txt-search" type="text" class="form-control" placeholder="Tìm kiếm" /></td>
					<td><button id="btn-search" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button></td>
				</tr>
			</table>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>
						Tên loại
					</th>

					<th>
						Mã loại cha
					</th>
				</tr>
			</thead>
			<tbody>
				<?php 
					getListCategoryLimit(0);
				 ?>
			</tbody>
		</table>
		
		
		<div id="col-right">
			<div id="category-pagination" data-total-page=<?php getTotalPage() ?>>
				
			</div>
		</div>
		<div id="col-left">
		
		</div>
	</div>
</div>

<div id="col-left">
	<div class="page-title form-style">
	    <span class="title">Danh mục sản phẩm</span>
	    <div class="description">Thông tin liên quan đến danh mục sản phẩm.</div>

	    <label for="id_tenloaisp">Tên loại sản phẩm</label>
	    <div style="margin-right: 10px">
	    	<input type="text" id="id_tenloaisp" class="form-control" placeholder="Nhập tên loại sản phẩm"  />
	    </div>
		<label>Loại cha</label> </br>
		<select id="select-cha">
			<optgroup label="Loại cha">
				<?php 
					showListParentCategory();
			 	?>	
			</optgroup>
		</select>
		<br>
		<label for="upload-img-category">Chọn hình ảnh</label>
		<br>
		<div id="div-img-category" class="form-group" style="margin-right: 10px">
            <div class="file">
			    <input id="upload-img-category" name="upload-img-category" type="file">
			</div>
        </div>

		<input type="button" class="btn btn-success" value="Thêm" id="btn-them-loaisanpham" style="width: 200px" />
		<input type="button" class="btn btn-success" value="Cập nhật" id="btn-update-loaisanpham" style="width: 200px" />
		<div class="my-notify">
					
		</div>
	</div>
</div>

<?php 
	function showListParentCategory(){
		global $conn;
		$query = "SELECT * FROM type_parent";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				echo "<option value='".$line["id_type_parent"]."'>".$line["name_type_parent"]."</option>";
			}
		}else{
			echo "Null";
		}
	}

	function getListCategoryLimit($limit){
		global $conn;
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

	function getTotalPage(){
		global $conn;
		$query = "SELECT * FROM type_child";
		$results = mysqli_query($conn, $query);
		$total = ceil(mysqli_num_rows($results)/6);
		echo $total;
	}	
	
?>

