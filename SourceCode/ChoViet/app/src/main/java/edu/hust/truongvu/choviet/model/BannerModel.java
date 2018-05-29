package edu.hust.truongvu.choviet.model;

import android.content.Context;

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
    public static String BANNER_PATH = Constants.Path.MY_PATH + "banner.php";
    private Context mContext;
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
            MyService myService = new MyService(mContext, BANNER_PATH, attrs);
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

}
