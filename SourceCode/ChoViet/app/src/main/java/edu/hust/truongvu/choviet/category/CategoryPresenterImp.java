package edu.hust.truongvu.choviet.category;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.ParentCategory;

/**
 * Created by truon on 2/22/2018.
 */

public class CategoryPresenterImp implements CategoryPresenter {

    private CategoryView categoryView;
    public CategoryPresenterImp(CategoryView categoryView){
        this.categoryView = categoryView;

    }


    @Override
    public void initParentCategory() {
        ArrayList<ParentCategory> parentCategories = new ArrayList<>();
        parentCategories.add(new ParentCategory(0, "Đồ điện tử", R.drawable.cate_dodientu));
        parentCategories.add(new ParentCategory(1, "Linh kiện điện tử", R.drawable.cate_linhkien));
        parentCategories.add(new ParentCategory(2, "Đồ gia dụng", R.drawable.cate_dogiadung));
        parentCategories.add(new ParentCategory(3, "Làm đẹp", R.drawable.cate_lamdep));
        parentCategories.add(new ParentCategory(4, "Tạp hóa", R.drawable.cate_taphoa));
        parentCategories.add(new ParentCategory(5, "Thời trang nam", R.drawable.cate_thoitrangnam));
        parentCategories.add(new ParentCategory(6, "Thời trang nữ", R.drawable.cate_thoitrangnu));
        parentCategories.add(new ParentCategory(7, "Phụ kiện thời trang", R.drawable.cate_phukien));
        parentCategories.add(new ParentCategory(8, "Thể thao", R.drawable.cate_thethao));
        categoryView.loadParentCategory(parentCategories);
    }

    @Override
    public void initChildCategory(int id) {
        ArrayList<ChildCategory> childCategories = new ArrayList<>();
        childCategories.add(new ChildCategory(0, "Máy tính", 0, R.drawable.iphone));
        childCategories.add(new ChildCategory(0, "Điện thoại", 0, R.drawable.iphone));
        childCategories.add(new ChildCategory(0, "Máy ảnh", 0, R.drawable.iphone));
        childCategories.add(new ChildCategory(1, "USB", 1, R.drawable.iphone));
        childCategories.add(new ChildCategory(1, "Bàn phím", 1, R.drawable.iphone));
        childCategories.add(new ChildCategory(1, "Chuột", 1, R.drawable.iphone));
        if (childCategories == null){
            return;
        }else {
            ArrayList<ChildCategory> filterData = new ArrayList<>();
            for (ChildCategory category : childCategories){
                if (category.getIdParent() == id){
                    filterData.add(category);
                }
            }
            categoryView.loadChildCategory(filterData);
        }
    }
}
