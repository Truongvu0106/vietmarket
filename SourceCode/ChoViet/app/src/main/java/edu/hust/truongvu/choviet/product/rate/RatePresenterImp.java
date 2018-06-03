package edu.hust.truongvu.choviet.product.rate;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Rate;
import edu.hust.truongvu.choviet.model.RateModel;

/**
 * Created by truon on 3/22/2018.
 */

public class RatePresenterImp implements RatePresenter{
    private RateView rateView;
    private Context mContext;

    public RatePresenterImp(Context context, RateView rateView){
        this.rateView = rateView;
        this.mContext = context;
    }

    @Override
    public void insertRate() {
        RateModel rateModel = new RateModel(mContext);
        if (rateModel.addRate(null)){
            rateView.insertRateSuccessful();
        }else {
            rateView.insertRateFalse();
        }
    }

    @Override
    public void loadRate(int idProduct) {
        RateModel rateModel = new RateModel(mContext);
        ArrayList<Rate> data = rateModel.getListRate(idProduct);
        rateView.loadListRateSuccessful(data);
    }
}
