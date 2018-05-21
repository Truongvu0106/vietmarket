package edu.hust.truongvu.choviet;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.home.HomePresenterImp;
import edu.hust.truongvu.choviet.home.HomeView;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.Shop;

/**
 * Created by truon on 5/21/2018.
 */

public class ListShopTest {
    @Mock
    private HomeView homeView;

    @Mock
    private Context context;

    private HomePresenterImp homePresenterImp;
    private ArrayList<Shop> listShop;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        homePresenterImp = new HomePresenterImp(context, homeView);
    }

    @Test
    public void testListBrand(){
        generateListBrand();
        homePresenterImp.initListHighLightShop(listShop);
    }

    private void generateListBrand(){
        listShop = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            listShop.add(new Shop(0, "abc", "abc", "http://",
                    "http://", 1, "abc", "123", "http://", 4.5f, false));
        }
        listShop.add(new Shop(0, "", "", "",
                "", 0, "", "", "", 0, false));
        listShop.add(new Shop(0, "", "", "",
                "", 0, "", "", "", 0, true));
    }
}
