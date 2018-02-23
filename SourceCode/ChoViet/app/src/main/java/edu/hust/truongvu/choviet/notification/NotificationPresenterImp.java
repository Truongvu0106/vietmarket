package edu.hust.truongvu.choviet.notification;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.MyNotification;

/**
 * Created by truon on 2/22/2018.
 */

public class NotificationPresenterImp implements NotificationPresenter {
    private NotificationView notificationView;
    public NotificationPresenterImp(NotificationView notificationView){
        this.notificationView = notificationView;
    }
    @Override
    public void initNotification() {
        ArrayList<MyNotification> listNotification = new ArrayList<>();
        notificationView.loadNotify(listNotification);
    }
}
