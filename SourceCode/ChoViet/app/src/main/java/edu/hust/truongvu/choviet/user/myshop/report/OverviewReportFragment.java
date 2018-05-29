package edu.hust.truongvu.choviet.user.myshop.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.entity.Shop;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewReportFragment extends Fragment implements OverviewReportView{
    private TextView tvTotalProduct, tvTotalShop,
            tvTotalOrder, tvFirst, tvSecond, tvThird, tvYourShopRank;
    private PieChartView pieChartProduct, pieChartOrder, pieChartBrand, pieChartCategory;
    private LineChartView lineChartView;
    private ReportPresenter reportPresenter;
    private int idShop;

    public OverviewReportFragment() {
        // Required empty public constructor
    }

    public static OverviewReportFragment getInstance(int idShop){
        OverviewReportFragment fragment = new OverviewReportFragment();
        fragment.idShop = idShop;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview_report, container, false);
        initView(view);
        reportPresenter = new OverviewReportPresenterImp(getContext(), this, idShop);
        reportPresenter.initTotalProduct();
        reportPresenter.initTotalShop();
        reportPresenter.initTotalOrder();
        reportPresenter.initPieChartProduct();
        reportPresenter.initPieChartOrder();
        reportPresenter.initTopShop();
        reportPresenter.initRankShop();
        reportPresenter.initPieChartBrand();
        reportPresenter.initPieChartCategory();
        reportPresenter.initLineChart();

        return view;
    }

    private void initView(View view){
        tvTotalShop = view.findViewById(R.id.tv_total_shop);
        tvTotalProduct = view.findViewById(R.id.total_product);
        tvTotalOrder = view.findViewById(R.id.total_order);
        tvFirst = view.findViewById(R.id.tv_first);
        tvSecond = view.findViewById(R.id.tv_second);
        tvThird = view.findViewById(R.id.tv_third);
        tvYourShopRank = view.findViewById(R.id.tv_your_shop_rank);
        pieChartProduct = view.findViewById(R.id.pieChartProduct);
        pieChartOrder = view.findViewById(R.id.pieChartOrder);
        pieChartBrand = view.findViewById(R.id.chart_brand);
        pieChartCategory = view.findViewById(R.id.chart_category);
        lineChartView = view.findViewById(R.id.chart_line);
    }

    @Override
    public void loadTotalShop(int number) {
        tvTotalShop.setText(getContext().getString(R.string.total_shop) + ": " + number);
    }

    @Override
    public void loadTotalProduct(int number) {
        tvTotalProduct.setText(number + "");
    }

    @Override
    public void loadTotalOrder(int number) {
        tvTotalOrder.setText(number + "");
    }

    @Override
    public void loadPieChartProductSuccessful(PieChartData data) {
        pieChartProduct.setPieChartData(data);
    }

    @Override
    public void loadPieChartProductFalse() {

    }

    @Override
    public void loadPieChartOrderSuccessful(PieChartData data) {
        pieChartOrder.setPieChartData(data);
    }

    @Override
    public void loadPieChartOrderFalse() {

    }

    @Override
    public void loadFirstShopSuccessful(Shop first) {
        tvFirst.setText(first.getName());
    }

    @Override
    public void loadSecondShopSuccessful(Shop second) {
        tvSecond.setText(second.getName());

    }

    @Override
    public void loadThirdShopSuccessful(Shop third) {
        tvThird.setText(third.getName());
    }

    @Override
    public void loadFirstShopFalse() {
        tvFirst.setText(getContext().getString(R.string.no_data));
    }

    @Override
    public void loadSecondShopFalse() {
        tvSecond.setText(getContext().getString(R.string.no_data));
    }

    @Override
    public void loadThirdShopFalse() {
        tvThird.setText(getContext().getString(R.string.no_data));
    }

    @Override
    public void loadRankShop(int rank) {
        tvYourShopRank.setText(getString(R.string.your_shop_rank) + ": #" + rank);
    }

    @Override
    public void loadPieChartBrandSuccessful(PieChartData data) {
        pieChartBrand.setPieChartData(data);
    }

    @Override
    public void loadPieChartBrandFalse() {

    }

    @Override
    public void loadPieChartCategorySuccessful(PieChartData data) {
        pieChartCategory.setPieChartData(data);
    }

    @Override
    public void loadPieChartCategoryFalse() {

    }

    @Override
    public void loadLineChartSuccessful(LineChartData data) {
        lineChartView.setZoomEnabled(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
        lineChartView.setLineChartData(data);
    }

    @Override
    public void loadLineChartFalse() {

    }
}
