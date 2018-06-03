package edu.hust.truongvu.choviet.admin.category;

import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;

/**
 * Created by truon on 5/30/2018.
 */

public interface ManageCategoryPresenter {
    void initListParentCategory();
    void initListChildCategory(int id);
    void addParentCategory(ParentCategory parentCategory);
    void addChildCategory(ChildCategory childCategory);
    void updateParentCategory(ParentCategory parentCategory);
    void updateChildCategory(ChildCategory childCategory);
}
