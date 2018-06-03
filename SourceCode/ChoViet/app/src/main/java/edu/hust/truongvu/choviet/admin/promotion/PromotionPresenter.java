package edu.hust.truongvu.choviet.admin.promotion;

import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * Created by truon on 5/31/2018.
 */

public interface PromotionPresenter {
    void loadListPromotion();
    void deletePromotion(int id);
    void addPromotion(Promotion promotion);
}
