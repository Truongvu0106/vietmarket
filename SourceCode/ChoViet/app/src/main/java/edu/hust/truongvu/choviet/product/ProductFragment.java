package edu.hust.truongvu.choviet.product;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.ProductRate;
import edu.hust.truongvu.choviet.helper.ZoomOutPageTransformer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment implements ProductView{
    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private ImgProductPagerAdapter adapter;
    private ProductPresenterImp productPresenterImp;
    private RecyclerView mListRate, mListProduct, mListSuggest;
    private Product product;
    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment getInstance(Product product){
        ProductFragment fragment = new ProductFragment();
        fragment.product = product;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        mViewPager = view.findViewById(R.id.img_pager);
        mIndicator = view.findViewById(R.id.img_indicator);
        mListRate = view.findViewById(R.id.list_rating);
        mListProduct = view.findViewById(R.id.list_another_product);
        mListSuggest = view.findViewById(R.id.list_maybe_like);

        productPresenterImp = new ProductPresenterImp(this);
        if (product != null){
            productPresenterImp.initListImage(product.getImgs());
        }else if (product == null){
            Log.e("details_product", "null");
        }
        productPresenterImp.initListProduct();
        productPresenterImp.initListRate();
        productPresenterImp.initListSuggest();
        return view;
    }

    @Override
    public void loadListImage(ArrayList<String> list) {
        adapter = new ImgProductPagerAdapter(getFragmentManager(), list);
        mViewPager.setAdapter(adapter);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mIndicator.setupWithViewPager(mViewPager);
        mIndicator.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void loadListRate(ArrayList<ProductRate> listRate) {
        RateProductAdapter adapter = new RateProductAdapter(listRate);
        mListRate.setLayoutManager(new LinearLayoutManager(getContext()));
        mListRate.setAdapter(adapter);
    }

    @Override
    public void loadListProduct(ArrayList<Product> listProduct) {
        ProductAdapter adapter = new ProductAdapter(getContext(), listProduct, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {

            }

            @Override
            public void onLikeClick() {

            }
        });
        mListProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListProduct.setAdapter(adapter);
    }

    @Override
    public void loadListSuggest(ArrayList<Product> listSuggest) {
        ProductAdapter adapter = new ProductAdapter(getContext(), listSuggest, new ProductAdapter.ProductListener() {
            @Override
            public void onProductResult(Product product) {

            }

            @Override
            public void onLikeClick() {

            }
        });
        mListSuggest.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListSuggest.setAdapter(adapter);
    }
}
