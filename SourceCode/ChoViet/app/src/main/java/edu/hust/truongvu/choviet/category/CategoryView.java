package edu.hust.truongvu.choviet.category;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;

/**
 * Created by truon on 2/22/2018.
 */

public interface CategoryView {
    void loadParentCategorySuccessful(ArrayList<ParentCategory> parentCategories);
    void loadParentCategoryFalse();
    void loadChildCategorySuccessful(ArrayList<ChildCategory> childCategories);
    void loadChildCategoryFalse();
}
