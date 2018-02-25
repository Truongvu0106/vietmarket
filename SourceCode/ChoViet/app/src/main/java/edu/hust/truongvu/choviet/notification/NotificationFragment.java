package edu.hust.truongvu.choviet.notification;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.MyNotification;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment implements NotificationView{

    private RecyclerView mListNotification;
    private NotificationPresenterImp notificationPresenterImp;
    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(){
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        mListNotification = view.findViewById(R.id.list_notification);
        notificationPresenterImp = new NotificationPresenterImp(this);
        notificationPresenterImp.initNotification();
        return view;
    }

    @Override
    public void loadNotify(ArrayList<MyNotification> listNotification) {
        NotificationAdapter adapter = new NotificationAdapter(listNotification, new NotificationAdapter.NotificationListener() {
            @Override
            public void onResult(MyNotification notification) {
                Toast.makeText(getContext(), notification.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        mListNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        mListNotification.setAdapter(adapter);
    }
}
