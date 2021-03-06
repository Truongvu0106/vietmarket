package edu.hust.truongvu.choviet.product.details_product;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.model.BrandModel;
import edu.hust.truongvu.choviet.model.CartModel;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.model.entity.Rate;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.RateModel;
import edu.hust.truongvu.choviet.model.ShopModel;

/**
 * Created by truon on 2/26/2018.
 */

public class ProductPresenterImp implements ProductPresenter{
    private Context mContext;
    private ProductView productView;
    private RateModel rateModel;
    private ProductModel productModel;
    private CartModel cartModel;
    private ShopModel shopModel;
//    private CategoryModel categoryModel;
    private BrandModel brandModel;
    public ProductPresenterImp(Context context){
        this.mContext = context;
        rateModel = new RateModel(mContext);
        productModel = new ProductModel(mContext);
        cartModel = new CartModel();
        shopModel = new ShopModel(mContext);
//        categoryModel = new CategoryModel(mContext);
        brandModel = new BrandModel(mContext);
    }

    public ProductPresenterImp(Context context, ProductView productView){
        this.productView = productView;
        this.mContext = context;
        rateModel = new RateModel(mContext);
        productModel = new ProductModel(mContext);
        cartModel = new CartModel();
        shopModel = new ShopModel(mContext);
//        categoryModel = new CategoryModel(mContext);
        brandModel = new BrandModel(mContext);
    }

//    public ChildCategory getChildCategory(int idCategory){
//        ChildCategory childCategory = categoryModel.getChildCategoryById(idCategory);
//        return childCategory;
//    }

    public Brand getBrand(int idBrand){
        Brand brand = brandModel.getBrandById(idBrand);
        return brand;
    }

    @Override
    public void initListImage(ArrayList<String> imgs) {
        if (imgs == null || imgs.size() == 0){
            productView.loadListImageFalse();
        }else {
            productView.loadListImageSuccessful(imgs);
        }
    }

    @Override
    public void initListRate(String username, int id_product) {
        ArrayList<Rate> listProductRate = rateModel.getListRate(id_product);
        if (listProductRate == null || listProductRate.size() == 0){
            productView.loadListRateFalse();
        }else {
            productView.loadListRateSuccessful(listProductRate);
        }
        productView.setEnableRate(rateModel.isRated(username, id_product));
    }

    @Override
    public void initListProductOther(int idShop) {
        ArrayList<Product> listProduct = productModel.getProductByIdShop(idShop);
        if (listProduct == null || listProduct.size() == 0){
            productView.loadListProductOtherFalse();
        }else {
            productView.loadListProductOtherSuccessful(listProduct);
        }
    }

    @Override
    public void initListProductSuggest(int idCategory) {
        ArrayList<Product> listProduct = productModel.getProductByCategory(idCategory);
        if (listProduct == null || listProduct.size() == 0) {
            productView.loadListProductSuggestFalse();
        }else {
            productView.loadLisProductSuggestSuccessful(listProduct);
        }
    }

    @Override
    public boolean addRate(Rate rate) {
        return rateModel.addRate(rate);
    }

    @Override
    public void addToCart(Context context, Product product) {
        cartModel.openDatabase(context);
        if (cartModel.addItemCart(product)){
            productView.addToCartSuccessful();
        }else {
            productView.addToCartFalse();
        }
        cartModel.closeDatabse();
    }

    @Override
    public void initInforShop(int idShop) {
        Shop shop = shopModel.getShopById(idShop);
        if (shop == null){
            productView.loadInforShopFalse();
        }else {
            productView.loadInforShopSuccessful(shop);

        }
    }

    @Override
    public int getNumLike(int idProduct) {
        return productModel.countLikeProduct(idProduct);
    }

    @Override
    public int getNumRate(int idProduct) {
        return productModel.countRateProduct(idProduct);
    }

    @Override
    public void updateRate(Rate rate) {
        float currentTotalRate = 0;
        int idProduct = rate.getIdProduct();
        ArrayList<Rate> listRated = rateModel.getListRate(idProduct);
        for (int i = 0; i < listRated.size(); i++){
            currentTotalRate += listRated.get(i).getStarRate();
        }
        currentTotalRate += rate.getStarRate();
        float newRate = currentTotalRate/(listRated.size()+1);
        if (productModel.updateRateProduct(idProduct, newRate)){
            Product product = productModel.getProductById(idProduct);
            if (product == null){
                productView.updateRateFalse();
            }else {
                productView.updateRateSuccessful(product.getRate());
            }
        }else {
            productView.updateRateFalse();
        }

    }


}
