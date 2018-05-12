package edu.hust.truongvu.choviet.product.list_product;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.PriceFilter;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 5/1/2018.
 */

public class FilterProductDialog extends AlertDialog implements View.OnClickListener{
    public interface FilterListener{
        void onApplyFilter(int category, int brand, PriceFilter priceFilter);
    }
    private View btnCategory, btnBrand, btnPrice, btnCancel, btnApply;
    private TextView tvCategory, tvBrand, tvPrice;
    private FilterListener mLitener;
    private int idCategory = 0, idBrand = 0;
    private PriceFilter mPriceFilter = null;
    private Context mContext;
    private int mType;
    public FilterProductDialog(Context context, int type, FilterListener listener) {
        super(context);
        this.mContext = context;
        this.mLitener = listener;
        this.mType = type;
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

        if (mType == Constants.MyTag.LOAD_PRODUCT_BY_CATEGORY){
            btnCategory.setVisibility(View.GONE);
            btnBrand.setVisibility(View.VISIBLE);
        }else if (mType == Constants.MyTag.LOAD_PRODUCT_BY_BRAND){
            btnBrand.setVisibility(View.GONE);
            btnCategory.setVisibility(View.VISIBLE);
        }else {
            btnBrand.setVisibility(View.VISIBLE);
            btnCategory.setVisibility(View.VISIBLE);
        }

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
                dismiss();
                mLitener.onApplyFilter(idCategory, idBrand, mPriceFilter);
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
                idCategory = 0;
            }

            @Override
            public void onChooseCategory(ChildCategory childCategory) {
                tvCategory.setText(childCategory.getName());
                idCategory = childCategory.getId();
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
                idBrand = 0;
            }

            @Override
            public void onChooseBrand(Brand brand) {
                tvBrand.setText(brand.getName());
                idBrand = brand.getId();
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
                mPriceFilter = null;
            }

            @Override
            public void onChoosePrice(PriceFilter priceFilter) {
                tvPrice.setText(priceFilter.getTxtDisPlay());
                mPriceFilter = priceFilter;
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


}
