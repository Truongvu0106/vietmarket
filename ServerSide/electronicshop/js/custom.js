$(document).ready(function(){
	//Thêm loại sản phẩm
	$("#btn-them-loaisanpham").click(function(){
		var nameChild = $("#id_tenloaisp").val();
		var idParent = $("#select-cha :selected").val();
		myThis = $(this);
		$.ajax({
			url: "../../controller/CategoryController.php",
			type: "POST",
			data: {
				action: "addChildCategory",
				name: nameChild,
				parent: idParent
			},
			success:function(data){
				$(".my-notify").empty();
				$(".my-notify").append(data);
			}
		});
	});

	//Phân trang
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

	//Check all
	$("#select-all").change(function(){
		var checkAll = $(this).closest("table").find("tbody input:checkbox");
		if ($(this).is(":checked")) {
			checkAll.prop("checked", true);
		}else{
			checkAll.prop("checked", false);
		}
	});
	
});