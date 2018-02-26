package edu.hust.truongvu.choviet.product;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import edu.hust.truongvu.choviet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImgProductFragment extends Fragment {

    private int img;
    private ImageView imageView;
    public ImgProductFragment() {
        // Required empty public constructor
    }

    public static ImgProductFragment getInstance(int img){
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
        imageView.setImageResource(img);
        return view;
    }

}
