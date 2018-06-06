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
    private MyService myService;

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
            json += "\"number\" : " + listDetails.get(i).getNumber() + ",";
            json += "\"status\" : " + listDetails.get(i).getStatus();
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

        HashMap<String, String> attrIdUser = new HashMap<>();
        attrIdUser.put("id_user", order.getIdUser() + "");

        HashMap<String, String> attrNameCustomer = new HashMap<>();
        attrNameCustomer.put("full_name", order.getFullName());

        HashMap<String, String> attrPhoneCustomer = new HashMap<>();
        attrPhoneCustomer.put("phone", order.getPhone());

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
        attrs.add(attrIdUser);
        attrs.add(attrNameCustomer);
        attrs.add(attrPhoneCustomer);
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

    public ArrayList<Order> getAllOrder(){
        ArrayList<Order> listOrder = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllOrder");
        attrs.add(attrFunc);

        MyService myService = new MyService(context, ORDER_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("orders");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonTransport.length(); i++) {
                JSONObject jsonObject1 = jsonTransport.getJSONObject(i);
                String id = jsonObject1.getString("id_order");
                String id_user = jsonObject1.getString("id_user");
                String fullname = jsonObject1.getString("fullname");
                String phone = jsonObject1.getString("phone");
                String date_order = jsonObject1.getString("date_order");
                String status = jsonObject1.getString("status");
                String type_transport = jsonObject1.getString("type_transport");
                String type_payment = jsonObject1.getString("type_payment");
                String value = jsonObject1.getString("value");
                String address = jsonObject1.getString("address");

                Order order = new Order(Integer.parseInt(id), Integer.parseInt(id_user), fullname,
                        phone, Long.parseLong(date_order), Integer.parseInt(status), Integer.parseInt(type_transport),
                        Integer.parseInt(type_payment), Long.parseLong(value), address);
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

    public ArrayList<Order> getOrderByUser(int idUser, int status1){
        ArrayList<Order> listOrder = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getOrderByUser");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id_user", idUser + "");

        HashMap<String, String> attrStatus = new HashMap<>();
        attrStatus.put("status", status1 + "");

        attrs.add(attrFunc);
        attrs.add(attrId);
        attrs.add(attrStatus);

        MyService myService = new MyService(context, ORDER_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("order_user", data);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("orders");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonTransport.length(); i++) {
                JSONObject jsonObject1 = jsonTransport.getJSONObject(i);
                String id = jsonObject1.getString("id_order");
                String id_user = jsonObject1.getString("id_user");
                String fullname = jsonObject1.getString("fullname");
                String phone = jsonObject1.getString("phone");
                String date_order = jsonObject1.getString("date_order");
                String status = jsonObject1.getString("status");
                String type_transport = jsonObject1.getString("type_transport");
                String type_payment = jsonObject1.getString("type_payment");
                String value = jsonObject1.getString("value");
                String address = jsonObject1.getString("address");

                Order order = new Order(Integer.parseInt(id), Integer.parseInt(id_user), fullname,
                        phone, Long.parseLong(date_order), Integer.parseInt(status), Integer.parseInt(type_transport),
                        Integer.parseInt(type_payment), Long.parseLong(value), address);
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
            Log.e("order_details", data);
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
                String dateOrder = jsonObject1.getString("date_order_shop");
                String status = jsonObject1.getString("status");

                OrderDetails orderDetails = new OrderDetails(Integer.parseInt(id), Integer.parseInt(id_order),
                        Integer.parseInt(id_product), Integer.parseInt(id_shop), Integer.parseInt(number),
                        Long.parseLong(dateOrder), Integer.parseInt(status));
                listOrder.add(orderDetails);
            }
        } catch (InterruptedException e) {
            Log.e("order_details", e.toString());
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ExecutionException e) {
            Log.e("order_details", e.toString());
            e.printStackTrace();
            return new ArrayList<>();
        } catch (JSONException e) {
            Log.e("order_details", e.toString());
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
            Log.e("details_order", data);
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
                String dateOrder = jsonObject1.getString("date_order_shop");
                String status = jsonObject1.getString("status");

                OrderDetails orderDetails = new OrderDetails(Integer.parseInt(id), Integer.parseInt(id_order),
                        Integer.parseInt(id_product), Integer.parseInt(id_shop), Integer.parseInt(number),
                        Long.parseLong(dateOrder), Integer.parseInt(status));
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

    public int countProductInOrder(int idShop, int idProduct){
        int number = 0;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "countProductInOrder");

        HashMap<String, String> attrIdShop = new HashMap<>();
        attrIdShop.put("id_shop", idShop + "");

        HashMap<String, String> attrIdProduct = new HashMap<>();
        attrIdProduct.put("id_product", idProduct + "");

        attrs.add(attrFunc);
        attrs.add(attrIdShop);
        attrs.add(attrIdProduct);

        MyService myService = new MyService(context, ORDER_PATH, attrs);
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

    public int countOrdeByTime(int idShop, long start, long end){
        int number = 0;
        ArrayList<Integer> listIdShop = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "countOrderByTime");

        HashMap<String, String> attrIdShop = new HashMap<>();
        attrIdShop.put("id_shop", idShop + "");

        HashMap<String, String> attrStartDate = new HashMap<>();
        attrStartDate.put("start_date", start + "");

        HashMap<String, String> attrEndDate = new HashMap<>();
        attrEndDate.put("end_date", end + "");

        attrs.add(attrFunc);
        attrs.add(attrIdShop);
        attrs.add(attrStartDate);
        attrs.add(attrEndDate);

        MyService myService = new MyService(context, ORDER_PATH, attrs);
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

    public boolean updateStatusOrder(int id, int status){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateStatusOrder");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", id + "");

        HashMap<String, String> attrStatus = new HashMap<>();
        attrStatus.put("status", status + "");

        attrs.add(attrFucn);
        attrs.add(attrId);
        attrs.add(attrStatus);

        myService = new MyService(context, ORDER_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            Log.e("tr_update_status", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error update status", data);
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

    public boolean deleteOrder(int id){
        return true;
    }

    public boolean updateStatusOrderDetails(int id, int status){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateStatusDetailsOrder");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", id + "");

        HashMap<String, String> attrStatus = new HashMap<>();
        attrStatus.put("status", status + "");

        attrs.add(attrFucn);
        attrs.add(attrId);
        attrs.add(attrStatus);

        myService = new MyService(context, ORDER_PATH, attrs);
        myService.execute();
        try {
            String data = myService.get();
            Log.e("tr_update_status", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error update status", data);
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

    public boolean deleteOrderDetails(int id){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "deleteDetailsOrder");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", id+"");


        attrs.add(func);
        attrs.add(attrId);

        myService = new MyService(context, ORDER_PATH, attrs);
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
}
