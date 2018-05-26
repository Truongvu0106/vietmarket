package edu.hust.truongvu.choviet.user.myshop.report;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.user.myshop.list_order.ShopListOrderPresenterImp;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by truon on 5/9/2018.
 */

public class ShopReportPresenterImp implements ShopReportPresenter {
    private Context mContext;
    private ShopReportView shopReportView;
    private ProductModel productModel;
    float[][] randomNumbersTab = new float[1][12];

    public ShopReportPresenterImp(Context context, ShopReportView shopReportView){
        this.mContext = context;
        this.shopReportView = shopReportView;
        productModel = new ProductModel(mContext);
    }

    @Override
    public void initTopProduct(int idShop) {
        ArrayList<Product> products = productModel.getProductByIdShop(idShop);
        if (products == null || products.isEmpty()){
            shopReportView.loadBestSellProductFalse();
            shopReportView.loadMostLikeProductFalse();
            shopReportView.loadTopRateProductFalse();
            return;
        }
        Product product = products.get(0);
        Product product1 = products.get(1);
        if (product == null || product1 == null){
            shopReportView.loadBestSellProductFalse();
            shopReportView.loadMostLikeProductFalse();
            shopReportView.loadTopRateProductFalse();
        }else {
            shopReportView.loadBestSellProductSucessful(product);
            shopReportView.loadMostLikeProductSucessful(product1);
            shopReportView.loadTopRateProductSucessful(product);
        }

    }

    @Override
    public void initPieChartBrand(int idShop) {
        int numValues = 6;
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            sliceValue.setLabel("abc");
            values.add(sliceValue);
        }
        PieChartData data = new PieChartData(values);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(true);

        if (data != null){
            shopReportView.loadPieChartBrandSucessful(data);
        }else {
            shopReportView.loadPieChartBrandFalse();
        }

    }

    @Override
    public void initPieChartCategory(int idShop) {
        int numValues = 5;
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            sliceValue.setLabel("def");
            values.add(sliceValue);
        }
        PieChartData data = new PieChartData(values);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(true);

        if (data != null){
            shopReportView.loadPieChartCategorySucessful(data);
        }else {
            shopReportView.loadPieChartCategoryFalse();
        }
    }

    @Override
    public void initLineChart(int idShop) {
        List<Line> lines = new ArrayList<Line>();
        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < 12; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }

        for (int i = 0; i < 1; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < 12; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(ValueShape.CIRCLE);
            line.setCubic(true);
            line.setFilled(true);
            line.setHasLabels(false);
            line.setHasLines(true);
            line.setHasPoints(true);
            line.setHasLabelsOnlyForSelected(true);
            line.setPointColor(ChartUtils.COLORS[i]);
            lines.add(line);
        }

        LineChartData data = new LineChartData(lines);
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Months");
        axisY.setName("Number of Order");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        if (data != null){
            shopReportView.loadLineChartSucessful(data);
        }else {
            shopReportView.loadLineChartFalse();
        }
    }
}
