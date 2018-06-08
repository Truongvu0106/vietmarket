package edu.hust.truongvu.choviet.admin.category.parent;

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
import android.widget.Button;
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
import edu.hust.truongvu.choviet.admin.category.child.ChildCategoryActivity;
import edu.hust.truongvu.choviet.admin.category.child.ManageChildActivity;
import edu.hust.truongvu.choviet.admin.category.child.ManageChildPresenter;
import edu.hust.truongvu.choviet.admin.category.child.ManageChildPresenterImp;
import edu.hust.truongvu.choviet.helper.Constants;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.ConfirmDialog;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.ParentCategory;
import edu.hust.truongvu.choviet.user.myshop.add_product.ChooseImageDialog;

public class ManageParentActivity extends AppCompatActivity implements ManageParentView, View.OnClickListener{
    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_PERMISSIONS = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final int SELECT_FILE = 3;

    private boolean isUpdate = false;
    private ParentCategory mParent;
    private EditText edtTitle;
    private ImageView icon, imgBanner;
    private View btnImg;
    private Button btnCru, btnUploadImg;

    private ManageParentPresenter presenter;

    private MyImage myImage;
    private boolean isUploadSuccessful = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_parent);
        isUpdate = getIntent().getBooleanExtra(ChildCategoryActivity.IS_UPDATE, false);

        if (isUpdate) {
            mParent = ParentCategoryFragment.mParent;
        }

        setUpToolbar();

        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (MyHelper.checkPermission(PERMISSIONS, this) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            }
        }

        presenter = new ManageParentPresenterImp(this, this);
    }

    private void initView() {
        edtTitle = findViewById(R.id.edt_title);
        imgBanner = findViewById(R.id.img_banner);
        btnImg = findViewById(R.id.btn_img_banner);
        icon = findViewById(R.id.icon);
        btnCru = findViewById(R.id.btn_cru);
        btnUploadImg = findViewById(R.id.btn_upload_image);

        if (isUpdate) {
            btnCru.setText(getString(R.string.update));
        } else {
            btnCru.setText(getString(R.string.add));
        }

        if (mParent != null) {
            edtTitle.setText(mParent.getName());
            MyHelper.setImagePicasso(this, imgBanner, Constants.MY_PATH + mParent.getPath_img());
            icon.setVisibility(View.GONE);
            imgBanner.setVisibility(View.VISIBLE);
        }

        btnCru.setOnClickListener(this);
        btnImg.setOnClickListener(this);
        btnUploadImg.setOnClickListener(this);
    }

    private void setUpToolbar() {
        String title = "";
        if (isUpdate) {
            title = getString(R.string.update);
        } else {
            title = getString(R.string.add);
        }
        new MyToolbarExtra(this, title, 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                back();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cru:
                if (isUpdate) {
                    updateChild();
                } else {
                    addChild();
                }
                break;
            case R.id.btn_upload_image:
                uploadImage();
                break;
            case R.id.btn_img_banner:
                addImage();
                break;
        }
    }

    private void addChild() {
        String title = edtTitle.getText().toString().trim();
        if (title.matches("")) {
            Toast.makeText(this, getString(R.string.please_enter_all), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isUploadSuccessful) {
            Toast.makeText(this, getString(R.string.not_upload_image), Toast.LENGTH_SHORT).show();
            return;
        }

        ParentCategory parent = new ParentCategory(0, title, 0, myImage.getName());
        presenter.addParent(parent);
    }

    private void updateChild() {
        final String title = edtTitle.getText().toString().trim();
        if (title.matches("")) {
            Toast.makeText(this, getString(R.string.please_enter_all), Toast.LENGTH_SHORT).show();
            return;
        }
        if (myImage == null) {
            ConfirmDialog dialog = new ConfirmDialog(this, getString(R.string.do_u_want_to_update), new ConfirmDialog.ConfirmListener() {
                @Override
                public void onCancel() {

                }

                @Override
                public void onOk() {
                    ParentCategory parent = new ParentCategory(mParent.getId(), title, 0, mParent.getPath_img());
                    presenter.updateParent(parent, false);
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }else {
            ConfirmDialog dialog = new ConfirmDialog(this, getString(R.string.do_u_want_to_update), new ConfirmDialog.ConfirmListener() {
                @Override
                public void onCancel() {

                }

                @Override
                public void onOk() {
                    ParentCategory parent = new ParentCategory(mParent.getId(), title, 0, myImage.getName());
                    presenter.updateParent(parent, true);
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
    }

    private void uploadImage() {
        if (myImage == null) {
            Toast.makeText(this, getString(R.string.not_choose_image), Toast.LENGTH_SHORT).show();
        } else {
            presenter.uploadImage(myImage);
        }
    }

    private void addImage() {
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
                startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
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
        String name = new Random().nextInt((1000 - 1) + 1) + 1 + "_" + time;
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

        myImage = new MyImage(name, thumbnail);
        imgBanner.setImageBitmap(thumbnail);
        imgBanner.setVisibility(View.VISIBLE);
    }

    private void onSelectFromGalleryResult(Intent data) {
        long time = System.currentTimeMillis();
        String name = new Random().nextInt((1000 - 1) + 1) + 1 + "_" + time;
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        myImage = new MyImage(name, bm);
        imgBanner.setImageBitmap(bm);
        imgBanner.setVisibility(View.VISIBLE);
    }

    private void back(){
        if (isUpdate) {
            ConfirmDialog dialog = new ConfirmDialog(ManageParentActivity.this, getString(R.string.do_u_really_want_to_exit), new ConfirmDialog.ConfirmListener() {
                @Override
                public void onCancel() {

                }

                @Override
                public void onOk() {
                    onBackPressed();
                }
            });
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        } else {
            onBackPressed();
        }
    }

    @Override
    public void loadListParentSuccessful(ArrayList<ParentCategory> data) {

    }

    @Override
    public void loadListParentFalse() {

    }

    @Override
    public void addParentSuccessful() {
        Toast.makeText(this, getString(R.string.add_successful), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void addParentFalse() {
        Toast.makeText(this, getString(R.string.add_false), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadNewImageSuccessful() {
        Toast.makeText(this, getString(R.string.upload_successful), Toast.LENGTH_SHORT).show();
        isUploadSuccessful = true;
    }

    @Override
    public void uploadNewImageFalse() {
        Toast.makeText(this, getString(R.string.file_size_too_large), Toast.LENGTH_SHORT).show();
        isUploadSuccessful = false;
    }

    @Override
    public void updateParentSuccessful() {
        Toast.makeText(this, getString(R.string.update_successful), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void updateParentFalse() {
        Toast.makeText(this, getString(R.string.update_false), Toast.LENGTH_SHORT).show();
    }


}
