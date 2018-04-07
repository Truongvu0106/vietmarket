<div id="col-right">
	<div class="card">
		<table class="table">
			<thead>
				<tr>
					<th>
						<div class="checkbox3 checkbox-inline checkbox-check checkbox-light">
							<input type="checkbox" id="select-all"/>
							<label for="select-all">Tất cả</label>
						</div>
					</th>
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
		<div>
			<nav>
	            <?php 
	            	categoryPagination();
	            ?>
	        </nav>
		</div>
	</div>
</div>

<div id="col-left">
	<div class="page-title form-style">
	    <span class="title">Danh mục sản phẩm</span>
	    <div class="description">Thông tin liên quan đến danh mục sản phẩm.</div>

	    <label for="id_tenloaisp">Tên loại sản phẩm</label>
		<input type="text" id="id_tenloaisp" class="form-control" placeholder="Nhập tên loại sản phẩm"/>

		<label>Loại cha</label> </br>
		<select id="select-cha">
			<optgroup label="Loại cha">
				<?php 
					showListParentCategory();
			 	?>	
			</optgroup>
		</select>
		<br>
		<input type="button" class="btn btn-success" value="Thêm" id="btn-them-loaisanpham" />
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

	function categoryPagination(){
		global $conn;
		$query = "SELECT * FROM type_child";
		$results = mysqli_query($conn, $query);
		$total = round(mysqli_num_rows($results)/10);
		echo '<ul class="pagination">';
	    echo  '<li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
               </li>';
		for ($i=1; $i <$total ; $i++) {
			if ($i == 1) {
			 	echo '<li class="active"><a href="#">'.$i.'</a></li>';
			 }else{
			 	echo '<li><a href="#">'.$i.'</a></li>';
			 } 
		}

		echo '<li>
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
	          </li>';
         echo '</ul>';
	}	
	
?>

