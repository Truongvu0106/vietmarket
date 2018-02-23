package edu.hust.truongvu.choviet.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.PopularSearch;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Store;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    private HomePresenterImp homePresenterImp;
    private SliderLayout mSliderLayout;

    private RecyclerView mListPopularSearch, mListBrand, mListHighlightProduct;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mSliderLayout = view.findViewById(R.id.slider);
        mListPopularSearch = view.findViewById(R.id.list_popular_search);
        mListBrand = view.findViewById(R.id.list_brand);
        mListHighlightProduct = view.findViewById(R.id.list_hightlight_product);
        homePresenterImp = new HomePresenterImp(this);
        homePresenterImp.initBanner();
        homePresenterImp.initListSearch();
        homePresenterImp.initListBrand();
        homePresenterImp.initListProduct();
        return view;
    }

    @Override
    public void loadBanner(HashMap<String, Integer> hashMap) {
        for(String name : hashMap.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description(name)
                    .image(hashMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mSliderLayout.addSlider(textSliderView);
        }
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setDuration(4000);
        mSliderLayout.addOnPageChangeListener(this);
    }

    @Override
    public void loadBrand(ArrayList<Brand> listBrand) {
        BrandAdapter adapter = new BrandAdapter(listBrand, new BrandAdapter.BrandListener() {
            @Override
            public void onClick(Brand brand) {
                Toast.makeText(getContext(), brand.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mListBrand.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
        mListBrand.setAdapter(adapter);
    }

    @Override
    public void loadListPopularSearch(ArrayList<PopularSearch> list) {
        PopularSearchAdapter adapter = new PopularSearchAdapter(list);
        mListPopularSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListPopularSearch.setAdapter(adapter);
    }

    @Override
    public void loadListHighlightStore(ArrayList<Store> listStore) {

    }

    @Override
    public void loadListHighlightProduct(ArrayList<Product> listProduct) {
        ProductAdapter adapter = new ProductAdapter(listProduct, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {
                Toast.makeText(getContext(), product.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLikeClick() {
                Toast.makeText(getContext(), "Like Click", Toast.LENGTH_SHORT).show();
            }
        });
        mListHighlightProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListHighlightProduct.setAdapter(adapter);
    }

    @Override
    public void loadListSuggest(ArrayList<Product> listProduct) {

    }




    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
