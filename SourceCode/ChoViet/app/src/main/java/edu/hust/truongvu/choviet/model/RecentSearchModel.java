package edu.hust.truongvu.choviet.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import edu.hust.truongvu.choviet.database.MyDb;

import static edu.hust.truongvu.choviet.database.MyDb.COLUMN_ID_SEARCH;
import static edu.hust.truongvu.choviet.database.MyDb.TABLE_RECENT_SEARCH;

/**
 * Created by truon on 5/6/2018.
 */

public class RecentSearchModel {
    SQLiteDatabase database;

    public void openDatabase(Context context){
        MyDb myDb = new MyDb(context);
        database = myDb.getWritableDatabase();
    }

    public void closeDatabse(){
        database.close();
    }

    public ArrayList<String> getListRecentSearch(){
        try{
            Cursor cursor = database.query(TABLE_RECENT_SEARCH,
                    new String[]{MyDb.COLUMN_ID_SEARCH, MyDb.COLUMN_CONTENT_SEARCH},
                    null,
                    null,
                    null,
                    null,
                    COLUMN_ID_SEARCH + " DESC",
                    5+"");
            return convertCursorToRecentSearch(cursor);
        }catch (Exception e){
            Log.e("err_db", e.toString());
            return new ArrayList<>();
        }

    }

    public boolean addRecentSearch(String txt){
        ContentValues values = new ContentValues();
        values.put(MyDb.COLUMN_CONTENT_SEARCH, txt);
        return database.insert(TABLE_RECENT_SEARCH, null, values) > 0;
    }

    private ArrayList<String> convertCursorToRecentSearch(Cursor cursor){
        if (cursor == null || cursor.getCount() <= 0){
            return new ArrayList<>();
        }
        ArrayList<String> data = new ArrayList<>();
        cursor.moveToFirst();
        String search = "";
        while (true) {
            search = cursor.getString(cursor.getColumnIndex(MyDb.COLUMN_CONTENT_SEARCH));
            data.add(search);
            if (cursor.isLast()) {
                break;
            }
            cursor.moveToNext();
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return data;
    }
}
