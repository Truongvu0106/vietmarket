package edu.hust.truongvu.choviet.shop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/21/2018.
 */

public class ShopModel {
    public static final String SHOP_PATH = Constants.Path.MY_PATH + "shop.php";

    public ArrayList<Shop> getAllShop(){
        ArrayList<Shop> listShop = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllShop");

        attrs.add(attrFunc);

        JsonHelper jsonHelper = new JsonHelper(SHOP_PATH, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("shops");
            JSONArray jsonBrands = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonBrands.length(); i++){
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
                        hightlight.matches("1")?true:false);
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
}
