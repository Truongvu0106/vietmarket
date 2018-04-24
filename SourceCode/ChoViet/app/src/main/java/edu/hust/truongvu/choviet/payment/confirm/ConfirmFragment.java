package edu.hust.truongvu.choviet.payment.confirm;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.adapter.ListCustomerOrderAdapter;
import edu.hust.truongvu.choviet.entity.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment implements ConfirmView{

    private RecyclerView recyclerView;
    private TextView tvTransport, tvPayment, tvSummary;
    private View btnConfirm;
    private ListCustomerOrderAdapter adapter;
    private ConfirmPresenterImp confirmPresenterImp;
    public ConfirmFragment() {
        // Required empty public constructor
    }

    public static ConfirmFragment getInstance(){
        ConfirmFragment fragment = new ConfirmFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);
        recyclerView = view.findViewById(R.id.list_order);
        tvTransport = view.findViewById(R.id.tv_shipping);
        tvPayment = view.findViewById(R.id.tv_payment);
        tvSummary = view.findViewById(R.id.tv_summary);

        confirmPresenterImp = new ConfirmPresenterImp(getContext(), this);
        confirmPresenterImp.initView();
        return view;
    }

    @Override
    public void loadView(ArrayList<Product> list) {
        adapter = new ListCustomerOrderAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
