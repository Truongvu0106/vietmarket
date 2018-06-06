package edu.hust.truongvu.choviet.admin.category.child;

import edu.hust.truongvu.choviet.model.entity.Banner;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;

/**
 * Created by truon on 6/6/2018.
 */

public interface ManageChildPresenter {
    void initListChildCategory(int id);
    void addChild(ChildCategory childCategory);
    void updateChild(ChildCategory childCategory, boolean isChangedImage);
    void uploadImage(MyImage myImage);
}
