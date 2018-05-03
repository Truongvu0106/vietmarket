package edu.hust.truongvu.choviet.payment.transport;

import android.content.Context;

import edu.hust.truongvu.choviet.model.TransportModel;

/**
 * Created by truon on 3/27/2018.
 */

public class TransportPresenterImp implements TransportPresenter {
    private TransportView transportView;
    private TransportModel transportModel;
    private Context mContext;

    public TransportPresenterImp(Context context, TransportView transportView){
        this.transportView = transportView;
        this.mContext = context;
        transportModel = new TransportModel(mContext);
    }

    @Override
    public void initListTransport() {
        transportView.loadListTransport(transportModel.getAllTransport());
    }
}
