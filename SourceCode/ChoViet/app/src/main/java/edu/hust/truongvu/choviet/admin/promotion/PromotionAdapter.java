package edu.hust.truongvu.choviet.admin.promotion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * Created by truon on 5/31/2018.
 */

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>{
    public interface PromotionListener{
        void onDelete(Promotion promotion);
    }

    private ArrayList<Promotion> data;
    private PromotionListener mListener;
    private Context mContext;

    public PromotionAdapter(Context mContext, ArrayList<Promotion> data, PromotionListener listener){
        this.mContext = mContext;
        this.data = data;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_promotion, parent, false);
        PromotionViewHolder holder = new PromotionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder holder, int position) {
        Promotion promotion = data.get(position);
        holder.setContent(promotion);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PromotionViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCode, tvAmount, tvRest, tvDate;
        private View btnDelete;
        public PromotionViewHolder(View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tv_code);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvRest = itemView.findViewById(R.id.tv_rest);
            tvDate = itemView.findViewById(R.id.tv_date);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }

        public void setContent(final Promotion promotion){
            tvCode.setText(promotion.getCode());
            tvAmount.setText("- " + MyHelper.formatMoney(promotion.getAmount()));
            tvRest.setText(mContext.getString(R.string.rest) + ": " + promotion.getNumber());
            tvDate.setText(MyHelper.convertDateToString(promotion.getStart()) + " - "
                    + MyHelper.convertDateToString(promotion.getEnd()));

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onDelete(promotion);
                }
            });

        }
    }
}
