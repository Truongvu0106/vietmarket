package edu.hust.truongvu.choviet.category;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.ParentCategory;

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
        categoryPresenterImp = new CategoryPresenterImp(this);
        categoryPresenterImp.initParentCategory();
        return view;
    }

    @Override
    public void loadParentCategory(ArrayList<ParentCategory> parentCategories) {
        parentAdapter = new ParentCategoryAdapter(parentCategories, new ParentCategoryAdapter.ParentCategoryListener() {
            @Override
            public void onResult(ParentCategory category) {
                categoryPresenterImp.initChildCategory(category.getId());
            }
        });
        mListParentCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        mListParentCategory.setAdapter(parentAdapter);
        categoryPresenterImp.initChildCategory(0);
    }

    @Override
    public void loadChildCategory(ArrayList<ChildCategory> childCategories) {
        childAdapter = new ChildCategoryAdapter(childCategories, new ChildCategoryAdapter.ChildCategoryListener() {
            @Override
            public void onResult(ChildCategory category) {
                Toast.makeText(getContext(), category.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mListChildCategory.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        mListChildCategory.setAdapter(childAdapter);
    }
}
