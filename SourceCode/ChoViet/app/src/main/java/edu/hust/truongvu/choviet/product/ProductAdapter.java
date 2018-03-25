package edu.hust.truongvu.choviet.product;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

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
    private Context context;

    public ProductAdapter(Context context, ArrayList<Product> data, ProductListener listener){
        this.data = data;
        this.myListener = listener;
        this.context = context;
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
            tvName.setText(product.getName());

            int discount = product.getDiscount();
            long oldPrice = product.getPrice();
            if (discount == 0){
                layoutDiscount.setVisibility(View.GONE);
                tvOldPrice.setText("");
                tvNewPrice.setText(MyHelper.formatMoney(oldPrice)+"đ");
            }else {
                layoutDiscount.setVisibility(View.VISIBLE);
                tvDiscount.setText("-"+discount+"%");
                long newPrice = oldPrice - oldPrice*discount/100;
                tvOldPrice.setText(MyHelper.formatMoney(oldPrice)+"đ");
                tvNewPrice.setText(MyHelper.formatMoney(newPrice)+"đ");
            }
            MyHelper.setImagePicasso(context, imgProduct, Constants.Path.MY_PATH + product.getImgs().get(0).trim());
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
