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

import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.entity.Promotion;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.services.MyService;

/**
 * Created by truon on 5/31/2018.
 */

public class PromotionModel {
    private Context mContext;
    private MyService myService;
    public static final String PROMOTION_PATH = Constants.MY_PATH + "promotion.php";

    public PromotionModel(Context context){
        this.mContext = context;
    }

    public ArrayList<Promotion> getAllPromotion(){
        ArrayList<Promotion> listPromotion = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllPromotion");

        attrs.add(attrFunc);

        MyService myService = new MyService(mContext, PROMOTION_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("data_promotion", data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("promotions");
            JSONArray jsonBrands = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonBrands.length(); i++) {
                JSONObject jsonObject1 = jsonBrands.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String code = jsonObject1.getString("code");
                String start = jsonObject1.getString("start");
                String end = jsonObject1.getString("end");
                String number = jsonObject1.getString("number");
                String amount = jsonObject1.getString("amount");

                Promotion promotion = new Promotion(Integer.parseInt(id), code, Long.parseLong(amount),
                        Integer.parseInt(number), Long.parseLong(start), Long.parseLong(end));
                listPromotion.add(promotion);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return listPromotion;
    }

    public boolean addPromotion(Promotion promotion){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "addPromotion");

        HashMap<String, String> attrCode = new HashMap<>();
        attrCode.put("code", promotion.getCode());

        HashMap<String, String> attrStart = new HashMap<>();
        attrStart.put("start", promotion.getStart() + "");

        HashMap<String, String> attrEnd = new HashMap<>();
        attrEnd.put("end", promotion.getEnd() + "");

        HashMap<String, String> attrNumber = new HashMap<>();
        attrNumber.put("number", promotion.getNumber() + "");

        HashMap<String, String> attrAmount = new HashMap<>();
        attrAmount.put("amount", promotion.getAmount() + "");



        attrs.add(attrFucn);
        attrs.add(attrCode);
        attrs.add(attrStart);
        attrs.add(attrEnd);
        attrs.add(attrNumber);
        attrs.add(attrAmount);


        myService = new MyService(mContext, PROMOTION_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error add promotion", data);
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

    public boolean deletePromotion(int id){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "deletePromotion");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", id+"");


        attrs.add(func);
        attrs.add(attrId);

        myService = new MyService(mContext, PROMOTION_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error_delete", jsonObject.getString("message"));
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

    public boolean updateNumber(int id, int number){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "updateNumber");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", id+"");

        HashMap<String, String> attrNumber = new HashMap<>();
        attrNumber.put("number", number+"");


        attrs.add(func);
        attrs.add(attrId);
        attrs.add(attrNumber);

        myService = new MyService(mContext, PROMOTION_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error_update", jsonObject.getString("message"));
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

    public Promotion getPromotionByCode(String s){
        Promotion promotion = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getPromotionByCode");
        HashMap<String, String> attrCode = new HashMap<>();
        attrCode.put("code", s + "");

        attrs.add(attrFunc);
        attrs.add(attrCode);

        MyService myService = new MyService(mContext, PROMOTION_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("data_shop", data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("promotions");
            JSONArray jsonBrands = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonBrands.getJSONObject(0);

            String id = jsonObject1.getString("id");
            String code = jsonObject1.getString("code");
            String start = jsonObject1.getString("start");
            String end = jsonObject1.getString("end");
            String number = jsonObject1.getString("number");
            String amount = jsonObject1.getString("amount");

            promotion = new Promotion(Integer.parseInt(id), code, Long.parseLong(amount),
                    Integer.parseInt(number), Long.parseLong(start), Long.parseLong(end));
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }
        return promotion;
    }
}
