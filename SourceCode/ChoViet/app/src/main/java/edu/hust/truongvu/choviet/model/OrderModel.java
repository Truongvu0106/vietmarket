package edu.hust.truongvu.choviet.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.entity.Order;
import edu.hust.truongvu.choviet.entity.OrderDetails;
import edu.hust.truongvu.choviet.entity.Transport;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 4/24/2018.
 */

public class OrderModel {
    public static final String ORDER_PATH = Constants.Path.MY_PATH + "order.php";
    Context context;
    public OrderModel(Context context){
        this.context = context;
    }

    public ArrayList<Order> getListOrderByShop(int idShop){
        ArrayList<Order> listOrder = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getListOrderByShopId");

        HashMap<String, String> attrShop = new HashMap<>();
        attrShop.put("id_shop", idShop+"");

        attrs.add(attrFunc);
        attrs.add(attrShop);

        JsonHelper jsonHelper = new JsonHelper(ORDER_PATH, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("orders");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonTransport.length(); i++) {
                JSONObject jsonObject1 = jsonTransport.getJSONObject(i);
                String id_order = jsonObject1.getString("id_order");
                String id_customer = jsonObject1.getString("id_customer");
                String id_shop = jsonObject1.getString("id_shop");
                String date_order = jsonObject1.getString("date_order");
                String status = jsonObject1.getString("status");
                String type_transport = jsonObject1.getString("type_transport");
                String type_payment = jsonObject1.getString("type_payment");
                String value = jsonObject1.getString("value");
                String address = jsonObject1.getString("address");

                Order order = new Order(Integer.parseInt(id_order), Integer.parseInt(id_customer), Integer.parseInt(id_shop),
                        Long.parseLong(date_order), Integer.parseInt(status), Integer.parseInt(type_transport), Integer.parseInt(type_payment),
                        Integer.parseInt(value), address);
                listOrder.add(order);
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
        return listOrder;
    }

    public ArrayList<OrderDetails> getDetailsOrder(int idOrder){
        ArrayList<OrderDetails> listOrder = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getDetailsOrderById");

        HashMap<String, String> attrShop = new HashMap<>();
        attrShop.put("id", idOrder+"");

        attrs.add(attrFunc);
        attrs.add(attrShop);

        JsonHelper jsonHelper = new JsonHelper(ORDER_PATH, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("orderdetails");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonTransport.length(); i++) {
                JSONObject jsonObject1 = jsonTransport.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String id_order = jsonObject1.getString("id_order");
                String id_product = jsonObject1.getString("id_product");
                String number = jsonObject1.getString("number");

                OrderDetails orderDetails = new OrderDetails(Integer.parseInt(id), Integer.parseInt(id_order),
                        Integer.parseInt(id_product), Integer.parseInt(number));
                listOrder.add(orderDetails);
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
        return listOrder;
    }
}
