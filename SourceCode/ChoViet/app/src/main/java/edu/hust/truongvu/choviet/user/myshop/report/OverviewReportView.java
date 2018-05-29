package edu.hust.truongvu.choviet.user.myshop.report;

import edu.hust.truongvu.choviet.model.entity.Shop;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;

/**
 * Created by truon on 5/24/2018.
 */

public interface OverviewReportView {
    void loadTotalShop(int number);
    void loadTotalProduct(int number);
    void loadTotalOrder(int number);

    void loadPieChartProductSuccessful(PieChartData data);
    void loadPieChartProductFalse();

    void loadPieChartOrderSuccessful(PieChartData data);
    void loadPieChartOrderFalse();

    void loadFirstShopSuccessful(Shop first);
    void loadSecondShopSuccessful(Shop second);
    void loadThirdShopSuccessful(Shop third);
    void loadFirstShopFalse();
    void loadSecondShopFalse();
    void loadThirdShopFalse();

    void loadRankShop(int rank);

    void loadPieChartBrandSuccessful(PieChartData data);
    void loadPieChartBrandFalse();

    void loadPieChartCategorySuccessful(PieChartData data);
    void loadPieChartCategoryFalse();

    void loadLineChartSuccessful(LineChartData data);
    void loadLineChartFalse();
}
