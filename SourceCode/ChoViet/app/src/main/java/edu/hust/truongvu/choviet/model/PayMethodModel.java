package edu.hust.truongvu.choviet.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.model.entity.PayMethod;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 3/29/2018.
 */

public class PayMethodModel {
    public static final String PAY_PATH = Constants.MY_PATH + "payment.php";
    private Context mContext;
    public PayMethodModel(Context context){
        this.mContext = context;
    }
    public ArrayList<PayMethod> getAllPayMethod() {
        ArrayList<PayMethod> listPaymenthods = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllPayment");

        attrs.add(attrFunc);

        MyService myService = new MyService(mContext, PAY_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("payment");
            JSONArray jsonPayments = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonPayments.length(); i++) {
                JSONObject jsonObject1 = jsonPayments.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                String image = jsonObject1.getString("image");
                String price = jsonObject1.getString("price");

                PayMethod payMethod = new PayMethod(Integer.parseInt(id), name, image, Long.parseLong(price));
                listPaymenthods.add(payMethod);
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
        return listPaymenthods;
    }

    public PayMethod getPayMethodById(int id) {
        PayMethod payMethod = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getPaymentById");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", id + "");

        attrs.add(attrFunc);
        attrs.add(attrId);

        MyService myService = new MyService(mContext, PAY_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("payment");
            JSONArray jsonPayments = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonPayments.getJSONObject(0);
            String id1 = jsonObject1.getString("id");
            String name = jsonObject1.getString("name");
            String image = jsonObject1.getString("image");
            String price = jsonObject1.getString("price");

            payMethod = new PayMethod(Integer.parseInt(id1), name, image, Long.parseLong(price));
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
        return payMethod;
    }
}
