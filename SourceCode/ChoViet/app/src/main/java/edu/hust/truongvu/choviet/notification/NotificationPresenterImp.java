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
        listNotification.add(new MyNotification(0, 0, "Cập nhật phiên bản", "Phiên bản hiện tại là mới nhất"));
        listNotification.add(new MyNotification(0, 1, "Sản phẩm mới", "Gian hàng bạn theo dõi đã cập nhật sản phẩm mới"));
        listNotification.add(new MyNotification(0, 2, "Khuyến mại 20%", "Tưng bừng đón Tết nguyên đán"));
        listNotification.add(new MyNotification(0, 3, "Cập nhật đơn hàng", "Đơn hàng đã được giao thành công"));
        notificationView.loadNotify(listNotification);
    }
}
