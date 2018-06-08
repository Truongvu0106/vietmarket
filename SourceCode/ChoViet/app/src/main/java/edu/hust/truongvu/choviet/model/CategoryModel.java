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
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 4/30/2018.
 */

public class CategoryModel {
    public static String CATEGORY_PATH = Constants.MY_PATH + "category.php";
    public static final String PATH_IMAGE = "img/category/";

    private Context mContext;
    private MyService myService;

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
            MyService myService = new MyService(mContext, CATEGORY_PATH, attrs);
            myService.execute();
            String results = myService.get();
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

            MyService myService = new MyService(mContext, CATEGORY_PATH, attrs);
            myService.execute();
            String results = myService.get();
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

        MyService myService = new MyService(mContext, CATEGORY_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
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

    public ArrayList<ChildCategory> getListChildCategoryByParent(int idParent){
        ArrayList<ChildCategory> childCategories = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunction = new HashMap<>();
        attrFunction.put("func", "getListChildCategoryByParent");

        HashMap<String, String> attrIdParent = new HashMap<>();
        attrIdParent.put("id_parent", idParent + "");

        attrs.add(attrFunction);
        attrs.add(attrIdParent);
        try {
            MyService myService = new MyService(mContext, CATEGORY_PATH, attrs);
            myService.execute();

            String results = myService.get();
            JSONObject jsonObject = new JSONObject(results);
            JSONArray jsonCategories = jsonObject.getJSONArray("child_categories");
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

    public boolean addParentCategory(ParentCategory parentCategory){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "addParentCategory");

        HashMap<String, String> attrName = new HashMap<>();
        attrName.put("name_type_parent", parentCategory.getName());

        HashMap<String, String> attrImage = new HashMap<>();
        attrImage.put("image", PATH_IMAGE + parentCategory.getPath_img() + ".jpeg");

        attrs.add(attrFucn);
        attrs.add(attrName);
        attrs.add(attrImage);

        myService = new MyService(mContext, CATEGORY_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error add parent", data);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateParentCategory(ParentCategory parentCategory, boolean isChangedImage){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateParentCategory");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_type_parent", parentCategory.getId() + "");

        HashMap<String, String> attrName = new HashMap<>();
        attrName.put("name_type_parent", parentCategory.getName());

        HashMap<String, String> attrImage = new HashMap<>();
        if (isChangedImage){
            attrImage.put("image", PATH_IMAGE + parentCategory.getPath_img() + ".jpeg");
        }else {
            attrImage.put("image", parentCategory.getPath_img());
        }

        attrs.add(attrFucn);
        attrs.add(attrId);
        attrs.add(attrName);
        attrs.add(attrImage);

        myService = new MyService(mContext, CATEGORY_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error add product", data);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean addChildCategory(ChildCategory childCategory){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "addChildCategory");

        HashMap<String, String> attrName = new HashMap<>();
        attrName.put("name_type_child", childCategory.getName());

        HashMap<String, String> attrIdParent = new HashMap<>();
        attrIdParent.put("id_type_parent", childCategory.getIdParent() + "");

        HashMap<String, String> attrImage = new HashMap<>();
        attrImage.put("image_cate", PATH_IMAGE + childCategory.getPath_img() + ".jpeg");

        attrs.add(attrFucn);
        attrs.add(attrName);
        attrs.add(attrIdParent);
        attrs.add(attrImage);

        myService = new MyService(mContext, CATEGORY_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error add child", data);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean updateChildCategory(ChildCategory childCategory, boolean isChangedImage){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateChildCategory");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_type_child", childCategory.getId() + "");

        HashMap<String, String> attrName = new HashMap<>();
        attrName.put("name_type_child", childCategory.getName());

        HashMap<String, String> attrIdParent = new HashMap<>();
        attrIdParent.put("id_type_parent", childCategory.getIdParent() + "");

        HashMap<String, String> attrImage = new HashMap<>();
        if (isChangedImage){
            attrImage.put("image_cate", PATH_IMAGE + childCategory.getPath_img() + ".jpeg");
        }else {
            attrImage.put("image_cate", childCategory.getPath_img());
        }

        attrs.add(attrFucn);
        attrs.add(attrId);
        attrs.add(attrName);
        attrs.add(attrIdParent);
        attrs.add(attrImage);

        myService = new MyService(mContext, CATEGORY_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error update child", data);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
