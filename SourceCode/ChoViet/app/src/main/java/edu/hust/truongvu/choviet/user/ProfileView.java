package edu.hust.truongvu.choviet.user;

import edu.hust.truongvu.choviet.model.entity.User;

/**
 * Created by truon on 3/13/2018.
 */

public interface ProfileView {
    void initLayoutWithFacebook();
    void initLayoutWithGoogle();

    void loadInforUserSucessful(User user);
    void loadInforUserFalse();
}
