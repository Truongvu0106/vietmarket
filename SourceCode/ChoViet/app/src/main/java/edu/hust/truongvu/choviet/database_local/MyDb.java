package edu.hust.truongvu.choviet.database_local;

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
    public static final String COLUMN_IDSHOP = "id_shop";
    public static final String COLUMN_NUMBER_SELECT = "number_select";
    public static final String COLUMN_IMAGE = "image";

    public static final String TABLE_RECENT_SEARCH = "recent_search";
    public static final String COLUMN_ID_SEARCH = "id_search";
    public static final String COLUMN_CONTENT_SEARCH = "content_search";

    private Context context;
    public MyDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query_create_table_cart = "create table " + TABLE_NAME + " ("
                + COLUMN_ID_PRODUCT + " integer primary key, "
                + COLUMN_NAME_PRODUCT + " text, "
                + COLUMN_PRICE + " real, "
                + COLUMN_DISCOUNT + " integer, "
                + COLUMN_AMOUNT + " integer, "
                + COLUMN_IDSHOP + " integer, "
                + COLUMN_NUMBER_SELECT + " integer, "
                + COLUMN_IMAGE + " text" + ");";

        String query_create_table_search = "create table " + TABLE_RECENT_SEARCH + " ("
                + COLUMN_ID_SEARCH + " integer primary key autoincrement, "
                + COLUMN_CONTENT_SEARCH + " text" + ");";
        sqLiteDatabase.execSQL(query_create_table_cart);
        sqLiteDatabase.execSQL(query_create_table_search);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_RECENT_SEARCH);
        onCreate(sqLiteDatabase);
    }
}
