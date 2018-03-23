package edu.hust.truongvu.choviet.rate;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.entity.Rate;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/22/2018.
 */

public class RateModel {
    public static final String PATH_RATE = Constants.Path.MY_PATH + "rate.php";

    public boolean addRate(Rate rate){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "addRate");

        HashMap<String, String> attrIdProduct = new HashMap<>();
        attrIdProduct.put("id_product", rate.getIdProduct() + "");

        HashMap<String, String> attrUserRate = new HashMap<>();
        attrUserRate.put("user_rate", rate.getUserName());

        HashMap<String, String> attrTitle = new HashMap<>();
        attrTitle.put("title", rate.getTitle());

        HashMap<String, String> attrContent = new HashMap<>();
        attrContent.put("content", rate.getContent());

        HashMap<String, String> attrStar = new HashMap<>();
        attrStar.put("star", rate.getStarRate() + "");

        attrs.add(attrFucn);
        attrs.add(attrIdProduct);
        attrs.add(attrUserRate);
        attrs.add(attrTitle);
        attrs.add(attrContent);
        attrs.add(attrStar);

        JsonHelper jsonHelper = new JsonHelper(PATH_RATE, attrs);
        jsonHelper.execute();
        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
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

    public ArrayList<Rate> loadListRate(int id){
        return new ArrayList<>();
    }

    public boolean isRated(String username, int id_product){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "isRated");

        HashMap<String, String> attrIdProduct = new HashMap<>();
        attrIdProduct.put("id_product", id_product + "");

        HashMap<String, String> attrUser = new HashMap<>();
        attrUser.put("user_rate", username);



        attrs.add(attrFucn);
        attrs.add(attrIdProduct);
        attrs.add(attrUser);

        JsonHelper jsonHelper = new JsonHelper(PATH_RATE, attrs);
        jsonHelper.execute();
        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
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
