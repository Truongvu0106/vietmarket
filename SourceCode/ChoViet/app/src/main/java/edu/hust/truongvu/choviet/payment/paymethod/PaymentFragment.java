package edu.hust.truongvu.choviet.payment.paymethod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.payment.confirm.ConfirmFragment;
import edu.hust.truongvu.choviet.payment.PaymentActivity;
import edu.hust.truongvu.choviet.payment.PaymentPresenterImp;
import edu.hust.truongvu.choviet.payment.PaymentView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment implements PaymentView, View.OnClickListener{

    private PaymentPresenterImp paymentPresenterImp;
    private View btnPayment;
    public PaymentFragment() {
        // Required empty public constructor
    }

    public static PaymentFragment getInstance(){
        PaymentFragment fragment = new PaymentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        btnPayment = view.findViewById(R.id.btn_payment);
        paymentPresenterImp = new PaymentPresenterImp(this);
        btnPayment.setOnClickListener(this);
        return view;
    }

    @Override
    public void loadNext() {
        ((PaymentActivity)getContext()).loadFragment(ConfirmFragment.getInstance());
        PaymentActivity.stepView.go(3, true);
//        PaymentActivity.stepView.done(true);
    }

    @Override
    public void onClick(View view) {
        paymentPresenterImp.onClickNext();
    }
}
