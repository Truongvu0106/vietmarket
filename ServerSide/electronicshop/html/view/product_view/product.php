<div class="card">
	<div id="col-right">
		<table class="table-search">
			<tr>
				<td><input id="txt-search-product" type="text" class="form-control" placeholder="Tìm kiếm" /></td>
				<td><button id="btn-search-product" class="btn btn-default"><i class="glyphicon glyphicon-search"></i></button></td>
			</tr>
		</table>
	</div>
	<table class="table">
			<thead>
				<tr>
					<th>Ảnh</th>
					<th>Tên sản phẩm</th>
					<th>Giá</th>
					<th>Cân nặng</th>
					<th>Loại sản phẩm</th>
					<th>Thương hiệu</th>
					<th>Bình chọn</th>
					<th>Số lượng trong kho</th>
					<th>Giảm giá</th>
				</tr>
			</thead>
			<tbody>
				<?php 
					getListProductLimit(0);
				?>
			</tbody>
		</table>
		<div>
			<div id="product-pagination" data-total-page=<?php getTotalPage() ?>>
				
			</div>
		</div>
</div>

<?php 
	function getTotalPage(){
		global $conn;
		$query = "SELECT * FROM product";
		$results = mysqli_query($conn, $query);
		$total = ceil(mysqli_num_rows($results)/6);
		echo $total;
	}

	function getListProductLimit($limit){
		global $conn;
		$query = "SELECT * FROM product pd, type_child tc, brand b WHERE pd.type_product = tc.id_type_child AND pd.brand = b.id_brand LIMIT ".$limit.", 6";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				$path_image = $line["image"];
				$first = strtok($path_image, '@');
				echo "<tr>";
				echo '<td><img style="width:50px; height:50px" src="../..'.$first.'" /></td>';
				echo '<td>'.$line["name_product"].'</td>';
				echo '<td>'.$line["price"].'</td>';
				echo '<td>'.$line["weight"].'</td>';
				echo '<td>'.$line["name_type_child"].'</td>';
				echo '<td>'.$line["name_brand"].'</td>';
				echo '<td>'.$line["rate"].'</td>';
				echo '<td>'.$line["amount"].'</td>';
				echo '<td>'.$line["discount"].'</td>';
				echo "</tr>";
			}
		}else{
			echo "Null";
		}
	}
?>