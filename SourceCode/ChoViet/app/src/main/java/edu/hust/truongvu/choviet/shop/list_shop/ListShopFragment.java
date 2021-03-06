package edu.hust.truongvu.choviet.shop.list_shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.shop.details_shop.ShopActivity;
import edu.hust.truongvu.choviet.helper.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListShopFragment extends Fragment implements ListShopView{
    private RecyclerView recyclerView;
    private ListShopAdapter adapter;
    private ListShopPresenterImp listShopPresenterImp;

    public ListShopFragment() {
        // Required empty public constructor
    }
    public static ListShopFragment getInstance(){
        ListShopFragment fragment = new ListShopFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_shop, container, false);
        recyclerView = view.findViewById(R.id.list_shop);
        listShopPresenterImp = new ListShopPresenterImp(getContext(),this);
        listShopPresenterImp.initListShop();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listShopPresenterImp.initListShop();
    }

    @Override
    public void loadListShop(ArrayList<Shop> listShop) {
        adapter = new ListShopAdapter(getContext(), listShop, new ListShopAdapter.ShopListener() {
            @Override
            public void onResults(Shop shop) {
                Intent intent = new Intent(getActivity(), ShopActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_SHOP, shop);
                startActivity(intent);
            }

            @Override
            public void onFollow(Shop shop, int idUser) {

            }

            @Override
            public void onUnFollow(Shop shop, int idUser) {

            }


        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
