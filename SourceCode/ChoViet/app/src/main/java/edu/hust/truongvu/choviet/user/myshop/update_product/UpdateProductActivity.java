package edu.hust.truongvu.choviet.user.myshop.update_product;

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
import android.util.Log;
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
import edu.hust.truongvu.choviet.helper.MyHelper;
import edu.hust.truongvu.choviet.helper.customview.MyToolbarExtra;
import edu.hust.truongvu.choviet.model.entity.Brand;
import edu.hust.truongvu.choviet.model.entity.ChildCategory;
import edu.hust.truongvu.choviet.model.entity.MyImage;
import edu.hust.truongvu.choviet.model.entity.Product;
import edu.hust.truongvu.choviet.user.myshop.add_product.ChooseImageDialog;
import edu.hust.truongvu.choviet.user.myshop.add_product.SpinnerBrandAdapter;
import edu.hust.truongvu.choviet.user.myshop.add_product.SpinnerCategoryAdapter;
import edu.hust.truongvu.choviet.user.myshop.list_product.ShopListProductActivity;

public class UpdateProductActivity extends AppCompatActivity implements UpdateProductView, View.OnClickListener{
    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_PERMISSIONS = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final int SELECT_FILE = 3;

    Product mProduct;
    private ArrayList<String> mlistNameImage = new ArrayList<>();
    private ArrayList<MyImage> mListMyImage = new ArrayList<>();
    private EditText edtName, edtWeight, edtDes, edtStock, edtPrice;
    private Spinner spinBrand, spinCategory, spinUnit;

    private View btnAddPhoto, btnUploadImage, btnUpdateProduct;
    private RecyclerView recyclerImgProduct;
    private ListUpdateImageAdapter adapter;
    private SpinnerBrandAdapter brandAdapter;
    private SpinnerCategoryAdapter categoryAdapter;
    private UpdateProductPresenter updateProductPresenter;

    private String unit = "";
    private int mCategory, mBrand;
    private boolean isUploadImageSuccessful = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        new MyToolbarExtra(this, getString(R.string.update_product), 0, new MyToolbarExtra.OnExtraToolbarListener() {
            @Override
            public void onMoreClick() {

            }

            @Override
            public void onBackClick() {
                onBackPressed();
            }
        });
        mProduct = (Product) getIntent().getSerializableExtra(ShopListProductActivity.TAG_PRODUCT);
        updateProductPresenter = new UpdateProductPresenterImp(this, this);
        initView();
        updateProductPresenter.initProduct(mProduct);
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
        btnUpdateProduct = findViewById(R.id.btn_add);
        recyclerImgProduct = findViewById(R.id.list_image);


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
        btnUpdateProduct.setOnClickListener(this);
    }

    @Override
    public void loadProductSuccessful(Product product) {
        edtName.setText(product.getName());
        edtWeight.setText(MyHelper.getWeight(product.getWeight()));
        if (MyHelper.getUnit(product.getWeight()).matches(getString(R.string.g))){
            spinUnit.setSelection(1);
        }else {
            spinUnit.setSelection(0);
        }

        edtDes.setText(product.getInfomation());
        edtStock.setText(product.getAmount() + "");
        edtPrice.setText(product.getPrice() + "");

//        mlistNameImage = product.getImgs();
        for (String s : product.getImgs()){
            mlistNameImage.add(MyHelper.convertPathToName(s));
        }

        for (String s : product.getImgs()){
            mListMyImage.add(MyHelper.convertPathToMyImage(s));
        }
        adapter = new ListUpdateImageAdapter(this, mListMyImage, new ListUpdateImageAdapter.ImageListener() {

            @Override
            public void onClear(MyImage myImage) {
                mlistNameImage.remove(myImage.getName());
                mListMyImage.remove(myImage);
                adapter.notifyDataSetChanged();
            }

        });
        recyclerImgProduct.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        recyclerImgProduct.setAdapter(adapter);

        updateProductPresenter.initListBrand();
        updateProductPresenter.initListCategory();
    }

    @Override
    public void loadProductFalse() {
        btnUpdateProduct.setEnabled(false);
        btnUploadImage.setEnabled(false);
    }

    @Override
    public void initListBrandSuccessful(final ArrayList<Brand> data) {
        brandAdapter = new SpinnerBrandAdapter(this, android.R.layout.simple_spinner_item, data);
        spinBrand.setAdapter(brandAdapter);

        Brand brand = null;
        for (Brand b : data){
            if (b.getId() == mProduct.getBrand()){
                brand = b;
                break;
            }
        }
        if (brand != null){
            spinBrand.setSelection(brandAdapter.getPosition(brand));
        }

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
    public void initListBrandFalse() {

    }

    @Override
    public void initListCategorySuccessful(final ArrayList<ChildCategory> data) {
        categoryAdapter = new SpinnerCategoryAdapter(this, android.R.layout.simple_spinner_item, data);
        spinCategory.setAdapter(categoryAdapter);
        ChildCategory childCategory = null;
        for (ChildCategory c : data){
            if (c.getId() == mProduct.getTypeProduct()){
                childCategory = c;
                break;
            }
        }
        if (childCategory != null){
            spinCategory.setSelection(categoryAdapter.getPosition(childCategory));
        }
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
    public void initListCategoryFalse() {

    }

    @Override
    public void uploadImageSuccessful() {
        isUploadImageSuccessful = true;
        MyHelper.showToast(this, getString(R.string.upload_successful), FancyToast.SUCCESS);
    }

    @Override
    public void uploadImageFalse() {
//        Toast.makeText(this, getString(R.string.file_size_too_large), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateSuccessful() {
        MyHelper.showToast(this, getString(R.string.update_successful), FancyToast.SUCCESS);

        onBackPressed();
    }

    @Override
    public void updateFalse() {
        MyHelper.showToast(this, getString(R.string.update_false), FancyToast.ERROR);

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
                updateProduct();
                break;
            default:
                break;
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
        mListMyImage.add(myImage);
        mlistNameImage.add(name);
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
        mlistNameImage.add(name);
        mListMyImage.add(myImage);
        adapter.notifyDataSetChanged();
    }

    private void updateProduct() {
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

        ArrayList<String> images = mlistNameImage;
        boolean isChanged = false;
        if (images.isEmpty()){
            MyHelper.showToast(this, getString(R.string.not_upload_image), FancyToast.WARNING);

            return;
        }else {
            for (String s : images){
                if (!s.contains(".")){
                    isChanged = true;
                    break;
                }
            }
        }

        if (isChanged){
            if (!isUploadImageSuccessful){
                MyHelper.showToast(this, getString(R.string.not_upload_image), FancyToast.WARNING);

                return;
            }
        }



        String weight = "";
        if (stockStr.matches("")){
            weight = "???";
        }else {
            weight = weightStr + " " + unit;
        }

        int stock = Integer.parseInt(stockStr);
        long price = Long.parseLong(priceStr);

        Product product = new Product(mProduct.getId(), name, price, 0, images, des, weight, mCategory, mBrand, mProduct.getRate(), stock, mProduct.getIdShop(), false, false);
        updateProductPresenter.updateProduct(product);
    }

    private void uploadImage(){
        if (mListMyImage.size() == 0){
            MyHelper.showToast(this, getString(R.string.upload_successful), FancyToast.SUCCESS);

            isUploadImageSuccessful = true;
        }else {
            for (String s : mlistNameImage){
                Log.e("MyImage", s);
            }
            updateProductPresenter.uploadImage(mListMyImage);
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


}
