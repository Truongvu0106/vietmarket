package edu.hust.truongvu.choviet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import edu.hust.truongvu.choviet.R;

/**
 * Created by truon on 4/25/2018.
 */

public class ChooseImageDialog extends AlertDialog{
    public interface OptionListener{
        void onCapture();
        void onChooseGallery();
    }
    private OptionListener mListener;
    private View btnTakePicture, btnChooseGallery;

    public ChooseImageDialog(Context context, OptionListener listener) {
        super(context);
        this.mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_image);
        btnTakePicture = findViewById(R.id.btn_take_picture);
        btnChooseGallery = findViewById(R.id.btn_choose_gallery);

        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCapture();
                dismiss();
            }
        });

        btnChooseGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onChooseGallery();
                dismiss();
            }
        });
    }
}
