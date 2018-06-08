package edu.hust.truongvu.choviet.admin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.admin.banner.list_banner.BannerFragment;
import edu.hust.truongvu.choviet.admin.category.parent.ParentCategoryFragment;
import edu.hust.truongvu.choviet.admin.member.MemberFragment;
import edu.hust.truongvu.choviet.admin.order.OrderFragment;
import edu.hust.truongvu.choviet.admin.promotion.PromotionFragment;
import edu.hust.truongvu.choviet.helper.customview.ConfirmDialog;
import edu.hust.truongvu.choviet.user.info_user.LogoutDialog;

public class MainAdminActivity extends AppCompatActivity {
    public Fragment fragmentCurr;
    AccountHeader headerResult = null;
    Drawer result = null;
    ProfileDrawerItem userinfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.manage_user));

        MemberFragment fragment = new MemberFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_admin, fragment).commit();


        userinfo = new ProfileDrawerItem().withName(getString(R.string.hello)).withEmail("truongvu@gmail.com").withIcon(getResources().getDrawable(R.drawable.user_default));

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bg_header)
                .addProfiles(userinfo)
                .build();

        result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withToolbar(toolbar)
                .withSelectedItem(1)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(getString(R.string.manage_user)).withIcon(FontAwesome.Icon.faw_id_card),
                        new PrimaryDrawerItem().withName(getString(R.string.manage_banner)).withIcon(FontAwesome.Icon.faw_adversal),
                        new PrimaryDrawerItem().withName(getString(R.string.manage_category)).withIcon(FontAwesome.Icon.faw_clone),
                        new PrimaryDrawerItem().withName(getString(R.string.add_prodmotion)).withIcon(FontAwesome.Icon.faw_credit_card),
                        new PrimaryDrawerItem().withName(getString(R.string.manage_order)).withIcon(FontAwesome.Icon.faw_clipboard),
                        new SectionDrawerItem().withName(getString(R.string.extend)),
                        new SecondaryDrawerItem().withName(getString(R.string.sign_out)).withIcon(FontAwesome.Icon.faw_power_off)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch (position) {

                            case 1: // Member
                                getSupportActionBar().setTitle(((Nameable) drawerItem).getName().getText());
                                Spannable text1 = new SpannableString(getSupportActionBar().getTitle());
                                text1.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text1.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                getSupportActionBar().setTitle(text1);
                                MemberFragment fragment = new MemberFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_admin, fragment).commit();
                                break;
                            case 2: // Banner
                                getSupportActionBar().setTitle(((Nameable) drawerItem).getName().getText());
                                Spannable text2 = new SpannableString(getSupportActionBar().getTitle());
                                text2.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text2.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                getSupportActionBar().setTitle(text2);
                                BannerFragment fragment2 = new BannerFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_admin, fragment2).commit();
                                break;
                            case 3: // Category
                                getSupportActionBar().setTitle(((Nameable) drawerItem).getName().getText());
                                Spannable text3 = new SpannableString(getSupportActionBar().getTitle());
                                text3.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text3.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                getSupportActionBar().setTitle(text3);
                                ParentCategoryFragment fragment3 = new ParentCategoryFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_admin, fragment3).commit();
                                break;
                            case 4: // Promotion
                                getSupportActionBar().setTitle(((Nameable) drawerItem).getName().getText());
                                Spannable text4 = new SpannableString(getSupportActionBar().getTitle());
                                text4.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text4.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                getSupportActionBar().setTitle(text4);
                                PromotionFragment fragment4 = new PromotionFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_admin, fragment4).commit();
                                break;
                            case 5:
                                getSupportActionBar().setTitle(((Nameable) drawerItem).getName().getText());
                                Spannable text5 = new SpannableString(getSupportActionBar().getTitle());
                                text5.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text5.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                                getSupportActionBar().setTitle(text5);
                                OrderFragment fragment5 = new OrderFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_admin, fragment5).commit();
                                break;
                            case 7:
                                LogoutDialog dialog = new LogoutDialog(MainAdminActivity.this, new LogoutDialog.LogoutListener() {
                                    @Override
                                    public void onLogout() {
                                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(homeIntent);
                                    }
                                });
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                })
                .build();

    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back(){
        ConfirmDialog dialog = new ConfirmDialog(this, getString(R.string.do_u_really_want_to_exit), new ConfirmDialog.ConfirmListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onOk() {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
