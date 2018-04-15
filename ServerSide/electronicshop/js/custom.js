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

	//Phân trang category
	$("ul.pagination>li").click(function(){
		var pageNumber = $(this).text();
		if ((pageNumber === '&laquo;') || (pageNumber === '&raquo;')) {
			alert("Error");
		}else{
			$("ul.pagination>li").removeClass("active");
			$(this).addClass("active");
			$.ajax({
				url: "../../controller/CategoryController.php",
				type: "POST",
				data: {
					action: "getListCategoryLimit",
					pageNumber: pageNumber
				},
				success:function(data){
					$("table.table").find("tbody").empty();
					$("table.table").find("tbody").append(data);
				}
			});
		}
		
	})

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

	//Phân trang product
	$("ul.product-pagination>li").click(function(){
		var pageNumber = $(this).text();
		if ((pageNumber === '&laquo;') || (pageNumber === '&raquo;')) {
			alert("Error");
		}else{
			$("ul.product-pagination>li").removeClass("active");
			$(this).addClass("active");
			$.ajax({
				url: "../../controller/ProductController.php",
				type: "POST",
				data: {
					action: "getListProductLimit",
					pageNumber: pageNumber
				},
				success:function(data){
					$("table.table").find("tbody").empty();
					$("table.table").find("tbody").append(data);
				}
			});
		}
		
	})

	//Xoa danh muc
	
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

	//Sua danh muc
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

		// $("#upload-img-category").fileinput({
		//     overwriteInitial: true,
		//     initialPreview: [
		//         ".."+imageCategory
		//     ],
		//     initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
		//     initialPreviewFileType: 'image', // image is the default and can be overridden in config below
		//     initialPreviewConfig: [
		//         {caption: nameImage },
		      
		//     ],
		 
		// });

	});

	//Cap nhat danh muc
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

	//Search
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

	//Upload image category
	$("#upload-img-category").fileinput({
        'allowedFileExtensions': ['jpg', 'png', 'gif'],
        uploadUrl: "../../controller/UploadImageCategory.php"
    });
	
});