package edu.hust.truongvu.choviet.advertisment.promotion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lid.lib.LabelButtonView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.Promotion;

/**
 * Created by truon on 6/8/2018.
 */

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>{
    public interface PromotionListener{
        void onLongClick(Promotion promotion);
    }
    private PromotionListener mListener;
    private Context mContext;
    private ArrayList<Promotion> data;


    public PromotionAdapter(Context context, ArrayList<Promotion> data, PromotionListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.data = data;
    }


    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion_user, parent, false);
        PromotionViewHolder viewHolder = new PromotionViewHolder(view);
        return viewHolder;
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
        private LabelButtonView labelButtonView;
        public PromotionViewHolder(View itemView) {
            super(itemView);
            labelButtonView = itemView.findViewById(R.id.labelbutton);
        }

        public void setContent(final Promotion promotion){
            labelButtonView.setText(promotion.getCode());
            labelButtonView.setLabelText("-" + MyHelper.formatMoney(promotion.getAmount()));

            labelButtonView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mListener.onLongClick(promotion);
                    return true;
                }
            });
        }
    }
}
