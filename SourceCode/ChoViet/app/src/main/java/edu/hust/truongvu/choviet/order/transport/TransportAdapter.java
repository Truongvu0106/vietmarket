package edu.hust.truongvu.choviet.order.transport;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Transport;
import edu.hust.truongvu.choviet.helper.MyHelper;

/**
 * Created by truon on 3/27/2018.
 */

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.TransportHolder>{
    public interface TransportListener{
        void onClick(Transport transport);
    }
    private TransportListener mListener;
    private ArrayList<Transport> listTransport;
    private int mCheckedPosition = -1;


    public TransportAdapter(ArrayList<Transport> data, TransportListener listener){
        this.listTransport = data;
        this.mListener = listener;
    }

    @Override
    public TransportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transport, parent, false);
        TransportHolder holder = new TransportHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TransportHolder holder, int position) {
        Transport transport = listTransport.get(position);
        holder.setContent(transport, position);
    }

    @Override
    public int getItemCount() {
        return listTransport.size();
    }

    class TransportHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle, tvPrice, tvNote;
        private View imgCheck, layoutCheck;

        public TransportHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvNote = itemView.findViewById(R.id.tv_note);
            imgCheck = itemView.findViewById(R.id.img_check);
            layoutCheck = itemView.findViewById(R.id.layout_check);
        }

        public void setContent(final Transport transport, final int position){
            tvTitle.setText(transport.getTitle());
            tvPrice.setText(MyHelper.formatMoney(transport.getPrice()));
            tvNote.setText(transport.getNote());
            if (position == mCheckedPosition){
                imgCheck.setVisibility(View.VISIBLE);
            }else {
                imgCheck.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == mCheckedPosition){
                        imgCheck.setVisibility(View.GONE);
                        mCheckedPosition = -1;
                    }else {
                        mCheckedPosition = position;
                        notifyDataSetChanged();
                        mListener.onClick(transport);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
