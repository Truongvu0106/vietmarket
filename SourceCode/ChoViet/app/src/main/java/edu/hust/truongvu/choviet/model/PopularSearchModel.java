package edu.hust.truongvu.choviet.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.hust.truongvu.choviet.entity.PopularSearch;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 5/6/2018.
 */

public class PopularSearchModel {
    public static final String POPULAR_SEARCH_PATH = Constants.Path.MY_PATH + "popularsearch.php";
    private Context mContext;
    public PopularSearchModel(Context context){
        this.mContext = context;
    }

    public ArrayList<PopularSearch> getAllPopularSearch(){
        ArrayList<PopularSearch> popularSearches = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunction = new HashMap<>();
        attrFunction.put("func", "getAllPopularSearch");
        attrs.add(attrFunction);
        try {
            MyService myService = new MyService(mContext, POPULAR_SEARCH_PATH, attrs);
            myService.execute();
            String results = myService.get();
            JSONObject jsonObject = new JSONObject(results);
            JSONArray jsonCategories = jsonObject.getJSONArray("popular_search");
            JSONArray myJsonArr = jsonCategories.getJSONArray(0);
            for (int i = 0; i < myJsonArr.length(); i++){
                JSONObject data = myJsonArr.getJSONObject(i);
                String id = data.getString("id");
                String name = data.getString("name");
                String img = data.getString("image");
                PopularSearch popularSearch = new PopularSearch(Integer.parseInt(id), name, img);
                popularSearches.add(popularSearch);
            }

        }catch (Exception e){
            return new ArrayList<>();
        }
        return popularSearches;
    }
}
