package edu.hust.truongvu.choviet.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.database.MyDb;
import edu.hust.truongvu.choviet.entity.Product;

import static edu.hust.truongvu.choviet.database.MyDb.COLUMN_AMOUNT;
import static edu.hust.truongvu.choviet.database.MyDb.COLUMN_ID_PRODUCT;
import static edu.hust.truongvu.choviet.database.MyDb.TABLE_NAME;

/**
 * Created by truon on 3/24/2018.
 */

public class CartModel {
    SQLiteDatabase database;

    public void openDatabase(Context context){
        MyDb myDb = new MyDb(context);
        database = myDb.getWritableDatabase();
    }

    public void closeDatabse(){
        database.close();
    }

    public boolean addItemCart(Product product){
        ContentValues values = new ContentValues();
        values.put(MyDb.COLUMN_ID_PRODUCT, product.getId());
        values.put(MyDb.COLUMN_NAME_PRODUCT, product.getName());
        values.put(MyDb.COLUMN_PRICE, product.getPrice());
        values.put(MyDb.COLUMN_DISCOUNT, product.getDiscount());
        values.put(MyDb.COLUMN_AMOUNT, product.getAmount());
        values.put(MyDb.COLUMN_NUMBER_SELECT, 1);
        values.put(MyDb.COLUMN_IMAGE, product.getImgs().get(0));

        return database.insert(TABLE_NAME, null, values) > 0;

    }

    public ArrayList<Product> getAllItemCart(){
        Cursor cursor = database.query(TABLE_NAME,
                new String[]{MyDb.COLUMN_ID_PRODUCT, MyDb.COLUMN_NAME_PRODUCT, MyDb.COLUMN_PRICE, MyDb.COLUMN_DISCOUNT,
                        MyDb.COLUMN_AMOUNT, MyDb.COLUMN_NUMBER_SELECT, MyDb.COLUMN_IMAGE},
                null,
                null,
                null,
                null,
                null);
        return convertCursorToProduct(cursor);
    }

    public boolean updateNumberOfItem(int id_product, int number){
        ContentValues values = new ContentValues();
        values.put(MyDb.COLUMN_NUMBER_SELECT, number);
        return database.update(TABLE_NAME, values, COLUMN_ID_PRODUCT + " = " + id_product, null) > 0;
    }


    public boolean deleteItemCart(int id_product){
        return database.delete(TABLE_NAME, COLUMN_ID_PRODUCT + " = " + id_product, null) > 0;
    }

    public boolean deleteAllItemCart(){
        return database.delete(TABLE_NAME, null, null) > 0;
    }

    private ArrayList<Product> convertCursorToProduct(Cursor cursor){
        if (cursor == null || cursor.getCount() <= 0){
            return new ArrayList<>();
        }
        ArrayList<Product> products = new ArrayList<>();
        cursor.moveToFirst();
        Product product;
        while (true) {
            product = new Product();
            int id = cursor.getInt(cursor.getColumnIndex(MyDb.COLUMN_ID_PRODUCT));
            String name = cursor.getString(cursor.getColumnIndex(MyDb.COLUMN_NAME_PRODUCT));
            long price = cursor.getLong(cursor.getColumnIndex(MyDb.COLUMN_PRICE));
            int discount = cursor.getInt(cursor.getColumnIndex(MyDb.COLUMN_DISCOUNT));
            int amount = cursor.getInt(cursor.getColumnIndex(MyDb.COLUMN_AMOUNT));
            int number = cursor.getInt(cursor.getColumnIndex(MyDb.COLUMN_NUMBER_SELECT));
            String image = cursor.getString(cursor.getColumnIndex(MyDb.COLUMN_IMAGE));
            ArrayList<String> images = new ArrayList<>();
            images.add(image);

            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setDiscount(discount);
            product.setImgs(images);
            product.setAmount(amount);
            product.setNumberSelect(number);

            products.add(product);

            if (cursor.isLast()) {
                break;
            }
            cursor.moveToNext();
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return products;
    }
}
