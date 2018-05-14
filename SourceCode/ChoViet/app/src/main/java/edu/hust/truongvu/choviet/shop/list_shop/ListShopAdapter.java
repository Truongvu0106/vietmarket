package edu.hust.truongvu.choviet.shop.list_shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.ShopModel;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 3/7/2018.
 */

public class ListShopAdapter extends RecyclerView.Adapter<ListShopAdapter.ListShopHolder>{

    public interface ShopListener{
        void onResults(Shop shop);
        void onFollow(Shop shop, int idUser);
        void onUnFollow(Shop shop, int idUser);
    }
    private ShopListener myListener;
    private ArrayList<Shop> data;
    private Context context;
    private ShopModel shopModel;

    public ListShopAdapter(Context context, ArrayList<Shop> data, ShopListener listener){
        this.context = context;
        this.data = data;
        this.myListener = listener;
        shopModel = new ShopModel(context);
    }

    @Override
    public ListShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_large, null);
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
        private View btnFollow, layoutFollowed;
        private boolean isLiked = false;

        public ListShopHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avater_shop);
            imgCover = itemView.findViewById(R.id.img_cover_shop);
            tvName = itemView.findViewById(R.id.tv_name_store);
            tvSlogan = itemView.findViewById(R.id.tv_slogan);
            tvNumProduct = itemView.findViewById(R.id.number_products);
            tvRate = itemView.findViewById(R.id.rate_shop);
            btnFollow = itemView.findViewById(R.id.btn_follow);
            layoutFollowed = itemView.findViewById(R.id.layout_followed);
        }

        public void setContent(final Shop shop){
            final int currentUserId = MyHelper.getUserIdPreference(context);
            MyHelper.setImagePicasso(context, imgAvatar, Constants.Path.MY_PATH + shop.getImgAvatar());
            MyHelper.setImagePicasso(context, imgCover, Constants.Path.MY_PATH + shop.getImgCover());
            tvRate.setText(shop.getRate() + "");
            tvName.setText(shop.getName());
            tvSlogan.setText(shop.getSlogan());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onResults(shop);
                }
            });

            isLiked = shopModel.isFollowing(shop.getId(), currentUserId);
            if (isLiked){
                layoutFollowed.setVisibility(View.VISIBLE);
            }else {
                layoutFollowed.setVisibility(View.GONE);
            }

            btnFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isLiked){
                        layoutFollowed.setVisibility(View.GONE);
                        if (shopModel.unFollow(shop.getId(), currentUserId)){
                            Toast.makeText(context, context.getString(R.string.remove_from_your_following), Toast.LENGTH_SHORT).show();
                        }
                        myListener.onUnFollow(shop, currentUserId);
                    }else {
                        layoutFollowed.setVisibility(View.VISIBLE);
                        if (shopModel.follow(shop.getId(), currentUserId)){
                            Toast.makeText(context, context.getString(R.string.added_to_your_following), Toast.LENGTH_SHORT).show();
                        }
                        myListener.onFollow(shop, currentUserId);
                    }
                }
            });
        }
    }
}
