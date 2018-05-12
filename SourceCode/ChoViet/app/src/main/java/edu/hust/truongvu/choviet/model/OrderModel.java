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

import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 4/24/2018.
 */

public class OrderModel {
    public static final String ORDER_PATH = Constants.Path.MY_PATH + "order.php";
    private Context context;
    public OrderModel(Context context){
        this.context = context;
    }

    public boolean insertOrder(Order order){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "addNewOrder");

        ArrayList<OrderDetails> listDetails = new ArrayList<>();
        listDetails = order.getOrderDetails();

        String json = "{\"list_products\":[";
        for (int i = 0; i < listDetails.size(); i++){
            json += "{";
            json += "\"id_product\" : " + listDetails.get(i).getIdProduct() + ",";
            json += "\"id_shop\" : " + listDetails.get(i).getIdShop() + ",";
            json += "\"number\" : " + listDetails.get(i).getNumber();
            if (i == listDetails.size() - 1){
                json += "}";
            }else {
                json += "},";
            }
        }
        json += "]}";

        HashMap<String, String> attrListDetailsJson = new HashMap<>();
        attrListDetailsJson.put("list_details", json);

        Log.e("myJson", json);

        HashMap<String, String> attrIdCustomer = new HashMap<>();
        attrIdCustomer.put("id_customer", order.getIdCustomer()+"");

        HashMap<String, String> attrStatus = new HashMap<>();
        attrStatus.put("status", order.getStatus() + "");

        HashMap<String, String> attrTransport = new HashMap<>();
        attrTransport.put("type_transport", order.getTypeTransport() + "");

        HashMap<String, String> attrPayment = new HashMap<>();
        attrPayment.put("type_payment", order.getTypePayment() + "");

        HashMap<String, String> attrValue = new HashMap<>();
        attrValue.put("value", order.getPrice() + "");

        HashMap<String, String> attrAddress = new HashMap<>();
        attrAddress.put("address", order.getAddress());

        attrs.add(attrFucn);
        attrs.add(attrListDetailsJson);
        attrs.add(attrIdCustomer);
        attrs.add(attrStatus);
        attrs.add(attrTransport);
        attrs.add(attrPayment);
        attrs.add(attrValue);
        attrs.add(attrAddress);

        MyService myService = new MyService(context, ORDER_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            Log.e("add_order", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error_add_order", data);
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

    public ArrayList<OrderDetails> getDetailsOrderByShop(int idShop){
        ArrayList<OrderDetails> listOrder = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getDetailsOrderByShop");

        HashMap<String, String> attrShop = new HashMap<>();
        attrShop.put("id_shop", idShop+"");

        attrs.add(attrFunc);
        attrs.add(attrShop);

        MyService myService = new MyService(context, ORDER_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("orderdetails");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonTransport.length(); i++) {
                JSONObject jsonObject1 = jsonTransport.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String id_order = jsonObject1.getString("id_order");
                String id_product = jsonObject1.getString("id_product");
                String id_shop = jsonObject1.getString("id_shop");
                String number = jsonObject1.getString("number");

                OrderDetails orderDetails = new OrderDetails(Integer.parseInt(id), Integer.parseInt(id_order),
                        Integer.parseInt(id_product), Integer.parseInt(id_shop), Integer.parseInt(number));
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

    public ArrayList<OrderDetails> getDetailsOrderById(int idOrder){
        ArrayList<OrderDetails> listOrder = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getDetailsOrderById");

        HashMap<String, String> attrShop = new HashMap<>();
        attrShop.put("id", idOrder+"");

        attrs.add(attrFunc);
        attrs.add(attrShop);

        MyService myService = new MyService(context, ORDER_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("orderdetails");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonTransport.length(); i++) {
                JSONObject jsonObject1 = jsonTransport.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String id_order = jsonObject1.getString("id_order");
                String id_product = jsonObject1.getString("id_product");
                String id_shop = jsonObject1.getString("id_shop");
                String number = jsonObject1.getString("number");

                OrderDetails orderDetails = new OrderDetails(Integer.parseInt(id), Integer.parseInt(id_order),
                        Integer.parseInt(id_product), Integer.parseInt(id_shop), Integer.parseInt(number));
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
