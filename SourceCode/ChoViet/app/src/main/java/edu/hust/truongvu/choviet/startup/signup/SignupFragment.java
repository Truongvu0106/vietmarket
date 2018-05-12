package edu.hust.truongvu.choviet.startup.signup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.startup.signin.SigninFragment;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements SignupView, View.OnClickListener{


    public static View view;
    EditText fullName, email, phone, password, retypePass;
    View alreadyUser;
    View signUp;
    LinearLayout root;
    private Animation animation;
    private static FragmentManager fragmentManager;

    private SignupPresenterImp signupPresenterImp;
    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance(){
        SignupFragment fragment = new SignupFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        root = view.findViewById(R.id.signup_layout);
        fullName = view.findViewById(R.id.signup_username);
        email =  view.findViewById(R.id.signup_email);
        phone = view.findViewById(R.id.signup_phone);
        password =  view.findViewById(R.id.signup_password);
        retypePass =  view.findViewById(R.id.retype_pass);
        alreadyUser = view.findViewById(R.id.already_user);
        signUp = view.findViewById(R.id.signUpBtn);

        signupPresenterImp = new SignupPresenterImp(getContext(), this);

        alreadyUser.setOnClickListener(this);
        signUp.setOnClickListener(this);


        fragmentManager = getActivity().getSupportFragmentManager();
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        return view;
    }

    @Override
    public void onClick(View view) {
        final Calendar calendar = Calendar.getInstance();
        switch (view.getId()){
            case R.id.already_user:
                navigateToSignin();
                break;
            case R.id.signUpBtn:
                String getUserName = fullName.getText().toString().trim();
                String getEmail = email.getText().toString().trim();
                String getPhone = phone.getText().toString().trim();
                String getPass = password.getText().toString().trim();
                String getRetype = retypePass.getText().toString().trim();
                signupPresenterImp.signup(getUserName, getEmail, getPhone, getPass, getRetype);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getContext(), getString(R.string.signup_successful), Toast.LENGTH_SHORT).show();
        navigateToSignin();
    }

    @Override
    public void onError(String error) {
        Log.e("signup_error", error);
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        root.startAnimation(animation);
    }

    private void navigateToSignin(){
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.start_container, new SigninFragment(), Constants.MyTag.SIGNIN_FRAGMENT).commit();
    }
}
