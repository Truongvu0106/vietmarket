package edu.hust.truongvu.choviet.user;


import android.content.Intent;
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
import edu.hust.truongvu.choviet.user.followingshop.FollowingShopActivity;
import edu.hust.truongvu.choviet.user.myshop.MyShopActivity;
import edu.hust.truongvu.choviet.user.wishlist.WishListProductActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileView{

    private View btnEditProfile, btnSetting, btnYourOrder, btnYourWishlist,
            btnYourFavoriteShop, btnYourShop, btnAccount, btnSettingTwo, btnAboutUs;
    private ImageView imgProfile;
    private TextView tvName;
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

        return view;
    }

    private void initView(View view){
        btnEditProfile = view.findViewById(R.id.btn_edit_profile);
        btnSetting = view.findViewById(R.id.btn_setting);
        btnYourOrder = view.findViewById(R.id.btn_your_order);
        btnYourWishlist = view.findViewById(R.id.btn_your_wishlist);
        btnYourFavoriteShop = view.findViewById(R.id.btn_your_favorite_shop);
        btnYourShop = view.findViewById(R.id.btn_your_shop);
        btnAccount = view.findViewById(R.id.btn_account);
        btnSettingTwo = view.findViewById(R.id.btn_setting_two);
        btnAboutUs = view.findViewById(R.id.btn_about_us);
        imgProfile = view.findViewById(R.id.profile_image);
        tvName = view.findViewById(R.id.tv_username);

        btnEditProfile.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnYourOrder.setOnClickListener(this);
        btnYourWishlist.setOnClickListener(this);
        btnYourFavoriteShop.setOnClickListener(this);
        btnYourShop.setOnClickListener(this);
        btnAccount.setOnClickListener(this);
        btnSettingTwo.setOnClickListener(this);
        btnAboutUs.setOnClickListener(this);
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
                break;
            case R.id.btn_setting:
                break;
            case R.id.btn_your_order:
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
                break;
            case R.id.btn_setting_two:
                break;
            case R.id.btn_about_us:
                break;
            default:
                break;
        }
    }

    @Override
    public void initLayoutWithFacebook() {

    }

    @Override
    public void initLayoutWithGoogle() {

    }
}
