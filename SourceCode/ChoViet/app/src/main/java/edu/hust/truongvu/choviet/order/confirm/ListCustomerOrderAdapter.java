package edu.hust.truongvu.choviet.order.confirm;

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
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.ShopModel;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 4/24/2018.
 */

public class ListCustomerOrderAdapter extends RecyclerView.Adapter<ListCustomerOrderAdapter.CustomerOrderViewHolder>{
    private ArrayList<Product> listProduct;
    private Context context;

    public ListCustomerOrderAdapter(Context context, ArrayList<Product> data){
        this.context = context;
        this.listProduct = data;
    }


    @Override
    public CustomerOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_customer, parent, false);
        CustomerOrderViewHolder holder = new CustomerOrderViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomerOrderViewHolder holder, int position) {
        Product product = listProduct.get(position);
        holder.setContent(product);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    class CustomerOrderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgShop, imgPrpduct;
        private TextView tvNameShop, tvNameProduct, tvPrice, tvNumber;
        private ShopModel shopModel;
        public CustomerOrderViewHolder(View itemView) {
            super(itemView);
            imgShop = itemView.findViewById(R.id.img_shop);
            imgPrpduct = itemView.findViewById(R.id.img_product);
            tvNameShop = itemView.findViewById(R.id.name_shop);
            tvNameProduct = itemView.findViewById(R.id.name_product);
            tvPrice = itemView.findViewById(R.id.price_product);
            tvNumber = itemView.findViewById(R.id.tv_number);
            shopModel = new ShopModel(context);
        }

        public void setContent(Product product){
            Shop shop = shopModel.getShopById(product.getIdShop());
            if (shop != null){
                MyHelper.setImagePicasso(context, imgShop, Constants.Path.MY_PATH + shop.getImgAvatar());
                tvNameShop.setText(shop.getName());
            }
            tvNameProduct.setText(product.getName());
            MyHelper.setImagePicasso(context, imgPrpduct, Constants.Path.MY_PATH + product.getImgs().get(0));
            long price = product.getPrice() - (product.getPrice() * product.getDiscount())/100;
            tvPrice.setText(MyHelper.formatMoney(price));
            tvNumber.setText(context.getString(R.string.number) + ": " + product.getNumberSelect());
        }
    }
}
