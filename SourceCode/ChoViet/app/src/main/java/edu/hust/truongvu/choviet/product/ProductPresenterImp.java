package edu.hust.truongvu.choviet.product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Product;
import edu.hust.truongvu.choviet.entity.ProductRate;

/**
 * Created by truon on 2/26/2018.
 */

public class ProductPresenterImp implements ProductPresenter{
    private ProductView productView;

    public ProductPresenterImp(ProductView productView){
        this.productView = productView;
    }
    @Override
    public void initListImage() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(R.drawable.giaydep);
        data.add(R.drawable.giaydep);
        data.add(R.drawable.giaydep);
        data.add(R.drawable.giaydep);
        data.add(R.drawable.giaydep);
        productView.loadListImage(data);
    }

    @Override
    public void initListRate() {
        ArrayList<ProductRate> listProductRate = new ArrayList<>();
        listProductRate.add(new ProductRate(0, R.drawable.avatar, "truongvu", 4.5f, "San pham tot", "26-2-2018"));
        listProductRate.add(new ProductRate(0, R.drawable.nikon, "bachkhoa", 4.8f, "San pham tot", "26-2-2018"));
        listProductRate.add(new ProductRate(0, R.drawable.giaydep, "hanoi", 4.8f, "San pham tot", "26-2-2018"));
        productView.loadListRate(listProductRate);
    }

    @Override
    public void initListProduct() {
        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product(0, "giày dép", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "quần áo", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "áo khoác", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "máy tính", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "điện thoại", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "laptop", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "máy ảnh", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "tai nghe", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "chuột", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "bàn phím", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "xe máy", R.drawable.giaydep, 20000, 25, false, 4));
        productView.loadListProduct(listProduct);
    }

    @Override
    public void initListSuggest() {
        ArrayList<Product> listProduct = new ArrayList<>();
        listProduct.add(new Product(0, "giày dép", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "quần áo", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "áo khoác", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "máy tính", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "điện thoại", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "laptop", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "máy ảnh", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "tai nghe", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "chuột", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "bàn phím", R.drawable.giaydep, 20000, 25, false, 4));
        listProduct.add(new Product(0, "xe máy", R.drawable.giaydep, 20000, 25, false, 4));
        productView.loadListSuggest(listProduct);
    }
}
