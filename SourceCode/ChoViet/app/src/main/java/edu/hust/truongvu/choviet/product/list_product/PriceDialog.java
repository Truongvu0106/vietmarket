package edu.hust.truongvu.choviet.product.list_product;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.product.list_product.PriceAdapter;
import edu.hust.truongvu.choviet.entity.PriceFilter;

/**
 * Created by truon on 5/3/2018.
 */

public class PriceDialog extends AlertDialog {
    public interface PriceDialogListener{
        void onClickAll();
        void onChoosePrice(PriceFilter priceFilter);
    }
    private PriceDialogListener mListener;
    private Context mContext;
    private View chooseAll;
    private RecyclerView recyclerView;
    private PriceAdapter adapter;
    private ArrayList<PriceFilter> data;
    public PriceDialog(Context context, PriceDialogListener listener) {
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
        data = new ArrayList<>();
        data.add(new PriceFilter(0, 2000000));
        data.add(new PriceFilter(2000000, 4000000));
        data.add(new PriceFilter(4000000, 6000000));
        data.add(new PriceFilter(6000000, 0));

        adapter = new PriceAdapter(mContext, data, new PriceAdapter.ChoosePriceListener() {
            @Override
            public void onChoose(PriceFilter priceFilter) {
                dismiss();
                mListener.onChoosePrice(priceFilter);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);

        chooseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.onClickAll();
            }
        });
    }
}
