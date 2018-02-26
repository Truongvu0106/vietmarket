package edu.hust.truongvu.choviet.product;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.ProductRate;

/**
 * Created by truon on 2/26/2018.
 */

public class RateProductAdapter extends RecyclerView.Adapter<RateProductAdapter.RateProductHolder>{
    private ArrayList<ProductRate> data;

    public RateProductAdapter(ArrayList<ProductRate> data){
        this.data = data;
    }
    @Override
    public RateProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_rate, parent, false);
        RateProductHolder holder = new RateProductHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RateProductHolder holder, int position) {
        ProductRate productRate = data.get(position);
        holder.setContent(productRate);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RateProductHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvName, tvContent, tvDate;
        private RatingBar ratingBar;
        public RateProductHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_customer);
            tvName = itemView.findViewById(R.id.name_customer);
            tvContent = itemView.findViewById(R.id.customer_comment);
            tvDate = itemView.findViewById(R.id.date_comment);
            ratingBar = itemView.findViewById(R.id.customer_rate);
        }
        public void setContent(ProductRate productRate){
            imageView.setImageResource(productRate.getImgCustomer());
            tvName.setText(productRate.getNameCustomer());
            tvContent.setText(productRate.getComment());
            tvDate.setText(productRate.getDate());
            ratingBar.setRating(productRate.getRate());
        }
    }
}
