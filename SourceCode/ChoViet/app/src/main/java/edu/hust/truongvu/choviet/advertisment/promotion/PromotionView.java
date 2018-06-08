package edu.hust.truongvu.choviet.advertisment.promotion;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * Created by truon on 6/8/2018.
 */

public interface PromotionView {
    void loadPromotionSuccessful(ArrayList<Promotion> data);
    void loadPromotionFalse();
}
