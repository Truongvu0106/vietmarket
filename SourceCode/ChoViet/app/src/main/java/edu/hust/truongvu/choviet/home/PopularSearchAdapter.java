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
import edu.hust.truongvu.choviet.model.entity.PopularSearch;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 2/22/2018.
 */

public class PopularSearchAdapter extends RecyclerView.Adapter<PopularSearchAdapter.PopularSearchHolder>{
    private ArrayList<PopularSearch> data;
    private Context mContext;

    public PopularSearchAdapter(Context context, ArrayList<PopularSearch> data){
        this.data = data;
        this.mContext = context;
    }

    @Override
    public PopularSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_search, null);
        PopularSearchHolder holder = new PopularSearchHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PopularSearchHolder holder, int position) {
        PopularSearch popularSearch = data.get(position);
        holder.setContent(popularSearch);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class PopularSearchHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public PopularSearchHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_popular_search);
            textView = itemView.findViewById(R.id.text_popular_search);
        }

        public void setContent(PopularSearch data){
            textView.setText(data.getKeySearch());
            MyHelper.setImagePicasso(mContext, imageView, Constants.MY_PATH + data.getImg());
        }
    }
}
