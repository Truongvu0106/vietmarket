package edu.hust.truongvu.choviet.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.activity.MainActivity;
import edu.hust.truongvu.choviet.customview.MyButton;
import edu.hust.truongvu.choviet.customview.MyEditText;
import edu.hust.truongvu.choviet.customview.MyTextView;
import edu.hust.truongvu.choviet.customview.MyToast;
import edu.hust.truongvu.choviet.helper.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment implements View.OnClickListener{


    MyEditText username, password;
    MyTextView signup, forgotPass;
    MyButton signin;
    LinearLayout root;
    private Animation animation;
    private static View view;
    private FragmentManager fragmentManager;

    public SigninFragment() {
        // Required empty public constructor
    }

    public static SigninFragment newInstance(){
        SigninFragment fragment = new SigninFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signin, container, false);
        root = (LinearLayout) view.findViewById(R.id.root_signin);
        username = (MyEditText) view.findViewById(R.id.signin_username);
        password = (MyEditText) view.findViewById(R.id.signin_password);
        signup = (MyTextView) view.findViewById(R.id.createAccount);
        forgotPass = (MyTextView) view.findViewById(R.id.forgot_password);
        signin = (MyButton) view.findViewById(R.id.loginBtn);

        signup.setOnClickListener(this);
        forgotPass.setOnClickListener(this);
        signin.setOnClickListener(this);
        fragmentManager = getActivity().getSupportFragmentManager();

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        return view;
    }

    private void checkValidation(){
        String name = username.getText().toString();
        String pass = password.getText().toString();

        if (name.equals("") || pass.equals("") || name.length() == 0 || pass.length() == 0){
            new MyToast().Show_Toast(getActivity(), view, getString(R.string.please_enter_signin));
            root.startAnimation(animation);
        }else if (!name.equals("t") || !pass.equals("1")){
            new MyToast().Show_Toast(getActivity(), view, getString(R.string.wrong_signin));
            root.startAnimation(animation);
        }else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forgot_password:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.start_container, new ForgotPassFragment(), Utils.FORGOTPASS_FRAGMENT).commit();
                break;
            case R.id.createAccount:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.start_container, new SignupFragment(), Utils.SIGNUP_FRAGMENT).commit();
                break;
            case R.id.loginBtn:
                checkValidation();
                break;
        }
    }

}
