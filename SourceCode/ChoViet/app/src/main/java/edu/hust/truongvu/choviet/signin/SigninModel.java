package edu.hust.truongvu.choviet.signin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.hust.truongvu.choviet.helper.JsonHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/17/2018.
 */

public class SigninModel {
    public static final String PATH_SIGNIN = Constants.Path.PATH_GENNYMOTION + "user.php";
    private Context context;
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
}
