package edu.hust.truongvu.choviet.user;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.startup.StartActivity;
import edu.hust.truongvu.choviet.user.about_us.AboutUsActivity;
import edu.hust.truongvu.choviet.user.followingshop.FollowingShopActivity;
import edu.hust.truongvu.choviet.user.info_user.InfoUserActivity;
import edu.hust.truongvu.choviet.user.myorder.MyOrderActivity;
import edu.hust.truongvu.choviet.user.myshop.MyShopActivity;
import edu.hust.truongvu.choviet.user.setting.SettingActivity;
import edu.hust.truongvu.choviet.user.wishlist.WishListProductActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileView{
    public static final String OPTION_TAG = "option_tag";
    private View btnEditProfile, btnSetting, btnYourOrder, btnYourWishlist,
            btnYourFavoriteShop, btnYourShop, btnAccount, btnSettingTwo, btnAboutUs, btnSignInNow;
    private View orderWaiting, orderShipping, orderReceived, orderCancel;
    private View layoutInfor, layoutErr;
    private ImageView imgProfile;
    private TextView tvName;
    private ProfilePresenter profilePresenter;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(){
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
//        if (MainActivity.IMAGE.matches("")){
//            if (MainActivity.URI_IMAGE != null){
//                loadGoogle(MainActivity.URI_IMAGE, MainActivity.USER_NAME);
//            }
//        }else {
//            loadFacebook(MainActivity.IMAGE, MainActivity.USER_NAME);
//        }
        profilePresenter = new ProfilePresenterImp(getContext(), this);
        profilePresenter.initInforUser();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        profilePresenter.initInforUser();
    }

    private void initView(View view){
        layoutInfor = view.findViewById(R.id.layout_infor);
        layoutErr = view.findViewById(R.id.layout_err);
        btnEditProfile = view.findViewById(R.id.btn_edit_profile);
        btnSetting = view.findViewById(R.id.btn_setting);
        btnYourOrder = view.findViewById(R.id.btn_your_order);
        btnYourWishlist = view.findViewById(R.id.btn_your_wishlist);
        btnYourFavoriteShop = view.findViewById(R.id.btn_your_favorite_shop);
        btnYourShop = view.findViewById(R.id.btn_your_shop);
        btnAccount = view.findViewById(R.id.btn_account);
        btnSettingTwo = view.findViewById(R.id.btn_setting_two);
        btnAboutUs = view.findViewById(R.id.btn_about_us);
        btnSignInNow = view.findViewById(R.id.btn_signin_now);
        imgProfile = view.findViewById(R.id.profile_image);
        tvName = view.findViewById(R.id.tv_username);
        orderWaiting = view.findViewById(R.id.order_wait);
        orderShipping = view.findViewById(R.id.order_ship);
        orderReceived = view.findViewById(R.id.order_receive);
        orderCancel = view.findViewById(R.id.order_cancel);

        btnEditProfile.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnYourOrder.setOnClickListener(this);
        btnYourWishlist.setOnClickListener(this);
        btnYourFavoriteShop.setOnClickListener(this);
        btnYourShop.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnSettingTwo.setOnClickListener(this);
        btnAboutUs.setOnClickListener(this);
        btnSignInNow.setOnClickListener(this);
        orderWaiting.setOnClickListener(this);
        orderShipping.setOnClickListener(this);
        orderReceived.setOnClickListener(this);
        orderCancel.setOnClickListener(this);
    }

    private void loadFacebook(String image, String name){
        Picasso.with(getContext())
                .load(image)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .resize(150, 150)
                .centerCrop()
                .into(imgProfile);
        tvName.setText(name);
    }

    private void loadGoogle(Uri image, String name){
        Picasso.with(getContext())
                .load(image)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .resize(150, 150)
                .centerCrop()
                .into(imgProfile);
        tvName.setText(name);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_edit_profile:
                startActivity(new Intent(getActivity(), InfoUserActivity.class));
                break;
            case R.id.btn_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.btn_your_order:
                startOrderAvtivity(Constants.OrderStatus.WAITING.getIdStatus());
                break;
            case R.id.btn_your_wishlist:
                startActivity(new Intent(getActivity(), WishListProductActivity.class));
                break;
            case R.id.btn_your_favorite_shop:
                startActivity(new Intent(getActivity(), FollowingShopActivity.class));
                break;
            case R.id.btn_your_shop:
                startActivity(new Intent(getActivity(), MyShopActivity.class));
                break;
            case R.id.btn_account:
                startActivity(new Intent(getActivity(), InfoUserActivity.class));
                break;
            case R.id.btn_setting_two:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.btn_about_us:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.btn_signin_now:
                startActivity(new Intent(getActivity(), StartActivity.class));
                break;
            case R.id.order_wait:
                startOrderAvtivity(Constants.OrderStatus.WAITING.getIdStatus());
                break;
            case R.id.order_ship:
                startOrderAvtivity(Constants.OrderStatus.SHIPPING.getIdStatus());
                break;
            case R.id.order_receive:
                startOrderAvtivity(Constants.OrderStatus.RECEIVED.getIdStatus());
                break;
            case R.id.order_cancel:
                startOrderAvtivity(Constants.OrderStatus.CANCEL.getIdStatus());
                break;
            default:
                break;
        }
    }

    private void startOrderAvtivity(int code){
        Intent intent = new Intent(getActivity(), MyOrderActivity.class);
        intent.putExtra(OPTION_TAG, code);
        startActivity(intent);
    }

    @Override
    public void initLayoutWithFacebook() {

    }

    @Override
    public void initLayoutWithGoogle() {

    }

    @Override
    public void loadInforUserSucessful(User user) {
        layoutInfor.setVisibility(View.VISIBLE);
        layoutErr.setVisibility(View.GONE);
        tvName.setText(user.getFullname());
        MyHelper.loadImageUser(getContext(), imgProfile, Constants.MY_PATH + user.getAvatar());
    }

    @Override
    public void loadInforUserFalse() {
        layoutInfor.setVisibility(View.GONE);
        layoutErr.setVisibility(View.VISIBLE);
    }
}
