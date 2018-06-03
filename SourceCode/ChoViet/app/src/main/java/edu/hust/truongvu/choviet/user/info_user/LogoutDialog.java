package edu.hust.truongvu.choviet.user.info_user;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 5/22/2018.
 */

public class LogoutDialog extends AlertDialog{
    public interface LogoutListener{
        void onLogout();
    }
    private Context context;
    private LogoutListener myListener;
    private View tvYes, tvCancel;
    public LogoutDialog(Context context, LogoutListener listener) {
        super(context);
        this.context = context;
        this.myListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_logout);
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
                myListener.onLogout();
                dismiss();
            }
        });
    }
}
