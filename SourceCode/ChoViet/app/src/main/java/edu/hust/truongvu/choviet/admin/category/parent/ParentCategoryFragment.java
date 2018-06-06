package edu.hust.truongvu.choviet.admin.category.parent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.admin.category.child.ChildCategoryActivity;
import edu.hust.truongvu.choviet.admin.category.child.ManageChildActivity;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParentCategoryFragment extends Fragment implements ManageParentView {
    public static final String CATEGORY_TAG = "category_tag";
    public static final String IS_UPDATE = "is_update";
    public static ParentCategory mParent;
    private RecyclerView recyclerView;
    private ParentCategoryAdapter adapter;
    private View btnAdd;
    private ManageParentPresenter presenter;
    public ParentCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_category, container, false);
        recyclerView = view.findViewById(R.id.list_category);
        btnAdd = view.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManageParentActivity.class);
                intent.putExtra(IS_UPDATE, false);
                startActivity(intent);
            }
        });
        presenter = new ManageParentPresenterImp(getContext(), this);
        presenter.initListParentCategory();


        return view;
    }

    @Override
    public void loadListParentSuccessful(ArrayList<ParentCategory> data) {
        adapter = new ParentCategoryAdapter(getContext(), data, new ParentCategoryAdapter.ParentCategoryListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(getActivity(), ChildCategoryActivity.class);
                intent.putExtra(CATEGORY_TAG, id);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListParentFalse() {

    }

    @Override
    public void addParentSuccessful() {

    }

    @Override
    public void addParentFalse() {

    }

    @Override
    public void uploadNewImageSuccessful() {

    }

    @Override
    public void uploadNewImageFalse() {

    }

    @Override
    public void updateParentSuccessful() {

    }

    @Override
    public void updateParentFalse() {

    }


}
