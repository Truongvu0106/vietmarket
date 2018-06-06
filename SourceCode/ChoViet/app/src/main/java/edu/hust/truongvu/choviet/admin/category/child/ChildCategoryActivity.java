package edu.hust.truongvu.choviet.admin.category.child;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.admin.banner.manage_banner.ManageBannerActivity;
import edu.hust.truongvu.choviet.admin.category.parent.ParentCategoryFragment;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;

public class ChildCategoryActivity extends AppCompatActivity implements ManageChildView {
    public static final String IS_UPDATE = "is_update";
    private RecyclerView recyclerView;
    private View btnAdd;
    private ChildCategoryAdapter adapter;
    private ManageChildPresenter presenter;
    public static ChildCategory mChild;
    public static int PARENT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_category);
        presenter = new ManageChildPresenterImp(this, this);
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
        PARENT = getIntent().getIntExtra(ParentCategoryFragment.CATEGORY_TAG, 0);
        presenter.initListChildCategory(PARENT);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChildCategoryActivity.this, ManageChildActivity.class);
                intent.putExtra(IS_UPDATE, false);
                startActivity(intent);
            }
        });

    }


    @Override
    public void loadListChildSuccessful(ArrayList<ChildCategory> data) {
        adapter = new ChildCategoryAdapter(this, data, new ChildCategoryAdapter.ChildCategoryListener() {
            @Override
            public void onClick(ChildCategory childCategory) {
                mChild = childCategory;
                Intent intent = new Intent(ChildCategoryActivity.this, ManageChildActivity.class);
                intent.putExtra(IS_UPDATE, true);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListChildFalse() {

    }

    @Override
    public void addChildSuccessful() {

    }

    @Override
    public void addChildFalse() {

    }

    @Override
    public void uploadNewImageSuccessful() {

    }

    @Override
    public void uploadNewImageFalse() {

    }

    @Override
    public void updateChildSuccessful() {

    }

    @Override
    public void updateChildFalse() {

    }

}
