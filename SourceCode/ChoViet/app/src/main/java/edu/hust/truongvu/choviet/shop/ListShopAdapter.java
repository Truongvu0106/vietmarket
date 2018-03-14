package edu.hust.truongvu.choviet.shop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Shop;

/**
 * Created by truon on 3/7/2018.
 */

public class ListShopAdapter extends RecyclerView.Adapter<ListShopAdapter.ListShopHolder>{

    public interface ShopListener{
        void onResults(Shop shop);
        void onFollow(Shop shop);
    }
    private ShopListener myListener;
    private ArrayList<Shop> data;

    public ListShopAdapter(ArrayList<Shop> data, ShopListener listener){
        this.data = data;
        this.myListener = listener;
    }

    @Override
    public ListShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_shop_large, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        ListShopHolder holder = new ListShopHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListShopHolder holder, int position) {
        Shop shop = data.get(position);
        holder.setContent(shop);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ListShopHolder extends RecyclerView.ViewHolder{
        private ImageView imgAvatar, imgCover;
        private TextView tvName, tvSlogan, tvNumProduct, tvRate;
        private View btnFollow;

        public ListShopHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avater_shop);
            imgCover = itemView.findViewById(R.id.img_cover_shop);
            tvName = itemView.findViewById(R.id.tv_name_store);
            tvSlogan = itemView.findViewById(R.id.tv_name_store);
            tvNumProduct = itemView.findViewById(R.id.number_products);
            tvRate = itemView.findViewById(R.id.rate_shop);
            btnFollow = itemView.findViewById(R.id.btn_follow);
        }

        public void setContent(final Shop shop){
            imgAvatar.setImageResource(shop.getImgAvatar());
            imgCover.setImageResource(shop.getImgCover());
            tvRate.setText(shop.getName());
            tvSlogan.setText(shop.getSlogan());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onResults(shop);
                }
            });

            btnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onFollow(shop);
                }
            });
        }
    }
}
