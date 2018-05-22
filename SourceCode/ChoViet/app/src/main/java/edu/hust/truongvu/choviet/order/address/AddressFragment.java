package edu.hust.truongvu.choviet.order.address;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.order.PaymentActivity;
import edu.hust.truongvu.choviet.order.PaymentPresenterImp;
import edu.hust.truongvu.choviet.order.PaymentView;
import edu.hust.truongvu.choviet.order.transport.TransportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment implements PaymentView, AddressView, View.OnClickListener{
    public static String selectedAddress = "";
    public static String mName = "", mPhone = "";
    private PaymentPresenterImp paymentPresenterImp;
    private AddressPresenterImp addressPresenterImp;
    private TextView tvuserName, tvPhone;
    private View btnNext, btnInsertAddress;
    private RecyclerView recyclerView;
    private AddressAdapter adapter;
    private User user;
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
        tvuserName = view.findViewById(R.id.tv_name_user);
        tvPhone = view.findViewById(R.id.tv_phone);
        btnInsertAddress = view.findViewById(R.id.btn_add_address);
        btnNext = view.findViewById(R.id.btn_address);
        recyclerView = view.findViewById(R.id.list_address);

        paymentPresenterImp = new PaymentPresenterImp(this);
        addressPresenterImp = new AddressPresenterImp(getContext(), this);

        user = MyHelper.getCurrentUser(getContext());
        if (user != null){
            btnInsertAddress.setVisibility(View.VISIBLE);
            addressPresenterImp.initUser(user);
            addressPresenterImp.initListAddress(user);

            mName = user.getFullname();
            mPhone = user.getPhone();
        }else {
            btnInsertAddress.setVisibility(View.GONE);
            String name = getActivity().getIntent().getStringExtra(GuessInfoActivity.GUESS_NAME);
            String phone = getActivity().getIntent().getStringExtra(GuessInfoActivity.GUESS_PHONE);
            String address = getActivity().getIntent().getStringExtra(GuessInfoActivity.GUESS_ADDRESS);

            mName = name;
            mPhone = phone;
            selectedAddress = address;

            addressPresenterImp.initGuess(name, phone);
            addressPresenterImp.initAddressGuess(address);
        }


        btnNext.setOnClickListener(this);
        btnInsertAddress.setOnClickListener(this);

        return view;
    }

    @Override
    public void loadNext() {
        if (selectedAddress.matches("")){
            Toast.makeText(getContext(), getContext().getString(R.string.please_select_address), Toast.LENGTH_SHORT).show();
        }else {
            PaymentActivity.stepView.go(1, true);
            ((PaymentActivity)getContext()).loadFragment(TransportFragment.getInstance());
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_address:
                paymentPresenterImp.onClickNext();
                break;
            case R.id.btn_add_address:
                InsertAddressDialog dialog = new InsertAddressDialog(getContext(), new InsertAddressDialog.InsertAddressListener() {
                    @Override
                    public void onInsert(String address) {
                        addressPresenterImp.insertAddress(address);
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void loadListAddress(ArrayList<String> list) {
        adapter = new AddressAdapter(list, new AddressAdapter.AddressListener() {
            @Override
            public void onCheck(String address) {
                selectedAddress = address;
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void loadUserInfo(String username, String phone) {
        tvuserName.setText(username);
        tvPhone.setText(phone);
    }

    @Override
    public void insertSuccessful() {
        addressPresenterImp.initListAddress(user);
    }

    @Override
    public void insertFalse() {
        Toast.makeText(getContext(), getContext().getString(R.string.insert_false), Toast.LENGTH_SHORT).show();
    }
}
