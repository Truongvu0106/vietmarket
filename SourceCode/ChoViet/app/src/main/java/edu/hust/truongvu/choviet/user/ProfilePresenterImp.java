package edu.hust.truongvu.choviet.user;

import android.content.Context;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 3/13/2018.
 */

public class ProfilePresenterImp implements ProfilePresenter {
    private ProfileView profileView;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    private Context mContext;

    public ProfilePresenterImp(Context context, ProfileView profileView){
        this.profileView = profileView;
        this.mContext = context;
    }

    @Override
    public void getAccessTokenFacebook() {

    }

    @Override
    public void setupWithGoogle() {

    }

    @Override
    public void initInforUser() {
        User user = MyHelper.getCurrentUser(mContext);
        if (user == null){
            profileView.loadInforUserFalse();
        }else {
            profileView.loadInforUserSucessful(user);
        }
    }
}
