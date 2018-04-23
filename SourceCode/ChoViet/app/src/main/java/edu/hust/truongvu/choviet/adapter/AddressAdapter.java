package edu.hust.truongvu.choviet.payment.address;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 3/26/2018.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder>{
    public interface AddressListener{
        void onCheck(String address);
    }
    private AddressListener myListener;
    private ArrayList<String> listAddress;
    private int mCheckedPosition = -1;

    public AddressAdapter(ArrayList<String> data, AddressListener listener){
        this.listAddress = data;
        this.myListener = listener;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        AddressViewHolder viewHolder = new AddressViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {
        String address = listAddress.get(position);
        holder.setContent(address, position);
    }

    @Override
    public int getItemCount() {
        return listAddress.size();
    }

    class AddressViewHolder extends RecyclerView.ViewHolder{
        private TextView tvAddress;
        private View imgCheck, layoutCheck;
        public AddressViewHolder(View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tv_address);
            imgCheck = itemView.findViewById(R.id.img_check);
            layoutCheck = itemView.findViewById(R.id.layout_check);
        }

        public void setContent(final String address, final int position){
            tvAddress.setText(address);
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
                        myListener.onCheck(address);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
