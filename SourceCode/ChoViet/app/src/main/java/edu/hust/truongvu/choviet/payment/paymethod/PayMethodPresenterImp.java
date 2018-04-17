package edu.hust.truongvu.choviet.payment.paymethod;

import edu.hust.truongvu.choviet.model.PayMethodModel;

/**
 * Created by truon on 3/29/2018.
 */

public class PayMethodPresenterImp implements PayMethodPresenter {
    private PayMethodView payMethodView;
    private PayMethodModel model;
    public PayMethodPresenterImp(PayMethodView payMethodView){
        this.payMethodView = payMethodView;
        model = new PayMethodModel();
    }

    @Override
    public void initListPayMethod() {
        payMethodView.loadListPayment(model.getAllPayMethod());
    }
}
