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
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.entity.Banner;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;
import edu.hust.truongvu.choviet.services.MyService;

/**
 * Created by truon on 5/28/2018.
 */

public class BannerModel {
    public static String BANNER_PATH = Constants.MY_PATH + "banner.php";
    public static final String PATH_IMAGE = "/img/banner/";
    private Context mContext;
    private MyService myService;
    public BannerModel(Context context){
        this.mContext = context;
    }

    public ArrayList<Banner> getAllBanner(){
        ArrayList<Banner> listBanner = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunction = new HashMap<>();
        attrFunction.put("func", "getAllBanner");
        attrs.add(attrFunction);
        try {
            myService = new MyService(mContext, BANNER_PATH, attrs);
            myService.execute();
            String results = myService.get();
            JSONObject jsonObject = new JSONObject(results);
            JSONArray jsonCategories = jsonObject.getJSONArray("banners");
            JSONArray myJsonArr = jsonCategories.getJSONArray(0);
            for (int i = 0; i < myJsonArr.length(); i++){
                JSONObject data = myJsonArr.getJSONObject(i);
                String id = data.getString("id");
                String title = data.getString("title");
                String image = data.getString("image");
                String type = data.getString("type");
                Banner banner = new Banner(Integer.parseInt(id), title, image, Integer.parseInt(type));
                listBanner.add(banner);
            }

        }catch (Exception e){
            return new ArrayList<>();
        }
        return listBanner;
    }

    public boolean addBanner(Banner banner){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "addBanner");

        HashMap<String, String> attrTitle = new HashMap<>();
        attrTitle.put("title", banner.getTitle());

        HashMap<String, String> attrImage = new HashMap<>();
        attrImage.put("image", PATH_IMAGE + banner.getImage() + ".jpeg");

        HashMap<String, String> attrType = new HashMap<>();
        attrType.put("type", banner.getType() + "");

        attrs.add(attrFucn);
        attrs.add(attrTitle);
        attrs.add(attrImage);
        attrs.add(attrType);

        myService = new MyService(mContext, BANNER_PATH, attrs);
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

    public boolean updateBanner(Banner banner, boolean isChangedImage){
        boolean flag = false;
        Log.e("banner", banner.getImage() + " - " + banner.getTitle());
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateBanner");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", banner.getId() + "");

        HashMap<String, String> attrTitle = new HashMap<>();
        attrTitle.put("title", banner.getTitle());

        HashMap<String, String> attrImage = new HashMap<>();
        if (isChangedImage){
            attrImage.put("image", PATH_IMAGE + banner.getImage() + ".jpeg");
        }else {
            attrImage.put("image", banner.getImage());
        }

        HashMap<String, String> attrType = new HashMap<>();
        attrType.put("type", banner.getType() + "");

        attrs.add(attrFucn);
        attrs.add(attrId);
        attrs.add(attrTitle);
        attrs.add(attrImage);
        attrs.add(attrType);

        myService = new MyService(mContext, BANNER_PATH, attrs);
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
}
