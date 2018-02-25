package edu.hust.truongvu.choviet.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Store;

/**
 * Created by truon on 2/23/2018.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreHolder>{
    public interface StoreListener{
        void onStoreResult(Store store);
    }
    private StoreListener myListener;
    private ArrayList<Store> data;

    public StoreAdapter(ArrayList<Store> data, StoreListener listener){
        this.data = data;
        this.myListener = listener;
    }

    @Override
    public StoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_store, null);
        StoreHolder holder = new StoreHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StoreHolder holder, int position) {
        Store store = data.get(position);
        holder.setContent(store);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class StoreHolder extends RecyclerView.ViewHolder{
        private ImageView imgCover, imgAvatar;
        private TextView name, slogan;
        public StoreHolder(View itemView) {
            super(itemView);
            imgCover = itemView.findViewById(R.id.img_cover_store);
            imgAvatar = itemView.findViewById(R.id.img_avatar_store);
            name = itemView.findViewById(R.id.name_store);
            slogan = itemView.findViewById(R.id.slogan_store);
        }

        public void setContent(final Store store){
            imgAvatar.setImageResource(store.getImgAvatar());
            imgCover.setImageResource(store.getImgCover());
            name.setText(store.getName());
            slogan.setText(store.getSlogan());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onStoreResult(store);
                }
            });
        }
    }
}
