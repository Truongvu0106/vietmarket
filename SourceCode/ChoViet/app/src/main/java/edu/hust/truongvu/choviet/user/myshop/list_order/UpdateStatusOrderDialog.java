package edu.hust.truongvu.choviet.user.myshop.list_order;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * Created by truon on 5/24/2018.
 */

public class UpdateStatusOrderDialog extends AlertDialog implements View.OnClickListener{


    public interface UpdateStatusListener{
        void onClick(Constants.OrderStatus status);
    }
    private Context mContext;
    private UpdateStatusListener mListener;
    private View btnWaiting, btnShipping, btnReceived, btnCancel;
    public UpdateStatusOrderDialog(Context context, UpdateStatusListener listener) {
        super(context);
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_status);
        btnWaiting = findViewById(R.id.btn_wait);
        btnShipping = findViewById(R.id.btn_shipping);
        btnReceived = findViewById(R.id.btn_received);
        btnCancel = findViewById(R.id.btn_cancel);

        btnWaiting.setOnClickListener(this);
        btnShipping.setOnClickListener(this);
        btnReceived.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_wait:
                mListener.onClick(Constants.OrderStatus.WAITING);
                dismiss();
                break;
            case R.id.btn_shipping:
                mListener.onClick(Constants.OrderStatus.SHIPPING);
                dismiss();
                break;
            case R.id.btn_received:
                mListener.onClick(Constants.OrderStatus.RECEIVED);
                dismiss();
                break;
            case R.id.btn_cancel:
                mListener.onClick(Constants.OrderStatus.CANCEL);
                dismiss();
                break;
            default:
                break;
        }
    }
}
