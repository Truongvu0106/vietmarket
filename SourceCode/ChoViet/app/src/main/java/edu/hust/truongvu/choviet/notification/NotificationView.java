package edu.hust.truongvu.choviet.notification;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.MyNotification;

/**
 * Created by truon on 2/22/2018.
 */

public interface NotificationView {
    void loadNotify(ArrayList<MyNotification> listNotification);
}
