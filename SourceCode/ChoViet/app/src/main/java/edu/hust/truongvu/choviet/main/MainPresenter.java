package edu.hust.truongvu.choviet.main;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by truon on 3/13/2018.
 */

public interface MainPresenter {
    void loadInfoFacebook();
    void stopTrackingFacebook();
    void loadInfoGoogle(GoogleSignInAccount account);
}
