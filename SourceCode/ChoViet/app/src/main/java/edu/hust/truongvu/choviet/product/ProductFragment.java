package edu.hust.truongvu.choviet.product;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.adapter.ImgProductPagerAdapter;
import edu.hust.truongvu.choviet.adapter.ProductAdapter;
import edu.hust.truongvu.choviet.cart.CartPresenterImp;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Rate;
import edu.hust.truongvu.choviet.entity.User;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.ZoomOutPageTransformer;
import edu.hust.truongvu.choviet.adapter.RateProductAdapter;
import edu.hust.truongvu.choviet.rate.RateProductDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment implements ProductView, View.OnClickListener{

    public interface ItemCartListener{
        void passNumberItem(int number);
    }
    private ItemCartListener itemCartListener;
    private String username;
    private ViewPager mViewPager;
    private ViewPagerIndicator mIndicator;
    private ImgProductPagerAdapter adapter;
    private ProductPresenterImp productPresenterImp;
    private CartPresenterImp cartPresenterImp;
    private RecyclerView mListRate, mListProduct, mListSuggest;
    private View btnRate, layoutDisableRate, layoutNoRate, btnAddToCart, btnBuyNow;
    TextView tvDisableRate;
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
    public void onAttach(Context context) {
        super.onAttach(context);
        itemCartListener = (ItemCartListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        username = MyHelper.getUserPreference(getContext());

        mViewPager = view.findViewById(R.id.img_pager);
        mIndicator = view.findViewById(R.id.img_indicator);
        mListRate = view.findViewById(R.id.list_rating);
        mListProduct = view.findViewById(R.id.list_another_product);
        mListSuggest = view.findViewById(R.id.list_maybe_like);
        btnRate = view.findViewById(R.id.btn_rate);
        btnAddToCart = view.findViewById(R.id.btn_add_to_cart);
        btnBuyNow = view.findViewById(R.id.btn_buy_now);
        layoutDisableRate = view.findViewById(R.id.layout_disable_rate);
        layoutNoRate = view.findViewById(R.id.layout_no_rate);
        tvDisableRate = view.findViewById(R.id.tv_disable_rate);

        btnRate.setOnClickListener(this);
        btnAddToCart.setOnClickListener(this);

        cartPresenterImp = new CartPresenterImp();
        productPresenterImp = new ProductPresenterImp(this);
        productPresenterImp.initListImage(product.getImgs());
        productPresenterImp.initListProduct();
        productPresenterImp.initListRate(username, product.getId());
        productPresenterImp.initListSuggest();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        itemCartListener.passNumberItem(cartPresenterImp.getNumberItemCart(getContext()));
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
    public void loadListRate(ArrayList<Rate> listRate) {
        if (listRate.size() == 0){
            layoutNoRate.setVisibility(View.VISIBLE);
        }else {
            layoutNoRate.setVisibility(View.GONE);
            RateProductAdapter adapter = new RateProductAdapter(getContext(), listRate);
            mListRate.setLayoutManager(new LinearLayoutManager(getContext()));
            mListRate.setAdapter(adapter);
        }

    }

    @Override
    public void setEnableRate(boolean isRated) {
        if (username.matches("")){
            btnRate.setVisibility(View.GONE);
            layoutDisableRate.setVisibility(View.VISIBLE);
            tvDisableRate.setText(getContext().getString(R.string.login_to_rate));
            return;
        }

        if (isRated){
            btnRate.setVisibility(View.GONE);
            layoutDisableRate.setVisibility(View.VISIBLE);
            tvDisableRate.setText(getContext().getString(R.string.already_rate));
            return;
        }

        btnRate.setVisibility(View.VISIBLE);
        layoutDisableRate.setVisibility(View.GONE);

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

    @Override
    public void addToCartSuccessful() {
        Toast.makeText(getContext(), getContext().getString(R.string.added_to_your_cart), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addToCartFalse() {
        Toast.makeText(getContext(), getContext().getString(R.string.already_in_your_cart), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_rate:
                rate();
                break;
            case R.id.btn_add_to_cart:
                productPresenterImp.addToCart(getContext(), product);
                itemCartListener.passNumberItem(cartPresenterImp.getNumberItemCart(getContext()));
                break;
            case R.id.btn_buy_now:
                break;
            default:
                break;
        }
    }

    private void rate(){
        User user = MyHelper.getCurrentUser(getContext());
        if (user == null){
            Toast.makeText(getContext(), getContext().getString(R.string.not_log_in), Toast.LENGTH_SHORT).show();
        }else {
            RateProductDialog rateDialog = new RateProductDialog(getContext(), product.getId(), user, new RateProductDialog.RateProductListener() {
                @Override
                public void onRate(Rate rate) {
                    if (productPresenterImp.addRate(rate)){
                        productPresenterImp.initListRate(username, product.getId());
                    }

                }
            });
            rateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            rateDialog.show();
        }

    }
}
