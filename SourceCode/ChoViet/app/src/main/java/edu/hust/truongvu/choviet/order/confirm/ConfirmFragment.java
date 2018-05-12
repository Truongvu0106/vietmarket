package edu.hust.truongvu.choviet.order.confirm;


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
    private View btnConfirm;
    private ListCustomerOrderAdapter adapter;
    private ConfirmPresenterImp confirmPresenterImp;
    private long summary = 0;
    private int idTransport, idPayment;
    private String mAddress = "";
    private ArrayList<OrderDetails> listOrderDetails;
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
        btnConfirm = view.findViewById(R.id.btn_confirm);

        btnConfirm.setOnClickListener(this);

        confirmPresenterImp = new ConfirmPresenterImp(getContext(), this);
        confirmPresenterImp.initView(AddressFragment.selectedAddress, TransportFragment.idTransport, PayMethodFragment.idMethod);
        return view;
    }

    @Override
    public void loadViewSuccess(Transport transport, PayMethod payMethod, String address, ArrayList<Product> list) {
        idPayment = payMethod.getId();
        idTransport = transport.getId();
        mAddress = address;

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
            OrderDetails orderDetails = new OrderDetails(0, 0, idProduct, idShop, number);
            listOrderDetails.add(orderDetails);
            //caculate summary
            long price = product.getPrice() - (product.getPrice()*product.getDiscount())/100;
            summary += price;
        }

        summary += transport.getPrice();
        summary += payMethod.getPrice();
        tvSummary.setText(MyHelper.formatMoney(summary));
    }

    @Override
    public void loadViewFalse() {
        Toast.makeText(getContext(), "False", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOrderSuccessful() {
        Toast.makeText(getContext(), getContext().getString(R.string.order_confirmed_successful), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOrderFalse() {
        Toast.makeText(getContext(), getContext().getString(R.string.m_false), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                confirm();
                break;
        }
    }

    private void confirm(){
        Order order = new Order(0, MyHelper.getUserIdPreference(getContext()), System.currentTimeMillis(),
                Constants.OrderStatus.WAITING.getIdStatus(), idTransport, idPayment, summary, mAddress);
        order.setOrderDetails(listOrderDetails);
        confirmPresenterImp.confirmOrder(order);
    }
}
