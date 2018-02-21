package edu.hust.truongvu.choviet.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.customview.MyButton;
import edu.hust.truongvu.choviet.customview.MyEditText;
import edu.hust.truongvu.choviet.customview.MyToast;
import edu.hust.truongvu.choviet.helper.Utils;
import edu.hust.truongvu.choviet.signin.SigninFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPassFragment extends Fragment implements View.OnClickListener{

    MyEditText enterEmail;
    MyButton btnBack, btnSubmit;
    RelativeLayout root;

    private static View view;
    private Animation animation;
    private FragmentManager fragmentManager;

    public ForgotPassFragment() {
        // Required empty public constructor
    }

    public static ForgotPassFragment newInstance(){
        ForgotPassFragment fragment = new ForgotPassFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        enterEmail = (MyEditText) view.findViewById(R.id.enter_email);
        btnBack = (MyButton) view.findViewById(R.id.back);
        btnSubmit = (MyButton) view.findViewById(R.id.submit);
        root = (RelativeLayout) view.findViewById(R.id.root_forgotpass);

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        fragmentManager = getActivity().getSupportFragmentManager();

        btnBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.start_container, new SigninFragment(), Utils.SIGNIN_FRAGMENT).commit();
                break;
            case R.id.submit:
                checkEmail();
                break;
        }
    }

    private void checkEmail(){
        String email = enterEmail.getText().toString();
        // Pattern for email id validation
        Pattern p = Pattern.compile(Utils.REG_EX);

        // Match the pattern
        Matcher m = p.matcher(email);

        if (email.equals("") || email.length()==0){
            new MyToast().Show_Toast(getActivity(), view, getString(R.string.please_enter_email));
            root.startAnimation(animation);
        }else if (!m.find()){
            new MyToast().Show_Toast(getActivity(), view, getString(R.string.invalid_email));
            root.startAnimation(animation);
        }else {
            Toast.makeText(getContext(), "Get Forgot Pass", Toast.LENGTH_SHORT).show();
        }

    }
}
