package edu.hust.truongvu.choviet.product.list_product;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.product.list_product.ListFilterCategoryAdapter;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.CategoryModel;

/**
 * Created by truon on 5/3/2018.
 */

public class CategoryDialog extends AlertDialog{
    public interface CategoryDialogListener{
        void onClickAll();
        void onChooseCategory(ChildCategory childCategory);
    }
    private CategoryDialogListener mListener;
    private Context mContext;
    private View chooseAll;
    private RecyclerView recyclerView;
    private ListFilterCategoryAdapter adapter;
    private CategoryModel categoryModel;
    private ArrayList<ChildCategory> data = new ArrayList<>();
    public CategoryDialog(Context context, CategoryDialogListener listener) {
        super(context);
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_category);
        chooseAll = findViewById(R.id.choose_all);
        recyclerView = findViewById(R.id.list_data);
        chooseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.onClickAll();
            }
        });

        categoryModel = new CategoryModel(mContext);
        data = categoryModel.getListChildCategory();
        adapter = new ListFilterCategoryAdapter(mContext, data, new ListFilterCategoryAdapter.ChooseCategoryListener() {
            @Override
            public void onChoose(ChildCategory childCategory) {
                dismiss();
                mListener.onChooseCategory(childCategory);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

    }
}
