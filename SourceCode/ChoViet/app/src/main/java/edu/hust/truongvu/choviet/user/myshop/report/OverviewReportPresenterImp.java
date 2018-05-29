package edu.hust.truongvu.choviet.user.myshop.report;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.DateHelper;
import edu.hust.truongvu.choviet.model.BrandModel;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.OrderModel;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.ShopModel;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.Order;
import edu.hust.truongvu.choviet.model.entity.OrderDetails;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Shop;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;

/**
 * Created by truon on 5/24/2018.
 */

public class OverviewReportPresenterImp implements ReportPresenter {

    private Context mContext;
    private OverviewReportView overviewReportView;
    private ProductModel productModel;
    private OrderModel orderModel;
    private ShopModel shopModel;
    private BrandModel brandModel;
    private CategoryModel categoryModel;
    private ArrayList<Product> mListShopProducts = new ArrayList<>();
    private ArrayList<Product> mListTotalProducts = new ArrayList<>();
    private ArrayList<Order> mListTotalOrders = new ArrayList<>();
    private ArrayList<OrderDetails> mListShopOrder = new ArrayList<>();
    private ArrayList<Shop> mListTopShop = new ArrayList<>();
    private int idShop;
    private DateHelper dateHelper;

    public OverviewReportPresenterImp(Context context, OverviewReportView overviewReportView, int idShop) {
        this.mContext = context;
        this.overviewReportView = overviewReportView;
        this.idShop = idShop;
        productModel = new ProductModel(mContext);
        orderModel = new OrderModel(mContext);
        shopModel = new ShopModel(mContext);
        brandModel = new BrandModel(mContext);
        categoryModel = new CategoryModel(mContext);
        mListShopProducts = productModel.getProductByIdShop(idShop);
        mListShopOrder = orderModel.getDetailsOrderByShop(idShop);
        mListTotalProducts = productModel.getAllProduct();
        mListTotalOrders = orderModel.getAllOrder();
        mListTopShop = shopModel.getTopShop();
        dateHelper = new DateHelper();
    }

    @Override
    public void initTopProduct() {

    }

    @Override
    public void initTotalProduct() {
        ArrayList<Product> products = productModel.getAllProduct();
        overviewReportView.loadTotalProduct(products.size());
    }

    @Override
    public void initNumberFollow() {

    }

    @Override
    public void initRate() {

    }

    @Override
    public void initTotalShop() {
        overviewReportView.loadTotalShop(mListTopShop.size());
    }

    @Override
    public void initTotalOrder() {
        overviewReportView.loadTotalOrder(mListTotalOrders.size());
    }

    @Override
    public void initPieChartProduct() {
        List<SliceValue> values = new ArrayList<SliceValue>();
        SliceValue sliceValue1 = new SliceValue((float) mListShopProducts.size(), ChartUtils.pickColor());

        SliceValue sliceValue2 = new SliceValue((float) (mListTotalProducts.size() - mListShopProducts.size()), ChartUtils.pickColor());
        values.add(sliceValue1);
        values.add(sliceValue2);
        PieChartData data = new PieChartData(values);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(false);

        if (data != null) {
            overviewReportView.loadPieChartProductSuccessful(data);
        } else {
            overviewReportView.loadPieChartProductFalse();
        }
    }

    @Override
    public void initPieChartOrder() {
        List<SliceValue> values = new ArrayList<SliceValue>();
        SliceValue sliceValue1 = new SliceValue((float) mListShopOrder.size(), ChartUtils.pickColor());

        SliceValue sliceValue2 = new SliceValue((float) mListTotalOrders.size(), ChartUtils.pickColor());
        values.add(sliceValue1);
        values.add(sliceValue2);
        PieChartData data = new PieChartData(values);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(false);

        if (data != null) {
            overviewReportView.loadPieChartOrderSuccessful(data);
        } else {
            overviewReportView.loadPieChartOrderFalse();
        }
    }

    @Override
    public void initTopShop() {
        if (mListTopShop == null || mListTopShop.isEmpty()){
            overviewReportView.loadFirstShopFalse();
            overviewReportView.loadSecondShopFalse();
            overviewReportView.loadThirdShopFalse();
            return;
        }
        if (mListTopShop.size() == 1){
            overviewReportView.loadFirstShopSuccessful(mListTopShop.get(0));
            overviewReportView.loadSecondShopFalse();
            overviewReportView.loadThirdShopFalse();
            return;
        }

        if (mListTopShop.size() == 2){
            overviewReportView.loadFirstShopSuccessful(mListTopShop.get(0));
            overviewReportView.loadSecondShopSuccessful(mListTopShop.get(1));
            overviewReportView.loadThirdShopFalse();
            return;
        }

        if (mListTopShop.size() >= 3){
            overviewReportView.loadFirstShopSuccessful(mListTopShop.get(0));
            overviewReportView.loadSecondShopSuccessful(mListTopShop.get(1));
            overviewReportView.loadThirdShopSuccessful(mListTopShop.get(2));
            return;
        }

    }

    @Override
    public void initRankShop() {
        int rank = 0;
        for (int i = 0; i < mListTopShop.size(); i++){
            if (mListTopShop.get(i).getId() == idShop){
                rank = i;
                break;
            }
        }
        overviewReportView.loadRankShop(rank+1);
    }

    @Override
    public void initPieChartBrand() {
        ArrayList<Brand> listBrand = brandModel.getListBrand();
        for (Brand brand : listBrand) {
            int numberProduct = productModel.countProductByBrand(0, brand.getId());
            brand.setNumberProduct(numberProduct);
        }

        Collections.sort(listBrand, new Comparator<Brand>() {
            @Override
            public int compare(Brand brand, Brand b1) {
                int numberProduct = brand.getNumberProduct();
                int numberB1 = b1.getNumberProduct();
                return (int) (numberB1 - numberProduct);
            }
        });

        int numValues = 4;
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < listBrand.size(); ++i) {
            SliceValue sliceValue = new SliceValue((float) listBrand.get(i).getNumberProduct(), ChartUtils.pickColor());
            sliceValue.setLabel(listBrand.get(i).getName() + ": " + listBrand.get(i).getNumberProduct());
            values.add(sliceValue);
        }
        PieChartData data = new PieChartData(values);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(true);

        if (data != null) {
            overviewReportView.loadPieChartBrandSuccessful(data);
        } else {
            overviewReportView.loadPieChartBrandFalse();
        }
    }

    @Override
    public void initPieChartCategory() {
        ArrayList<ChildCategory> childCategories = categoryModel.getListChildCategory();
        for (ChildCategory child : childCategories) {
            int numberProduct = productModel.countProductByCategory(idShop, child.getId());
            child.setNumberProduct(numberProduct);
        }

        Collections.sort(childCategories, new Comparator<ChildCategory>() {
            @Override
            public int compare(ChildCategory child, ChildCategory c1) {
                int numberProduct = child.getNumberProduct();
                int numberc1 = c1.getNumberProduct();
                return (int) (numberc1 - numberProduct);
            }
        });

        int numValues = 4;
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < childCategories.size(); ++i) {
            SliceValue sliceValue = new SliceValue((float) childCategories.get(i).getNumberProduct(), ChartUtils.pickColor());
            sliceValue.setLabel(childCategories.get(i).getName() + ": " + childCategories.get(i).getNumberProduct());
            values.add(sliceValue);
        }
        PieChartData data = new PieChartData(values);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(true);

        if (data != null) {
            overviewReportView.loadPieChartCategorySuccessful(data);
        } else {
            overviewReportView.loadPieChartCategoryFalse();
        }
    }

    @Override
    public void initLineChart() {
        ArrayList<Integer> listCount = new ArrayList<>();
        ArrayList<Long> listStarts = dateHelper.getListFirstDate();
        ArrayList<Long> listEnds = dateHelper.getListLastDate();

        for (int i = 0; i < listStarts.size(); i++){
            int number = orderModel.countOrdeByTime(0, listStarts.get(i), listEnds.get(i));
            listCount.add(number);
        }
        Log.e("count", listCount.size() + "");
        List<Line> lines = new ArrayList<Line>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int j = 0; j < 12; j++) {
            values.add(new PointValue(j, listCount.get(j)));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLORS[0]);
        line.setShape(ValueShape.CIRCLE);
        line.setCubic(true);
        line.setFilled(true);
        line.setHasLabels(true);
        line.setHasLines(true);
        line.setHasPoints(true);
        line.setHasLabelsOnlyForSelected(true);
        line.setPointColor(ChartUtils.COLORS[0]);
        lines.add(line);

        LineChartData data = new LineChartData(lines);
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);

        setupAxis(axisX, axisY);

        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        if (data != null) {
            overviewReportView.loadLineChartSuccessful(data);
        } else {
            overviewReportView.loadLineChartFalse();
        }
    }

    private void setupAxis(Axis axisX, Axis axisY){
        axisX.setName(mContext.getString(R.string.month));
        axisY.setName(mContext.getString(R.string.number_order));

        String[] months = {
                mContext.getString(R.string.January),
                mContext.getString(R.string.February),
                mContext.getString(R.string.March),
                mContext.getString(R.string.April),
                mContext.getString(R.string.May),
                mContext.getString(R.string.June),
                mContext.getString(R.string.July),
                mContext.getString(R.string.August),
                mContext.getString(R.string.September),
                mContext.getString(R.string.October),
                mContext.getString(R.string.November),
                mContext.getString(R.string.December)};
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < months.length; i++) {
            AxisValue axisValue = new AxisValue(i).setLabel(months[i]);
            axisValues.add(axisValue);
        }
        axisX.setValues(axisValues);
    }
}
