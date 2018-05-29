package edu.hust.truongvu.choviet.user.myshop.report;

import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/9/2018.
 */

public interface ReportPresenter {
    // Shop report
    void initTopProduct();

    void initNumberFollow();
    void initRate();

    // Overview report
    void initTotalShop();
    void initTotalOrder();
    void initPieChartProduct();
    void initPieChartOrder();
    void initTopShop();
    void initRankShop();

    void initPieChartBrand();
    void initPieChartCategory();
    void initLineChart();
    void initTotalProduct();
}
