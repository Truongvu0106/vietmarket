package edu.hust.truongvu.choviet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by truon on 3/24/2018.
 */

public class MyDb extends SQLiteOpenHelper {
    public static final String DB_NAME = "vietmarket";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "cart";
    public static final String COLUMN_ID_PRODUCT = "id_product";
    public static final String COLUMN_NAME_PRODUCT = "name_product";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DISCOUNT = "discount";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_NUMBER_SELECT = "number_select";
    public static final String COLUMN_IMAGE = "image";

    private Context context;
    public MyDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query_create_table = "create table " + TABLE_NAME + " ("
                + COLUMN_ID_PRODUCT + " integer primary key, "
                + COLUMN_NAME_PRODUCT + " text, "
                + COLUMN_PRICE + " real, "
                + COLUMN_DISCOUNT + " integer, "
                + COLUMN_AMOUNT + " integer, "
                + COLUMN_NUMBER_SELECT + " integer, "
                + COLUMN_IMAGE + " text" + ");";
        sqLiteDatabase.execSQL(query_create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
