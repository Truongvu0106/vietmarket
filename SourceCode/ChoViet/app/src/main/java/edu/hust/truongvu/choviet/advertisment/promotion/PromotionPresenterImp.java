package edu.hust.truongvu.choviet.advertisment.promotion;

import android.content.Context;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.model.PromotionModel;
import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * Created by truon on 6/8/2018.
 */

public class PromotionPresenterImp implements PromotionPresenter {
    private Context mContext;
    private PromotionView view;
    private PromotionModel model;

    public PromotionPresenterImp(Context context, PromotionView view){
        this.mContext = context;
        this.view = view;
        model = new PromotionModel(mContext);
    }

    @Override
    public void initListPresenter() {
        ArrayList<Promotion> data = model.getAllPromotion();
        if (data == null || data.size() == 0){
            view.loadPromotionFalse();
        }else {
            view.loadPromotionSuccessful(data);
        }
    }
}
