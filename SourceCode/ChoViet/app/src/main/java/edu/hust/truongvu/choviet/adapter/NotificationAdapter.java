package edu.hust.truongvu.choviet.notification;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.MyNotification;

/**
 * Created by truon on 2/25/2018.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationHolder>{
    public interface NotificationListener{
        void onResult(MyNotification notification);
    }
    private NotificationListener myListener;
    private ArrayList<MyNotification> data;

    public NotificationAdapter(ArrayList<MyNotification> data, NotificationListener listener){
        this.data = data;
        this.myListener = listener;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        NotificationHolder holder = new NotificationHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, int position) {
        MyNotification notification = data.get(position);
        holder.setContent(notification);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NotificationHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title, content;
        public NotificationHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_notification);
            title = itemView.findViewById(R.id.title_notification);
            content = itemView.findViewById(R.id.content_notification);
        }

        public void setContent(final MyNotification notification){
            int img = 0;
            switch (notification.getType()){
                case 0:
                    img = R.drawable.noty_hethong;
                    break;
                case 1:
                    img = R.drawable.noty_gianhang;
                    break;
                case 2:
                    img = R.drawable.noty_khuyenmai;
                    break;
                case 3:
                    img = R.drawable.noty_hoadon;
                    break;
                default:
                    img = R.drawable.noty_hethong;
                    break;
            }
            imageView.setImageResource(img);
            title.setText(notification.getTitle());
            content.setText(notification.getContent());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onResult(notification);
                }
            });
        }
    }
}
