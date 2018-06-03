package edu.hust.truongvu.choviet.admin.category;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;

/**
 * Created by truon on 5/30/2018.
 */

public class ManageCategoryPresenterImp implements ManageCategoryPresenter{
    private Context mContext;
    private ManageCategoryView view;
    private CategoryModel categoryModel;

    public ManageCategoryPresenterImp(Context context, ManageCategoryView view){
        this.mContext = context;
        this.view = view;
        categoryModel = new CategoryModel(mContext);
    }

    @Override
    public void initListParentCategory() {
        ArrayList<ParentCategory> data = categoryModel.getListParentCategory();
        if (data.isEmpty()){
            view.loadListParentFalse();
        }else {
            view.loadListParentSuccessful(data);
        }
    }

    @Override
    public void initListChildCategory(int id) {
        ArrayList<ChildCategory> data = categoryModel.getListChildCategoryByParent(id);
        if (data.isEmpty()){
            view.loadListChildFalse();
        }else {
            view.loadListChildSuccessful(data);

        }
    }

    @Override
    public void addParentCategory(ParentCategory parentCategory) {

    }

    @Override
    public void addChildCategory(ChildCategory childCategory) {

    }

    @Override
    public void updateParentCategory(ParentCategory parentCategory) {

    }

    @Override
    public void updateChildCategory(ChildCategory childCategory) {

    }
}
