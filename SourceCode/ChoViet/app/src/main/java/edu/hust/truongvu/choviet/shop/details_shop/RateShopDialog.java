package edu.hust.truongvu.choviet.shop.details_shop;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 6/2/2018.
 */

public class RateShopDialog extends AlertDialog{
    public interface RateShopListener{
        void onRate(float rate);
    }
    private RateShopListener mListener;
    private Context mContext;
    private View btnCancel, btnRate;
    private RatingBar ratingBar;
    private float mRate = 0;
    public RateShopDialog(Context context, float rate, RateShopListener listener) {
        super(context);
        this.mContext = context;
        this.mRate = rate;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rate_shop);
        btnCancel = findViewById(R.id.btn_cancel);
        btnRate = findViewById(R.id.btn_rate);
        ratingBar = findViewById(R.id.rating_bar);

        ratingBar.setRating(mRate);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRate = v;
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRate(mRate);
                dismiss();
            }
        });
    }
}
