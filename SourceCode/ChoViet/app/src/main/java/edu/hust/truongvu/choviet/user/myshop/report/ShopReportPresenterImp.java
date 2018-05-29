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
 * Created by truon on 5/9/2018.
 */

public class ShopReportPresenterImp implements ReportPresenter {

    private Context mContext;
    private ShopReportView shopReportView;
    private ProductModel productModel;
    private OrderModel orderModel;
    private ShopModel shopModel;
    private BrandModel brandModel;
    private CategoryModel categoryModel;
    private ArrayList<Product> mListProducts = new ArrayList<>();
    private int idShop;
    private DateHelper dateHelper;

    public ShopReportPresenterImp(Context context, ShopReportView shopReportView, int idShop) {
        this.mContext = context;
        this.shopReportView = shopReportView;
        this.idShop = idShop;
        productModel = new ProductModel(mContext);
        orderModel = new OrderModel(mContext);
        shopModel = new ShopModel(mContext);
        brandModel = new BrandModel(mContext);
        categoryModel = new CategoryModel(mContext);
        mListProducts = productModel.getProductByIdShop(idShop);
        dateHelper = new DateHelper();
    }

    @Override
    public void initTopProduct() {
        if (mListProducts == null || mListProducts.isEmpty()) {
            shopReportView.loadBestSellProductFalse();
            shopReportView.loadMostLikeProductFalse();
            shopReportView.loadTopRateProductFalse();
            return;
        }

        //Top rate
        Product topRateProduct = mListProducts.get(0);
        for (Product product : mListProducts) {
            if (product.getRate() > topRateProduct.getRate()) {
                topRateProduct = product;
            }
        }
        shopReportView.loadTopRateProductSucessful(topRateProduct);

        //Most like
        Product topLikeProduct = getTopProductLike(mListProducts);
        if (topLikeProduct == null) {
            shopReportView.loadMostLikeProductFalse();
        } else {
            shopReportView.loadMostLikeProductSucessful(topLikeProduct);
        }

        //Best sell
        Product bestSell = getTopProductOrder(mListProducts);
        if (bestSell == null) {
            shopReportView.loadBestSellProductFalse();
        } else {
            shopReportView.loadBestSellProductSucessful(bestSell);
        }
    }

    private Product getTopProductLike(ArrayList<Product> data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        for (Product product : data) {
            int number = productModel.countLikeProduct(product.getId());
            product.setCountLike(number);
        }
        Product topLikeProduct = data.get(0);
        for (Product product : data) {
            if (product.getCountLike() > topLikeProduct.getCountLike()) {
                topLikeProduct = product;
            }
        }

        if (topLikeProduct.getCountLike() == 0) {
            return null;
        } else {
            return topLikeProduct;
        }

    }

    private Product getTopProductOrder(ArrayList<Product> data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        for (Product product : data) {
            int number = orderModel.countProductInOrder(idShop, product.getId());
            product.setCountInOrder(number);
        }
        Product topInOrderProduct = data.get(0);
        for (Product product : data) {
            if (product.getCountInOrder() > topInOrderProduct.getCountInOrder()) {
                topInOrderProduct = product;
            }
        }
        if (topInOrderProduct.getCountInOrder() == 0) {
            return null;
        } else {
            return topInOrderProduct;
        }
    }

    @Override
    public void initTotalProduct() {
        shopReportView.loadTotalProduct(mListProducts.size());
    }

    @Override
    public void initNumberFollow() {
        shopReportView.loadNumberFollow(shopModel.getNumberCustomerFollowing(idShop));
    }

    @Override
    public void initRate() {
        Shop shop = shopModel.getShopById(idShop);
        if (shop == null) {
            shopReportView.loadRate(0);
        } else {
            shopReportView.loadRate(shop.getRate());
        }
    }

    @Override
    public void initTotalShop() {

    }

    @Override
    public void initTotalOrder() {

    }

    @Override
    public void initPieChartProduct() {

    }

    @Override
    public void initPieChartOrder() {

    }

    @Override
    public void initTopShop() {

    }

    @Override
    public void initRankShop() {

    }

    @Override
    public void initPieChartBrand() {
        ArrayList<Brand> listBrand = brandModel.getListBrand();
        for (Brand brand : listBrand) {
            int numberProduct = productModel.countProductByBrand(idShop, brand.getId());
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
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) listBrand.get(i).getNumberProduct(), ChartUtils.pickColor());
            sliceValue.setLabel(listBrand.get(i).getName() + ": " + listBrand.get(i).getNumberProduct());
            values.add(sliceValue);
        }
        PieChartData data = new PieChartData(values);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(true);

        if (data != null) {
            shopReportView.loadPieChartBrandSucessful(data);
        } else {
            shopReportView.loadPieChartBrandFalse();
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
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) childCategories.get(i).getNumberProduct(), ChartUtils.pickColor());
            sliceValue.setLabel(childCategories.get(i).getName() + ": " + childCategories.get(i).getNumberProduct());
            values.add(sliceValue);
        }
        PieChartData data = new PieChartData(values);
        data.setHasLabelsOnlyForSelected(true);
        data.setHasCenterCircle(true);

        if (data != null) {
            shopReportView.loadPieChartCategorySucessful(data);
        } else {
            shopReportView.loadPieChartCategoryFalse();
        }
    }

    @Override
    public void initLineChart() {
        ArrayList<Integer> listCount = new ArrayList<>();
        ArrayList<Long> listStarts = dateHelper.getListFirstDate();
        ArrayList<Long> listEnds = dateHelper.getListLastDate();

        for (int i = 0; i < listStarts.size(); i++){
            int number = orderModel.countOrdeByTime(idShop, listStarts.get(i), listEnds.get(i));
            listCount.add(number);
        }
        List<Line> lines = new ArrayList<Line>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int j = 0; j < listCount.size(); j++) {
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
            shopReportView.loadLineChartSucessful(data);
        } else {
            shopReportView.loadLineChartFalse();
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
