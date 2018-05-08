package edu.hust.truongvu.choviet.product.list_product;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImgProductFragment extends Fragment {

    private String img;
    private ImageView imageView;
    public ImgProductFragment() {
        // Required empty public constructor
    }

    public static ImgProductFragment getInstance(String img){
        ImgProductFragment fragment = new ImgProductFragment();
        fragment.img = img;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_img_product, container, false);
        imageView = view.findViewById(R.id.img_product);
        MyHelper.setImagePicasso(getContext(), imageView, Constants.Path.MY_PATH + img);
        return view;
    }

}
