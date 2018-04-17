package edu.hust.truongvu.choviet.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.entity.Transport;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/27/2018.
 */

public class TransportModel {
    private static final String TRANSPORT_PATH = Constants.Path.MY_PATH + "transport.php";
    public ArrayList<Transport> getAllTransport(){
        ArrayList<Transport> listTransport = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllTransport");

        attrs.add(attrFunc);

        JsonHelper jsonHelper = new JsonHelper(TRANSPORT_PATH, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("transport");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonTransport.length(); i++){
                JSONObject jsonObject1 = jsonTransport.getJSONObject(i);
                String id = jsonObject1.getString("id");
                String name = jsonObject1.getString("name");
                String note = jsonObject1.getString("note");
                String price = jsonObject1.getString("price");

                Transport transport = new Transport(Integer.parseInt(id), note, name, Long.parseLong(price));
                listTransport.add(transport);
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
        return listTransport;
    }
}
