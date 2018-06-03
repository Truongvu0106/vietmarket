package edu.hust.truongvu.choviet.startup.signin;

import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 2/21/2018.
 */

public interface SigninView {
    void onSuccess(User user);
    void onError(String message);
}
