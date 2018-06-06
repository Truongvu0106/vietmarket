package edu.hust.truongvu.choviet.admin.category.child;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.ChildCategory;

/**
 * Created by truon on 6/6/2018.
 */

public interface ManageChildView {
    void loadListChildSuccessful(ArrayList<ChildCategory> data);
    void loadListChildFalse();

    void addChildSuccessful();
    void addChildFalse();

    void uploadNewImageSuccessful();
    void uploadNewImageFalse();

    void updateChildSuccessful();
    void updateChildFalse();
}
