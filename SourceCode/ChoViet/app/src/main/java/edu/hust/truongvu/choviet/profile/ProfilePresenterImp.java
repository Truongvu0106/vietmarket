package edu.hust.truongvu.choviet.profile;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

/**
 * Created by truon on 3/13/2018.
 */

public class ProfilePresenterImp implements ProfilePresenter {
    private ProfileView profileView;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;

    public ProfilePresenterImp(ProfileView profileView){
        this.profileView = profileView;
    }

    @Override
    public void getAccessTokenFacebook() {

    }

    @Override
    public void setupWithGoogle() {

    }
}
