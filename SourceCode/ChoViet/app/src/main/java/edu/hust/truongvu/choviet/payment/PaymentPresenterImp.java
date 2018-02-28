package edu.hust.truongvu.choviet.payment;

/**
 * Created by truon on 2/28/2018.
 */

public class PaymentPresenterImp implements PaymentPresenter{
    private PaymentView paymentView;

    public PaymentPresenterImp(PaymentView paymentView){
        this.paymentView = paymentView;
    }
    @Override
    public void onClickNext() {
        paymentView.loadNext();
    }

}
