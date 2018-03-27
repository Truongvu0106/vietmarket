package edu.hust.truongvu.choviet.payment.transport;

/**
 * Created by truon on 3/27/2018.
 */

public class TransportPresenterImp implements TransportPresenter {
    private TransportView transportView;
    private TransportModel transportModel;

    public TransportPresenterImp(TransportView transportView){
        this.transportView = transportView;
        transportModel = new TransportModel();
    }

    @Override
    public void initListTransport() {
        transportView.loadListTransport(transportModel.getAllTransport());
    }
}
