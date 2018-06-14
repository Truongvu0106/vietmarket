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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.cart.CartActivity;
import edu.hust.truongvu.choviet.category.CategoryPresenterImp;
import edu.hust.truongvu.choviet.product.list_product.ListProductActivity;
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
    private RatingBar ratingBar;
    private CategoryPresenterImp categoryPresenterImp;
    private ProductPresenterImp productPresenterImp;
    private CartPresenterImp cartPresenterImp;
    private RecyclerView mListRate, mListProduct, mListSuggest;
    private View btnRate, layoutDisableRate, layoutNoRate, btnAddToCart, btnViewShop,
            btnBuyNow, layoutDiscount, btnShare, btnViewOtherProduct, btnViewSuggestProduct;
    private ImageView imgShop;
    private TextView tvDisableRate, tvName, tvOldPrice, tvNewPrice, tvNameShop,
            tvRate, tvTotalProduct, tvRateShop, tvDescription, tvCategory, tvWeight,
            tvBrand, tvStock, tvFrom, tvRateOther, tvDiscount, tvNumLike, tvNumRate;
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
        btnShare = view.findViewById(R.id.btn_share);
        btnViewOtherProduct = view.findViewById(R.id.btn_view_other_product);
        btnViewSuggestProduct = view.findViewById(R.id.btn_view_suggest_product);
        layoutDisableRate = view.findViewById(R.id.layout_disable_rate);
        layoutNoRate = view.findViewById(R.id.layout_no_rate);
        tvDisableRate = view.findViewById(R.id.tv_disable_rate);
        ratingBar = view.findViewById(R.id.rating_bar);

        username = MyHelper.getUserNamePreference(getContext());
        cartPresenterImp = new CartPresenterImp();
        productPresenterImp = new ProductPresenterImp(getContext(), this);
        categoryPresenterImp = new CategoryPresenterImp(getContext());
        productPresenterImp.initListImage(product.getImgs());
        productPresenterImp.initListProductOther(product.getIdShop());
        productPresenterImp.initListRate(username, product.getId());
        productPresenterImp.initListProductSuggest(product.getTypeProduct());

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
        tvNumLike = view.findViewById(R.id.tv_num_like);
        tvNumRate = view.findViewById(R.id.tv_num_rate);
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
        ratingBar.setRating(product.getRate());
        tvDescription.setText(product.getInfomation());
        tvCategory.setText(product.getTypeProduct() + "");
        tvNumLike.setText(productPresenterImp.getNumLike(product.getId()) + "");
        tvNumRate.setText(productPresenterImp.getNumRate(product.getId()) + "");

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
        btnShare.setOnClickListener(this);
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
    public void loadListProductOtherSuccessful(final ArrayList<Product> listProduct) {
        ArrayList<Product> listShow = new ArrayList<>();
        if (listProduct.size() < 4){
            listShow = listProduct;
        }else {
            for (int i = 0; i < 4; i++){
                listShow.add(listProduct.get(i));
            }
        }
        ProductAdapter adapter = new ProductAdapter(getContext(), listShow, new ProductAdapter.ProductListener() {
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
        btnViewOtherProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), ListProductActivity.class);
                intent1.putExtra(Constants.MyTag.INTENT_LIST_PRODUCT, listProduct);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void loadListProductOtherFalse() {

    }

    @Override
    public void loadLisProductSuggestSuccessful(final ArrayList<Product> listSuggest) {
        ArrayList<Product> listShow = new ArrayList<>();
        if (listSuggest.size() < 4){
            listShow = listSuggest;
        }else {
            for (int i = 0; i < 4; i++){
                listShow.add(listSuggest.get(i));
            }
        }
        ProductAdapter adapter = new ProductAdapter(getContext(), listShow, new ProductAdapter.ProductListener() {
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

        btnViewSuggestProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), ListProductActivity.class);
                intent1.putExtra(Constants.MyTag.INTENT_LIST_PRODUCT, listSuggest);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void loadListProductSuggestFalse() {

    }

    @Override
    public void addToCartSuccessful() {
        MyHelper.showToast(getContext(), getContext().getString(R.string.added_to_your_cart), FancyToast.SUCCESS);
    }

    @Override
    public void addToCartFalse() {
        MyHelper.showToast(getContext(), getContext().getString(R.string.already_in_your_cart), FancyToast.ERROR);
    }

    @Override
    public void loadInforShopSuccessful(final Shop shop) {
        MyHelper.setImagePicasso(getContext(), imgShop, Constants.MY_PATH + shop.getImgAvatar());
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
    public void updateRateSuccessful(float newRate) {
        tvRate.setText(newRate + "/5");
        tvRateOther.setText(newRate + "/5");
        ratingBar.setRating(newRate);
    }

    @Override
    public void updateRateFalse() {

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
            case R.id.btn_share:
                share(product);
                break;
            default:
                break;
        }
    }

    private void share(Product product){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, product.getName());
        share.putExtra(Intent.EXTRA_TEXT, product.getName() + " - " + MyHelper.formatMoney(product.getPrice()));

        startActivity(Intent.createChooser(share, "Share link!"));
    }

    private void addToCart(){
        if (product.getAmount() <= 0){
            MyHelper.showToast(getContext(), getContext().getString(R.string.out_of_stock), FancyToast.ERROR);
        }else {
            productPresenterImp.addToCart(getContext(), product);
            itemCartListener.passNumberItem(cartPresenterImp.getNumberItemCart(getContext()));
        }
    }

    private void buyNow(){
        if (product.getAmount() <= 0){
            MyHelper.showToast(getContext(), getContext().getString(R.string.out_of_stock), FancyToast.ERROR);
        }else {
            productPresenterImp.addToCart(getContext(), product);
            itemCartListener.passNumberItem(cartPresenterImp.getNumberItemCart(getContext()));
            startActivity(new Intent(getActivity(), CartActivity.class));
        }
    }


    private void rate(){
        User user = MyHelper.getCurrentUser(getContext());
        if (user == null){
            MyHelper.showToast(getContext(), getContext().getString(R.string.not_log_in), FancyToast.WARNING);
        }else {
            RateProductDialog rateDialog = new RateProductDialog(getContext(), product.getId(), user, new RateProductDialog.RateProductListener() {
                @Override
                public void onRate(Rate rate) {
                    if (productPresenterImp.addRate(rate)){
                        productPresenterImp.initListRate(username, product.getId());
                        productPresenterImp.updateRate(rate);
                    }

                }
            });
            rateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            rateDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            rateDialog.show();
        }

    }
}
