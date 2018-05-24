package edu.hust.truongvu.choviet.user.myshop.list_product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/24/2018.
 */

public class ShopListProductAdapter extends RecyclerView.Adapter<ShopListProductAdapter.ShopListViewHolder>{
    public interface ShopProductListener{
        void setDiscount(Product product);
        void onDelete(Product product);
        void onUpdate(Product product);
    }
    private ShopProductListener mListener;
    private ArrayList<Product> listProduct;
    private Context mContext;

    public ShopListProductAdapter(Context context, ArrayList<Product> data, ShopProductListener listener){
        this.mContext = context;
        this.listProduct = data;
        this.mListener = listener;
    }

    @Override
    public ShopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_shop, parent, false);
        ShopListViewHolder holder = new ShopListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShopListViewHolder holder, int position) {
        Product product = listProduct.get(position);
        holder.setContent(product);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    class ShopListViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvName, tvPrice, tvStock, tvSold;
        private RatingBar ratingBar;
        private View btnMore;
        public ShopListViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_product);
            tvName = itemView.findViewById(R.id.name_product);
            tvPrice = itemView.findViewById(R.id.price_product);
            tvStock = itemView.findViewById(R.id.tv_stock);
            tvSold = itemView.findViewById(R.id.tv_sold);
            ratingBar = itemView.findViewById(R.id.rate_product);
            btnMore = itemView.findViewById(R.id.more_option);
        }

        public void setContent(final Product product){
            MyHelper.setImagePicasso(mContext, imageView, Constants.Path.MY_PATH + product.getImgs().get(0));
            tvName.setText(product.getName());
            tvPrice.setText(MyHelper.formatMoney(product.getPrice()));
            tvStock.setText(mContext.getString(R.string.stock) + ": " + product.getAmount());
            tvSold.setText(mContext.getString(R.string.sold) + ": ");
            ratingBar.setRating(product.getRate());
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(mContext, btnMore);
                    popup.getMenuInflater()
                            .inflate(R.menu.popup_product_menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.update_product:
                                    mListener.onUpdate(product);
                                    break;
                                case R.id.delete_product:
                                    mListener.onDelete(product);
                                    break;
                                case R.id.add_discount:
                                    mListener.setDiscount(product);
                                    break;
                                default:
                                    break;
                            }
                            return true;
                        }
                    });
                    popup.show();
                }
            });
        }
    }
}
