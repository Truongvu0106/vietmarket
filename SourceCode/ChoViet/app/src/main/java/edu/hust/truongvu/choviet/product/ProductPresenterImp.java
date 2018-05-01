package edu.hust.truongvu.choviet.product;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.entity.ChildCategory;
import edu.hust.truongvu.choviet.entity.Shop;
import edu.hust.truongvu.choviet.model.BrandModel;
import edu.hust.truongvu.choviet.model.CartModel;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.Rate;
import edu.hust.truongvu.choviet.model.CategoryModel;
import edu.hust.truongvu.choviet.model.ProductModel;
import edu.hust.truongvu.choviet.model.RateModel;
import edu.hust.truongvu.choviet.model.ShopModel;

/**
 * Created by truon on 2/26/2018.
 */

public class ProductPresenterImp implements ProductPresenter{
    private ProductView productView;
    private RateModel rateModel;
    private ProductModel productModel;
    private CartModel cartModel;
    private ShopModel shopModel;
    private CategoryModel categoryModel;
    private BrandModel brandModel;
    public ProductPresenterImp(){
        rateModel = new RateModel();
        productModel = new ProductModel();
        cartModel = new CartModel();
        shopModel = new ShopModel();
        categoryModel = new CategoryModel();
        brandModel = new BrandModel();
    }

    public ProductPresenterImp(ProductView productView){
        this.productView = productView;
        rateModel = new RateModel();
        productModel = new ProductModel();
        cartModel = new CartModel();
        shopModel = new ShopModel();
        categoryModel = new CategoryModel();
        brandModel = new BrandModel();
    }

    public ChildCategory getChildCategory(int idCategory){
        ChildCategory childCategory = categoryModel.getChildCategoryById(idCategory);
        return childCategory;
    }

    public Brand getBrand(int idBrand){
        Brand brand = brandModel.getBrandById(idBrand);
        return brand;
    }

    @Override
    public Product getProductById(int id) {
        return productModel.getProductById(id);
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
        ArrayList<Rate> listProductRate = rateModel.loadListRate(id_product);
        productView.loadListRate(listProductRate);
        productView.setEnableRate(rateModel.isRated(username, id_product));
    }

    @Override
    public void initListProduct() {
        ArrayList<Product> listProduct = productModel.getAllProduct();
        productView.loadListProduct(listProduct);
    }

    @Override
    public void initListSuggest() {
        ArrayList<Product> listProduct = productModel.getAllProduct();
        productView.loadListSuggest(listProduct);
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

}
