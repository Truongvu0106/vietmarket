package edu.hust.truongvu.choviet.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 3/20/2018.
 */

public class BrandModel {
    public static final String BRAND_PATH = Constants.MY_PATH + "brand.php";
    private Context mContext;

    public BrandModel(Context context){
        this.mContext = context;
    }

    public ArrayList<Brand> getListBrand() {
        ArrayList<Brand> listBrand = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getListBrand");

        attrs.add(attrFunc);

        MyService myService = new MyService(mContext, BRAND_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("brand");
            JSONArray jsonBrands = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonBrands.length(); i++) {
                JSONObject jsonObject1 = jsonBrands.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                String image = jsonObject1.getString("image");

                Brand brand = new Brand(Integer.parseInt(id), name, image);
                listBrand.add(brand);
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
        return listBrand;
    }

    public Brand getBrandById(int idBrand) {
        Brand brand = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getBrandById");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_brand", idBrand + "");

        attrs.add(attrFunc);
        attrs.add(attrId);

        MyService myService = new MyService(mContext, BRAND_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("brand");
            JSONArray jsonBrands = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonBrands.getJSONObject(0);
            String id = jsonObject1.getString("id");
            String name = jsonObject1.getString("name");
            String image = jsonObject1.getString("image");

            brand = new Brand(Integer.parseInt(id), name, image);
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
        return brand;
    }
}
