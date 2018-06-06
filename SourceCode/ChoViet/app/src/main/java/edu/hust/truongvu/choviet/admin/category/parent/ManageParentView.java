package edu.hust.truongvu.choviet.admin.category.parent;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.ParentCategory;

/**
 * Created by truon on 6/6/2018.
 */

public interface ManageParentView {
    void loadListParentSuccessful(ArrayList<ParentCategory> data);
    void loadListParentFalse();

    void addParentSuccessful();
    void addParentFalse();

    void uploadNewImageSuccessful();
    void uploadNewImageFalse();

    void updateParentSuccessful();
    void updateParentFalse();
}
