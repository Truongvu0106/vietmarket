package edu.hust.truongvu.choviet.user.myshop.registershop;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Shop;
import edu.hust.truongvu.choviet.user.myshop.add_product.ChooseImageDialog;

public class RegisterShopActivity extends AppCompatActivity implements View.OnClickListener, RegisterView{
    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_PERMISSIONS = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final int SELECT_FILE = 3;

    private EditText edtName, edtslogan, edtPhone, edtAddress, edtWebsite;
    private View btnRegister, btnImgAvatar, btnImgCover, btnUploadImage;
    private ImageView imgAvatar, imgCover;

    private RegisterPresenter registerPresenterImp;

    private MyImage avatar, cover;
    private static MyImage mImage;
    private static boolean flag;

    private boolean isUploadImageSuccessful = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shop);
        registerPresenterImp = new RegisterPresenterImp(this, this);
        new MyToolbarExtra(this, getString(R.string.register_shop), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });

        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (MyHelper.checkPermission(PERMISSIONS, this) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            }
        }
    }

    private void initView(){
        edtName = findViewById(R.id.edt_name);
        edtslogan = findViewById(R.id.edt_slogan);
        edtPhone = findViewById(R.id.edt_phone);
        edtAddress = findViewById(R.id.edt_address);
        edtWebsite = findViewById(R.id.edt_website);
        btnRegister = findViewById(R.id.btn_register);
        btnImgAvatar = findViewById(R.id.btn_img_avatar);
        btnImgCover = findViewById(R.id.btn_img_cover);
        btnUploadImage = findViewById(R.id.btn_upload_image);
        imgAvatar = findViewById(R.id.img_avatar);
        imgCover = findViewById(R.id.img_cover);

        imgAvatar.setVisibility(View.GONE);
        imgCover.setVisibility(View.GONE);

        btnRegister.setOnClickListener(this);
        btnImgAvatar.setOnClickListener(this);
        btnImgCover.setOnClickListener(this);
        btnUploadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_img_avatar:
                addPhoto(true);
                break;
            case R.id.btn_img_cover:
                addPhoto(false);
                break;
            case R.id.btn_upload_image:
                uploadImage();
            default:
                break;
        }
    }

    private void register() {
        String name = edtName.getText().toString().trim();
        String slogan = edtslogan.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String website = edtWebsite.getText().toString().trim();

        if (name.matches("") || slogan.matches("") || phone.matches("") || address.matches("")){
            Toast.makeText(this, getString(R.string.please_enter_all), Toast.LENGTH_SHORT).show();
        }else if (avatar == null || cover == null){
            Toast.makeText(this, getString(R.string.not_choose_image), Toast.LENGTH_SHORT).show();
        }else {
            Shop shop = new Shop(0, name, slogan, avatar.getName(), cover.getName(),
                    MyHelper.getUserIdPreference(this), address, phone, website, 0, false);
            registerPresenterImp.register(shop);
        }
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
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data, flag);
            } else if (requestCode == REQUEST_CAMERA){
                onCaptureImageResult(data, flag);
            }
        }
    }

    private void onCaptureImageResult(Intent data, boolean flag) {
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

        if (flag == true){
            avatar = new MyImage(name, thumbnail);
            imgAvatar.setVisibility(View.VISIBLE);
            imgAvatar.setImageBitmap(avatar.getBitmap());
        }else {
            cover = new MyImage(name, thumbnail);
            imgCover.setVisibility(View.VISIBLE);
            imgCover.setImageBitmap(cover.getBitmap());
        }
    }

    private void onSelectFromGalleryResult(Intent data, boolean flag) {
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

        if (flag == true){
            avatar = new MyImage(name, bm);
            imgAvatar.setVisibility(View.VISIBLE);
            imgAvatar.setImageBitmap(avatar.getBitmap());
        }else {
            cover = new MyImage(name, bm);
            imgCover.setVisibility(View.VISIBLE);
            imgCover.setImageBitmap(cover.getBitmap());
        }

    }

    private void addPhoto(final boolean isAvatar){
        ChooseImageDialog dialog = new ChooseImageDialog(this, new ChooseImageDialog.OptionListener() {
            @Override
            public void onCapture() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                flag = isAvatar;
                startActivityForResult(intent, REQUEST_CAMERA);
            }

            @Override
            public void onChooseGallery() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                flag = isAvatar;
                startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void uploadImage(){
        if (avatar == null || cover == null){
            Toast.makeText(this, getString(R.string.not_choose_image), Toast.LENGTH_SHORT).show();
        }else {
            ArrayList<MyImage> listMyImage = new ArrayList<>();
            listMyImage.add(avatar);
            listMyImage.add(cover);
            registerPresenterImp.uploadImage(listMyImage);
        }
    }

    @Override
    public void resgisterSuccessful() {
        Toast.makeText(this, getString(R.string.register_successful), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void registerFalse() {
        Toast.makeText(this, getString(R.string.register_false), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void uploadImageSucessful() {
        isUploadImageSuccessful = true;
        btnUploadImage.setEnabled(false);
        Toast.makeText(this, getString(R.string.upload_successful), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadImageFalse() {
        isUploadImageSuccessful = false;
        Toast.makeText(this, getString(R.string.file_size_too_large), Toast.LENGTH_SHORT).show();
    }
}
