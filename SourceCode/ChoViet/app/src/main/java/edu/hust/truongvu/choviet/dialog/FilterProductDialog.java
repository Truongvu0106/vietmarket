package edu.hust.truongvu.choviet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.logging.Filter;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.PriceFilter;

/**
 * Created by truon on 5/1/2018.
 */

public class FilterProductDialog extends AlertDialog implements View.OnClickListener{
    public interface FilterListener{
        void onApplyFilter(int category, int brand, int price);
    }
    private View btnCategory, btnBrand, btnPrice, btnCancel, btnApply;
    private TextView tvCategory, tvBrand, tvPrice;
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
        tvCategory = findViewById(R.id.tv_category);
        tvBrand = findViewById(R.id.tv_brand);
        tvPrice = findViewById(R.id.tv_price);

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
                chooseCategory();
                break;
            case R.id.btn_brand:
                chooseBrand();
                break;
            case R.id.btn_price:
                choosePrice();
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

    private void chooseCategory(){
        CategoryDialog dialog = new CategoryDialog(mContext, new CategoryDialog.CategoryDialogListener() {
            @Override
            public void onClickAll() {
                tvCategory.setText(mContext.getString(R.string.all));
            }

            @Override
            public void onChooseCategory(ChildCategory childCategory) {
                tvCategory.setText(childCategory.getName());
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void chooseBrand(){
        BrandDialog dialog = new BrandDialog(mContext, new BrandDialog.BrandDialogListener() {
            @Override
            public void onClickAll() {
                tvBrand.setText(mContext.getString(R.string.all));
            }

            @Override
            public void onChooseBrand(Brand brand) {
                tvBrand.setText(brand.getName());
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void choosePrice(){
        PriceDialog dialog = new PriceDialog(mContext, new PriceDialog.PriceDialogListener() {
            @Override
            public void onClickAll() {
                tvPrice.setText(mContext.getString(R.string.all));
            }

            @Override
            public void onChoosePrice(PriceFilter priceFilter) {
                tvPrice.setText(priceFilter.getTxtDisPlay());
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


}
