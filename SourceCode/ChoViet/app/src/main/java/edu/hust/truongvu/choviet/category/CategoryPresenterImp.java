package edu.hust.truongvu.choviet.category;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.ParentCategory;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 2/22/2018.
 */

public class CategoryPresenterImp implements CategoryPresenter {

    public static String PATH_PARENT = Constants.Path.MY_PATH + "categoryparent.php";
    public static String PATH_CHILD = Constants.Path.MY_PATH + "categorychild.php";
    public static ArrayList<ChildCategory> childCategories;
    private CategoryModel categoryModel;
    private CategoryView categoryView;
    private Context mContext;
    public CategoryPresenterImp(Context context, CategoryView categoryView){
        this.categoryView = categoryView;
        this.mContext = context;
        categoryModel = new CategoryModel(mContext);

    }


    @Override
    public void initParentCategory() {
        ArrayList<ParentCategory> data = new ArrayList<>();
        data = categoryModel.getListParentCategory();
        if (data == null || data.size() == 0){
            categoryView.loadChildCategoryFalse();
        }else {
            categoryView.loadParentCategorySuccessful(data);
        }
    }

    @Override
    public void initChildCategory() {
        childCategories = new ArrayList<>();
        childCategories = categoryModel.getListChildCategory();
    }

    @Override
    public void initChildCategoryById(int id) {
        if (childCategories == null || childCategories.size() == 0){
            categoryView.loadParentCategoryFalse();
            return;
        }else {
            ArrayList<ChildCategory> filterData = new ArrayList<>();
            for (ChildCategory category : childCategories){
                if (category.getIdParent() == id){
                    filterData.add(category);
                }
            }
            categoryView.loadChildCategorySuccessful(filterData);
        }
    }
}
