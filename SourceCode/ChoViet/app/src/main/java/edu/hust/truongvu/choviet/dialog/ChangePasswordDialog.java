package edu.hust.truongvu.choviet.dialog;

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

public class ChangePasswordDialog extends AlertDialog{
    public interface ChangePasswordListener{
        void onChange(String newPass);
    }
    private Context context;
    private ChangePasswordListener mListener;
    public ChangePasswordDialog(Context context, ChangePasswordListener listener) {
        super(context);
        this.context = context;
        this.mListener = listener;
    }
    private EditText edtPass, edtRepass;
    private View btnCancel, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_pass);
        edtPass = findViewById(R.id.edt_pass);
        edtRepass = findViewById(R.id.edt_repass);

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
                String pass = edtPass.getText().toString().trim();
                String repass = edtRepass.getText().toString().trim();

                if (pass.matches("") || repass.matches("")){
                    Toast.makeText(context, context.getString(R.string.please_enter_all), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.matches(repass)){
                    Toast.makeText(context, context.getString(R.string.pass_not_match), Toast.LENGTH_SHORT).show();
                    return;
                }

                mListener.onChange(pass);
                dismiss();
            }
        });
    }
}
