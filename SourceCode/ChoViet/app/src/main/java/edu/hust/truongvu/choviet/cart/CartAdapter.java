package edu.hust.truongvu.choviet.cart;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 2/27/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{
    public interface CartItemListener{
        void onClick(Product product);
        void onLike(Product product);
        void onDelete(Product product);
        void onSpinnerSelect(Product product, int numberSelect);
    }
    private CartItemListener myListener;
    private Context context;
    private ArrayList<Product> data;

    public CartAdapter(Context context, ArrayList<Product> data, CartItemListener listener){
        this.myListener = listener;
        this.context = context;
        this.data = data;
    }

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        CartHolder holder = new CartHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartHolder holder, int position) {
        Product product = data.get(position);
        holder.setContent(product);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class CartHolder extends RecyclerView.ViewHolder{
        private ImageView imgShop, imgProduct;
        private TextView tvNameShop, tvNameProduct, tvOldPrice, tvNewPrice;
        private Spinner spinNumber;
        private View btnDelete, btnLike, like_fill;
        public CartHolder(View itemView) {
            super(itemView);
            imgShop = itemView.findViewById(R.id.img_shop);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvNameShop = itemView.findViewById(R.id.name_shop);
            tvNameProduct = itemView.findViewById(R.id.name_product);
            tvOldPrice = itemView.findViewById(R.id.old_price_product);
            tvNewPrice = itemView.findViewById(R.id.new_price_product);
            spinNumber = itemView.findViewById(R.id.spin_number_stock);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnLike = itemView.findViewById(R.id.btn_like);
            like_fill = itemView.findViewById(R.id.like_fill);

            tvOldPrice.setPaintFlags(tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void setContent(final Product product){
            long oldPrice = product.getPrice();
            int discount = product.getDiscount();
            if (discount == 0){
                tvOldPrice.setText("");
                tvNewPrice.setText(MyHelper.formatMoney(product.getPrice()));
            }else {
                tvOldPrice.setText(MyHelper.formatMoney(oldPrice));
                long newPrice = oldPrice - oldPrice*product.getDiscount()/100;
                tvNewPrice.setText(MyHelper.formatMoney(newPrice));
            }
//            imgProduct.setImageResource(R.drawable.giaydep);
            MyHelper.setImagePicasso(context,imgProduct,Constants.Path.MY_PATH + product.getImgs().get(0));
            tvNameProduct.setText(product.getName());
            like_fill.setVisibility(View.GONE);
            initSpinner(product);

            spinNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    myListener.onSpinnerSelect(product, i+1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onClick(product);
                }
            });

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onLike(product);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onDelete(product);
                }
            });
        }

        private void initSpinner(Product product){
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 1; i <= product.getAmount(); i++){
                arrayList.add(i + "");
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, arrayList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinNumber.setAdapter(dataAdapter);

            spinNumber.setSelection(product.getNumberSelect() - 1);
        }

    }
}
