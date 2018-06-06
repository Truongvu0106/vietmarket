package edu.hust.truongvu.choviet.helper.customview;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.animation.content.Content;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 6/6/2018.
 */

public class ConfirmDialog extends AlertDialog{
    public interface ConfirmListener{
        void onCancel();
        void onOk();
    }
    private View btnCancel, btnOk;
    private TextView tvContent;
    private Context mContext;
    private ConfirmListener mListener;
    private String content;

    public ConfirmDialog(Context context, String content, ConfirmListener listener) {
        super(context);
        this.mContext = context;
        this.content = content;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        tvContent = findViewById(R.id.content);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.btn_ok);

        tvContent.setText(content);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.onCancel();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.onOk();
            }
        });
    }
}
