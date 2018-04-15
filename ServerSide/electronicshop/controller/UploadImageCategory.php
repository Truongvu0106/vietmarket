<?php 
	if (empty($_FILES['upload-img-category'])) {
	    echo json_encode(['error'=>'No files found for upload.']);  
	    return; 
	}
	$image = $_FILES["upload-img-category"];
	$file_dir = "../img/category/";
	$filename = $image['name'];
	$file_tmp = $image['tmp_name'];
	
	if(move_uploaded_file($file_tmp, $file_dir.$filename)){
		echo json_encode(['notification'=>'Upload successful.']); 
	}else{
		echo json_encode(['notification'=>'Upload false.']); 
	}
?>