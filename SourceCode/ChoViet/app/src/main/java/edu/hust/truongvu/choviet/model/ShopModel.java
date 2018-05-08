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

import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/21/2018.
 */

public class ShopModel {
    public static final String SHOP_PATH = Constants.Path.MY_PATH + "shop.php";
    private Context mContext;
    public ShopModel(Context context){
        this.mContext = context;
    }

    public ArrayList<Shop> getAllShop() {
        ArrayList<Shop> listShop = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllShop");

        attrs.add(attrFunc);

        MyService myService = new MyService(mContext, SHOP_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("shops");
            JSONArray jsonBrands = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonBrands.length(); i++) {
                JSONObject jsonObject1 = jsonBrands.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                String slogan = jsonObject1.getString("slogan");
                String avatar = jsonObject1.getString("avatar");
                String cover = jsonObject1.getString("cover");
                String owner = jsonObject1.getString("owner");
                String address = jsonObject1.getString("address");
                String phone = jsonObject1.getString("phone");
                String website = jsonObject1.getString("website");
                String rate = jsonObject1.getString("rate");
                String hightlight = jsonObject1.getString("highlight");

                Shop shop = new Shop(Integer.parseInt(id), name, slogan, avatar, cover,
                        Integer.parseInt(owner), address, phone, website, Float.parseFloat(rate),
                        hightlight.matches("1") ? true : false);
                listShop.add(shop);
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
        return listShop;
    }

    public Shop getShopById(int id) {
        Shop shop = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getShopById");
        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", id + "");

        attrs.add(attrFunc);
        attrs.add(attrId);

        MyService myService = new MyService(mContext, SHOP_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("data_shop", data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("shops");
            JSONArray jsonBrands = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonBrands.getJSONObject(0);

            String id1 = jsonObject1.getString("id");
            String name = jsonObject1.getString("name");
            String slogan = jsonObject1.getString("slogan");
            String avatar = jsonObject1.getString("avatar");
            String cover = jsonObject1.getString("cover");
            String owner = jsonObject1.getString("owner");
            String address = jsonObject1.getString("address");
            String phone = jsonObject1.getString("phone");
            String website = jsonObject1.getString("website");
            String rate = jsonObject1.getString("rate");
            String hightlight = jsonObject1.getString("highlight");

            shop = new Shop(Integer.parseInt(id1), name, slogan, avatar, cover,
                    Integer.parseInt(owner), address, phone, website, Float.parseFloat(rate),
                    hightlight.matches("1") ? true : false);
        } catch (InterruptedException e) {
            Log.e("data_shop", e.toString());
            return null;
        } catch (ExecutionException e) {
            Log.e("data_shop", e.toString());
            return null;
        } catch (JSONException e) {
            Log.e("data_shop", e.toString());
            return null;
        }
        return shop;
    }
}
