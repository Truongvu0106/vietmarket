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
public class OverviewReportFragment extends Fragment {


    public OverviewReportFragment() {
        // Required empty public constructor
    }

    public static OverviewReportFragment getInstance(){
        OverviewReportFragment fragment = new OverviewReportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview_report, container, false);
        return view;
    }

}
