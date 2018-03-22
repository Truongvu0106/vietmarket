package edu.hust.truongvu.choviet.product;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/21/2018.
 */

public class ProductModel {
    public static final String PRODUCT_PATH = Constants.Path.MY_PATH + "product.php";

    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> listProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllProduct");

        attrs.add(attrFunc);

        JsonHelper jsonHelper = new JsonHelper(PRODUCT_PATH, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("products");
            JSONArray jsonProducts = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonProducts.length(); i++){
                JSONObject jsonObject1 = jsonProducts.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                String price = jsonObject1.getString("price");
                String discount = jsonObject1.getString("discount");
                String pathImage = jsonObject1.getString("image");
                ArrayList<String> listImage = MyHelper.getListSubString(pathImage);
                String information = jsonObject1.getString("information");
                String weight = jsonObject1.getString("weight");
                String type_product = jsonObject1.getString("type_product");
                String brand = jsonObject1.getString("brand");
                String rate = jsonObject1.getString("rate");
                String hightlight = jsonObject1.getString("highlight");

                Product product = new Product(Integer.parseInt(id), name, Long.parseLong(price), Integer.parseInt(discount),
                        listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                        Float.parseFloat(rate), hightlight.matches("1")?true:false, false);
                listProduct.add(product);
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
        return listProduct;
    }

    public Product getProductById(int id){
        Product product = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getProductById");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_product", id + "");

        attrs.add(attrFunc);
        attrs.add(attrId);

        JsonHelper jsonHelper = new JsonHelper(PRODUCT_PATH, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("product");
            JSONArray jsonArray = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            String name = jsonObject1.getString("name");
            String price = jsonObject1.getString("price");
            String discount = jsonObject1.getString("discount");
            String pathImage = jsonObject1.getString("image");
            ArrayList<String> listImage = MyHelper.getListSubString(pathImage);
            String information = jsonObject1.getString("information");
            String weight = jsonObject1.getString("weight");
            String type_product = jsonObject1.getString("type_product");
            String brand = jsonObject1.getString("brand");
            String rate = jsonObject1.getString("rate");
            String hightlight = jsonObject1.getString("highlight");

            product = new Product(id, name, Long.parseLong(price), Integer.parseInt(discount),
                    listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                    Float.parseFloat(rate), hightlight.matches("1")?true:false, false);
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
        return product;
    }
}
