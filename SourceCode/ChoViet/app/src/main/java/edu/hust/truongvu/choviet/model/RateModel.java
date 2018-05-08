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

import edu.hust.truongvu.choviet.entity.Rate;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/22/2018.
 */

public class RateModel {
    public static final String PATH_RATE = Constants.Path.MY_PATH + "rate.php";
    private Context context;
    public RateModel(Context context){
        this.context = context;
    }
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

        MyService myService = new MyService(context, PATH_RATE, attrs);
        myService.execute();
        try {
            String data = myService.get();
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
        ArrayList<Rate> rates = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunction = new HashMap<>();
        attrFunction.put("func", "getRateByProduct");
        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_product", id + "");

        attrs.add(attrFunction);
        attrs.add(attrId);

        MyService myService = new MyService(context, PATH_RATE, attrs);
        myService.execute();
        try {
            String results = myService.get();
            Log.e("rate", results);
            JSONObject jsonObject = new JSONObject(results);
            JSONArray jsonArrays = jsonObject.getJSONArray("rate");
            Log.e("rate", "json array 1: " + jsonArrays.length());
            JSONArray myJsonArr = jsonArrays.getJSONArray(0);
            Log.e("rate", "json array 2: " + myJsonArr.length());
            for (int i = 0; i < myJsonArr.length(); i++){
                JSONObject data = myJsonArr.getJSONObject(i);
                String id_product = data.getString("id");
                String username = data.getString("user");
                String title = data.getString("title");
                String content = data.getString("content");
                String star = data.getString("star");
                String date = data.getString("date");

                Rate rate = new Rate(Integer.parseInt(id_product), username, title, content, Float.parseFloat(star), date);
                rates.add(rate);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        Log.e("rate", rates.size() + "");
        return rates;
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

        MyService myService = new MyService(context, PATH_RATE, attrs);
        myService.execute();
        try {
            String data = myService.get();
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
