package edu.hust.truongvu.choviet.home;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 2/23/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{
    public interface ProductListener{
        void onProductResult(Product product);
        void onLikeClick();
    }
    private ProductListener myListener;
    private ArrayList<Product> data;

    public ProductAdapter(ArrayList<Product> data, ProductListener listener){
        this.data = data;
        this.myListener = listener;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_product, null);
        ProductHolder holder = new ProductHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        Product product = data.get(position);
        holder.setContent(product);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ProductHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct, imgLikeFill;
        private TextView tvDiscount, tvName, tvOldPrice, tvNewPrice;
        private View layoutDiscount, layoutLike;
        private RatingBar ratingBar;
        public ProductHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            imgLikeFill = itemView.findViewById(R.id.img_like_fill);
            tvDiscount = itemView.findViewById(R.id.tv_discount);
            tvName = itemView.findViewById(R.id.name_product);
            tvOldPrice = itemView.findViewById(R.id.olđ_price_product);
            tvNewPrice = itemView.findViewById(R.id.new_price_product);
            layoutDiscount = itemView.findViewById(R.id.layout_discount);
            layoutLike = itemView.findViewById(R.id.btn_like);
            ratingBar = itemView.findViewById(R.id.rate_product);

            tvOldPrice.setPaintFlags(tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        public void setContent(final Product product){
            int discount = product.getDiscount();
            long oldPrice = product.getPrice();
            long newPrice = oldPrice - oldPrice*discount/100;
            imgProduct.setImageResource(product.getImg());
            tvDiscount.setText("-" + discount + "%");
            tvName.setText(product.getName());
            tvOldPrice.setText(oldPrice + "đ");
            tvNewPrice.setText(newPrice + "đ");
            ratingBar.setRating(product.getRate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onProductResult(product);
                }
            });

            layoutLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onLikeClick();
                }
            });
        }
    }
}
