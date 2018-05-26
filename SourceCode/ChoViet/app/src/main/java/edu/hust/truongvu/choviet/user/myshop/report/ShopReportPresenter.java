package edu.hust.truongvu.choviet.user.myshop.report;

import edu.hust.truongvu.choviet.model.entity.Product;

/**
 * Created by truon on 5/9/2018.
 */

public interface ShopReportPresenter {
    void initTopProduct(int idShop);

    void initPieChartBrand(int idShop);
    void initPieChartCategory(int idShop);

    void initLineChart(int idShop);
}
