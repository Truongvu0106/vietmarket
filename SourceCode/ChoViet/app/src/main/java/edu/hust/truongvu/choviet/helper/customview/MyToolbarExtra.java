package edu.hust.truongvu.choviet.helper.customview;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 4/22/2018.
 */

public class MyToolbarExtra {
    public interface OnExtraToolbarListener{
        void onMoreClick();
        void onBackClick();
    }
    private OnExtraToolbarListener mListener;
    private View btnBack, btnMore;
    private ImageView imgMore;
    private TextView tvTitle;
    private Activity activity;

    public MyToolbarExtra(final Activity activity, String title, int moreRes, OnExtraToolbarListener listener){
        this.activity = activity;
        this.mListener = listener;
        btnBack = activity.findViewById(R.id.btn_back);
        btnMore = activity.findViewById(R.id.btn_more);
        tvTitle = activity.findViewById(R.id.title_toolbar);
        imgMore = activity.findViewById(R.id.img_more);

        if (title.matches("")){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

        if (moreRes == 0){
            btnMore.setVisibility(View.GONE);
        }else {
            btnMore.setVisibility(View.VISIBLE);
            imgMore.setImageResource(moreRes);
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onMoreClick();
                }
            });
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBackClick();
            }
        });


    }
}
