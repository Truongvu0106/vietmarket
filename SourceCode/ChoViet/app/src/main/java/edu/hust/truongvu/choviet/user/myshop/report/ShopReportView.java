package edu.hust.truongvu.choviet.user.myshop.report;

import edu.hust.truongvu.choviet.model.entity.Product;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;

/**
 * Created by truon on 5/9/2018.
 */

public interface ShopReportView {
    void loadTopRateProductSucessful(Product product);
    void loadTopRateProductFalse();

    void loadMostLikeProductSucessful(Product product);
    void loadMostLikeProductFalse();

    void loadBestSellProductSucessful(Product product);
    void loadBestSellProductFalse();

    void loadPieChartBrandSucessful(PieChartData data);
    void loadPieChartBrandFalse();

    void loadPieChartCategorySucessful(PieChartData data);
    void loadPieChartCategoryFalse();

    void loadLineChartSucessful(LineChartData data);
    void loadLineChartFalse();
}
