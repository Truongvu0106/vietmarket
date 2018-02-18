package edu.hust.truongvu.choviet.fragment;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener{


    public static View view;
    MyEditText username, email, password, retypePass, birthday;
    MyTextView alreadyUser;
    MyButton signUp;
    LinearLayout root;
    private Animation animation;
    private static FragmentManager fragmentManager;
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
        root = (LinearLayout) view.findViewById(R.id.signup_layout);
        username = (MyEditText) view.findViewById(R.id.signup_username);
        email = (MyEditText) view.findViewById(R.id.signup_email);
        password = (MyEditText) view.findViewById(R.id.signup_password);
        retypePass = (MyEditText) view.findViewById(R.id.retype_pass);
        birthday = (MyEditText) view.findViewById(R.id.birth_day);
        alreadyUser = (MyTextView) view.findViewById(R.id.already_user);
        signUp = (MyButton) view.findViewById(R.id.signUpBtn);



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
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.start_container, new SigninFragment(), Utils.SIGNIN_FRAGMENT).commit();
                break;
            case R.id.signUpBtn:
                checkValid();
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


    private void checkValid(){
        String getUserName = username.getText().toString();
        String getEmail = email.getText().toString();
        String getPass = password.getText().toString();
        String getRetype = retypePass.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.REG_EX);
        Matcher m = p.matcher(getEmail);

        if (getUserName.equals("")||getEmail.equals("")||getPass.equals("")||getRetype.equals("")
                ||getUserName.length()==0||getEmail.length()==0||getPass.length()==0||getRetype.length()==0){
            new MyToast().Show_Toast(getActivity(), view, getString(R.string.please_enter_all));
            root.startAnimation(animation);
        }else if (!getPass.equals(getRetype)) {
            new MyToast().Show_Toast(getActivity(), view, getString(R.string.pass_not_match));
            root.startAnimation(animation);
        }else if (!m.find()){
            new MyToast().Show_Toast(getActivity(), view, getString(R.string.invalid_email));
            root.startAnimation(animation);
        }else {
            Toast.makeText(getContext(), "Signup successful!", Toast.LENGTH_SHORT).show();
        }
    }

}
