package edu.hust.truongvu.choviet.product.list_product;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 5/5/2018.
 */

public class ClearFilterDialog extends AlertDialog {

    public interface ClearFilterListener{
        void onClear();
    }
    private Context context;
    private ClearFilterListener myListener;
    private View tvYes, tvCancel;
    public ClearFilterDialog(Context context, ClearFilterListener listener) {
        super(context);
        this.context = context;
        this.myListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_clear_filter);
        tvCancel = findViewById(R.id.btn_cancel);
        tvYes = findViewById(R.id.btn_yes);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.onClear();
                dismiss();
            }
        });
    }
}
