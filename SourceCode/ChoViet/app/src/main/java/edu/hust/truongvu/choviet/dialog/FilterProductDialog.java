package edu.hust.truongvu.choviet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.logging.Filter;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 5/1/2018.
 */

public class FilterProductDialog extends AlertDialog implements View.OnClickListener{
    public interface FilterListener{
        void onApplyFilter(int category, int brand, int price);
    }
    private View btnCategory, btnBrand, btnPrice, btnCancel, btnApply;
    private FilterListener mLitener;
    private Context mContext;
    public FilterProductDialog(Context context, FilterListener listener) {
        super(context);
        this.mContext = context;
        this.mLitener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_filter_product);
        btnCategory = findViewById(R.id.btn_category);
        btnBrand = findViewById(R.id.btn_brand);
        btnPrice = findViewById(R.id.btn_price);
        btnCancel = findViewById(R.id.btn_cancel);
        btnApply = findViewById(R.id.btn_apply);

        btnCategory.setOnClickListener(this);
        btnBrand.setOnClickListener(this);
        btnPrice.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnApply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_category:
                break;
            case R.id.btn_brand:
                break;
            case R.id.btn_price:
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_apply:
                break;
            default:
                break;
        }
    }
}
