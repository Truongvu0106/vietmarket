package edu.hust.truongvu.choviet.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 2/24/2018.
 */

public class ParentCategoryAdapter extends RecyclerView.Adapter<ParentCategoryAdapter.ParentCategoryHolder>{
    public interface ParentCategoryListener{
        void onResult(ParentCategory category);
    }
    private Context context;
    private ParentCategoryListener myListener;
    private ArrayList<ParentCategory> data;
    public ParentCategoryAdapter(Context context, ArrayList<ParentCategory> data, ParentCategoryListener listener){
        this.context = context;
        this.data = data;
        this.myListener = listener;
    }
    @Override
    public ParentCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_parent, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        ParentCategoryHolder holder = new ParentCategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ParentCategoryHolder holder, int position) {
        ParentCategory category = data.get(position);
        holder.setContent(category);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ParentCategoryHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name;
        public ParentCategoryHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_parent_category);
            name = itemView.findViewById(R.id.name_parent_category);
        }
        public void setContent(final ParentCategory category){
//            Picasso.with(context)
//                    .load(Constants.Path.MY_PATH + category.getPath_img().trim())
//                    .placeholder(R.drawable.loading)
//                    .error(R.drawable.error)
//                    .resize(150, 150)
//                    .centerCrop()
//                    .into(img);
            MyHelper.setImagePicasso(context, img, Constants.MY_PATH + category.getPath_img().trim());
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
