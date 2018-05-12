package edu.hust.truongvu.choviet.init;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.BottomNavigationViewHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    ViewPager viewPager;
    MenuItem prevMenuItem;
    BottomNavigationView bottomNavigation;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        bottomNavigation = view.findViewById(R.id.navigation);
        viewPager =  view.findViewById(R.id.view_pager);

        viewPager.setAdapter(new MainViewPagerAdapter(getFragmentManager()));
        viewPager.getAdapter().notifyDataSetChanged();

        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_category:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_market:
                        viewPager.setCurrentItem(2);
                        break;
//                    case R.id.nav_notification:
//                        viewPager.setCurrentItem(3);
//                        break;
                    case R.id.nav_profile:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        viewPager.setCurrentItem(0);
                        break;

                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigation.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

}
