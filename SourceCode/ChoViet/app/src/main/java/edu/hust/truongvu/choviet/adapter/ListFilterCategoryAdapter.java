package edu.hust.truongvu.choviet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.dialog.FilterProductDialog;
import edu.hust.truongvu.choviet.entity.ChildCategory;

/**
 * Created by truon on 5/3/2018.
 */

public class ListFilterCategoryAdapter extends RecyclerView.Adapter<ListFilterCategoryAdapter.FilterCategoryViewHolder>{
    public interface ChooseCategoryListener{
        void onChoose(ChildCategory childCategory);
    }
    private Context context;
    private ArrayList<ChildCategory> data;
    private ChooseCategoryListener mListener;

    public ListFilterCategoryAdapter(Context context, ArrayList<ChildCategory> data, ChooseCategoryListener listener){
        this.context = context;
        this.data = data;
        this.mListener = listener;
    }

    @Override
    public FilterCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_filter, parent, false);
        FilterCategoryViewHolder viewHolder = new FilterCategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FilterCategoryViewHolder holder, int position) {
        ChildCategory childCategory = data.get(position);
        holder.setContent(childCategory);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FilterCategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        public FilterCategoryViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }

        public void setContent(final ChildCategory childCategory){
            tvName.setText(childCategory.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onChoose(childCategory);
                }
            });
        }
    }
}
