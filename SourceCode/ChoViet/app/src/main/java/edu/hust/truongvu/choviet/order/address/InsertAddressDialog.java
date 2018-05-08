package edu.hust.truongvu.choviet.order.address;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 3/25/2018.
 */

public class InsertAddressDialog extends AlertDialog{
    public interface InsertAddressListener{
        void onInsert(String address);
    }
    private InsertAddressListener myListener;
    private Context context;
    private EditText edtAddress;
    private View btnCancel, btnOk;
    public InsertAddressDialog(Context context, InsertAddressListener listener) {
        super(context);
        this.myListener = listener;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_insert_address);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.btn_ok);
        edtAddress = findViewById(R.id.edt_address);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = edtAddress.getText().toString().trim();
                if (address.matches("")){
                    Toast.makeText(context, context.getString(R.string.please_enter_address), Toast.LENGTH_SHORT).show();
                }else {
                    myListener.onInsert(address);
                    dismiss();
                }
            }
        });
    }


}
