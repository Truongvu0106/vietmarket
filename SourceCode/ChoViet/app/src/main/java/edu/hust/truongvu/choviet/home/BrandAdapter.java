package edu.hust.truongvu.choviet.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.entity.Brand;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * Created by truon on 2/22/2018.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandHolder>{
    public interface BrandListener{
        void onClick(Brand brand);
    }
    private BrandListener myListener;
    private ArrayList<Brand> data;
    private Context context;

    public BrandAdapter(Context context, ArrayList<Brand> data, BrandListener listener){
        this.context = context;
        this.data = data;
        this.myListener = listener;
    }

    @Override
    public BrandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_brand, null);
        BrandHolder holder = new BrandHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BrandHolder holder, int position) {
        Brand brand = data.get(position);
        holder.setContent(brand);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BrandHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public BrandHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.logo_brand);
        }

        public void setContent(final Brand brand){
//            Picasso.with(context)
//                    .load(Constants.Path.MY_PATH + brand.getLogo().trim())
//                    .placeholder(R.drawable.loading)
//                    .error(R.drawable.error)
//                    .resize(150, 150)
//                    .centerCrop()
//                    .into(imageView);
            MyHelper.setImagePicasso(context, imageView, Constants.Path.MY_PATH + brand.getLogo().trim());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myListener.onClick(brand);
                }
            });
        }
    }
}
