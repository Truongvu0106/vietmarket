package edu.hust.truongvu.choviet.rate;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.entity.Rate;

/**
 * Created by truon on 3/22/2018.
 */

public interface RateView {
    void insertRateSuccessful();
    void insertRateFalse();
    void loadListRateSuccessful(ArrayList<Rate> listRates);
    void loadListRateFalse();
}
