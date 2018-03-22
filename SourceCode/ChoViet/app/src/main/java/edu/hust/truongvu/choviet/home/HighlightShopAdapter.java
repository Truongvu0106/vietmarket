package edu.hust.truongvu.choviet.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 2/23/2018.
 */

public class HighlightShopAdapter extends RecyclerView.Adapter<HighlightShopAdapter.HighlightStoreHolder>{
    public interface StoreListener{
        void onStoreResult(Shop shop);
    }
    private StoreListener myListener;
    private ArrayList<Shop> data;
    private Context context;

    public HighlightShopAdapter(Context context, ArrayList<Shop> data, StoreListener listener){
        this.context = context;
        this.data = data;
        this.myListener = listener;
    }

    @Override
    public HighlightStoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_shop, null);
        HighlightStoreHolder holder = new HighlightStoreHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HighlightStoreHolder holder, int position) {
        Shop shop = data.get(position);
        holder.setContent(shop);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class HighlightStoreHolder extends RecyclerView.ViewHolder{
        private ImageView imgCover, imgAvatar;
        private TextView name, slogan;
        public HighlightStoreHolder(View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.img_cover_store);
            imgAvatar = itemView.findViewById(R.id.img_avatar_store);
            name = itemView.findViewById(R.id.name_store);
            slogan = itemView.findViewById(R.id.slogan_store);
        }

        public void setContent(final Shop shop){
            MyHelper.setImagePicasso(context, imgAvatar, Constants.Path.MY_PATH + shop.getImgAvatar());
            MyHelper.setImagePicasso(context, imgCover, Constants.Path.MY_PATH + shop.getImgCover());
            name.setText(shop.getName());
            slogan.setText(shop.getSlogan());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onStoreResult(shop);
                }
            });
        }
    }
}
