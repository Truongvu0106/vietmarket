<div id="col-right">
	<div class="card">
		<table class="table">
			<thead>
				<tr>
					<th>
						Ảnh
					</th>
					<th>
						Tên loại
					</th>

					<th>
						Mức giá
					</th>
				</tr>
			</thead>
			<tbody>
				<?php 
					getListPayment();
				 ?>
			</tbody>
		</table>
		
		
		
		<div id="col-left">
		
		</div>
	</div>
</div>

<div id="col-left">
	<div class="page-title form-style">
	    <span class="title">Loại hình thanh toán</span>
	    <div class="description">Thông tin liên quan đến cách thức thanh toán.</div>

	    <label for="id_tenloaisp">Tên cách thức thanh toán</label>
	    <div style="margin-right: 10px">
	    	<input type="text" id="id_name_payment" class="form-control" placeholder="Nhập tên cách thức thanh toán"  />
	    </div>
	    <br>
		<label for="price_payment">Mức giá</label>
		<div style="margin-right: 10px">
			<input type="number" class="form-control" id="price_payment" placeholder="Nhập mức giá" />
		</div>
		<br>
		<label for="upload-img-payment">Chọn hình ảnh</label>
		<br>
		<div id="div-img-payment" class="form-group" style="margin-right: 10px">
            <div class="file">
			    <input id="upload-img-payment" name="upload-img-payment" type="file">
			</div>
        </div>

		<input type="button" class="btn btn-success" value="Thêm" id="btn-them-payment" style="width: 200px" />
		<input type="button" class="btn btn-success" value="Cập nhật" id="btn-update-payment" style="width: 200px" />
		<div class="my-notify">
					
		</div>
	</div>
</div>

<?php 
	function getListPayment(){
		global $conn;
		$query = "SELECT * FROM payment";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				echo "<tr>";
				echo '<td><img style="width:50px; height:50px" src="../..'.$line["image"].'" /></td>';
				echo '<td data-name-payment="'.$line["name"].'">'.$line["name"].'</td>';
				echo '<td data-price-payment="'.$line["price"].'">'.$line["price"].'</td>';
				echo '<td data-id="'.$line["id"].'"><a class="btn btn-success btn-edit-payment">Sửa</a> <a class="btn btn-danger btn-delete-payment">Xóa</a></td>';
				echo '<td data-image-payment="'.$line["image"].'"></td>';
				echo "</tr>";
			}
		}else{
			echo "Null";
		}
	}

	function getTotalPage(){
		global $conn;
		$query = "SELECT * FROM payment";
		$results = mysqli_query($conn, $query);
		$total = mysqli_num_rows($results);
		echo $total;
	}	
	
?>

