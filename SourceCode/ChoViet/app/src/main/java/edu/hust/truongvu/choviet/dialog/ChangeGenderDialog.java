package edu.hust.truongvu.choviet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 4/17/2018.
 */

public class ChangeGenderDialog extends AlertDialog{
    public interface ChangeGengerListener{
        void onChange(int gender);
    }
    private ChangeGengerListener mListener;
    private Context context;
    private View btnMale, btnFemale;

    public ChangeGenderDialog(Context context, ChangeGengerListener listener) {
        super(context);
        this.context = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_gender);
        btnMale = findViewById(R.id.btn_male);
        btnFemale = findViewById(R.id.btn_female);

        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onChange(Constants.User.MALE);
                dismiss();
            }
        });

        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onChange(Constants.User.FEMALE);
                dismiss();
            }
        });
    }

}
