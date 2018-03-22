package edu.hust.truongvu.choviet.rate;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Rate;

/**
 * Created by truon on 3/22/2018.
 */

public class RatePresenterImp implements RatePresenter{
    private RateView rateView;

    public RatePresenterImp(RateView rateView){
        this.rateView = rateView;
    }

    @Override
    public void insertRate() {
        RateModel rateModel = new RateModel();
        if (rateModel.addRate(null)){
            rateView.insertRateSuccessful();
        }else {
            rateView.insertRateFalse();
        }
    }

    @Override
    public void loadRate(int idProduct) {
        RateModel rateModel = new RateModel();
        ArrayList<Rate> data = rateModel.loadListRate(idProduct);
        rateView.loadListRateSuccessful(data);
    }
}
