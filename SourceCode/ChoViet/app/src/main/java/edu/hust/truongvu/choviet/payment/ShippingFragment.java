package edu.hust.truongvu.choviet.payment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.hust.truongvu.choviet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShippingFragment extends Fragment implements PaymentView, View.OnClickListener{

    private PaymentPresenterImp paymentPresenterImp;
    private View btnShipping;
    public ShippingFragment() {
        // Required empty public constructor
    }

    public static ShippingFragment getInstance(){
        ShippingFragment fragment = new ShippingFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shipping, container, false);
        btnShipping = view.findViewById(R.id.btn_shipping);
        paymentPresenterImp = new PaymentPresenterImp(this);
        btnShipping.setOnClickListener(this);
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
}
