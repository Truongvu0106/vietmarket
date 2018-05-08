package edu.hust.truongvu.choviet.user.info_user;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 4/17/2018.
 */

public class ChangeFullnameDialog extends AlertDialog{
    public interface ChangeFullnameListener{
        void onChange(String name);
    }
    private Context context;
    private ChangeFullnameListener mListener;
    private EditText edtFullName;
    private View btnCancel, btnUpdate;

    public ChangeFullnameDialog(Context context, ChangeFullnameListener listener) {
        super(context);
        this.context = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_fullname);

        edtFullName = findViewById(R.id.edt_full_name);
        btnCancel = findViewById(R.id.btn_cancel);
        btnUpdate = findViewById(R.id.btn_update);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtFullName.getText().toString().trim();
                if (name.matches("")){
                    Toast.makeText(context, context.getString(R.string.please_enter_all), Toast.LENGTH_SHORT).show();
                    return;
                }
                mListener.onChange(name);
                dismiss();
            }
        });
    }
}
