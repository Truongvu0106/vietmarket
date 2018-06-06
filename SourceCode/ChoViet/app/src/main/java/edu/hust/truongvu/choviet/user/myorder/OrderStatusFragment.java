package edu.hust.truongvu.choviet.user.myorder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.Order;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderStatusFragment extends Fragment implements OrderStatusView{
    private int status = 0;
    private RecyclerView recyclerView;
    private ListOrderAdapter adapter;
    private OrderStatusPresenter presenter;

    public OrderStatusFragment() {
        // Required empty public constructor
    }

    public static OrderStatusFragment getInstance(int status){
        OrderStatusFragment fragment = new OrderStatusFragment();
        fragment.status = status;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_status, container, false);
        recyclerView = view.findViewById(R.id.list_order);
        presenter = new OrderStatusPresenterImp(getContext(), this);
        presenter.initListOrder(MyHelper.getUserIdPreference(getContext()), status);
        return view;
    }

    @Override
    public void loadListSuccessful(ArrayList<Order> orders) {
        adapter = new ListOrderAdapter(getContext(), orders, true, new ListOrderAdapter.ShopListOrderListener() {
            @Override
            public void onUpdateStatus(Order order, Constants.OrderStatus status) {

            }

            @Override
            public void onDelete(Order order) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadFalse() {

    }
}
