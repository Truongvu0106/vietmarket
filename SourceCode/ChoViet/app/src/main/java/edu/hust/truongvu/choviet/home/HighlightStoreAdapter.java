package edu.hust.truongvu.choviet.home;

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
 * Created by truon on 2/23/2018.
 */

public class HighlightStoreAdapter extends RecyclerView.Adapter<HighlightStoreAdapter.HighlightStoreHolder>{
    public interface StoreListener{
        void onStoreResult(Shop shop);
    }
    private StoreListener myListener;
    private ArrayList<Shop> data;

    public HighlightStoreAdapter(ArrayList<Shop> data, StoreListener listener){
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
            imgAvatar.setImageResource(shop.getImgAvatar());
            imgCover.setImageResource(shop.getImgCover());
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
