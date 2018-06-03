package edu.hust.truongvu.choviet.admin.promotion;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * Created by truon on 5/31/2018.
 */

public interface PromotionView {
    void loadListSuccessful(ArrayList<Promotion> data);
    void loadListFalse();

    void deleteSuccessful();
    void deleteFalse();

    void addPromotionSuccessful();
    void addPromotionFalse();
}
