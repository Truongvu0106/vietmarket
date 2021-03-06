package edu.hust.truongvu.choviet.admin.category.parent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;

/**
 * Created by truon on 5/29/2018.
 */

public class ParentCategoryAdapter extends RecyclerView.Adapter<ParentCategoryAdapter.ParentCategoryHolder>{
    public interface ParentCategoryListener{
        void onClick(int id);
        void onUpdate(ParentCategory parentCategory);
    }

    private ArrayList<ParentCategory> data;
    private Context mContext;
    private ParentCategoryListener mListener;


    public ParentCategoryAdapter(Context context, ArrayList<ParentCategory> data, ParentCategoryListener listener){
        this.mContext = context;
        this.data = data;
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ParentCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_category, parent, false);
        ParentCategoryHolder holder = new ParentCategoryHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParentCategoryHolder holder, int position) {
        ParentCategory parentCategory = data.get(position);
        holder.setContent(parentCategory);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ParentCategoryHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvName, tvNumber;
        private CategoryModel categoryModel;
        private View btnMore;
        public ParentCategoryHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.tv_title);
            tvNumber = itemView.findViewById(R.id.tv_number_child);
            btnMore = itemView.findViewById(R.id.btn_more);
            categoryModel = new CategoryModel(mContext);
        }

        public void setContent(final ParentCategory parentCategory){
            MyHelper.setImagePicasso(mContext, img, Constants.MY_PATH + parentCategory.getPath_img());
            tvName.setText(parentCategory.getName());
            final ArrayList<ChildCategory> childCategories = categoryModel.getListChildCategoryByParent(parentCategory.getId());
            tvNumber.setText(mContext.getString(R.string.number_of_child) + ": " + childCategories.size());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(parentCategory.getId());
                }
            });

            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(mContext, btnMore);
                    popup.getMenuInflater()
                            .inflate(R.menu.popup_category_menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.update_category:
                                    mListener.onUpdate(parentCategory);
                                    break;
                                default:
                                    break;
                            }
                            return true;
                        }
                    });
                    popup.show();
                }
            });
        }
    }
}
