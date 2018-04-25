package edu.hust.truongvu.choviet.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 4/25/2018.
 */

public class ListAddImageAdapter extends RecyclerView.Adapter<ListAddImageAdapter.ImageViewHolder>{
    public interface ClearImageListener{
        void onClear(Bitmap bitmap);
    }
    private ClearImageListener mListener;
    private ArrayList<Bitmap> listBitmap;
    public ListAddImageAdapter(ArrayList<Bitmap> listBitmap, ClearImageListener listener){
        this.listBitmap = listBitmap;
        this.mListener = listener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_add_product, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Bitmap bitmap = listBitmap.get(position);
        holder.setContent(bitmap);
    }

    @Override
    public int getItemCount() {
        return listBitmap.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private View btnClear;
        public ImageViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product);
            btnClear = itemView.findViewById(R.id.btn_clear);
        }
        public void setContent(final Bitmap bitmap){
            img.setImageBitmap(bitmap);

            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClear(bitmap);
                }
            });
        }
    }
}
