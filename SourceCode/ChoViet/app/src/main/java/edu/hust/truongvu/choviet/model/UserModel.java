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

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.services.MyService;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 3/17/2018.
 */

public class UserModel {
    public static final String PATH_USER = Constants.MY_PATH + "user.php";
    private Context context;

    public UserModel(Context context){
        this.context = context;
    }

    public boolean checkLogin(String username, String password){
        Log.e("trtrtrtr", "dadsadasdasd");
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

        MyService myService = new MyService(context, PATH_USER, attrs);
        myService.execute();

        try {
            String data = myService.get();
            Log.e("trtrtrtr", data + "");
            JSONObject jsonObject = new JSONObject(data);
            String result = jsonObject.getString("result");
            if (result.matches("true")){
                flag = true;
                String username1 = jsonObject.getString("username");
                int id = jsonObject.getInt("id");
                int type = jsonObject.getInt("type");
                Log.e("type", type + "");
                if (type == Constants.User.TYPE_ADMIN){
                    SharedPreferences loginPreference = context.getSharedPreferences(Constants.MyTag.MY_LOGIN, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginPreference.edit();
                    editor.putString(Constants.MyTag.USERNAME, "");
                    editor.putInt(Constants.MyTag.USERID, 0);
                    editor.commit();
                }else {
                    SharedPreferences loginPreference = context.getSharedPreferences(Constants.MyTag.MY_LOGIN, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginPreference.edit();
                    editor.putString(Constants.MyTag.USERNAME, username1);
                    editor.putInt(Constants.MyTag.USERID, id);
                    editor.commit();
                }
            }else {
                flag = false;
                Log.e("error_sign_in", data);
            }
        } catch (InterruptedException e) {
            Log.e("trtrtrtr", e.toString());
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.e("trtrtrtr", e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e("trtrtrtr", e.toString());
            e.printStackTrace();
        }
        return flag;
    }

    public ArrayList<User> getAllMember(){
        ArrayList<User> listUser = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        HashMap<String, String> attrFunction = new HashMap<>();
        attrFunction.put("func", "getAllMember");
        attrs.add(attrFunction);
        try {
            MyService myService = new MyService(context, PATH_USER, attrs);
            myService.execute();
            String results = myService.get();
            JSONObject jsonObject = new JSONObject(results);
            JSONArray jsonCategories = jsonObject.getJSONArray("users");
            JSONArray myJsonArr = jsonCategories.getJSONArray(0);
            for (int i = 0; i < myJsonArr.length(); i++){
                JSONObject data = myJsonArr.getJSONObject(i);
                String id_user = data.getString("id_user");
                String fullname = data.getString("fullname");
                String username = data.getString("username");
                String password = data.getString("password");
                String address = data.getString("address");
                String birthday = data.getString("birthday");
                String phone = data.getString("phone");
                String gender = data.getString("gender");
                String img_avatar = data.getString("img_avatar");
                String id_type = data.getString("id_type");
                String type_login = data.getString("type_login");

                User user = new User(Integer.parseInt(id_user), fullname, username, password, address, birthday, phone,
                        Integer.parseInt(gender), img_avatar, Integer.parseInt(id_type), Integer.parseInt(type_login));
                listUser.add(user);
            }

        }catch (Exception e){
            return new ArrayList<>();
        }
        return listUser;
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

        MyService myService = new MyService(context, PATH_USER, attrs);
        myService.execute();

        try {
            String data = myService.get();
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

        MyService myService = new MyService(context, PATH_USER, attrs);
        myService.execute();

        try {
            String data = myService.get();
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

        MyService myService = new MyService(context, PATH_USER, attrs);
        myService.execute();
        try {
            String data = myService.get();
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

        MyService myService = new MyService(context, PATH_USER, attrs);
        myService.execute();
        try {
            String data = myService.get();
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
