package edu.hust.truongvu.choviet.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.ParentCategory;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 4/30/2018.
 */

public class CategoryModel {
    public static String CATEGORY_PATH = Constants.Path.MY_PATH + "category.php";
    private Context mContext;
    public CategoryModel(Context context){
        this.mContext = context;
    }

    public ArrayList<ChildCategory> getListChildCategory(){
        ArrayList<ChildCategory> childCategories = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunction = new HashMap<>();
        attrFunction.put("func", "getListChildCategory");
        attrs.add(attrFunction);
        try {
            JsonHelper jsonHelper = new JsonHelper(mContext, CATEGORY_PATH, attrs);
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
            return new ArrayList<>();
        }
        return childCategories;
    }

    public ArrayList<ParentCategory> getListParentCategory(){
        ArrayList<ParentCategory> parentCategories = new ArrayList<>();
        try {
            List<HashMap<String, String>> attrs = new ArrayList<>();
            HashMap<String, String> attrFunction = new HashMap<>();
            attrFunction.put("func", "getListParentCategory");
            attrs.add(attrFunction);

            JsonHelper jsonHelper = new JsonHelper(mContext, CATEGORY_PATH, attrs);
            jsonHelper.execute();
            String results = jsonHelper.get();
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
        }catch (Exception e){
            return new ArrayList<>();
        }
        return parentCategories;
    }

    public ChildCategory getChildCategoryById(int idChild){
        ChildCategory childCategory = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getChildCategoryById");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_child", idChild + "");

        attrs.add(attrFunc);
        attrs.add(attrId);

        JsonHelper jsonHelper = new JsonHelper(mContext, CATEGORY_PATH, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("child_category");
            JSONArray jsonArray = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            String id = jsonObject1.getString("id");
            String name = jsonObject1.getString("name");
            String parent = jsonObject1.getString("parent");
            String img = jsonObject1.getString("image");
            childCategory = new ChildCategory(Integer.parseInt(id), name, Integer.parseInt(parent), R.drawable.cate_dodientu, img);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return childCategory;
    }
}
