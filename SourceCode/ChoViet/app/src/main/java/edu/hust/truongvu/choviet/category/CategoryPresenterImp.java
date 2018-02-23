package edu.hust.truongvu.choviet.category;

import java.util.ArrayList;

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
    public void initCategory() {
        ArrayList<ParentCategory> parentCategories = new ArrayList<>();
        ArrayList<ChildCategory> childCategories = new ArrayList<>();
        categoryView.loadParentCategory(parentCategories);
        categoryView.loadChildCategory(childCategories);
    }
}
