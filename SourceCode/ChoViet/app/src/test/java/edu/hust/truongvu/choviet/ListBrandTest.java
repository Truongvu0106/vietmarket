package edu.hust.truongvu.choviet;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.home.HomePresenter;
import edu.hust.truongvu.choviet.home.HomePresenterImp;
import edu.hust.truongvu.choviet.home.HomeView;
import edu.hust.truongvu.choviet.model.entity.Brand;

/**
 * Created by truon on 5/21/2018.
 */

public class ListBrandTest {
    @Mock
    private HomeView homeView;

    @Mock
    private Context context;

    private HomePresenterImp homePresenterImp;
    private ArrayList<Brand> listBrand;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        homePresenterImp = new HomePresenterImp(context, homeView);
    }

    @Test
    public void testListBrand(){
        generateListBrand();
        homePresenterImp.initListBrand(listBrand);
    }

    private void generateListBrand(){
        listBrand = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            listBrand.add(new Brand(0, "abc", "http://"));
        }
        listBrand.add(new Brand(0, "", ""));
    }
}
