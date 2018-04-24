package edu.hust.truongvu.choviet.model;

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

import edu.hust.truongvu.choviet.entity.User;
import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/17/2018.
 */

public class UserModel {
    public static final String PATH_USER = Constants.Path.MY_PATH + "user.php";
    private Context context;
    public UserModel(){

    }

    public UserModel(Context context){
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

        JsonHelper jsonHelper = new JsonHelper(PATH_USER, attrs);
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

        JsonHelper jsonHelper = new JsonHelper(PATH_USER, attrs);
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

    public User getUserById(int id){
        User user = null;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunc = new HashMap<>();
        attrFunc.put("func", "getUserByUsername");

        HashMap<String, String> attrUsername = new HashMap<>();
        attrUsername.put("id", id+"");

        attrs.add(attrFunc);
        attrs.add(attrUsername);

        JsonHelper jsonHelper = new JsonHelper(PATH_USER, attrs);
        jsonHelper.execute();

        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            JSONArray myJsonArr = jsonObject.getJSONArray("user");
            JSONArray jsonArray = myJsonArr.getJSONArray(0);
            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
            String id1 = jsonObject1.getString("id");
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

            user = new User(Integer.parseInt(id1), fullname, username1, password, address, birthday, phone,
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

    public boolean registerUser(User user){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "registerUser");

        HashMap<String, String> attrFullName = new HashMap<>();
        attrFullName.put("fullname", user.getFullname());

        HashMap<String, String> attrUserName = new HashMap<>();
        attrUserName.put("username", user.getUsername());

        HashMap<String, String> attrPhone = new HashMap<>();
        attrPhone.put("phone", user.getPhone());

        HashMap<String, String> attrPassword = new HashMap<>();
        attrPassword.put("password", user.getPassword());

        HashMap<String, String> attrTypeUser = new HashMap<>();
        attrTypeUser.put("typeid", user.getIdTypeUser() + "");

        attrs.add(attrFucn);
        attrs.add(attrFullName);
        attrs.add(attrUserName);
        attrs.add(attrPassword);
        attrs.add(attrPhone);
        attrs.add(attrTypeUser);

        JsonHelper jsonHelper = new JsonHelper(PATH_USER, attrs);
        jsonHelper.execute();
        try {
            String data = jsonHelper.get();
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error_sign_up", data);
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

    public boolean updateUser(User user){
        boolean flag = false;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> attrFucn = new HashMap<>();
        attrFucn.put("func", "updateUser");

        HashMap<String, String> attrFullName = new HashMap<>();
        attrFullName.put("fullname", user.getFullname());

        HashMap<String, String> attrUserName = new HashMap<>();
        attrUserName.put("username", user.getUsername());

        HashMap<String, String> attrPassword = new HashMap<>();
        attrPassword.put("password", user.getPassword());

        HashMap<String, String> attrAddress = new HashMap<>();
        attrAddress.put("address", user.getAddress());

        HashMap<String, String> attrBirthday = new HashMap<>();
        attrBirthday.put("birthday", user.getBirthday());

        HashMap<String, String> attrPhone = new HashMap<>();
        attrPhone.put("phone", user.getPhone());

        HashMap<String, String> attrGender = new HashMap<>();
        attrGender.put("gender", user.getGender() + "");

        HashMap<String, String> attrAvatar = new HashMap<>();
        attrAvatar.put("img_avatar", user.getAvatar());

        HashMap<String, String> attrTypeUser = new HashMap<>();
        attrTypeUser.put("typeid", user.getIdTypeUser() + "");

        HashMap<String, String> attrTypeLogin = new HashMap<>();
        attrTypeLogin.put("type_login", user.getTypeLogin() + "");

        attrs.add(attrFucn);
        attrs.add(attrFullName);
        attrs.add(attrUserName);
        attrs.add(attrPassword);
        attrs.add(attrAddress);
        attrs.add(attrBirthday);
        attrs.add(attrPhone);
        attrs.add(attrGender);
        attrs.add(attrAvatar);
        attrs.add(attrTypeUser);
        attrs.add(attrTypeLogin);

        JsonHelper jsonHelper = new JsonHelper(PATH_USER, attrs);
        jsonHelper.execute();
        try {
            String data = jsonHelper.get();
            Log.e("update_user", data);
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
            }else {
                flag = false;
                Log.e("error_sign_up", data);
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
