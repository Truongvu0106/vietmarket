package edu.hust.truongvu.choviet.product.list_product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Brand;

/**
 * Created by truon on 5/3/2018.
 */

public class ListFilterBrandAdapter extends RecyclerView.Adapter<ListFilterBrandAdapter.FilterBrandViewHolder>{
    public interface ChooseBrandListener{
        void onChoose(Brand brand);
    }
    private Context context;
    private ArrayList<Brand> data;
    private ChooseBrandListener mListener;

    public ListFilterBrandAdapter(Context context, ArrayList<Brand> data, ChooseBrandListener listener){
        this.context = context;
        this.data = data;
        this.mListener = listener;
    }

    @Override
    public FilterBrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_filter, parent, false);
        FilterBrandViewHolder viewHolder = new FilterBrandViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FilterBrandViewHolder holder, int position) {
        Brand brand = data.get(position);
        holder.setContent(brand);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FilterBrandViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        public FilterBrandViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }

        public void setContent(final Brand brand){
            tvName.setText(brand.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onChoose(brand);
                }
            });
        }
    }
}
