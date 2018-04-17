$(document).ready(function(){
	//Thêm loại sản phẩm
	$("#btn-them-loaisanpham").click(function(){
		var nameChild = $("#id_tenloaisp").val();
		var idParent = $("#select-cha :selected").val();
		var image_category = $("#div-img-category").find(".file-footer-caption").attr("title");
		if (idParent === null ) {
			alert("Chưa nhập tên");
		}else if(typeof image_category == undefined){
			alert("Chưa chọn hình");
		}else{
			$.ajax({
				url: "../../controller/CategoryController.php",
				type: "POST",
				data: {
					action: "addChildCategory",
					name: nameChild,
					parent: idParent,
					image_category: image_category
				},
				success:function(data){
					$(".my-notify").empty();
					$(".my-notify").append(data);
				}
			});
		}	
	});

	//Phân trang loại sản phẩm
	$('#category-pagination').bootpag({
	    total: $("#category-pagination").attr("data-total-page"),
	    maxVisible: 4,
	    page: 1
	}).on("page", function(event, num){
	 	$.ajax({
				url: "../../controller/CategoryController.php",
				type: "POST",
				data: {
					action: "getListCategoryLimit",
					pageNumber: num
				},
				success:function(data){
					$("table.table").find("tbody").empty();
					$("table.table").find("tbody").append(data);
				}
			});
	});

	//Xoa loại sản phẩm	
	$("body").delegate(".btn-delete-category", "click", function(){
		var id = $(this).parent().attr("data-id");
		mThis = $(this);
		var conf = confirm("Bạn chắc chắn muốn xóa danh mục này?");
		if (conf) {
			$.ajax({
				url: "../../controller/CategoryController.php",
				type: "POST",
				data: {
					action: "deleteCategoryById",
					idCategory: id
				},
				success:function(data){
					if (data == 1) {
						mThis.closest('tr').remove();
					}else{
						alert("Xóa thất bại");
					}
				}
			});
		}
		
	});

	//Sua loại sản phẩm
	$("body").delegate(".btn-edit-category", "click", function(){
		newHTML = '<div class="file-loading"><input id="upload-img-category" name="upload-img-category" type="file"></div>';
		// $("#div-img-category").empty;
		// $("#div-img-category").append(newHTML);
		line = $(this).closest("tr");
		idCategory = $(this).parent().attr("data-id");

		nameChild = "";
		nameParent = "";
		imageCategory = "";
		idCategoryParent = "";

		line.find("td").each(function(){
			if ($(this).attr("data-name-child")) {
				nameChild = $(this).attr("data-name-child");
			}else if ($(this).attr("data-name-parent")) {
				nameParent = $(this).attr("data-name-parent");
			}else if ($(this).attr("data-image")) {
				imageCategory = $(this).attr("data-image");
			}else if ($(this).attr("data-id-parent")) {
				idCategoryParent = $(this).attr("data-id-parent");
			}
		});

		positionCutImage = imageCategory.lastIndexOf("/");
		nameImage = imageCategory.substring(positionCutImage+1);

		$("#id_tenloaisp").val(nameChild);
		$("#select-cha").val(idCategoryParent).change();

		$("#btn-update-loaisanpham").attr("data-id", idCategory);

	});

	//Cap nhat loại sản phẩm
	$("#btn-update-loaisanpham").click(function(){
		var idChild = $(this).attr("data-id");
		var nameChild = $("#id_tenloaisp").val();
		var idParent = $("#select-cha :selected").val();
		var image_category = $("#div-img-category").find(".file-footer-caption").attr("title");
		if (idParent === null ) {
			alert("Chưa nhập tên");
		}else if(typeof image_category == undefined){
			alert("Chưa chọn hình");
		}else{
			$.ajax({
				url: "../../controller/CategoryController.php",
				type: "POST",
				data: {
					action: "updateChildCategory",
					idChild: idChild,
					name: nameChild,
					parent: idParent,
					// image_category: image_category
				},
				success:function(data){
					$(".my-notify").empty();
					$(".my-notify").append(data);
				}
			});
		}	
	});

	//Tìm kiếm loại sản phẩm
	$("#btn-search").click(function(){
		var searchContent = $("#txt-search").val();
		$.ajax({
				url: "../../controller/CategoryController.php",
				type: "POST",
				data: {
					action: "searchCategoryByName",
					content: searchContent
				},
				success:function(data){
					$("table.table").find("tbody").empty();
					$("table.table").find("tbody").append(data);
				}
		});
	});

	//Upload ảnh loại sản phẩm
	$("#upload-img-category").fileinput({
        'allowedFileExtensions': ['jpg', 'png', 'gif'],
        uploadUrl: "../../controller/UploadImageCategory.php"
    });



    //Phân trang sản phẩm
	$('#product-pagination').bootpag({
	    total: $("#product-pagination").attr("data-total-page"),
	    maxVisible: 5,
	    page: 1
	}).on("page", function(event, num){
	 	$.ajax({
				url: "../../controller/ProductController.php",
				type: "POST",
				data: {
					action: "getListProductLimit",
					pageNumber: num
				},
				success:function(data){
					$("table.table").find("tbody").empty();
					$("table.table").find("tbody").append(data);
				}
			});
	});

	//Tìm kiếm loại sản phẩm
	$("#btn-search-product").click(function(){
		var searchContent = $("#txt-search-product").val();
		$.ajax({
				url: "../../controller/ProductController.php",
				type: "POST",
				data: {
					action: "searchProductByName",
					content: searchContent
				},
				success:function(data){
					$("table.table").find("tbody").empty();
					$("table.table").find("tbody").append(data);
				}
		});
	});
	
});