package edu.hust.truongvu.choviet.init;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.json.JSONObject;

/**
 * Created by truon on 3/13/2018.
 */

public class MainPresenterImp implements MainPresenter{
    private MainView mainView;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;

    public MainPresenterImp(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void loadInfoFacebook() {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        mainView.initWithFacebook(object);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void stopTrackingFacebook() {
        if (accessTokenTracker != null){
            accessTokenTracker.stopTracking();
        }
    }

    @Override
    public void loadInfoGoogle(GoogleSignInAccount account) {
        mainView.initWithGoogle(account);
    }
}
