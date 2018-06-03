package edu.hust.truongvu.choviet.admin.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;

public class ChildCategoryActivity extends AppCompatActivity implements ManageCategoryView{
    private RecyclerView recyclerView;
    private View btnAdd;
    private ChildCategoryAdapter adapter;
    private ManageCategoryPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_category);
        presenter = new ManageCategoryPresenterImp(this, this);
        recyclerView = findViewById(R.id.list_category);
        btnAdd = findViewById(R.id.btn_add);
        new MyToolbarExtra(this, "", 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        int parent = getIntent().getIntExtra(ParentCategoryFragment.CATEGORY_TAG, 0);
        presenter.initListChildCategory(parent);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void loadListParentSuccessful(ArrayList<ParentCategory> data) {

    }

    @Override
    public void loadListParentFalse() {

    }

    @Override
    public void loadListChildSuccessful(ArrayList<ChildCategory> data) {
        adapter = new ChildCategoryAdapter(this, data, new ChildCategoryAdapter.ChildCategoryListener() {
            @Override
            public void onClick(ChildCategory childCategory) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListChildFalse() {

    }

    @Override
    public void addParentSuccessful() {

    }

    @Override
    public void addParentFalse() {

    }

    @Override
    public void updateParentSuccessful() {

    }

    @Override
    public void updateParentFalse() {

    }

    @Override
    public void addChildSuccessful() {

    }

    @Override
    public void addChildFalse() {

    }

    @Override
    public void updateChildSuccessful() {

    }

    @Override
    public void updateChildFalse() {

    }
}
