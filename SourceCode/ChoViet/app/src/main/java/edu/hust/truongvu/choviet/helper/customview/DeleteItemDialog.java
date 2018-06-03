package edu.hust.truongvu.choviet.helper.customview;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 3/25/2018.
 */

public class DeleteItemDialog extends AlertDialog{
    public interface DeleteItemCartListener{
        void onDelete(int id);
    }
    private int id;
    private Context context;
    private DeleteItemCartListener myListener;
    private View tvYes, tvCancel;
    public DeleteItemDialog(Context context, int id, DeleteItemCartListener listener) {
        super(context);
        this.context = context;
        this.id = id;
        this.myListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete_item_cart);
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
                myListener.onDelete(id);
                dismiss();
            }
        });
    }
}
