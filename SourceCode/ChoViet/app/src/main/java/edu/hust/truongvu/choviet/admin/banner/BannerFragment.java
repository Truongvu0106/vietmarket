package edu.hust.truongvu.choviet.admin.banner;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.Banner;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends Fragment implements BannerView{
    private RecyclerView recyclerView;
    private BannerAdapter adapter;
    private View btnAdd;
    private BannerPresenter bannerPresenter;

    public BannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_banner, container, false);
        recyclerView = view.findViewById(R.id.list_banner);
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        bannerPresenter = new BannerPresenterImp(getContext(), this);
        bannerPresenter.initListBanner();

        return view;
    }

    @Override
    public void loadListBannerSuccessful(ArrayList<Banner> data) {
        adapter = new BannerAdapter(getContext(), data, new BannerAdapter.BannerListener() {
            @Override
            public void onClick(Banner banner) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void loadListBannerFalse() {

    }
}
