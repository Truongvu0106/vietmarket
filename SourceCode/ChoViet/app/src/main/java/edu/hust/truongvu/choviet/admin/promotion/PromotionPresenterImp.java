package edu.hust.truongvu.choviet.admin.promotion;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.PromotionModel;
import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * Created by truon on 5/31/2018.
 */

public class PromotionPresenterImp implements PromotionPresenter{
    private Context mContext;
    private PromotionView view;
    private PromotionModel model;

    public PromotionPresenterImp(Context context, PromotionView view){
        this.mContext = context;
        this.view = view;
        model = new PromotionModel(mContext);
    }
    @Override
    public void loadListPromotion() {
        ArrayList<Promotion> data = model.getAllPromotion();
        if (data == null || data.isEmpty()){
            view.loadListFalse();
        }else {
            view.loadListSuccessful(data);
        }
    }

    @Override
    public void deletePromotion(int id) {
        if (model.deletePromotion(id)){
            view.deleteSuccessful();
        }else {
            view.deleteFalse();
        }
    }

    @Override
    public void addPromotion(Promotion promotion) {
        if (model.addPromotion(promotion)){
            view.addPromotionSuccessful();
        }else {
            view.addPromotionFalse();
        }
    }
}
