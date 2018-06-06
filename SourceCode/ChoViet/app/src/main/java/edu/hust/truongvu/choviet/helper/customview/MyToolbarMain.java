package edu.hust.truongvu.choviet.helper.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartActivity;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.search.SearchActivity;

/**
 * Created by truon on 4/22/2018.
 */

public class MyToolbarMain {
    public interface MainToolbarListener{
        void onBackClick();
    }
    private MainToolbarListener mListener;
    private View btnBack, btnCart, layoutSearch, rootViewNumber;
    private TextView tvNumber;
    public MyToolbarMain(final Activity activity, final Context context, int numberItemCart, MainToolbarListener listener){
        this.mListener = listener;
        btnBack = activity.findViewById(R.id.btn_back);
        btnCart = activity.findViewById(R.id.img_cart);
        layoutSearch = activity.findViewById(R.id.layout_search);
        rootViewNumber = activity.findViewById(R.id.layout_number_item_cart);
        tvNumber = activity.findViewById(R.id.tv_number_item_cart);
        MyHelper.setViewCart(rootViewNumber, tvNumber, numberItemCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBackClick();
            }
        });

        layoutSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(context, SearchActivity.class));
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(context, CartActivity.class));
            }
        });

    }
}
