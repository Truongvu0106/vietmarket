package edu.hust.truongvu.choviet.user.myshop.update_product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.user.myshop.add_product.ListAddImageAdapter;

/**
 * Created by truon on 5/24/2018.
 */

public class ListUpdateImageAdapter extends RecyclerView.Adapter<ListUpdateImageAdapter.ImageViewHolder>{
    public interface ImageListener{
        void onClear(MyImage myImage);
    }
    private ImageListener mListener;
    private ArrayList<MyImage> listMyImage;
    private Context mContext;
    public ListUpdateImageAdapter(Context context, ArrayList<MyImage> listMyImage, ImageListener listener){
        this.listMyImage = listMyImage;
        this.mListener = listener;
        this.mContext = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img_add_product, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        MyImage myImage = listMyImage.get(position);
        holder.setContent(myImage);
    }

    @Override
    public int getItemCount() {
        return listMyImage.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private View btnClear;
        public ImageViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product);
            btnClear = itemView.findViewById(R.id.btn_clear);
        }
        public void setContent(final MyImage myImage){
            if (myImage.getBitmap() == null){
                MyHelper.setImagePicasso(mContext, img, Constants.MY_PATH + myImage.getPath());
            }else {
                img.setImageBitmap(myImage.getBitmap());
            }

            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onClear(myImage);
                }
            });


        }
    }
}
