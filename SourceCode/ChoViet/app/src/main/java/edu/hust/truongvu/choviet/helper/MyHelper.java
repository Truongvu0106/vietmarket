package edu.hust.truongvu.choviet.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.User;
import edu.hust.truongvu.choviet.signin.SigninModel;
import edu.hust.truongvu.choviet.utils.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by truon on 3/21/2018.
 */

public class MyHelper {

    public static ArrayList<String> getListSubString(String path){
        if (path.matches("")) {
            return new ArrayList<>();
        }
        ArrayList<String> results = new ArrayList<>();
//        boolean flag = false;
//        for (int i = 0; i < path.length(); i++) {
//            if (path.charAt(i) == '@') {
//                flag = true;
//                break;
//            }
//        }
//
//        if (flag) {
//            int i = 0, j = 0;
//            while(i < (path.length() - 1)){
//                i++;
//                if (path.charAt(i) == '@') {
//                    String subPath = path.substring(j, i);
//                    results.add(subPath);
//                    j = i+1;
//                }else if(i == (path.length() - 1)){
//                    String subPath1 = path.substring(j, path.length());
//                    results.add(subPath1);
//                }
//            }
//        }else{
//            results.add(path);
//        }
        String[] paths = path.split("@");
        for (int i = 0; i< paths.length; i++){
            results.add(paths[i]);
        }
        return results;

    }

    public static void setImagePicasso(Context context, ImageView imageView, String path){
        if (path == null || path.matches("")){
            return;
        }
        Picasso.with(context)
                .load(path)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .resize(150, 150)
                .centerCrop()
                .into(imageView);
    }

    public static void loadImageUser(Context context, ImageView imageView, String path){
        if (path == null || path.matches("")){
            return;
        }
        Picasso.with(context)
                .load(path)
                .placeholder(R.drawable.loading)
                .error(R.drawable.user_default)
                .resize(150, 150)
                .centerCrop()
                .into(imageView);
    }

    public static String getUserPreference(Context context){
        SharedPreferences userPreference = context.getSharedPreferences("mylogin", MODE_PRIVATE);
        String username = userPreference.getString("username", "");
        return username;
    }

    public static User getCurrentUser(Context context){
        SigninModel signinModel = new SigninModel();
        User user = signinModel.getUserByUsername(getUserPreference(context));
        if (user == null){
            Log.e("user", "null");
        }else {
            Log.e("user", "not null");
        }
        return user;
    }

    public static String formatMoney(long money){
        return NumberFormat.getNumberInstance(Locale.US).format(money);
    }

    public static void setViewCart(View root, TextView tvNumber, int number){
        if (root == null || tvNumber == null){
            return;
        }
        if (number == 0){
            root.setVisibility(View.GONE);
        }else {
            root.setVisibility(View.VISIBLE);
            tvNumber.setText(number + "");
        }
    }
}
