package edu.hust.truongvu.choviet.user.myshop.add_product;

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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import edu.hust.truongvu.choviet.R;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.user.myshop.MyShopActivity;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener, AddProductView{
    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_PERMISSIONS = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final int SELECT_FILE = 3;

    private EditText edtName, edtWeight, edtDes, edtStock, edtPrice;
    private Spinner spinBrand, spinCategory, spinUnit;

    private View btnAddPhoto, btnUploadImage, btnAddProduct;
    private RecyclerView recyclerImgProduct;
    private ListAddImageAdapter adapter;
    private SpinnerBrandAdapter brandAdapter;
    private SpinnerCategoryAdapter categoryAdapter;
    private ArrayList<MyImage> listMyImage;
    private AddProductPresenterImp addProductPresenterImp;

    private String unit = "";
    private int idShop;
    private int mCategory, mBrand;
    private boolean isUploadImageSuccessful = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        new MyToolbarExtra(this, getString(R.string.add_new_product), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });

        addProductPresenterImp = new AddProductPresenterImp(this, this);
        idShop = getIntent().getIntExtra(MyShopActivity.TAG_SHOP, 0);
        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (MyHelper.checkPermission(PERMISSIONS, this) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
            }
        }

        addProductPresenterImp.initListCategory();
        addProductPresenterImp.initListBrand();

    }

    private void initView(){
        edtName = findViewById(R.id.edt_name);
        edtWeight = findViewById(R.id.edt_weight);
        edtDes = findViewById(R.id.edt_des);
        edtStock = findViewById(R.id.edt_stock);
        edtPrice = findViewById(R.id.edt_price);
        spinBrand = findViewById(R.id.spinner_brand);
        spinCategory = findViewById(R.id.spinner_category);
        spinUnit = findViewById(R.id.spinner_weight_unit);
        btnAddPhoto = findViewById(R.id.btn_add_photo);
        btnUploadImage = findViewById(R.id.btn_upload_image);
        btnAddProduct = findViewById(R.id.btn_add);
        recyclerImgProduct = findViewById(R.id.list_image);
        listMyImage = new ArrayList<>();
        adapter = new ListAddImageAdapter(listMyImage, new ListAddImageAdapter.ClearImageListener() {
            @Override
            public void onClear(MyImage myImage) {

            }
        });
        recyclerImgProduct.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        recyclerImgProduct.setAdapter(adapter);

        final ArrayList<String> listUnits = new ArrayList<>();
        listUnits.add(getString(R.string.kg));
        listUnits.add(getString(R.string.g));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listUnits);
        spinUnit.setAdapter(arrayAdapter);
        spinUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unit = listUnits.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAddPhoto.setOnClickListener(this);
        btnUploadImage.setOnClickListener(this);
        btnAddProduct.setOnClickListener(this);
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
            case R.id.btn_add:
                addProduct();
                break;
        }
    }

    private void addProduct() {
        String name = edtName.getText().toString();
        String weightStr = edtWeight.getText().toString();
        String des = edtDes.getText().toString();
        String stockStr = edtStock.getText().toString();
        String priceStr = edtPrice.getText().toString();

        if (name.matches("") || weightStr.matches("") || des.matches("") || priceStr.matches("") ){
            MyHelper.showToast(this, getString(R.string.please_enter_all), FancyToast.WARNING);
            return;
        }

        if (!stockStr.matches("")){
            if (unit.matches("")){
                MyHelper.showToast(this, getString(R.string.not_select_unit), FancyToast.WARNING);

                return;
            }
        }

        if (mBrand == 0){
            MyHelper.showToast(this, getString(R.string.not_select_brand), FancyToast.WARNING);

            return;
        }

        if (mCategory == 0){
            MyHelper.showToast(this, getString(R.string.not_select_category), FancyToast.WARNING);

            return;
        }

        ArrayList<String> images = new ArrayList<>();
        if (!isUploadImageSuccessful){
            MyHelper.showToast(this, getString(R.string.not_upload_image), FancyToast.WARNING);

            return;
        }else {
            for (MyImage myImage : listMyImage){
                images.add(myImage.getName());
            }
        }
        String weight = "";
        if (weightStr.matches("")){
            weight = "";
        }else {
            weight = weightStr + " " + unit;
        }

        int stock = Integer.parseInt(stockStr);
        long price = Long.parseLong(priceStr);
        Product product = new Product(0, name, price, 0, images, des, weight, mCategory, mBrand, 0, stock, idShop, false, false);
        addProductPresenterImp.addProduct(product);
    }

    private void uploadImage(){
        if (listMyImage.size() == 0){
            MyHelper.showToast(this, getString(R.string.not_choose_image), FancyToast.WARNING);

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
        isUploadImageSuccessful = true;
        MyHelper.showToast(this, getString(R.string.upload_successful), FancyToast.SUCCESS);
    }

    @Override
    public void uploadImageFalse() {
        isUploadImageSuccessful = false;
        MyHelper.showToast(this, getString(R.string.file_size_too_large), FancyToast.ERROR);

    }

    @Override
    public void addProductSuccessful() {
        MyHelper.showToast(this, getString(R.string.add_successful), FancyToast.SUCCESS);
        startActivity(new Intent(this, MyShopActivity.class));
    }

    @Override
    public void addProductFalse() {
        MyHelper.showToast(this, getString(R.string.add_false), FancyToast.ERROR);

    }

    @Override
    public void loadListCategorySuccessful(final ArrayList<ChildCategory> data) {
        categoryAdapter = new SpinnerCategoryAdapter(this, android.R.layout.simple_spinner_item, data);
        spinCategory.setAdapter(categoryAdapter);
        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCategory = data.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void loadListCategoryFalse() {

    }

    @Override
    public void loadListBrandSuccessful(final ArrayList<Brand> data) {
        brandAdapter = new SpinnerBrandAdapter(this, android.R.layout.simple_spinner_item, data);
        spinBrand.setAdapter(brandAdapter);
        spinBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mBrand = data.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void loadListBrandFalse() {

    }
}
