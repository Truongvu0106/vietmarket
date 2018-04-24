package edu.hust.truongvu.choviet.customview;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 4/22/2018.
 */

public class MyToolbarExtra {
    public interface OnExtraToolbarListener{
        void onMoreClick();
    }
    private OnExtraToolbarListener mListener;
    private View btnBack, btnMore;
    private TextView tvTitle;
    private Activity activity;

    public MyToolbarExtra(final Activity activity, String title){
        this.activity = activity;
        btnBack = activity.findViewById(R.id.btn_back);
        btnMore = activity.findViewById(R.id.btn_more);
        tvTitle = activity.findViewById(R.id.title_toolbar);

        if (title.matches("")){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

        btnMore.setVisibility(View.GONE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });
    }

    public MyToolbarExtra(final Activity activity, String title, OnExtraToolbarListener listener){
        this.activity = activity;
        this.mListener = listener;
        btnBack = activity.findViewById(R.id.btn_back);
        btnMore = activity.findViewById(R.id.btn_more);
        tvTitle = activity.findViewById(R.id.title_toolbar);

        if (title.matches("")){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

        btnMore.setVisibility(View.VISIBLE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMoreClick();
            }
        });
    }
}
