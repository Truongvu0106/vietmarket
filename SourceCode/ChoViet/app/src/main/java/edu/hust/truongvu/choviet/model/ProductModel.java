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
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 3/21/2018.
 */

public class ProductModel {
    public static final String IMAGE_ROOT = "/img/product/";
    public static final String IMAGE_EXTEND = ".jpeg";
    private static final String PRODUCT_PATH = Constants.Path.MY_PATH + "product.php";
    private Context mContext;
    private MyService myService;
    public ProductModel(Context context){
        this.mContext = context;
    }
    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> listProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllProduct");

        attrs.add(attrFunc);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
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
                String amount = jsonObject1.getString("amount");
                String idShop = jsonObject1.getString("id_shop");
                String hightlight = jsonObject1.getString("highlight");

                Product product = new Product(Integer.parseInt(id), name, Long.parseLong(price), Integer.parseInt(discount),
                        listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                        Float.parseFloat(rate), Integer.parseInt(amount), Integer.parseInt(idShop), hightlight.matches("1")?true:false, false);
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

    public ArrayList<Product> getProductLastest(){
        ArrayList<Product> listProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getProductLastest");

        attrs.add(attrFunc);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
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
                String amount = jsonObject1.getString("amount");
                String idShop = jsonObject1.getString("id_shop");
                String hightlight = jsonObject1.getString("highlight");

                Product product = new Product(Integer.parseInt(id), name, Long.parseLong(price), Integer.parseInt(discount),
                        listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                        Float.parseFloat(rate), Integer.parseInt(amount), Integer.parseInt(idShop), hightlight.matches("1")?true:false, false);
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

    public ArrayList<Product> getProductHighlight(){
        ArrayList<Product> listProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getProductHighlight");

        attrs.add(attrFunc);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
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
                String amount = jsonObject1.getString("amount");
                String idShop = jsonObject1.getString("id_shop");
                String hightlight = jsonObject1.getString("highlight");

                Product product = new Product(Integer.parseInt(id), name, Long.parseLong(price), Integer.parseInt(discount),
                        listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                        Float.parseFloat(rate), Integer.parseInt(amount), Integer.parseInt(idShop), hightlight.matches("1")?true:false, false);
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

    public ArrayList<Product> getProductByCategory(int idCategory){
        ArrayList<Product> listProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getProductByCategory");

        HashMap<String, String> attrIdCategory = new HashMap<>();
        attrIdCategory.put("id_category", idCategory + "");

        attrs.add(attrFunc);
        attrs.add(attrIdCategory);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
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
                String amount = jsonObject1.getString("amount");
                String idShop = jsonObject1.getString("id_shop");
                String hightlight = jsonObject1.getString("highlight");

                Product product = new Product(Integer.parseInt(id), name, Long.parseLong(price), Integer.parseInt(discount),
                        listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                        Float.parseFloat(rate), Integer.parseInt(amount), Integer.parseInt(idShop), hightlight.matches("1")?true:false, false);
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

    public ArrayList<Product> getProductByBrand(int idBrand){
        ArrayList<Product> listProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getProductByBrand");

        HashMap<String, String> attrIdBrand = new HashMap<>();
        attrIdBrand.put("id_brand", idBrand + "");

        attrs.add(attrFunc);
        attrs.add(attrIdBrand);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
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
                String amount = jsonObject1.getString("amount");
                String idShop = jsonObject1.getString("id_shop");
                String hightlight = jsonObject1.getString("highlight");

                Product product = new Product(Integer.parseInt(id), name, Long.parseLong(price), Integer.parseInt(discount),
                        listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                        Float.parseFloat(rate), Integer.parseInt(amount), Integer.parseInt(idShop), hightlight.matches("1")?true:false, false);
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

    public ArrayList<Product> getProductByIdShop(int shopId){
        ArrayList<Product> listProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getProductByShop");

        HashMap<String, String> attrIdShop = new HashMap<>();
        attrIdShop.put("id_shop", shopId + "");

        attrs.add(attrFunc);
        attrs.add(attrIdShop);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("shop_product", data);
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
                String amount = jsonObject1.getString("amount");
                String idShop = jsonObject1.getString("id_shop");
                String hightlight = jsonObject1.getString("highlight");

                Product product = new Product(Integer.parseInt(id), name, Long.parseLong(price), Integer.parseInt(discount),
                        listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                        Float.parseFloat(rate), Integer.parseInt(amount), Integer.parseInt(idShop), hightlight.matches("1")?true:false, false);
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

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
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
            String amount = jsonObject1.getString("amount");
            String idShop = jsonObject1.getString("id_shop");
            String hightlight = jsonObject1.getString("highlight");

            product = new Product(id, name, Long.parseLong(price), Integer.parseInt(discount),
                    listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                    Float.parseFloat(rate), Integer.parseInt(amount), Integer.parseInt(idShop),
                    hightlight.matches("1")?true:false, false);
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

    public boolean addProduct(Product product){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "addProduct");

        HashMap<String, String> attrName = new HashMap<>();
        attrName.put("name_product", product.getName());

        HashMap<String, String> attrPrice = new HashMap<>();
        attrPrice.put("price", product.getPrice() + "");

        String imgPath = "";
        ArrayList<String> listImg = product.getImgs();
        for (int i = 0; i < listImg.size(); i++){
            String name = listImg.get(i);
            if (i == (listImg.size() - 1)){
                if (name.contains(".")){
                    imgPath += IMAGE_ROOT + listImg.get(i);
                }else {
                    imgPath += IMAGE_ROOT + listImg.get(i) + IMAGE_EXTEND;
                }
            }else {
                if (name.contains(".")){
                    imgPath += IMAGE_ROOT + listImg.get(i) + "@";
                }else {
                    imgPath += IMAGE_ROOT + listImg.get(i) + IMAGE_EXTEND + "@";
                }
            }
        }

        HashMap<String, String> attrImage = new HashMap<>();
        attrImage.put("image", imgPath);

        HashMap<String, String> attrInformation = new HashMap<>();
        attrInformation.put("information", product.getInfomation());

        HashMap<String, String> attrWeight = new HashMap<>();
        attrWeight.put("weight", product.getWeight());

        HashMap<String, String> attrTypeProduct = new HashMap<>();
        attrTypeProduct.put("type_product", product.getTypeProduct() + "");

        HashMap<String, String> attrBrand = new HashMap<>();
        attrBrand.put("brand", product.getBrand() + "");

        HashMap<String, String> attrRate = new HashMap<>();
        attrRate.put("rate", product.getRate() + "");

        HashMap<String, String> attrAmount = new HashMap<>();
        attrAmount.put("amount", product.getAmount() + "");

        HashMap<String, String> attrShop = new HashMap<>();
        attrShop.put("id_shop", product.getIdShop() + "");

        HashMap<String, String> attrHightlight = new HashMap<>();
        attrHightlight.put("hightlight", product.isHighlight() ? 1 + "" : 0 + "");

        HashMap<String, String> attrDiscount = new HashMap<>();
        attrDiscount.put("discount", product.getDiscount() + "");


        attrs.add(attrFucn);
        attrs.add(attrName);
        attrs.add(attrPrice);
        attrs.add(attrImage);
        attrs.add(attrInformation);
        attrs.add(attrWeight);
        attrs.add(attrTypeProduct);
        attrs.add(attrBrand);
        attrs.add(attrRate);
        attrs.add(attrAmount);
        attrs.add(attrShop);
        attrs.add(attrHightlight);
        attrs.add(attrDiscount);


        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error add product", data);
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

    public boolean updateProduct(Product product){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateProduct");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_product", product.getId() + "");

        HashMap<String, String> attrName = new HashMap<>();
        attrName.put("name_product", product.getName());

        HashMap<String, String> attrPrice = new HashMap<>();
        attrPrice.put("price", product.getPrice() + "");

        String imgPath = "";
        ArrayList<String> listImg = product.getImgs();
        for (int i = 0; i < listImg.size(); i++){
            String name = listImg.get(i);
            if (i == (listImg.size() - 1)){
                if (name.contains(".")){
                    imgPath += IMAGE_ROOT + listImg.get(i);
                }else {
                    imgPath += IMAGE_ROOT + listImg.get(i) + IMAGE_EXTEND;
                }
            }else {
                if (name.contains(".")){
                    imgPath += IMAGE_ROOT + listImg.get(i) + "@";
                }else {
                    imgPath += IMAGE_ROOT + listImg.get(i) + IMAGE_EXTEND + "@";
                }
            }
        }
        Log.e("tr_imag", imgPath);
        HashMap<String, String> attrImage = new HashMap<>();
        attrImage.put("image", imgPath);

        HashMap<String, String> attrInformation = new HashMap<>();
        attrInformation.put("information", product.getInfomation());

        HashMap<String, String> attrWeight = new HashMap<>();
        attrWeight.put("weight", product.getWeight());

        HashMap<String, String> attrTypeProduct = new HashMap<>();
        attrTypeProduct.put("type_product", product.getTypeProduct() + "");

        HashMap<String, String> attrBrand = new HashMap<>();
        attrBrand.put("brand", product.getBrand() + "");

        HashMap<String, String> attrRate = new HashMap<>();
        attrRate.put("rate", product.getRate() + "");

        HashMap<String, String> attrAmount = new HashMap<>();
        attrAmount.put("amount", product.getAmount() + "");

        HashMap<String, String> attrShop = new HashMap<>();
        attrShop.put("id_shop", product.getIdShop() + "");

        HashMap<String, String> attrHightlight = new HashMap<>();
        attrHightlight.put("hightlight", product.isHighlight() ? 1 + "" : 0 + "");

        HashMap<String, String> attrDiscount = new HashMap<>();
        attrDiscount.put("discount", product.getDiscount() + "");


        attrs.add(attrFucn);
        attrs.add(attrId);
        attrs.add(attrName);
        attrs.add(attrPrice);
        attrs.add(attrImage);
        attrs.add(attrInformation);
        attrs.add(attrWeight);
        attrs.add(attrTypeProduct);
        attrs.add(attrBrand);
        attrs.add(attrRate);
        attrs.add(attrAmount);
        attrs.add(attrShop);
        attrs.add(attrHightlight);
        attrs.add(attrDiscount);


        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            Log.e("tr_update", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error update product", data);
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

    public boolean updateStockProduct(int idProduct, int newNumber){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateStock");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_product", idProduct + "");

        HashMap<String, String> attrNumber = new HashMap<>();
        attrNumber.put("number", newNumber + "");

        attrs.add(attrFucn);
        attrs.add(attrId);
        attrs.add(attrNumber);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            Log.e("tr_update_stock", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error update product", data);
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

    public boolean updateRateProduct(int idProduct, float rate){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateRate");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_product", idProduct + "");

        HashMap<String, String> attrRate = new HashMap<>();
        attrRate.put("rate", rate + "");

        attrs.add(attrFucn);
        attrs.add(attrId);
        attrs.add(attrRate);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            Log.e("tr_update_rate", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error update product", data);
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

    public ArrayList<Product> searchProductByName(String search){
        ArrayList<Product> listProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "searchProductByName");

        HashMap<String, String> attrIdShop = new HashMap<>();
        attrIdShop.put("search", search);

        attrs.add(attrFunc);
        attrs.add(attrIdShop);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
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
                String amount = jsonObject1.getString("amount");
                String idShop = jsonObject1.getString("id_shop");
                String hightlight = jsonObject1.getString("highlight");

                Product product = new Product(Integer.parseInt(id), name, Long.parseLong(price), Integer.parseInt(discount),
                        listImage, information, weight, Integer.parseInt(type_product), Integer.parseInt(brand),
                        Float.parseFloat(rate), Integer.parseInt(amount), Integer.parseInt(idShop), hightlight.matches("1")?true:false, false);
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

    public boolean isLiked(int idUser, int idProduct){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "isLiked");

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("idUser", idUser+"");

        HashMap<String, String> attrIdProduct = new HashMap<>();
        attrIdProduct.put("idProduct", idProduct+"");

        attrs.add(func);
        attrs.add(attrIdUser);
        attrs.add(attrIdProduct);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
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

    public boolean likeProduct(int idUser, int idProduct){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "like");

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("idUser", idUser+"");

        HashMap<String, String> attrIdProduct = new HashMap<>();
        attrIdProduct.put("idProduct", idProduct+"");

        attrs.add(func);
        attrs.add(attrIdUser);
        attrs.add(attrIdProduct);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error like", jsonObject.getString("message"));
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

    public boolean unlikeProduct(int idUser, int idProduct){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "unLike");

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("idUser", idUser+"");

        HashMap<String, String> attrIdProduct = new HashMap<>();
        attrIdProduct.put("idProduct", idProduct+"");

        attrs.add(func);
        attrs.add(attrIdUser);
        attrs.add(attrIdProduct);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error like", jsonObject.getString("message"));
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

    public ArrayList<Product> getListWishlist(int idUser){
        ArrayList<Integer> listIdProduct = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getListLikedProduct");

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("idUser", idUser + "");

        attrs.add(attrFunc);
        attrs.add(attrIdUser);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("products");
            JSONArray jsonProducts = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonProducts.length(); i++){
                JSONObject jsonObject1 = jsonProducts.getJSONObject(i);
                String id = jsonObject1.getString("id_product");
                listIdProduct.add(Integer.parseInt(id));
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

        ArrayList<Product> results = new ArrayList<>();
        for (int i = 0; i < listIdProduct.size(); i++){
            Product product = getProductById(listIdProduct.get(i));
            results.add(product);
        }
        return results;
    }

    public int countLikeProduct(int idProduct){
        int number = 0;
        ArrayList<Integer> listIdShop = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "countLikeProduct");

        HashMap<String, String> attrIdProduct = new HashMap<>();
        attrIdProduct.put("id_product", idProduct + "");

        attrs.add(attrFunc);
        attrs.add(attrIdProduct);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("number");
            number = Integer.parseInt(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }

        return number;
    }

    public int countProductByBrand(int idShop, int idBrand){
        int number = 0;
        ArrayList<Integer> listIdShop = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "countProductByBrand");

        HashMap<String, String> attrIdShop = new HashMap<>();
        attrIdShop.put("id_shop", idShop + "");

        HashMap<String, String> attrIdBrand = new HashMap<>();
        attrIdBrand.put("id_brand", idBrand + "");

        attrs.add(attrFunc);
        attrs.add(attrIdShop);
        attrs.add(attrIdBrand);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("number");
            number = Integer.parseInt(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }

        return number;
    }

    public int countProductByCategory(int idShop, int idCategory){
        int number = 0;
        ArrayList<Integer> listIdShop = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "countProductByCategory");

        HashMap<String, String> attrIdShop = new HashMap<>();
        attrIdShop.put("id_shop", idShop + "");

        HashMap<String, String> attrIdCategory = new HashMap<>();
        attrIdCategory.put("id_category", idCategory + "");

        attrs.add(attrFunc);
        attrs.add(attrIdShop);
        attrs.add(attrIdCategory);

        myService = new MyService(mContext, PRODUCT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("number");
            number = Integer.parseInt(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return 0;
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }

        return number;
    }
}
