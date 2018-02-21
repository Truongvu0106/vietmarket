package edu.hust.truongvu.choviet.signup;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.customview.MyButton;
import edu.hust.truongvu.choviet.customview.MyEditText;
import edu.hust.truongvu.choviet.customview.MyTextView;
import edu.hust.truongvu.choviet.customview.MyToast;
import edu.hust.truongvu.choviet.helper.Utils;
import edu.hust.truongvu.choviet.signin.SigninFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements SignupView, View.OnClickListener{


    public static View view;
    MyEditText username, email, password, retypePass, birthday;
    MyTextView alreadyUser;
    MyButton signUp;
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
        username = view.findViewById(R.id.signup_username);
        email =  view.findViewById(R.id.signup_email);
        password =  view.findViewById(R.id.signup_password);
        retypePass =  view.findViewById(R.id.retype_pass);
        birthday = view.findViewById(R.id.birth_day);
        alreadyUser = view.findViewById(R.id.already_user);
        signUp = view.findViewById(R.id.signUpBtn);

        signupPresenterImp = new SignupPresenterImp(getContext(), this);

        alreadyUser.setOnClickListener(this);
        signUp.setOnClickListener(this);
        birthday.setOnClickListener(this);


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
                String getUserName = username.getText().toString();
                String getEmail = email.getText().toString();
                String getPass = password.getText().toString();
                String getRetype = retypePass.getText().toString();
                signupPresenterImp.signup(getUserName, getEmail, getPass, getRetype);
                break;
            case R.id.birth_day:
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        birthday.setText(i2 + "/" + (i1 + 1) + "/" + i);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
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
        new MyToast().Show_Toast(getActivity(), view, error);
        root.startAnimation(animation);
    }

    private void navigateToSignin(){
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.start_container, new SigninFragment(), Utils.SIGNIN_FRAGMENT).commit();
    }
}
