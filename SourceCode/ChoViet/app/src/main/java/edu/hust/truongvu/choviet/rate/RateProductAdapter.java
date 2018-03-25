package edu.hust.truongvu.choviet.rate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Rate;
import edu.hust.truongvu.choviet.entity.User;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.signin.SigninModel;

/**
 * Created by truon on 2/26/2018.
 */

public class RateProductAdapter extends RecyclerView.Adapter<RateProductAdapter.RateProductHolder>{
    private ArrayList<Rate> data;
    private Context context;
    public RateProductAdapter(Context context, ArrayList<Rate> data){
        this.data = data;
        this.context = context;
    }
    @Override
    public RateProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_rate, parent, false);
        RateProductHolder holder = new RateProductHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RateProductHolder holder, int position) {
        Rate productRate = data.get(position);
        holder.setContent(productRate);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RateProductHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tvName, tvTitle, tvContent, tvDate;
        private RatingBar ratingBar;
        public RateProductHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_customer);
            tvName = itemView.findViewById(R.id.name_customer);
            tvTitle = itemView.findViewById(R.id.customer_comment);
            tvContent = itemView.findViewById(R.id.customer_content);
            tvDate = itemView.findViewById(R.id.date_comment);
            ratingBar = itemView.findViewById(R.id.customer_rate);
        }
        public void setContent(Rate productRate){
            SigninModel signinModel = new SigninModel();
            User user = signinModel.getUserByUsername(productRate.getUserName());
            MyHelper.loadImageUser(context, imageView, user.getAvatar());
            tvName.setText(user.getFullname());
            tvTitle.setText(productRate.getTitle());
            tvContent.setText(productRate.getContent());
            tvDate.setText(productRate.getDate());
            ratingBar.setRating(productRate.getStarRate());
        }
    }
}
