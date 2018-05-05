package edu.hust.truongvu.choviet.product;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.hust.truongvu.choviet.entity.PriceFilter;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/8/2018.
 */

public class ListProductPresenterImp implements ListProductPresenter {
    private ListProductView listProductView;
    private ProductModel productModel;
    private Context mContext;

    public ListProductPresenterImp(Context context, ListProductView listProductView){
        this.listProductView = listProductView;
        this.mContext = context;
        productModel = new ProductModel(mContext);
    }

    public ArrayList<Product> getProductByCategory(int idCategory){
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByCategory(idCategory);
        return data;
    }

    public ArrayList<Product> getProductByBrand(int idBrand){
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByBrand(idBrand);
        return data;
    }


    @Override
    public void initListProductByCategory(boolean isLastest, int idCategory) {
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByCategory(idCategory);
        if (data == null || data.size() == 0){
            listProductView.loadListFalse();
        }else {
            listProductView.loadListSuccessful(isLastest, data);
        }
    }

    @Override
    public void initListProductByBrand(boolean isLastest, int idBrand) {
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByBrand(idBrand);
        if (data == null || data.size() == 0){
            listProductView.loadListFalse();
        }else {
            listProductView.loadListSuccessful(isLastest, data);
        }
    }

    @Override
    public void initListProductByShop(boolean isLastest, int idShop) {
        ArrayList<Product> data = new ArrayList<>();
        data = productModel.getProductByIdShop(idShop);
        if (data == null || data.size() == 0){
            listProductView.loadListFalse();
        }else {
            listProductView.loadListSuccessful(isLastest, data);
        }
    }

    @Override
    public void initListProductByData(ArrayList<Product> data) {
        if (data == null || data.size() == 0){
            listProductView.loadListFalse();
        }else {
            listProductView.loadListSuccessful(false, data);
        }
    }

    @Override
    public void initListProductByOptions(boolean isLastest, int sortByPrice, int idBrand, int idCategory,
                                         PriceFilter priceFilter, ArrayList<Product> data) {

        Log.e("tr_brand", idBrand + "");
        Log.e("tr_cate", idCategory + "");
        if (idBrand == 0 && idCategory == 0 && priceFilter == null){
            if (sortByPrice != Constants.MyTag.NONE_ORDER_BY_PRICE){
                sortByPrice(sortByPrice, data);
            }

            if (data == null || data.size() == 0){
                listProductView.loadListFalse();
            }else {
                listProductView.loadListSuccessful(isLastest, data);
            }
        }else {
            ArrayList<Product> results = new ArrayList<>();

            if (idBrand != 0){
                for (Product product : data){
                    if (product.getBrand() == idBrand){
                        results.add(product);
                    }
                }
            }

            if (idCategory != 0){
                for (Product product : data){
                    if (product.getTypeProduct() == idCategory){
                        results.add(product);
                    }
                }
            }

            if (priceFilter != null){
                long from = priceFilter.getPriceFrom();
                long to = priceFilter.getPriceTo();
                if (from == 0){
                    for (Product product : data){
                        if (product.getPrice() <= to){
                            results.add(product);
                        }
                    }
                }else if (to == 0){
                    for (Product product : data){
                        if (product.getPrice() >= from){
                            results.add(product);
                        }
                    }
                }else {
                    for (Product product : data){
                        if (product.getPrice() >= from && product.getPrice() <= to){
                            results.add(product);
                        }
                    }
                }
            }

            if (sortByPrice != Constants.MyTag.NONE_ORDER_BY_PRICE && results.size() != 0){
                sortByPrice(sortByPrice, results);
            }

            if (results == null || results.size() == 0){
                listProductView.loadListFalse();
            }else {
                listProductView.loadListSuccessful(isLastest, results);
            }
        }
    }

    private ArrayList<Product> sortByPrice(int sort, ArrayList<Product> data){
        if (sort == Constants.MyTag.LOAD_PRODUCT_PRICE_DESC){
            Collections.sort(data, new Comparator<Product>() {
                @Override
                public int compare(Product product, Product t1) {
                    long priceProduct = product.getPrice() - (product.getPrice()*product.getDiscount()/100);
                    long priceT1 = t1.getPrice() - (t1.getPrice()*t1.getDiscount()/100);
                    return (int) (priceT1 - priceProduct);
                }
            });
        }else {
            Collections.sort(data, new Comparator<Product>() {
                @Override
                public int compare(Product product, Product t1) {
                    long priceProduct = product.getPrice() - (product.getPrice()*product.getDiscount()/100);
                    long priceT1 = t1.getPrice() - (t1.getPrice()*t1.getDiscount()/100);
                    return (int) (priceProduct - priceT1);
                }
            });
        }

        return data;
    }

}
