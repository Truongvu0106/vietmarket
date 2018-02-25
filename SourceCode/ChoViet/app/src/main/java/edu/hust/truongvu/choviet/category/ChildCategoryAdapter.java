package edu.hust.truongvu.choviet.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.ParentCategory;

/**
 * Created by truon on 2/24/2018.
 */

public class ChildCategoryAdapter extends RecyclerView.Adapter<ChildCategoryAdapter.ChildCategoryHolder>{

    public interface ChildCategoryListener{
        void onResult(ChildCategory category);
    }
    private ChildCategoryListener myListener;
    private ArrayList<ChildCategory> data;
    public ChildCategoryAdapter(ArrayList<ChildCategory> data, ChildCategoryAdapter.ChildCategoryListener listener){
        this.data = data;
        this.myListener = listener;
    }

    @Override
    public ChildCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_category_child, null);
        ChildCategoryHolder holder = new ChildCategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChildCategoryHolder holder, int position) {
        ChildCategory category = data.get(position);
        holder.setContent(category);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ChildCategoryHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name;
        public ChildCategoryHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_child_category);
            name = itemView.findViewById(R.id.name_child_category);
        }
        public void setContent(final ChildCategory category){
            img.setImageResource(category.getImg());
            name.setText(category.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onResult(category);
                }
            });
        }
    }
}
