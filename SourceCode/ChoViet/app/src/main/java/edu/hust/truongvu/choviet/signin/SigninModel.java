package edu.hust.truongvu.choviet.signin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.User;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/17/2018.
 */

public class SigninModel {
    public static final String PATH_SIGNIN = Constants.Path.MY_PATH + "user.php";
    private Context context;
    public SigninModel(){

    }

    public SigninModel(Context context){
        this.context = context;
    }
    public boolean checkLogin(String username, String password){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> func = new HashMap<>();
        func.put("func", "checkLogin");

        HashMap<String, String> attrUsername = new HashMap<>();
        attrUsername.put("username", username);

        HashMap<String, String> attrPassword = new HashMap<>();
        attrPassword.put("password", password);

        attrs.add(func);
        attrs.add(attrUsername);
        attrs.add(attrPassword);

        JsonHelper jsonHelper = new JsonHelper(PATH_SIGNIN, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
                String username1 = jsonObject.getString("username");
                SharedPreferences loginPreference = context.getSharedPreferences("mylogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = loginPreference.edit();
                editor.putString("username", username1);
                editor.commit();
            }else {
                flag = false;
                Log.e("error_sign_in", data);
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

    public User getUserByUsername(String username){
        User user = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getUserByUsername");

        HashMap<String, String> attrUsername = new HashMap<>();
        attrUsername.put("username", username);

        attrs.add(attrFunc);
        attrs.add(attrUsername);

        JsonHelper jsonHelper = new JsonHelper(PATH_SIGNIN, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("user");
            JSONArray jsonArray = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            String id = jsonObject1.getString("id");
            String fullname = jsonObject1.getString("fullname");
            String username1 = jsonObject1.getString("username");
            String password = jsonObject1.getString("password");
            String address = jsonObject1.getString("address");
            String birthday = jsonObject1.getString("birthday");
            String phone = jsonObject1.getString("phone");
            String gender = jsonObject1.getString("gender");
            String img_avatar = jsonObject1.getString("img_avatar");
            String id_type = jsonObject1.getString("id_type");
            String type_login = jsonObject1.getString("type_login");

            user = new User(Integer.parseInt(id), fullname, username1, password, address, birthday, phone,
                    Integer.parseInt(gender), img_avatar, Integer.parseInt(id_type), Integer.parseInt(type_login));
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
        return user;
    }
}
