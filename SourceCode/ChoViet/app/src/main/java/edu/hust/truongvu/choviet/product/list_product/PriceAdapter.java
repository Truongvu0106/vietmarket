package edu.hust.truongvu.choviet.product.list_product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.PriceFilter;
import edu.hust.truongvu.choviet.helper.MyHelper;

/**
 * Created by truon on 5/3/2018.
 */

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceViewHolder>{
    public interface ChoosePriceListener{
        void onChoose(PriceFilter priceFilter);
    }
    private ChoosePriceListener mListener;
    private Context mContext;
    private ArrayList<PriceFilter> data;
    public PriceAdapter(Context context, ArrayList<PriceFilter> data, ChoosePriceListener listener){
        this.mContext = context;
        this.mListener = listener;
        this.data = data;
    }

    @Override
    public PriceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_price, parent, false);
        PriceViewHolder holder = new PriceViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PriceViewHolder holder, int position) {
        PriceFilter filter = data.get(position);
        holder.setContent(filter);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PriceViewHolder extends RecyclerView.ViewHolder{
        private RadioButton radioButton;
        private TextView tvPrice;
        public PriceViewHolder(View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radio_button);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }

        public void setContent(final PriceFilter priceFilter){
            if (priceFilter.getPriceFrom() == 0){
                priceFilter.setTxtDisPlay(mContext.getString(R.string.less_than) + " "
                        + MyHelper.formatMoney(priceFilter.getPriceTo()));
            }else if (priceFilter.getPriceTo() == 0){
                priceFilter.setTxtDisPlay(mContext.getString(R.string.more_than) + " "
                        + MyHelper.formatMoney(priceFilter.getPriceFrom()));
            }else {
                priceFilter.setTxtDisPlay(MyHelper.formatMoney(priceFilter.getPriceFrom()) + " - "
                        + MyHelper.formatMoney(priceFilter.getPriceTo()));
            }
            tvPrice.setText(priceFilter.getTxtDisPlay());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    radioButton.setChecked(true);
                    mListener.onChoose(priceFilter);
                }
            });

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onChoose(priceFilter);
                }
            });
        }
    }
}
