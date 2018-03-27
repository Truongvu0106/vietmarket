package edu.hust.truongvu.choviet.payment.transport;


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
import edu.hust.truongvu.choviet.entity.Transport;
import edu.hust.truongvu.choviet.payment.PaymentActivity;
import edu.hust.truongvu.choviet.payment.paymethod.PaymentFragment;
import edu.hust.truongvu.choviet.payment.PaymentPresenterImp;
import edu.hust.truongvu.choviet.payment.PaymentView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransportFragment extends Fragment implements PaymentView, View.OnClickListener, TransportView{

    private PaymentPresenterImp paymentPresenterImp;
    private TransportPresenterImp transportPresenterImp;
    private View btnTransport;
    private RecyclerView recyclerView;
    private TransportAdapter adapter;
    public TransportFragment() {
        // Required empty public constructor
    }

    public static TransportFragment getInstance(){
        TransportFragment fragment = new TransportFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transport, container, false);
        btnTransport = view.findViewById(R.id.btn_shipping);
        recyclerView = view.findViewById(R.id.list_transport);

        paymentPresenterImp = new PaymentPresenterImp(this);
        transportPresenterImp = new TransportPresenterImp(this);
        transportPresenterImp.initListTransport();
        btnTransport.setOnClickListener(this);
        return view;
    }

    @Override
    public void loadNext() {
        ((PaymentActivity)getContext()).loadFragment(PaymentFragment.getInstance());
        PaymentActivity.stepView.go(2, true);
//        PaymentActivity.stepView.done(true);
    }

    @Override
    public void onClick(View view) {
        paymentPresenterImp.onClickNext();
    }

    @Override
    public void loadListTransport(ArrayList<Transport> arrayList) {
        adapter = new TransportAdapter(arrayList, new TransportAdapter.TransportListener() {
            @Override
            public void onClick(Transport transport) {
                Toast.makeText(getContext(), transport.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
