package edu.hust.truongvu.choviet.user;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 5/21/2018.
 */

public class ProfileCheckDialog extends AlertDialog{
    public interface ProfileCheckListener{
        void login();
        void continueAsGuess();
    }
    private View btnSignin, btnContinueAsGuess;
    private ProfileCheckListener mListener;
    private Context mContext;
    public ProfileCheckDialog(Context context, ProfileCheckListener listener) {
        super(context);
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_profile_check);
        btnSignin = findViewById(R.id.sigin_in);
        btnContinueAsGuess = findViewById(R.id.continue_as_guess);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.login();
            }
        });

        btnContinueAsGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.continueAsGuess();
            }
        });
    }

}
