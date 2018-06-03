package edu.hust.truongvu.choviet.admin.category;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;

/**
 * Created by truon on 5/30/2018.
 */

public interface ManageCategoryView {
    void loadListParentSuccessful(ArrayList<ParentCategory> data);
    void loadListParentFalse();

    void loadListChildSuccessful(ArrayList<ChildCategory> data);
    void loadListChildFalse();

    void addParentSuccessful();
    void addParentFalse();

    void updateParentSuccessful();
    void updateParentFalse();

    void addChildSuccessful();
    void addChildFalse();

    void updateChildSuccessful();
    void updateChildFalse();


}
