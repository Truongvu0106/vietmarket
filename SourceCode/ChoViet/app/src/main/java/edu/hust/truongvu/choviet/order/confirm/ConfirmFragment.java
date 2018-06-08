package edu.hust.truongvu.choviet.order.confirm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.init.MainActivity;
import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;
import edu.hust.truongvu.choviet.model.entity.PayMethod;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Transport;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.order.address.AddressFragment;
import edu.hust.truongvu.choviet.order.paymethod.PayMethodFragment;
import edu.hust.truongvu.choviet.order.transport.TransportFragment;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment implements ConfirmView, View.OnClickListener{

    private RecyclerView recyclerView;
    private TextView tvAddress, tvTransport, tvPayment, tvSummary;
    private EditText edtPromotion;
    private View btnConfirm, btnApply;
    private ListCustomerOrderAdapter adapter;
    private ConfirmPresenterImp confirmPresenterImp;
    private long summary = 0;
    private int idTransport, idPayment;
    private String mAddress = "", mName = "", mPhone = "";
    private ArrayList<OrderDetails> listOrderDetails;
    private boolean isApplyPromotionSuccessful = false;
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
        tvAddress = view.findViewById(R.id.tv_address);
        tvTransport = view.findViewById(R.id.tv_shipping);
        tvPayment = view.findViewById(R.id.tv_payment);
        tvSummary = view.findViewById(R.id.tv_summary);
        edtPromotion = view.findViewById(R.id.edt_code);
        btnApply = view.findViewById(R.id.btn_apply);
        btnConfirm = view.findViewById(R.id.btn_confirm);

        btnConfirm.setOnClickListener(this);
        btnApply.setOnClickListener(this);

        confirmPresenterImp = new ConfirmPresenterImp(getContext(), this);
        Log.e("id_transport", TransportFragment.idTransport + "");
        confirmPresenterImp.initView(AddressFragment.mName, AddressFragment.mPhone, AddressFragment.selectedAddress,
                TransportFragment.idTransport, PayMethodFragment.idMethod);
        return view;
    }

    @Override
    public void loadViewSuccess(Transport transport, PayMethod payMethod, String name, String phone, String address, ArrayList<Product> list) {
        idPayment = payMethod.getId();
        idTransport = transport.getId();
        Log.e("transport", idTransport+ "");
        Log.e("payment", idPayment+ "");
        mAddress = address;
        mName = name;
        mPhone = phone;

        tvAddress.setText(address);
        tvPayment.setText(payMethod.getName());
        tvTransport.setText(transport.getTitle());

        adapter = new ListCustomerOrderAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        listOrderDetails = new ArrayList<>();
        for (Product product : list){
            //generate list details
            int idProduct = product.getId();
            int idShop = product.getIdShop();
            int number = product.getNumberSelect();
            OrderDetails orderDetails = new OrderDetails(0, 0, idProduct, idShop, number, 0,
                    Constants.OrderStatus.WAITING.getIdStatus());
            listOrderDetails.add(orderDetails);
            //caculate summary
            long price = (product.getPrice() - (product.getPrice()*product.getDiscount())/100)*product.getNumberSelect();
            summary += price;
        }

        summary += transport.getPrice();
        summary += payMethod.getPrice();
        tvSummary.setText(MyHelper.formatMoney(summary));
    }

    @Override
    public void loadViewFalse() {
        MyHelper.showToast(getContext(), "False", FancyToast.ERROR);
    }

    @Override
    public void addOrderSuccessful() {
        confirmPresenterImp.updateStockProduct(listOrderDetails);

    }

    @Override
    public void addOrderFalse() {
        MyHelper.showToast(getContext(), getContext().getString(R.string.m_false), FancyToast.ERROR);
    }

    @Override
    public void updateStockSuccessful() {
        startActivity(new Intent(getActivity(), OrderSuccessfulActivity.class));
    }

    @Override
    public void updateStockFalse() {
        MyHelper.showToast(getContext(), getContext().getString(R.string.update_false), FancyToast.ERROR);
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void applyPromotionSuccessful(long price) {
        isApplyPromotionSuccessful = true;
        summary -= price;
        Log.e("promotion_price", price + "");
        Log.e("promotion_summary", summary + "");
        if (summary <= 0) summary = 0;
        tvSummary.setText(MyHelper.formatMoney(summary));
        MyHelper.showToast(getContext(), getContext().getString(R.string.apply_successful), FancyToast.SUCCESS);
    }

    @Override
    public void applyPromotionFalse(String message) {
        isApplyPromotionSuccessful = false;
        MyHelper.showToast(getContext(), message, FancyToast.ERROR);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                confirm();
                break;
            case R.id.btn_apply:
                applyPromotion();
                break;
        }
    }

    private void applyPromotion(){
        if (isApplyPromotionSuccessful){
            MyHelper.showToast(getContext(), getContext().getString(R.string.only_one_code_used), FancyToast.WARNING);
            return;
        }
        String code = edtPromotion.getText().toString().trim();
        if (code.matches("")){
            MyHelper.showToast(getContext(), getContext().getString(R.string.please_enter_all), FancyToast.WARNING);
        }else {
            confirmPresenterImp.applyPromotion(code);
        }
    }

    private void confirm(){
        int currentUserId = MyHelper.getUserIdPreference(getContext());
        Log.e("user_order", currentUserId + "");
        Order order = new Order(0, currentUserId, mName, mPhone, System.currentTimeMillis(),
                Constants.OrderStatus.WAITING.getIdStatus(), idTransport, idPayment, summary, mAddress);
        order.setOrderDetails(listOrderDetails);
        confirmPresenterImp.confirmOrder(order);
    }
}
