package edu.hust.truongvu.choviet.user.myshop.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.hust.truongvu.choviet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopReportFragment extends Fragment {

    public static ShopReportFragment getInstance(){
        ShopReportFragment fragment = new ShopReportFragment();
        return fragment;
    }

    public ShopReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_report, container, false);
    }

}
