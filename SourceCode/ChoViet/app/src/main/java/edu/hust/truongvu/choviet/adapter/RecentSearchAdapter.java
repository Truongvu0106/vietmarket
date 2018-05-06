package edu.hust.truongvu.choviet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 5/6/2018.
 */

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>{
    public interface RecentSearchListener{
        void onResults(String search);
    }

    private RecentSearchListener mListener;
    private ArrayList<String> data;
    private Context mContext;
    public RecentSearchAdapter(Context context, ArrayList<String> data, RecentSearchListener listener){
        this.mContext = context;
        this.data = data;
        this.mListener = listener;
    }

    @Override
    public RecentSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_search, parent, false);
        RecentSearchViewHolder viewHolder = new RecentSearchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecentSearchViewHolder holder, int position) {
        String search = data.get(position);
        holder.setContent(search);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecentSearchViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSeach;
        public RecentSearchViewHolder(View itemView) {
            super(itemView);
            tvSeach = itemView.findViewById(R.id.tv_search);
        }

        public void setContent(final String search){
            tvSeach.setText(search);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onResults(search);
                }
            });
        }
    }
}
