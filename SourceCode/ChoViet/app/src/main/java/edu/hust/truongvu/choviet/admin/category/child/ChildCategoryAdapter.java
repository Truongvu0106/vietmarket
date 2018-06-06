package edu.hust.truongvu.choviet.admin.category.child;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;

/**
 * Created by truon on 5/30/2018.
 */

public class ChildCategoryAdapter extends RecyclerView.Adapter<ChildCategoryAdapter.ChildCategoryHolder>{
    public interface ChildCategoryListener{
        void onClick(ChildCategory childCategory);
    }

    private ArrayList<ChildCategory> data;
    private Context mContext;
    private ChildCategoryListener mListener;


    public ChildCategoryAdapter(Context context, ArrayList<ChildCategory> data, ChildCategoryListener listener){
        this.mContext = context;
        this.data = data;
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ChildCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_parent_category, parent, false);
        ChildCategoryHolder holder = new ChildCategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChildCategoryHolder holder, int position) {
        ChildCategory childCategory = data.get(position);
        holder.setContent(childCategory);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ChildCategoryHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvName, tvNumber;
        public ChildCategoryHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tv_title);
            tvNumber = itemView.findViewById(R.id.tv_number_child);
        }

        public void setContent(final ChildCategory childCategory){
            MyHelper.setImagePicasso(mContext, img, Constants.Path.MY_PATH + childCategory.getPath_img());
            tvName.setText(childCategory.getName());
            tvNumber.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(childCategory);
                }
            });
        }
    }
}
