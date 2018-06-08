package edu.hust.truongvu.choviet.startup.signin;


import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.FacebookSdk;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shashank.sony.fancytoastlib.FancyToast;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.admin.MainAdminActivity;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.init.MainActivity;
import edu.hust.truongvu.choviet.helper.customview.MyToast;
import edu.hust.truongvu.choviet.model.UserModel;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.startup.signup.SignupFragment;
import edu.hust.truongvu.choviet.helper.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment implements SigninView, View.OnClickListener{
    private static final int SIGN_IN_GOOGLE = 9999;

    EditText username, password;
    View signup;
    View signin, signInFacebook, signInGplus, continueAsGuest;
    View root;
    private Animation animation;
    private static View view;
    private FragmentManager fragmentManager;
    private SigninPresenterImp signinPresenterImp;
//    CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
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
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
//        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
//        callbackManager = CallbackManager.Factory.create();
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                startActivity(new Intent(getActivity(), MainActivity.class));
//            }
//
//            @Override
//            public void onCancel() {
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });
        root = view.findViewById(R.id.root_signin);
        username = view.findViewById(R.id.signin_username);
        password = view.findViewById(R.id.signin_password);
        signup = view.findViewById(R.id.createAccount);
        signin = view.findViewById(R.id.loginBtn);
        signInFacebook = view.findViewById(R.id.login_with_facebook);
        signInGplus = view.findViewById(R.id.login_with_gplus);
        continueAsGuest = view.findViewById(R.id.continue_as_guest);

        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
        signInFacebook.setOnClickListener(this);
        signInGplus.setOnClickListener(this);
        continueAsGuest.setOnClickListener(this);
        fragmentManager = getActivity().getSupportFragmentManager();

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        signinPresenterImp = new SigninPresenterImp(getContext(),this);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        if (account != null){
            startActivity(new Intent(getActivity(), MainActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.createAccount:
                navigate(new SignupFragment(), Constants.MyTag.SIGNUP_FRAGMENT);
                break;
            case R.id.loginBtn:
                String name = username.getText().toString();
                String pass = password.getText().toString();
                signinPresenterImp.signin(name, pass);
                break;
            case R.id.login_with_facebook:
//                LoginManager.getInstance()
//                        .logInWithReadPermissions(SigninFragment.this, Arrays.asList("public_profile", "email"));
                break;
            case R.id.login_with_gplus:
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, SIGN_IN_GOOGLE);
                break;
            case R.id.continue_as_guest:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(User user) {
        Log.e("login", "success");
        if (user.getIdTypeUser() == Constants.User.TYPE_ADMIN){
            startActivity(new Intent(getActivity(), MainAdminActivity.class));
        }else {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onError(String error) {
        MyHelper.showToast(getContext(), getString(R.string.wrong_signin), FancyToast.ERROR);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("RequestCode", requestCode + "");
        if (requestCode == SIGN_IN_GOOGLE){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.e("google", account.getPhotoUrl().toString());
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("GOOGLE", account);
                startActivity(intent);
            } catch (ApiException e) {
                e.printStackTrace();
                Log.e("Error", e.toString());
            }
        }else {
//            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
