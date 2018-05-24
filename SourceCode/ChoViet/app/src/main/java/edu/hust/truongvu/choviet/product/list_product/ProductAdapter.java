package edu.hust.truongvu.choviet.product.list_product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartActivity;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.order.address.GuessInfoActivity;
import edu.hust.truongvu.choviet.startup.StartActivity;
import edu.hust.truongvu.choviet.user.ProfileCheckDialog;

/**
 * Created by truon on 2/23/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{
    public interface ProductListener{
        void onProductResult(Product product);
        void onLikeClick(int idUser, Product product);
        void onUnlikeClick(int idUser, Product product);
    }
    private ProductListener myListener;
    private ArrayList<Product> data;
    private Context context;
    private ProductModel productModel;

    public ProductAdapter(Context context, ArrayList<Product> data, ProductListener listener){
        this.data = data;
        this.myListener = listener;
        this.context = context;
        productModel = new ProductModel(context);
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, null);
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
        private boolean isLiked = false;
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
            final int currentUserId = MyHelper.getUserIdPreference(context);
            tvName.setText(product.getName());

            int discount = product.getDiscount();
            long oldPrice = product.getPrice();
            if (discount == 0){
                layoutDiscount.setVisibility(View.GONE);
                tvOldPrice.setText("");
                tvNewPrice.setText(MyHelper.formatMoney(oldPrice));
            }else {
                layoutDiscount.setVisibility(View.VISIBLE);
                tvDiscount.setText("-"+discount+"%");
                long newPrice = oldPrice - oldPrice*discount/100;
                tvOldPrice.setText(MyHelper.formatMoney(oldPrice));
                tvNewPrice.setText(MyHelper.formatMoney(newPrice));
            }
            isLiked = productModel.isLiked(currentUserId, product.getId());
            if (isLiked){
                imgLikeFill.setVisibility(View.VISIBLE);
            }else {
                imgLikeFill.setVisibility(View.GONE);
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
                    if (currentUserId != 0){
                        if (isLiked){
                            imgLikeFill.setVisibility(View.GONE);
                            if (productModel.unlikeProduct(currentUserId, product.getId())){
                                Toast.makeText(context, context.getString(R.string.remove_from_your_wishlist), Toast.LENGTH_SHORT).show();
                            }
                            myListener.onUnlikeClick(currentUserId, product);
                        }else {
                            imgLikeFill.setVisibility(View.VISIBLE);
                            if (productModel.likeProduct(currentUserId, product.getId())){
                                Toast.makeText(context, context.getString(R.string.add_to_your_wishlist), Toast.LENGTH_SHORT).show();
                            }
                            myListener.onLikeClick(currentUserId, product);
                        }
                    }else {
                        ProfileCheckDialog dialog = new ProfileCheckDialog(context, new ProfileCheckDialog.ProfileCheckListener() {
                            @Override
                            public void login() {
                                context.startActivity(new Intent(context, StartActivity.class));
                            }

                            @Override
                            public void continueAsGuess() {

                            }
                        });
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }

                }
            });
        }
    }
}
