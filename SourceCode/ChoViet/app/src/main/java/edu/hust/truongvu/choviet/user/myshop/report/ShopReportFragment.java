package edu.hust.truongvu.choviet.user.myshop.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.Product;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopReportFragment extends Fragment implements ShopReportView{
    private ImageView imgToprate, imgMostLike, imgBestSell;
    private TextView tvToprate, tvMostlike, tvBestsell, tvTotalProduct, tvNumberFollow, tvRate;
    private PieChartView pieChartBrand, pieChartCategory;
    private LineChartView lineChartView;
    private ReportPresenter reportPresenter;
    private int idShop;

    public static ShopReportFragment getInstance(int idShop){
        ShopReportFragment fragment = new ShopReportFragment();
        fragment.idShop = idShop;
        return fragment;
    }

    public ShopReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_report, container, false);
        initView(view);
        reportPresenter = new ShopReportPresenterImp(getContext(), this, idShop);
        reportPresenter.initTotalProduct();
        reportPresenter.initRate();
        reportPresenter.initNumberFollow();
        reportPresenter.initLineChart();
        reportPresenter.initTopProduct();
        reportPresenter.initPieChartBrand();
        reportPresenter.initPieChartCategory();
        return view;
    }

    private void initView(View view){
        imgToprate = view.findViewById(R.id.img_toprate);
        imgMostLike = view.findViewById(R.id.img_mostlike);
        imgBestSell = view.findViewById(R.id.img_bestsell);
        tvToprate = view.findViewById(R.id.name_toprate);
        tvMostlike = view.findViewById(R.id.name_mostlike);
        tvBestsell = view.findViewById(R.id.name_bestsell);
        tvTotalProduct = view.findViewById(R.id.tv_total_product);
        tvNumberFollow = view.findViewById(R.id.tv_user_follow);
        tvRate = view.findViewById(R.id.tv_rate);

        pieChartBrand = view.findViewById(R.id.chart_brand);
        pieChartCategory = view.findViewById(R.id.chart_category);
        lineChartView = view.findViewById(R.id.chart_line);
    }

    @Override
    public void loadTotalProduct(int number) {
        tvTotalProduct.setText(getString(R.string.total_product) + ": " + number);
    }

    @Override
    public void loadRate(float rate) {
        tvRate.setText(rate + "");
    }

    @Override
    public void loadNumberFollow(int number) {
        tvNumberFollow.setText(number + "");
    }

    @Override
    public void loadTopRateProductSucessful(Product product) {
        MyHelper.setImagePicasso(getContext(), imgToprate, Constants.Path.MY_PATH + product.getImgs().get(0));
        tvToprate.setText(product.getName());
    }

    @Override
    public void loadTopRateProductFalse() {
        imgToprate.setImageResource(R.drawable.img_error);
        tvToprate.setText(getString(R.string.no_data));
    }

    @Override
    public void loadMostLikeProductSucessful(Product product) {
        MyHelper.setImagePicasso(getContext(), imgMostLike, Constants.Path.MY_PATH + product.getImgs().get(0));
        tvMostlike.setText(product.getName());
    }

    @Override
    public void loadMostLikeProductFalse() {
        imgMostLike.setImageResource(R.drawable.img_error);
        tvMostlike.setText(getString(R.string.no_data));
    }

    @Override
    public void loadBestSellProductSucessful(Product product) {
        MyHelper.setImagePicasso(getContext(), imgBestSell, Constants.Path.MY_PATH + product.getImgs().get(0));
        tvBestsell.setText(product.getName());
    }

    @Override
    public void loadBestSellProductFalse() {
        imgBestSell.setImageResource(R.drawable.img_error);
        tvBestsell.setText(getString(R.string.no_data));
    }

    @Override
    public void loadPieChartBrandSucessful(PieChartData data) {
        pieChartBrand.setValueSelectionEnabled(true);
        pieChartBrand.setPieChartData(data);
    }

    @Override
    public void loadPieChartBrandFalse() {

    }

    @Override
    public void loadPieChartCategorySucessful(PieChartData data) {
        pieChartCategory.setValueSelectionEnabled(true);
        pieChartCategory.setPieChartData(data);
    }

    @Override
    public void loadPieChartCategoryFalse() {

    }

    @Override
    public void loadLineChartSucessful(LineChartData data) {
        lineChartView.setZoomEnabled(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        lineChartView.setLineChartData(data);

    }

    @Override
    public void loadLineChartFalse() {

    }
}
