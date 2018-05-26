package edu.hust.truongvu.choviet.product.details_product;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartActivity;
import edu.hust.truongvu.choviet.category.CategoryPresenterImp;
import edu.hust.truongvu.choviet.product.list_product.ProductAdapter;
import edu.hust.truongvu.choviet.cart.CartPresenterImp;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Rate;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.model.entity.User;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.ZoomOutPageTransformer;
import edu.hust.truongvu.choviet.product.rate.RateProductAdapter;
import edu.hust.truongvu.choviet.product.list_product.ImgProductPagerAdapter;
import edu.hust.truongvu.choviet.product.rate.RateProductDialog;
import edu.hust.truongvu.choviet.shop.details_shop.ShopActivity;
import edu.hust.truongvu.choviet.helper.Constants;

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
    private CategoryPresenterImp categoryPresenterImp;
    private ProductPresenterImp productPresenterImp;
    private CartPresenterImp cartPresenterImp;
    private RecyclerView mListRate, mListProduct, mListSuggest;
    private View btnRate, layoutDisableRate, layoutNoRate, btnAddToCart, btnViewShop, btnBuyNow, layoutDiscount;
    private ImageView imgShop;
    private TextView tvDisableRate, tvName, tvOldPrice, tvNewPrice, tvNameShop,
            tvRate, tvTotalProduct, tvRateShop, tvDescription, tvCategory, tvWeight,
            tvBrand, tvStock, tvFrom, tvRateOther, tvDiscount;
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

        username = MyHelper.getUserNamePreference(getContext());
        cartPresenterImp = new CartPresenterImp();
        productPresenterImp = new ProductPresenterImp(getContext(), this);
        categoryPresenterImp = new CategoryPresenterImp(getContext());
        productPresenterImp.initListImage(product.getImgs());
        productPresenterImp.initListProductOther(0);
        productPresenterImp.initListRate(username, product.getId());
        productPresenterImp.initListProductSuggest(0);

        initView(view);
        productPresenterImp.initInforShop(product.getIdShop());

        return view;
    }

    private void initView(View view){
        imgShop = view.findViewById(R.id.img_shop);
        tvName = view.findViewById(R.id.details_name_product);
        tvOldPrice= view.findViewById(R.id.details_old_price);
        tvNewPrice = view.findViewById(R.id.details_new_price);
        tvNameShop = view.findViewById(R.id.tv_shop);
        tvRate = view.findViewById(R.id.tv_rating);
        tvTotalProduct = view.findViewById(R.id.tv_total_product_shop);
        tvRateShop = view.findViewById(R.id.tv_rate_shop);
        tvDescription = view.findViewById(R.id.des);
        tvCategory = view.findViewById(R.id.tv_category);
        tvWeight = view.findViewById(R.id.tv_weight);
        tvBrand = view.findViewById(R.id.tv_brand);
        tvStock = view.findViewById(R.id.tv_stock);
        tvFrom = view.findViewById(R.id.tv_from);
        tvRateOther = view.findViewById(R.id.tv_rate_product_other);
        btnViewShop = view.findViewById(R.id.btn_view_shop);
        layoutDiscount = view.findViewById(R.id.layout_discount);
        tvDiscount = view.findViewById(R.id.tv_discount);

        tvName.setText(product.getName());
        int discount = product.getDiscount();
        if (discount == 0){
            tvOldPrice.setVisibility(View.GONE);
            layoutDiscount.setVisibility(View.GONE);
            tvNewPrice.setText(MyHelper.formatMoney(product.getPrice()));
        }else {
            tvOldPrice.setVisibility(View.VISIBLE);
            layoutDiscount.setVisibility(View.VISIBLE);
            long newPrice = product.getPrice() - (product.getPrice()*discount)/100;

            tvDiscount.setText("-" + discount + "%");
            tvOldPrice.setText(MyHelper.formatMoney(product.getPrice()));
            tvNewPrice.setText(MyHelper.formatMoney(newPrice));

            tvOldPrice.setPaintFlags(tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        tvRate.setText(product.getRate() + "/5");
        tvDescription.setText(product.getInfomation());
        tvCategory.setText(product.getTypeProduct() + "");

        ChildCategory childCategory = categoryPresenterImp.getChildCategory(product.getTypeProduct());
        if (childCategory == null){
            tvCategory.setText("???");
        }else {
            tvCategory.setText(childCategory.getName());
        }

        if (product.getWeight().matches("")){
            tvWeight.setText("???");
        }else {
            tvWeight.setText(product.getWeight());
        }

        Brand brand = productPresenterImp.getBrand(product.getBrand());
        if (brand == null){
            tvBrand.setText("???");
        }else {
            tvBrand.setText(brand.getName());
        }
        tvStock.setText(product.getAmount() + "");
        tvRateOther.setText(product.getRate() + "/5");



        btnRate.setOnClickListener(this);
        btnAddToCart.setOnClickListener(this);
        btnBuyNow.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        itemCartListener.passNumberItem(cartPresenterImp.getNumberItemCart(getContext()));
    }

    @Override
    public void loadListImageSuccessful(ArrayList<String> list) {
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
    public void loadListImageFalse() {

    }

    @Override
    public void loadListRateSuccessful(ArrayList<Rate> listRate) {
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
    public void loadListRateFalse() {

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
    public void loadListProductOtherSuccessful(ArrayList<Product> listProduct) {
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
        mListProduct.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListProduct.setAdapter(adapter);
    }

    @Override
    public void loadListProductOtherFalse() {

    }

    @Override
    public void loadLisProductSuggestSuccessful(ArrayList<Product> listSuggest) {
        ProductAdapter adapter = new ProductAdapter(getContext(), listSuggest, new ProductAdapter.ProductListener() {
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
        mListSuggest.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mListSuggest.setAdapter(adapter);
    }

    @Override
    public void loadListProductSuggestFalse() {

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
    public void loadInforShopSuccessful(final Shop shop) {
        MyHelper.setImagePicasso(getContext(), imgShop, Constants.Path.MY_PATH + shop.getImgAvatar());
        tvNameShop.setText(shop.getName());
        tvRateShop.setText(shop.getRate() + "");
        tvTotalProduct.setText("0");
        tvRateShop.setText(shop.getRate() + "");
        tvFrom.setText(shop.getAddress());
        btnViewShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShopActivity.class);
                intent.putExtra(Constants.MyTag.INTENT_SHOP, shop);
                startActivity(intent);
            }
        });


    }

    @Override
    public void loadInforShopFalse() {

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_rate:
                rate();
                break;
            case R.id.btn_add_to_cart:
                addToCart();
                break;
            case R.id.btn_buy_now:
                buyNow();
                break;
            default:
                break;
        }
    }

    private void addToCart(){
        if (product.getAmount() <= 0){
            Toast.makeText(getContext(), getContext().getString(R.string.out_of_stock), Toast.LENGTH_SHORT).show();
        }else {
            productPresenterImp.addToCart(getContext(), product);
            itemCartListener.passNumberItem(cartPresenterImp.getNumberItemCart(getContext()));
        }
    }

    private void buyNow(){
        if (product.getAmount() <= 0){
            Toast.makeText(getContext(), getContext().getString(R.string.out_of_stock), Toast.LENGTH_SHORT).show();
        }else {
            productPresenterImp.addToCart(getContext(), product);
            itemCartListener.passNumberItem(cartPresenterImp.getNumberItemCart(getContext()));
            startActivity(new Intent(getActivity(), CartActivity.class));
        }
    }

    private void viewShop(){

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
