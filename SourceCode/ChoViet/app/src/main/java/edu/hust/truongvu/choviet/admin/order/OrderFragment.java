package edu.hust.truongvu.choviet.admin.order;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.customview.DeleteItemDialog;
import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.user.myorder.ListOrderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment implements OrderView{
    private RecyclerView recyclerView;
    private ListOrderAdapter adapter;
    private OrderPresenter presenter;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView = view.findViewById(R.id.list_order);
        presenter = new OrderPresenterimp(getContext(), this);
        presenter.initListOrder();
        return view;
    }

    @Override
    public void loadOrdersSuccessful(final ArrayList<Order> data) {
        adapter = new ListOrderAdapter(getContext(), data, false, new ListOrderAdapter.ShopListOrderListener() {
            @Override
            public void onUpdateStatus(Order order, Constants.OrderStatus status) {
                presenter.updateStatusOrder(order.getId(), status.getIdStatus());
            }

            @Override
            public void onDelete(final Order order) {
                DeleteItemDialog deleteItemDialog = new DeleteItemDialog(getContext(), order.getId(), new DeleteItemDialog.DeleteItemCartListener() {
                    @Override
                    public void onDelete(int id) {
                        data.remove(order);
                        adapter.notifyDataSetChanged();
                    }
                });
                deleteItemDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                deleteItemDialog.show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadOrderFalse() {

    }

    @Override
    public void updateSuccessful() {
        Toast.makeText(getContext(), getContext().getString(R.string.update_successful), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateFalse() {
        Toast.makeText(getContext(), getContext().getString(R.string.update_false), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSuccessful() {

    }

    @Override
    public void deleteFalse() {

    }
}
