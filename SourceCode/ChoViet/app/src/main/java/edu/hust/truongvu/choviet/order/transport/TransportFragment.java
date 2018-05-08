package edu.hust.truongvu.choviet.order.transport;


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
import edu.hust.truongvu.choviet.order.PaymentActivity;
import edu.hust.truongvu.choviet.order.paymethod.PayMethodFragment;
import edu.hust.truongvu.choviet.order.PaymentPresenterImp;
import edu.hust.truongvu.choviet.order.PaymentView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransportFragment extends Fragment implements PaymentView, View.OnClickListener, TransportView{

    private PaymentPresenterImp paymentPresenterImp;
    private TransportPresenterImp transportPresenterImp;
    private View btnTransport;
    private RecyclerView recyclerView;
    private TransportAdapter adapter;
    public static int idTransport = 0;
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
        transportPresenterImp = new TransportPresenterImp(getContext(), this);
        transportPresenterImp.initListTransport();
        btnTransport.setOnClickListener(this);
        return view;
    }

    @Override
    public void loadNext() {
        if (idTransport == 0){
            Toast.makeText(getContext(), getContext().getString(R.string.please_select_transport), Toast.LENGTH_SHORT).show();
        }else {
            ((PaymentActivity)getContext()).loadFragment(PayMethodFragment.getInstance());
            PaymentActivity.stepView.go(2, true);
        }

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
                idTransport = transport.getId();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
