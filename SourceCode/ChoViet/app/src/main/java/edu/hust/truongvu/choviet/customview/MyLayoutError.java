package edu.hust.truongvu.choviet.customview;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 4/30/2018.
 */

public class MyLayoutError {
    public interface ErrorListener{
        void onTryAgain();
    }
    private Activity activity;
    private ErrorListener mListener;
    private TextView tvErr;
    private ImageView img;
    private View btnTryAgain;
    public MyLayoutError(Activity activity, boolean isTryAgain, String tile, int image, ErrorListener listener){
        this.activity = activity;
        this.mListener = listener;
        tvErr = activity.findViewById(R.id.tv_err);
        img = activity.findViewById(R.id.img_err);
        btnTryAgain = activity.findViewById(R.id.btn_try_again);

        if (tvErr != null) {
            tvErr.setText(tile);
        }

        if (img != null){
            img.setImageResource(image);
        }

        if (isTryAgain){
            if (btnTryAgain != null){
                btnTryAgain.setVisibility(View.VISIBLE);
                btnTryAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onTryAgain();
                    }
                });
            }
        }else {
            if (btnTryAgain != null){
                btnTryAgain.setVisibility(View.GONE);
            }
        }

    }
}
