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

import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 3/21/2018.
 */

public class ShopModel {
    public static final String IMAGE_ROOT = "/img/shop/";
    public static final String IMAGE_EXTEND = ".jpeg";
    public static final String SHOP_PATH = Constants.Path.MY_PATH + "shop.php";
    private Context mContext;
    private MyService myService;
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

    public Shop getShopByOwner(int idUser){
        Shop shop = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getShopByOwner");
        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_owner", idUser + "");

        attrs.add(attrFunc);
        attrs.add(attrId);

        MyService myService = new MyService(mContext, SHOP_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("data_shop", data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("shop");
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

    public boolean isFollowing(int idShop, int idUser){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "isFollowing");

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("idUser", idUser+"");

        HashMap<String, String> attrIdShop = new HashMap<>();
        attrIdShop.put("idShop", idShop+"");

        attrs.add(func);
        attrs.add(attrIdUser);
        attrs.add(attrIdShop);

        myService = new MyService(mContext, SHOP_PATH, attrs);
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

    public boolean follow(int idShop, int idUser){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "follow");

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("idUser", idUser+"");

        HashMap<String, String> attrIdShop = new HashMap<>();
        attrIdShop.put("idShop", idShop+"");

        attrs.add(func);
        attrs.add(attrIdUser);
        attrs.add(attrIdShop);

        myService = new MyService(mContext, SHOP_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("follow", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error_follow", jsonObject.getString("message"));
            }
        } catch (InterruptedException e) {
            Log.e("error_follow", e.toString());
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.e("error_follow", e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("error_follow", e.toString());
            e.printStackTrace();
        }
        return flag;
    }

    public boolean unFollow(int idShop, int idUser){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "unFollow");

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("idUser", idUser+"");

        HashMap<String, String> attrIdProduct = new HashMap<>();
        attrIdProduct.put("idShop", idShop+"");

        attrs.add(func);
        attrs.add(attrIdUser);
        attrs.add(attrIdProduct);

        myService = new MyService(mContext, SHOP_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("unfollow", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error_unfollow", jsonObject.getString("message"));
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

    public ArrayList<Shop> getListShopFollowing(int idUser){
        ArrayList<Integer> listIdShop = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getListShopFollow");

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("idUser", idUser + "");

        attrs.add(attrFunc);
        attrs.add(attrIdUser);

        myService = new MyService(mContext, SHOP_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("list_follow", data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("shops");
            JSONArray jsonShops = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonShops.length(); i++){
                JSONObject jsonObject1 = jsonShops.getJSONObject(i);
                String id = jsonObject1.getString("id_shop");
                listIdShop.add(Integer.parseInt(id));
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

        ArrayList<Shop> results = new ArrayList<>();
        Log.e("list_follow", listIdShop.size() + "");
        for (int i = 0; i < listIdShop.size(); i++){
            Shop shop = getShopById(listIdShop.get(i));
            results.add(shop);
        }
        return results;
    }

    public boolean registerShop(Shop shop){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "registerShop");

        HashMap<String, String> attrName = new HashMap<>();
        attrName.put("name_shop", shop.getName());

        HashMap<String, String> attrSlogan = new HashMap<>();
        attrSlogan.put("slogan", shop.getSlogan());

        HashMap<String, String> attrAvatar = new HashMap<>();
        attrAvatar.put("img_avatar", IMAGE_ROOT + shop.getImgAvatar() + IMAGE_EXTEND);

        HashMap<String, String> attrCover = new HashMap<>();
        attrCover.put("img_cover", IMAGE_ROOT + shop.getImgCover() + IMAGE_EXTEND);

        HashMap<String, String> attrOwner = new HashMap<>();
        attrOwner.put("id_owner", shop.getIdOwner() + "");

        HashMap<String, String> attrAddress = new HashMap<>();
        attrAddress.put("address", shop.getAddress());

        HashMap<String, String> attrPhone = new HashMap<>();
        attrPhone.put("phone", shop.getPhone());

        HashMap<String, String> attrWeb = new HashMap<>();
        attrWeb.put("website", shop.getWebsite());

        HashMap<String, String> attrRate = new HashMap<>();
        attrRate.put("rate", shop.getRate() + "");

        HashMap<String, String> attrHighlight = new HashMap<>();
        attrHighlight.put("highlight", (shop.getHighlight() ? 1 : 0) + "");


        attrs.add(attrFucn);
        attrs.add(attrName);
        attrs.add(attrSlogan);
        attrs.add(attrAvatar);
        attrs.add(attrCover);
        attrs.add(attrOwner);
        attrs.add(attrAddress);
        attrs.add(attrPhone);
        attrs.add(attrRate);
        attrs.add(attrWeb);
        attrs.add(attrRate);
        attrs.add(attrHighlight);


        myService = new MyService(mContext, SHOP_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error add shop", data);
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
