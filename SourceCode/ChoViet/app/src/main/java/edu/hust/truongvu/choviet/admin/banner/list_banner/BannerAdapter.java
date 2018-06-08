package edu.hust.truongvu.choviet.admin.banner.list_banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.model.entity.Banner;

/**
 * Created by truon on 5/29/2018.
 */

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder>{
    public interface BannerListener{
        void onClick(Banner banner);
    }
    private ArrayList<Banner> data;
    private Context mContext;
    private BannerListener mListener;

    public BannerAdapter(Context context, ArrayList<Banner> banners, BannerListener listener){
        this.mContext = context;
        this.data = banners;
        this.mListener = listener;
    }
    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        BannerViewHolder holder = new BannerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner = data.get(position);
        holder.setContent(banner);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private ImageView imageView;
        public BannerViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.img_banner);
        }

        public void setContent(final Banner banner){
            tvName.setText(banner.getTitle());
            Picasso.with(mContext)
                    .load(Constants.MY_PATH + banner.getImage())
                    .placeholder(R.drawable.img_loading)
                    .error(R.drawable.img_error)
                    .resize(350, 150)
                    .centerCrop()
                    .into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClick(banner);
                }
            });
        }
    }
}
