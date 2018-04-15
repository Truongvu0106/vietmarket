<div>
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
			<nav>
	            <?php 
	            	productPagination();
	            ?>
	        </nav>
		</div>
</div>

<?php 
	function getListProductLimit($limit){
		global $conn;
		$query = "SELECT * FROM product pd, type_child tc, brand b WHERE pd.type_product = tc.id_type_child AND pd.brand = b.id_brand LIMIT ".$limit.", 10";
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

	function productPagination(){
		global $conn;
		$query = "SELECT * FROM product";
		$results = mysqli_query($conn, $query);
		$total = ceil(mysqli_num_rows($results)/10);
		echo '<ul class="pagination product-pagination">';
	    echo  '<li>
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
               </li>';
		for ($i=1; $i<=$total ; $i++) {
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