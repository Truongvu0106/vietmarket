package edu.hust.truongvu.choviet.payment.paymethod;

import android.content.Context;

import edu.hust.truongvu.choviet.model.PayMethodModel;

/**
 * Created by truon on 3/29/2018.
 */

public class PayMethodPresenterImp implements PayMethodPresenter {
    private PayMethodView payMethodView;
    private PayMethodModel model;
    private Context mContext;
    public PayMethodPresenterImp(Context context, PayMethodView payMethodView){
        this.payMethodView = payMethodView;
        this.mContext = context;
        model = new PayMethodModel(mContext);
    }

    @Override
    public void initListPayMethod() {
        payMethodView.loadListPayment(model.getAllPayMethod());
    }
}
