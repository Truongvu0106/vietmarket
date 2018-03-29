package edu.hust.truongvu.choviet.payment.paymethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.entity.PayMethod;
import edu.hust.truongvu.choviet.entity.Transport;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/29/2018.
 */

public class PayMethodModel {
    public static final String PAY_PATH = Constants.Path.MY_PATH + "payment.php";

    public ArrayList<PayMethod> getAllPayMethod(){
        ArrayList<PayMethod> listPaymenthods = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllPayment");

        attrs.add(attrFunc);

        JsonHelper jsonHelper = new JsonHelper(PAY_PATH, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("payment");
            JSONArray jsonPayments = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonPayments.length(); i++){
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
}
