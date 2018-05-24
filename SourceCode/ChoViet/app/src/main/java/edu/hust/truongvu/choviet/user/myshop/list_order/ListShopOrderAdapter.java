package edu.hust.truongvu.choviet.user.myshop.list_order;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Transport;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.OrderModel;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.TransportModel;
import edu.hust.truongvu.choviet.model.UserModel;
import edu.hust.truongvu.choviet.helper.Constants.OrderStatus;


/**
 * Created by truon on 4/23/2018.
 */

public class ListShopOrderAdapter extends RecyclerView.Adapter<ListShopOrderAdapter.ShopListOrderViewHolder>{
    public interface ShopListOrderListener{
        void onUpdateStatus(Order order, Constants.OrderStatus status);
        void onDelete(int id);
    }

    private Context mContext;
    private ShopListOrderListener mListener;
    private ArrayList<Order> listOrder;

    public ListShopOrderAdapter(Context context, ArrayList<Order> data, ShopListOrderListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.listOrder = data;
    }

    @Override
    public ShopListOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_shop, parent, false);
        ShopListOrderViewHolder holder = new ShopListOrderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopListOrderViewHolder holder, int position) {
        Order order = listOrder.get(position);
        holder.setContent(order);
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    class ShopListOrderViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNameUser, tvAddress, tvPhone, tvPayment, tvTransport, tvSummary;
        private ImageView checkWait, checkShipping, checkReceived, checkCancel;
        private RecyclerView recyclerView;
        private View btnUpdate, btnDelete;
        private UserModel userModel;
        private TransportModel transportModel;
        private OrderModel orderModel;

        public ShopListOrderViewHolder(View itemView) {
            super(itemView);
            tvNameUser = itemView.findViewById(R.id.tv_name_customer);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvPhone = itemView.findViewById(R.id.phone);
            tvPayment = itemView.findViewById(R.id.tv_payment);
            tvTransport = itemView.findViewById(R.id.tv_shipping);
            tvSummary = itemView.findViewById(R.id.tv_summary);
            checkWait = itemView.findViewById(R.id.check_wait);
            checkShipping = itemView.findViewById(R.id.check_shipping);
            checkReceived = itemView.findViewById(R.id.check_received);
            checkCancel = itemView.findViewById(R.id.check_cancel);

            recyclerView = itemView.findViewById(R.id.list_product);
            btnUpdate = itemView.findViewById(R.id.btn_update_status);
            btnDelete = itemView.findViewById(R.id.btn_delete);

            userModel = new UserModel(mContext);
            transportModel = new TransportModel(mContext);
            orderModel = new OrderModel(mContext);
        }

        public void setContent(final Order order){
            Transport transport = transportModel.getTransportById(order.getTypeTransport());
            ArrayList<OrderDetails> orderDetails = orderModel.getDetailsOrderById(order.getId());
            tvAddress.setText(order.getAddress());
            tvNameUser.setText(order.getFullName());
            tvPhone.setText(order.getAddress());


            if (transport != null){
                tvTransport.setText(transport.getTitle());
            }

            tvSummary.setText(MyHelper.formatMoney(order.getPrice()));

            int status = order.getStatus();
            if (status == OrderStatus.WAITING.getIdStatus()){
                checkWait.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            }else if (status == OrderStatus.SHIPPING.getIdStatus()){
                checkShipping.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            }else if (status == OrderStatus.CANCEL.getIdStatus()){
                checkReceived.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            }else {
                checkCancel.setColorFilter(ContextCompat.getColor(mContext, R.color.colorRed));
            }

            ArrayList<Product> listProduct = new ArrayList<>();
            ProductModel productModel = new ProductModel(mContext);

            ArrayList<Integer> listNumber = new ArrayList<>();

            if (orderDetails != null){
                for (OrderDetails details : orderDetails){
                    Product product = productModel.getProductById(details.getIdProduct());
                    int number = details.getNumber();
                    listProduct.add(product);
                    listNumber.add(number);
                }
            }

            ProductInOrderAdapter adapter = new ProductInOrderAdapter(mContext, listProduct, listNumber);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateStatusOrderDialog dialog = new UpdateStatusOrderDialog(mContext, new UpdateStatusOrderDialog.UpdateStatusListener() {
                        @Override
                        public void onClick(OrderStatus status) {
                            mListener.onUpdateStatus(order, status);
                        }
                    });
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onDelete(order.getId());
                }
            });
        }
    }
}
