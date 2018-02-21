package edu.hust.truongvu.choviet.signin;


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
import edu.hust.truongvu.choviet.fragment.ForgotPassFragment;
import edu.hust.truongvu.choviet.signup.SignupFragment;
import edu.hust.truongvu.choviet.helper.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment implements SigninView, View.OnClickListener{


    MyEditText username, password;
    MyTextView signup, forgotPass;
    MyButton signin;
    LinearLayout root;
    private Animation animation;
    private static View view;
    private FragmentManager fragmentManager;
    private SigninPresenterImp signinPresenterImp;

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
        root = view.findViewById(R.id.root_signin);
        username = view.findViewById(R.id.signin_username);
        password = view.findViewById(R.id.signin_password);
        signup = view.findViewById(R.id.createAccount);
        forgotPass = view.findViewById(R.id.forgot_password);
        signin = view.findViewById(R.id.loginBtn);

        signup.setOnClickListener(this);
        forgotPass.setOnClickListener(this);
        signin.setOnClickListener(this);
        fragmentManager = getActivity().getSupportFragmentManager();

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        signinPresenterImp = new SigninPresenterImp(getContext(),this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forgot_password:
                navigate(new ForgotPassFragment(), Utils.FORGOTPASS_FRAGMENT);
                break;
            case R.id.createAccount:
                navigate(new SignupFragment(), Utils.SIGNUP_FRAGMENT);
                break;
            case R.id.loginBtn:
                String name = username.getText().toString();
                String pass = password.getText().toString();
                signinPresenterImp.signin(name, pass);
                break;
        }
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError(String error) {
        new MyToast().Show_Toast(getActivity(), view, error);
        root.startAnimation(animation);
    }

    private void navigate(Fragment fragment, String tag){
        if (fragment == null){
            return;
        }else {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                    .replace(R.id.start_container, fragment, tag).commit();
        }

    }
}
