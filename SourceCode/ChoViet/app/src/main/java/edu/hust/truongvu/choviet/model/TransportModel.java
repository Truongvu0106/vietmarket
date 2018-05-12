package edu.hust.truongvu.choviet.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.model.entity.Transport;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 3/27/2018.
 */

public class TransportModel {
    private static final String TRANSPORT_PATH = Constants.Path.MY_PATH + "transport.php";
    private Context context;
    public TransportModel(Context context){
        this.context = context;
    }

    public ArrayList<Transport> getAllTransport() {
        ArrayList<Transport> listTransport = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllTransport");

        attrs.add(attrFunc);

        MyService myService = new MyService(context, TRANSPORT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("transport");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            for (int i = 0; i < jsonTransport.length(); i++) {
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

    public Transport getTransportById(int id) {
        Transport transport = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getAllTransport");

        HashMap<String, String> attrId = new HashMap<>();
        attrId.put("id", id + "");

        attrs.add(attrFunc);
        attrs.add(attrId);

        MyService myService = new MyService(context, TRANSPORT_PATH, attrs);
        myService.execute();

        try {
            String data = myService.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("transport");
            JSONArray jsonTransport = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonTransport.getJSONObject(0);

            String id1 = jsonObject1.getString("id");
            String name = jsonObject1.getString("name");
            String note = jsonObject1.getString("note");
            String price = jsonObject1.getString("price");

            transport = new Transport(Integer.parseInt(id1), note, name, Long.parseLong(price));
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
        return transport;
    }
}
