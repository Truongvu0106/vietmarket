package edu.hust.truongvu.choviet.signup;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
 * Created by truon on 3/15/2018.
 */

public class SignupModel {
    public static final String PATH_SIGNUP = Constants.Path.MY_PATH + "user.php";
    private Context context;
    public SignupModel(Context context){
        this.context = context;
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

        HashMap<String, String> attrPassword = new HashMap<>();
        attrPassword.put("password", user.getPassword());

        HashMap<String, String> attrTypeUser = new HashMap<>();
        attrTypeUser.put("typeid", user.getIdTypeUser() + "");

        attrs.add(attrFucn);
        attrs.add(attrFullName);
        attrs.add(attrUserName);
        attrs.add(attrPassword);
        attrs.add(attrTypeUser);

        JsonHelper jsonHelper = new JsonHelper(PATH_SIGNUP, attrs);
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
}
