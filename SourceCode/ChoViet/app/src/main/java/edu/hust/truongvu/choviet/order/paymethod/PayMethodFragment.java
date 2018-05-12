package edu.hust.truongvu.choviet.order.paymethod;


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
import edu.hust.truongvu.choviet.model.entity.PayMethod;
import edu.hust.truongvu.choviet.order.confirm.ConfirmFragment;
import edu.hust.truongvu.choviet.order.PaymentActivity;
import edu.hust.truongvu.choviet.order.PaymentPresenterImp;
import edu.hust.truongvu.choviet.order.PaymentView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayMethodFragment extends Fragment implements PaymentView, View.OnClickListener, PayMethodView{

    private PaymentPresenterImp paymentPresenterImp;
    private PayMethodPresenterImp payMethodPresenterImp;
    private View btnPayment;
    private RecyclerView recyclerView;
    private ListPayMethodAdapter adapter;
    public static int idMethod = 0;
    public PayMethodFragment() {
        // Required empty public constructor
    }

    public static PayMethodFragment getInstance(){
        PayMethodFragment fragment = new PayMethodFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        btnPayment = view.findViewById(R.id.btn_payment);
        recyclerView = view.findViewById(R.id.list_payment);
        paymentPresenterImp = new PaymentPresenterImp(this);
        payMethodPresenterImp = new PayMethodPresenterImp(getContext(), this);

        payMethodPresenterImp.initListPayMethod();

        btnPayment.setOnClickListener(this);
        return view;
    }

    @Override
    public void loadNext() {
        if (idMethod == 0){
            Toast.makeText(getContext(), getContext().getString(R.string.please_select_paymethod), Toast.LENGTH_SHORT).show();
        }else {
            ((PaymentActivity)getContext()).loadFragment(ConfirmFragment.getInstance());
            PaymentActivity.stepView.go(3, true);
        }

    }

    @Override
    public void onClick(View view) {
        paymentPresenterImp.onClickNext();
    }

    @Override
    public void loadListPayment(ArrayList<PayMethod> list) {
        adapter = new ListPayMethodAdapter(getContext(), list, new ListPayMethodAdapter.PayMethodListener() {
            @Override
            public void onClick(PayMethod payMethod) {
                idMethod = payMethod.getId();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
