package edu.hust.truongvu.choviet.category;

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
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 2/22/2018.
 */

public class CategoryPresenterImp implements CategoryPresenter {

    public static String PATH_PARENT = Constants.Path.PATH_GENNYMOTION + "categoryparent.php";
    public static String PATH_CHILD = Constants.Path.PATH_GENNYMOTION + "categorychild.php";
    public static ArrayList<ChildCategory> childCategories;
    private CategoryView categoryView;
    public CategoryPresenterImp(CategoryView categoryView){
        this.categoryView = categoryView;

    }


    @Override
    public void initParentCategory() {
        try {
            ArrayList<ParentCategory> parentCategories = new ArrayList<>();
            List<HashMap<String, String>> attrs = new ArrayList<>();
            HashMap<String, String> attrFunction = new HashMap<>();
            attrFunction.put("func", "getListParentCategory");
            attrs.add(attrFunction);

            JsonHelper jsonHelper = new JsonHelper(PATH_PARENT, attrs);
            jsonHelper.execute();
            String results = jsonHelper.get();
            Log.e("parent_category", results);
            JSONObject jsonObject = new JSONObject(results);
            JSONArray jsonCategories = jsonObject.getJSONArray("parent_category");
            JSONArray myJsonArr = jsonCategories.getJSONArray(0);
            for (int i = 0; i < myJsonArr.length(); i++){
                JSONObject data = myJsonArr.getJSONObject(i);
                String id = data.getString("id");
                String name = data.getString("name");
                String img = data.getString("image");
                ParentCategory parentCategory = new ParentCategory(Integer.parseInt(id), name, R.drawable.cate_dodientu, img);
                parentCategories.add(parentCategory);
            }
            Log.e("truong", parentCategories.size() + "");
            if (parentCategories.size() != 0){
                categoryView.loadParentCategory(parentCategories);
            }else {
                categoryView.onLoadError("Null data");
            }
        }catch (Exception e){
            categoryView.onLoadError(e.toString());
        }
    }

    @Override
    public void initChildCategory() {
        childCategories = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunction = new HashMap<>();
        attrFunction.put("func", "getListChildCategory");
        try {
            JsonHelper jsonHelper = new JsonHelper(PATH_CHILD, attrs);
            jsonHelper.execute();
            String results = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(results);
            JSONArray jsonCategories = jsonObject.getJSONArray("child_category");
            JSONArray myJsonArr = jsonCategories.getJSONArray(0);
            for (int i = 0; i < myJsonArr.length(); i++){
                JSONObject data = myJsonArr.getJSONObject(i);
                String id = data.getString("id");
                String name = data.getString("name");
                String parent = data.getString("parent");
                String img = data.getString("image");
                ChildCategory childCategory = new ChildCategory(Integer.parseInt(id), name, Integer.parseInt(parent), R.drawable.iphone, img);
                childCategories.add(childCategory);
            }
        }catch (Exception e){
            categoryView.onLoadError(e.toString());
        }
    }

    @Override
    public void initChildCategoryById(int id) {
//        ArrayList<ChildCategory> childCategories = new ArrayList<>();
//        childCategories.add(new ChildCategory(0, "Máy tính", 0, R.drawable.iphone));
//        childCategories.add(new ChildCategory(0, "Điện thoại", 0, R.drawable.iphone));
//        childCategories.add(new ChildCategory(0, "Máy ảnh", 0, R.drawable.iphone));
//        childCategories.add(new ChildCategory(1, "USB", 1, R.drawable.iphone));
//        childCategories.add(new ChildCategory(1, "Bàn phím", 1, R.drawable.iphone));
//        childCategories.add(new ChildCategory(1, "Chuột", 1, R.drawable.iphone));
        if (childCategories == null){
            categoryView.onLoadError("Null data");
            return;
        }else {
            ArrayList<ChildCategory> filterData = new ArrayList<>();
            for (ChildCategory category : childCategories){
                if (category.getIdParent() == id){
                    filterData.add(category);
                }
            }
            categoryView.loadChildCategory(filterData);
        }
    }
}
