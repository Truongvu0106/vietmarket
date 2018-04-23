package edu.hust.truongvu.choviet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.PayMethod;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 3/29/2018.
 */

public class ListPayMethodAdapter extends RecyclerView.Adapter<ListPayMethodAdapter.PayMethodHolder>{
    public interface PayMethodListener{
        void onClick(PayMethod payMethod);
    }

    private PayMethodListener mListener;
    private ArrayList<PayMethod> listPays;
    private Context context;
    private int mCheckedPosition = -1;

    public ListPayMethodAdapter(Context context, ArrayList<PayMethod> data, PayMethodListener listener){
        this.context = context;
        this.listPays = data;
        this.mListener = listener;
    }

    @Override
    public PayMethodHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment, parent, false);
        PayMethodHolder holder = new PayMethodHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PayMethodHolder holder, int position) {
        PayMethod payMethod = listPays.get(position);
        holder.setContent(context, payMethod, position);
    }

    @Override
    public int getItemCount() {
        return listPays.size();
    }

    class PayMethodHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvName;
        private View imgCheck;

        public PayMethodHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.icon_payment);
            tvName = itemView.findViewById(R.id.tv_payment);
            imgCheck = itemView.findViewById(R.id.img_check);
        }

        public void setContent(Context context, final PayMethod payMethod, final int position){
            tvName.setText(payMethod.getName());
            MyHelper.setImagePicasso(context, imageView, Constants.Path.MY_PATH + payMethod.getImage());
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
                        mListener.onClick(payMethod);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
