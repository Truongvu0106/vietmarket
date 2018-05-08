package edu.hust.truongvu.choviet.home;


import android.content.Intent;
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
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.product.list_product.ListProductActivity;
import edu.hust.truongvu.choviet.product.details_product.ProductActivity;
import edu.hust.truongvu.choviet.product.list_product.ProductAdapter;
import edu.hust.truongvu.choviet.shop.details_shop.ShopActivity;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView,
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,
        View.OnClickListener{
    private HomePresenterImp homePresenterImp;
    private SliderLayout mSliderLayout;
    private View btnLoadMoreHighLightShop, btnLoadMoreHighLightProduct,
            btnLoadMoreLastestProduct, btnLoadMoreSuggestProduct;
    private ArrayList<Product> mlistHighlightProduct = new ArrayList<>(),
            mListLastestProduct = new ArrayList<>(),
            mListSuggestProduct = new ArrayList<>();
    private ArrayList<Shop> mListHighLightShop = new ArrayList<>();

    private RecyclerView mRecyclerPopularSearch, mRecyclerBrand, mRecyclerHighlightProduct,
            mRecyclerHighlightShop, mRecyclerLastestProduct, mRecyclerSuggestProduct;

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
        mRecyclerPopularSearch = view.findViewById(R.id.list_popular_search);
        mRecyclerBrand = view.findViewById(R.id.list_brand);
        mRecyclerHighlightShop = view.findViewById(R.id.list_highlight_store);
        mRecyclerHighlightProduct = view.findViewById(R.id.list_hightlight_product);
        mRecyclerLastestProduct = view.findViewById(R.id.list_lastest_product);
        mRecyclerSuggestProduct = view.findViewById(R.id.list_suggest);
        btnLoadMoreHighLightShop = view.findViewById(R.id.more_highlight_store);
        btnLoadMoreHighLightProduct = view.findViewById(R.id.more_highlight_product);
        btnLoadMoreLastestProduct = view.findViewById(R.id.more_lastest_product);
        btnLoadMoreSuggestProduct = view.findViewById(R.id.more_suggest);
        homePresenterImp = new HomePresenterImp(getContext(),this);
        homePresenterImp.initBanner();
        homePresenterImp.initListSearch();
        homePresenterImp.initListBrand();
        homePresenterImp.initListHighlightProduct();
        homePresenterImp.initListHighlightShop();
        homePresenterImp.initListLastestProduct();
        homePresenterImp.initListSuggest();

        btnLoadMoreHighLightShop.setOnClickListener(this);
        btnLoadMoreHighLightProduct.setOnClickListener(this);
        btnLoadMoreLastestProduct.setOnClickListener(this);
        btnLoadMoreSuggestProduct.setOnClickListener(this);
        return view;
    }

    @Override
    public void loadBannerSuccessful(HashMap<String, Integer> hashMap) {
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
    public void loadBannerFalse() {

    }

    @Override
    public void loadListBrandSuccessful(ArrayList<Brand> listBrand) {
        BrandAdapter adapter = new BrandAdapter(getContext(), listBrand, new BrandAdapter.BrandListener() {
            @Override
            public void onClick(Brand brand) {
                Intent intent = new Intent(getActivity(), ListProductActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_TYPE_LOAD_PRODUCT, Constants.MyTag.LOAD_PRODUCT_BY_BRAND);
                intent.putExtra(Constants.MyTag.ID_BRAND, brand.getId());
                startActivity(intent);
            }
        });
        mRecyclerBrand.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));
        mRecyclerBrand.setAdapter(adapter);
    }

    @Override
    public void loadListBrandFalse() {

    }

    @Override
    public void loadListPopularSearchSuccessful(ArrayList<PopularSearch> list) {
        PopularSearchAdapter adapter = new PopularSearchAdapter(getContext(), list);
        mRecyclerPopularSearch.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerPopularSearch.setAdapter(adapter);
    }

    @Override
    public void loadListPopularSearchFalse() {

    }

    @Override
    public void loadListHighlightShopSuccessful(ArrayList<Shop> listShop) {
        HighlightShopAdapter adapter = new HighlightShopAdapter(getContext(), listShop, new HighlightShopAdapter.ShopListener() {
            @Override
            public void onStoreResult(Shop shop) {
                Intent intent = new Intent(getActivity(), ShopActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_SHOP, shop);
                startActivity(intent);
            }
        });
        mRecyclerHighlightShop.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerHighlightShop.setAdapter(adapter);
    }

    @Override
    public void loadListHighlightShopFalse() {

    }

    @Override
    public void loadListHighlightProductSuccessful(ArrayList<Product> listProduct) {
        mlistHighlightProduct = listProduct;
        ProductAdapter adapter = new ProductAdapter(getContext(), listProduct, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_PRODUCT, product);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(int idUser, Product product) {
                Toast.makeText(getContext(), "Like Click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnlikeClick(int idUser, Product product) {

            }
        });
        mRecyclerHighlightProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerHighlightProduct.setAdapter(adapter);
    }

    @Override
    public void loadListHighlightProductFalse() {

    }

    @Override
    public void loadListLastestProductSuccessful(ArrayList<Product> listProduct) {
        mListLastestProduct = listProduct;
        ProductAdapter adapter = new ProductAdapter(getContext(), listProduct, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_PRODUCT, product);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(int idUser, Product product) {

            }

            @Override
            public void onUnlikeClick(int idUser, Product product) {

            }
        });
        mRecyclerLastestProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerLastestProduct.setAdapter(adapter);
    }

    @Override
    public void loadListLastestProductFalse() {

    }

    @Override
    public void loadListSuggestProductSuccessful(ArrayList<Product> listProduct) {
        mListSuggestProduct = listProduct;
        ProductAdapter adapter = new ProductAdapter(getContext(), listProduct, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_PRODUCT, product);
                startActivity(intent);
            }

            @Override
            public void onLikeClick(int idUser, Product product) {
            }

            @Override
            public void onUnlikeClick(int idUser, Product product) {

            }
        });
        mRecyclerSuggestProduct.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        mRecyclerSuggestProduct.setAdapter(adapter);
    }

    @Override
    public void loadListSuggestProductFalse() {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.more_highlight_store:
                break;
            case R.id.more_highlight_product:
                Intent intent = new Intent(getContext(), ListProductActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_LIST_PRODUCT, mlistHighlightProduct);
                startActivity(intent);
                break;
            case R.id.more_lastest_product:
                Intent intent1 = new Intent(getContext(), ListProductActivity.class);
                intent1.putExtra(Constants.MyTag.INTENT_LIST_PRODUCT, mListLastestProduct);
                startActivity(intent1);
                break;
            case R.id.more_suggest:
                Intent intent2 = new Intent(getContext(), ListProductActivity.class);
                intent2.putExtra(Constants.MyTag.INTENT_LIST_PRODUCT, mListSuggestProduct);
                startActivity(intent2);
                break;
        }
    }
}
