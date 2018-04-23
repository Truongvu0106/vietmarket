package edu.hust.truongvu.choviet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 2/24/2018.
 */

public class ChildCategoryAdapter extends RecyclerView.Adapter<ChildCategoryAdapter.ChildCategoryHolder>{

    public interface ChildCategoryListener{
        void onResult(ChildCategory category);
    }
    private Context context;
    private ChildCategoryListener myListener;
    private ArrayList<ChildCategory> data;
    public ChildCategoryAdapter(Context context, ArrayList<ChildCategory> data, ChildCategoryAdapter.ChildCategoryListener listener){
        this.context = context;
        this.data = data;
        this.myListener = listener;
    }

    @Override
    public ChildCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_child, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
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
//            Picasso.with(context)
//                    .load(Constants.Path.MY_PATH + category.getPath_img().trim())
//                    .placeholder(R.drawable.loading)
//                    .error(R.drawable.error)
//                    .resize(150, 150)
//                    .centerCrop()
//                    .into(img);
            MyHelper.setImagePicasso(context, img, Constants.Path.MY_PATH + category.getPath_img().trim());
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
