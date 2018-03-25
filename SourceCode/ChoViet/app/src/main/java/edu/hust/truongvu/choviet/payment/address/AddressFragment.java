package edu.hust.truongvu.choviet.payment.address;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.payment.PaymentActivity;
import edu.hust.truongvu.choviet.payment.PaymentPresenterImp;
import edu.hust.truongvu.choviet.payment.PaymentView;
import edu.hust.truongvu.choviet.payment.shipping.ShippingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment implements PaymentView, View.OnClickListener{

    private PaymentPresenterImp paymentPresenterImp;
    private View btnNext;
    public AddressFragment() {
        // Required empty public constructor
    }

    public static AddressFragment getInstance(){
        AddressFragment fragment = new AddressFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        btnNext = view.findViewById(R.id.btn_address);
        paymentPresenterImp = new PaymentPresenterImp(this);
        btnNext.setOnClickListener(this);
        return view;
    }

    @Override
    public void loadNext() {
        PaymentActivity.stepView.go(1, true);
//        PaymentActivity.stepView.done(true);
        ((PaymentActivity)getContext()).loadFragment(ShippingFragment.getInstance());
    }

    @Override
    public void onClick(View view) {
        paymentPresenterImp.onClickNext();
    }
}
