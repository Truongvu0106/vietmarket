<div id="col-right">
	<div class="card">
		<table class="table">
			<thead>
				<tr>
					<th>
						Tên
					</th>
					<th>
						Ghi chú
					</th>

					<th>
						Mức giá
					</th>
				</tr>
			</thead>
			<tbody>
				<?php 
					getListTransport();
				 ?>
			</tbody>
		</table>
		
		
		
		<div id="col-left">
		
		</div>
	</div>
</div>

<div id="col-left">
	<div class="page-title form-style">
	    <span class="title">Loại hình vận chuyển</span>
	    <div class="description">Thông tin liên quan đến cách thức vận chuyển.</div>

	    <label for="id_name_tranport">Tên cách thức vận chuyển</label>
	    <div style="margin-right: 10px">
	    	<input type="text" id="id_name_transport" class="form-control" placeholder="Nhập tên cách thức thanh toán"  />
	    </div>
		<br>
		<label for="price_transport">Mức giá</label>
		<div style="margin-right: 10px">
			<input type="number" class="form-control" id="price_transport" placeholder="Nhập mức giá" />
		</div>
		<br>
		<label for="upload-img-transport">Chọn hình ảnh</label>
		<br>
		<div id="div-img-transport" class="form-group" style="margin-right: 10px">
            <div class="file">
			    <input id="upload-img-transport" name="upload-img-transport" type="file">
			</div>
        </div>

		<input type="button" class="btn btn-success" value="Thêm" id="btn-add-transport" style="width: 200px" />
		<input type="button" class="btn btn-success" value="Cập nhật" id="btn-update-transport" style="width: 200px" />
		<div class="my-notify">
					
		</div>
	</div>
</div>

<?php 
	function getListTransport(){
		global $conn;
		$query = "SELECT * FROM transport";
		$results = mysqli_query($conn, $query);
		if ($results) {
			while ($line = mysqli_fetch_array($results)) {
				echo "<tr>";
				echo '<td data-name-transport="'.$line["name"].'">'.$line["name"].'</td>';
				echo '<td data-note-transport="'.$line["note"].'">'.$line["note"].'</td>';
				echo '<td data-price-transport="'.$line["price"].'">'.$line["price"].'</td>';
				echo '<td data-id="'.$line["id"].'"><a class="btn btn-success btn-edit-payment">Sửa</a> <a class="btn btn-danger btn-delete-payment">Xóa</a></td>';
				echo "</tr>";
			}
		}else{
			echo "Null";
		}
	}

	function getTotalPage(){
		global $conn;
		$query = "SELECT * FROM transport";
		$results = mysqli_query($conn, $query);
		$total = mysqli_num_rows($results);
		echo $total;
	}	
	
?>

