package edu.hust.truongvu.choviet.category;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.ParentCategory;

/**
 * Created by truon on 2/22/2018.
 */

public interface CategoryView {
    void loadParentCategorySuccessful(ArrayList<ParentCategory> parentCategories);
    void loadParentCategoryFalse();
    void loadChildCategorySuccessful(ArrayList<ChildCategory> childCategories);
    void loadChildCategoryFalse();
}
