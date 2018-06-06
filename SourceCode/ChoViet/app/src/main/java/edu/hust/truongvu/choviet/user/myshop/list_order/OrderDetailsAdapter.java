package edu.hust.truongvu.choviet.user.myshop.list_order;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;
import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 6/4/2018.
 */

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder>{
    public interface OrderDetailsListener{
        void onUpdate(int id, int status);
        void onDelete(OrderDetails orderDetails);
    }
    private Context mContext;
    private OrderDetailsListener mListener;
    private ArrayList<OrderDetails> data;

    public OrderDetailsAdapter(Context context, ArrayList<OrderDetails> data, OrderDetailsListener listener){
        this.mContext = context;
        this.data = data;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_order_details, parent, false);
        OrderDetailsViewHolder holder = new OrderDetailsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
        OrderDetails details = data.get(position);
        holder.setContent(details);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OrderDetailsViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIdOrder, tvDate, tvName, tvPrice, tvNumber;
        private ImageView img, checkWait, checkShip, checkCancel, checkReceive;
        private View btnUpdate, btnDelete;

        private ProductModel productModel;
        public OrderDetailsViewHolder(View itemView) {
            super(itemView);
            tvIdOrder = itemView.findViewById(R.id.tv_id);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvName = itemView.findViewById(R.id.name_product);
            tvPrice = itemView.findViewById(R.id.price_product);
            tvNumber = itemView.findViewById(R.id.tv_number);
            img = itemView.findViewById(R.id.img_product);
            checkWait = itemView.findViewById(R.id.check_wait);
            checkShip = itemView.findViewById(R.id.check_shipping);
            checkCancel = itemView.findViewById(R.id.check_cancel);
            checkReceive = itemView.findViewById(R.id.check_received);
            btnUpdate = itemView.findViewById(R.id.btn_update_status);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            productModel = new ProductModel(mContext);
        }

        public void setContent(final OrderDetails orderDetails){
            tvIdOrder.setText(mContext.getString(R.string.id_order) + ": " + orderDetails.getIdOrder());
            tvDate.setText(MyHelper.convertDateToString(orderDetails.getDate()));

            Product product = productModel.getProductById(orderDetails.getIdProduct());
            if (product != null){
                MyHelper.setImagePicasso(mContext, img, Constants.Path.MY_PATH + product.getImgs().get(0));
                tvName.setText(product.getName());
                tvPrice.setText(MyHelper.formatMoney(product.getPrice()));
            }
            tvNumber.setText(mContext.getString(R.string.number) + ": " + orderDetails.getNumber());

            int status = orderDetails.getStatus();
            if (status == Constants.OrderStatus.WAITING.getIdStatus()){
                checkWait.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            }else if (status == Constants.OrderStatus.SHIPPING.getIdStatus()){
                checkShip.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            }else if (status == Constants.OrderStatus.RECEIVED.getIdStatus()){
                checkReceive.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
            }else {
                checkCancel.setColorFilter(ContextCompat.getColor(mContext, R.color.colorRed));
            }

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateStatusOrderDialog dialog = new UpdateStatusOrderDialog(mContext, new UpdateStatusOrderDialog.UpdateStatusListener() {
                        @Override
                        public void onClick(Constants.OrderStatus status) {
                            mListener.onUpdate(orderDetails.getId(), status.getIdStatus());
                            updateStatus(status.getIdStatus());
                        }
                    });
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onDelete(orderDetails);
                }
            });
        }

        private void updateStatus(int status){
            if (status == Constants.OrderStatus.WAITING.getIdStatus()){
                checkWait.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                checkShip.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkReceive.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkCancel.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
            }else if (status == Constants.OrderStatus.SHIPPING.getIdStatus()){
                checkWait.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkShip.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                checkReceive.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkCancel.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
            }else if (status == Constants.OrderStatus.RECEIVED.getIdStatus()){
                checkWait.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkShip.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkReceive.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimaryDark));
                checkCancel.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
            }else {
                checkWait.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkShip.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkReceive.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray));
                checkCancel.setColorFilter(ContextCompat.getColor(mContext, R.color.colorRed));
            }
        }
    }
}
