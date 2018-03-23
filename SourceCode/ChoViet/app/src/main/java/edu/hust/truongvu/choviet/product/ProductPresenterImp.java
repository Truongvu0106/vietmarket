package edu.hust.truongvu.choviet.product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.ProductRate;
import edu.hust.truongvu.choviet.entity.User;
import edu.hust.truongvu.choviet.rate.RateModel;
import edu.hust.truongvu.choviet.signin.SigninModel;

/**
 * Created by truon on 2/26/2018.
 */

public class ProductPresenterImp implements ProductPresenter{
    private ProductView productView;

    public ProductPresenterImp(){

    }

    public ProductPresenterImp(ProductView productView){
        this.productView = productView;
    }

    @Override
    public Product getProductById(int id) {
        ProductModel model = new ProductModel();
        return model.getProductById(id);
    }

    @Override
    public void initListImage(ArrayList<String> imgs) {
        productView.loadListImage(imgs);
    }

    @Override
    public void initListRate(String username, int id_product) {
        ArrayList<ProductRate> listProductRate = new ArrayList<>();
        listProductRate.add(new ProductRate(0, R.drawable.avatar, "truongvu", 4.5f, "San pham tot", "26-2-2018"));
        listProductRate.add(new ProductRate(0, R.drawable.nikon, "bachkhoa", 4.8f, "San pham tot", "26-2-2018"));
        listProductRate.add(new ProductRate(0, R.drawable.giaydep, "hanoi", 4.8f, "San pham tot", "26-2-2018"));

        productView.loadListRate(listProductRate);

        RateModel rateModel = new RateModel();
        productView.setEnableRate(rateModel.isRated(username, id_product));
    }

    @Override
    public void initListProduct() {
        ProductModel productModel = new ProductModel();
        ArrayList<Product> listProduct = productModel.getAllProduct();

        productView.loadListProduct(listProduct);
    }

    @Override
    public void initListSuggest() {
        ProductModel productModel = new ProductModel();
        ArrayList<Product> listProduct = productModel.getAllProduct();
        productView.loadListSuggest(listProduct);
    }

}
