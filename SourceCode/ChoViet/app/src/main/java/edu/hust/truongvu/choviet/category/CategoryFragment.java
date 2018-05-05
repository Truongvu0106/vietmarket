package edu.hust.truongvu.choviet.category;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.adapter.ChildCategoryAdapter;
import edu.hust.truongvu.choviet.adapter.ParentCategoryAdapter;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.ParentCategory;
import edu.hust.truongvu.choviet.product.ListProductActivity;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryView{

    private RecyclerView mListParentCategory, mListChildCategory;
    private ParentCategoryAdapter parentAdapter;
    private ChildCategoryAdapter childAdapter;
    private CategoryPresenterImp categoryPresenterImp;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(){
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mListParentCategory = view.findViewById(R.id.list_parent_category);
        mListChildCategory = view.findViewById(R.id.list_child_category);
        categoryPresenterImp = new CategoryPresenterImp(getContext(), this);
        categoryPresenterImp.initChildCategory();
        categoryPresenterImp.initChildCategoryById(1);
        categoryPresenterImp.initParentCategory();
        return view;
    }

    @Override
    public void loadParentCategorySuccessful(ArrayList<ParentCategory> parentCategories) {
        parentAdapter = new ParentCategoryAdapter(getContext(), parentCategories, new ParentCategoryAdapter.ParentCategoryListener() {
            @Override
            public void onResult(ParentCategory category) {
                categoryPresenterImp.initChildCategoryById(category.getId());
            }
        });
        mListParentCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        mListParentCategory.setAdapter(parentAdapter);
    }

    @Override
    public void loadParentCategoryFalse() {

    }


    @Override
    public void loadChildCategorySuccessful(ArrayList<ChildCategory> childCategories) {
        childAdapter = new ChildCategoryAdapter(getContext(), childCategories, new ChildCategoryAdapter.ChildCategoryListener() {
            @Override
            public void onResult(ChildCategory category) {
                Intent intent = new Intent(getActivity(), ListProductActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_TYPE_LOAD_PRODUCT, Constants.MyTag.LOAD_PRODUCT_BY_CATEGORY);
                intent.putExtra(Constants.MyTag.ID_CATEGORY, category.getId());
                startActivity(intent);
            }
        });
        mListChildCategory.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        mListChildCategory.setAdapter(childAdapter);
    }

    @Override
    public void loadChildCategoryFalse() {

    }

}
