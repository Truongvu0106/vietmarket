package edu.hust.truongvu.choviet.myshop.add_product;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.adapter.ListAddImageAdapter;
import edu.hust.truongvu.choviet.dialog.ChooseImageDialog;
import edu.hust.truongvu.choviet.entity.MyImage;
import edu.hust.truongvu.choviet.helper.MyHelper;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener, AddProductView{
    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_PERMISSIONS = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final int SELECT_FILE = 3;

    private View btnAddPhoto, btnUploadImage;
    private RecyclerView recyclerImgProduct;
    private ListAddImageAdapter adapter;
    private ArrayList<MyImage> listMyImage;
    private AddProductPresenterImp addProductPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        addProductPresenterImp = new AddProductPresenterImp(this, this);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (MyHelper.checkPermission(PERMISSIONS, this) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            }
        }

    }

    private void initView(){
        btnAddPhoto = findViewById(R.id.btn_add_photo);
        btnUploadImage = findViewById(R.id.btn_upload_image);
        recyclerImgProduct = findViewById(R.id.list_image);
        listMyImage = new ArrayList<>();
        adapter = new ListAddImageAdapter(listMyImage, new ListAddImageAdapter.ClearImageListener() {
            @Override
            public void onClear(MyImage myImage) {

            }
        });
        recyclerImgProduct.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        recyclerImgProduct.setAdapter(adapter);

        btnAddPhoto.setOnClickListener(this);
        btnUploadImage.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_PERMISSIONS:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (MyHelper.checkPermission(PERMISSIONS, this) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
                    } else {
                        //Do something
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        long time = System.currentTimeMillis();
        String name = new Random().nextInt((1000-1)+1)+1 + "_" + time;
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyImage myImage = new MyImage(name, thumbnail);
        listMyImage.add(myImage);
        adapter.notifyDataSetChanged();

    }

    private void onSelectFromGalleryResult(Intent data) {
        long time = System.currentTimeMillis();
        String name = new Random().nextInt((1000-1)+1)+1 + "_" + time;
        Bitmap bm = null;
        if (data != null){
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MyImage myImage = new MyImage(name, bm);
        listMyImage.add(myImage);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_photo:
                addPhoto();
                break;
            case R.id.btn_upload_image:
                uploadImage();
                break;
        }
    }

    private void uploadImage(){
        if (listMyImage == null || listMyImage.size() == 0){
            Toast.makeText(this, getString(R.string.not_choose_image), Toast.LENGTH_SHORT).show();
        }else {
            addProductPresenterImp.upLoadImage(listMyImage);
        }
    }

    private void addPhoto(){
        ChooseImageDialog dialog = new ChooseImageDialog(this, new ChooseImageDialog.OptionListener() {
            @Override
            public void onCapture() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }

            @Override
            public void onChooseGallery() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }


    @Override
    public void uploadImageSuccessful() {
        Toast.makeText(this, getString(R.string.upload_successful), Toast.LENGTH_SHORT);
    }

    @Override
    public void uploadImageFalse() {
        Toast.makeText(this, getString(R.string.file_size_too_large), Toast.LENGTH_SHORT);
    }
}
