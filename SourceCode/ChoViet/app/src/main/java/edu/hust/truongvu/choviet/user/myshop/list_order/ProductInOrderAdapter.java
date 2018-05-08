package edu.hust.truongvu.choviet.user.myshop.list_order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 4/24/2018.
 */

public class ProductInOrderAdapter extends RecyclerView.Adapter<ProductInOrderAdapter.ProductInOrderViewHolder>{

    private ArrayList<Product> listProduct;
    private ArrayList<Integer> listNumber;
    private Context context;

    public ProductInOrderAdapter(Context context, ArrayList<Product> listProduct, ArrayList<Integer> listNumber){
        this.context = context;
        this.listProduct = listProduct;
        this.listNumber = listNumber;
    }

    @Override
    public ProductInOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_in_order, parent, false);
        ProductInOrderViewHolder holder = new ProductInOrderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductInOrderViewHolder holder, int position) {
        Product product = listProduct.get(position);
        int number = listNumber.get(position);
        holder.setContent(product, number);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    class ProductInOrderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView tvName, tvPrice, tvNumber;
        public ProductInOrderViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvName = itemView.findViewById(R.id.name_product);
            tvPrice = itemView.findViewById(R.id.price_product);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }

        public void setContent(Product product, int number){
            MyHelper.setImagePicasso(context, imgProduct, Constants.Path.MY_PATH + product.getImgs().get(0));
            tvName.setText(product.getName());
            tvPrice.setText(MyHelper.formatMoney(product.getPrice()));
            tvNumber.setText(context.getString(R.string.number) + ": " + number);
        }
    }
}
