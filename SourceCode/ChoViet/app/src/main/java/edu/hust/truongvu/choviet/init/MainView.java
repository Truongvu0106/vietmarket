package edu.hust.truongvu.choviet.init;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONObject;

/**
 * Created by truon on 3/13/2018.
 */

public interface MainView {
    void initWithFacebook(JSONObject jsonObject);
    void initWithGoogle(GoogleSignInAccount account);

}
