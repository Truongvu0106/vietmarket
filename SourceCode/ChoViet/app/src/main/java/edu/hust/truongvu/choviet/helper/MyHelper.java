package edu.hust.truongvu.choviet.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

import java.security.SecureRandom;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.model.UserModel;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by truon on 3/21/2018.
 */

public class MyHelper {
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static ArrayList<String> getListSubString(String path){
        if (path.matches("")) {
            return new ArrayList<>();
        }
        ArrayList<String> results = new ArrayList<>();
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
                .placeholder(R.drawable.img_loading)
                .error(R.drawable.img_error)
                .resize(250, 250)
                .centerInside()
//                .centerCrop()
                .into(imageView);
    }

    public static void setLargeImagePicasso(Context context, ImageView imageView, String path){
        if (path == null || path.matches("")){
            return;
        }
        Picasso.with(context)
                .load(path)
                .placeholder(R.drawable.img_loading)
                .error(R.drawable.img_error)
                .resize(500, 250)
                .centerInside()
//                .centerCrop()
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

    public static String getUserNamePreference(Context context){
        SharedPreferences userPreference = context.getSharedPreferences(Constants.MyTag.MY_LOGIN, MODE_PRIVATE);
        String username = userPreference.getString(Constants.MyTag.USERNAME, "");
        return username;
    }

    public static int getUserIdPreference(Context context){
        SharedPreferences userPreference = context.getSharedPreferences(Constants.MyTag.MY_LOGIN, MODE_PRIVATE);
        int id = userPreference.getInt(Constants.MyTag.USERID, 0);
        return id;
    }

    public static User getCurrentUser(Context context){
        UserModel userModel = new UserModel(context);
        User user = userModel.getUserByUsername(getUserNamePreference(context));
        if (user == null){
            Log.e("user", "null");
        }else {
            Log.e("user", "not null");
        }
        return user;
    }


    public static String formatMoney(long money){
        return NumberFormat.getNumberInstance(Locale.US).format(money) + "đ";
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

    public static String convertTimeString(long miliseconds){
        return DateFormat.format("dd/MM/yyyy  HH:mm", new Date(miliseconds)).toString();
    }

    public static int checkPermission(String[] permissions, Context context) {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (String permission : permissions) {
            permissionCheck += ContextCompat.checkSelfPermission(context, permission);
        }
        return permissionCheck;
    }

    public static String getWeight(String weight){
        if (weight.matches("")){
            return 0 + "";
        }
        String[] strings = weight.split(" ");
        if (strings.length == 2){
            return strings[0];
        }else {
            return weight;
        }
    }

    public static String getUnit(String weight){
        if (weight.matches("")){
            return "Kg";
        }
        String[] strings = weight.split(" ");
        if (strings.length == 2){
            return strings[1];
        }else {
            return "Kg";
        }
    }

    public static MyImage convertPathToMyImage(String path){
        if (path.matches("")){
            return null;
        }
        String[] strings = path.split("/");
        MyImage myImage = new MyImage(strings[strings.length - 1], null, path);
        return myImage;
    }

    public static String convertPathToName(String path){
        if (path.matches("")){
            return "";
        }
        String[] strings = path.split("/");
        return strings[strings.length - 1];
    }

    public static String convertDateToString(long time){
        Date date=new Date(time);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = df2.format(date);
        return dateText;
    }

    public static String randomString( int len ){
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static void showToast(Context context, String title, int type){
        FancyToast.makeText(context, title, FancyToast.LENGTH_SHORT, type,false).show();
    }

}
