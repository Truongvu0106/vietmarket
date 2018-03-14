package edu.hust.truongvu.choviet.product;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Product;

/**
 * Created by truon on 3/8/2018.
 */

public class ListProductPresenterImp implements ListProductPresenter{
    private ListProductView listProductView;

    public ListProductPresenterImp(ListProductView listProductView){
        this.listProductView = listProductView;
    }

    @Override
    public void initList() {
        ArrayList<Product> data = new ArrayList<>();
        listProductView.loadList(data);
    }
}
