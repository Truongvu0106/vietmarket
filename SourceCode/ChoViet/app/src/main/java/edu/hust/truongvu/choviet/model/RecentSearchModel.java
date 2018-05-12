package edu.hust.truongvu.choviet.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        Cursor cursor = database.query(TABLE_RECENT_SEARCH,
                new String[]{MyDb.COLUMN_ID_SEARCH, MyDb.COLUMN_CONTENT_SEARCH},
                null,
                null,
                null,
                null,
                COLUMN_ID_SEARCH + " DESC",
                5+"");
        return convertCursorToRecentSearch(cursor);
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
