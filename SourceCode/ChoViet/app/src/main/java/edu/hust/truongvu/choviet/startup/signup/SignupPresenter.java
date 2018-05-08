package edu.hust.truongvu.choviet.startup.signup;

/**
 * Created by truon on 2/21/2018.
 */

public interface SignupPresenter {
    void signup(String username, String email, String phone, String password, String retype);
}
