package edu.hust.truongvu.choviet.product.list_product;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 5/1/2018.
 */

public class SortProductDialog extends AlertDialog implements View.OnClickListener{

    public interface SortListener{
        void onSortTimeOldToNew();
        void onSortTimeNewToOld();
        void onSortMoneyCheapToExps();
        void onSortMoneyExpsToCheap();
    }
    private SortListener mListener;
    private Context context;
    private View btnLastest, btnOldest, btnCheapToExps, btnExpToCheap;
    public SortProductDialog(Context context, SortListener listener) {
        super(context);
        this.context = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sort_product);
        btnLastest = findViewById(R.id.lastest);
        btnOldest = findViewById(R.id.oldest);
        btnCheapToExps = findViewById(R.id.cheap_to_exps);
        btnExpToCheap = findViewById(R.id.exps_to_cheap);

        btnLastest.setOnClickListener(this);
        btnOldest.setOnClickListener(this);
        btnCheapToExps.setOnClickListener(this);
        btnExpToCheap.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lastest:
                dismiss();
                mListener.onSortTimeNewToOld();
                break;
            case R.id.oldest:
                dismiss();
                mListener.onSortTimeOldToNew();
                break;
            case R.id.cheap_to_exps:
                dismiss();
                mListener.onSortMoneyCheapToExps();
                break;
            case R.id.exps_to_cheap:
                dismiss();
                mListener.onSortMoneyExpsToCheap();
                break;
        }
    }

}
