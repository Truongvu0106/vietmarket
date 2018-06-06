package edu.hust.truongvu.choviet.admin.category.parent;

import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;

/**
 * Created by truon on 6/6/2018.
 */

public interface ManageParentPresenter {
    void initListParentCategory();
    void addParent(ParentCategory parentCategory);
    void updateParent(ParentCategory parentCategory, boolean isChangedImage);
    void uploadImage(MyImage myImage);
}
