package edu.hust.truongvu.choviet.product.list_product;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.product.list_product.ListFilterBrandAdapter;
import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.model.BrandModel;

/**
 * Created by truon on 5/3/2018.
 */

public class BrandDialog extends AlertDialog{
    public interface BrandDialogListener{
        void onClickAll();
        void onChooseBrand(Brand brand);
    }
    private BrandDialogListener mListener;
    private Context mContext;
    private View chooseAll;
    private RecyclerView recyclerView;
    private ListFilterBrandAdapter adapter;
    private BrandModel brandModel;
    private ArrayList<Brand> data = new ArrayList<>();
    public BrandDialog(Context context, BrandDialogListener listener) {
        super(context);
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_price);
        chooseAll = findViewById(R.id.choose_all);
        recyclerView = findViewById(R.id.list_data);
        chooseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.onClickAll();
            }
        });

        brandModel = new BrandModel(mContext);
        data = brandModel.getListBrand();
        adapter = new ListFilterBrandAdapter(mContext, data, new ListFilterBrandAdapter.ChooseBrandListener() {
            @Override
            public void onChoose(Brand brand) {
                dismiss();
                mListener.onChooseBrand(brand);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

    }

}
