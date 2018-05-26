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
    private TextView tvToprate, tvMostlike, tvBestsell;
    private PieChartView pieChartBrand, pieChartCategory;
    private LineChartView lineChartView;
    private ShopReportPresenter shopReportPresenter;
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
        shopReportPresenter = new ShopReportPresenterImp(getContext(), this);
        shopReportPresenter.initLineChart(idShop);
        shopReportPresenter.initTopProduct(idShop);
        shopReportPresenter.initPieChartBrand(idShop);
        shopReportPresenter.initPieChartCategory(idShop);
        return view;
    }

    private void initView(View view){
        imgToprate = view.findViewById(R.id.img_toprate);
        imgMostLike = view.findViewById(R.id.img_mostlike);
        imgBestSell = view.findViewById(R.id.img_bestsell);
        tvToprate = view.findViewById(R.id.name_toprate);
        tvMostlike = view.findViewById(R.id.name_mostlike);
        tvBestsell = view.findViewById(R.id.name_bestsell);

        pieChartBrand = view.findViewById(R.id.chart_brand);
        pieChartCategory = view.findViewById(R.id.chart_category);
        lineChartView = view.findViewById(R.id.chart_line);
    }

    @Override
    public void loadTopRateProductSucessful(Product product) {
        MyHelper.setImagePicasso(getContext(), imgToprate, Constants.Path.MY_PATH + product.getImgs().get(0));
        tvToprate.setText(product.getName());
    }

    @Override
    public void loadTopRateProductFalse() {

    }

    @Override
    public void loadMostLikeProductSucessful(Product product) {
        MyHelper.setImagePicasso(getContext(), imgMostLike, Constants.Path.MY_PATH + product.getImgs().get(0));
        tvMostlike.setText(product.getName());
    }

    @Override
    public void loadMostLikeProductFalse() {

    }

    @Override
    public void loadBestSellProductSucessful(Product product) {
        MyHelper.setImagePicasso(getContext(), imgBestSell, Constants.Path.MY_PATH + product.getImgs().get(0));
        tvBestsell.setText(product.getName());
    }

    @Override
    public void loadBestSellProductFalse() {

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
        lineChartView.setLineChartData(data);
        lineChartView.setZoomEnabled(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);
    }

    @Override
    public void loadLineChartFalse() {

    }
}
