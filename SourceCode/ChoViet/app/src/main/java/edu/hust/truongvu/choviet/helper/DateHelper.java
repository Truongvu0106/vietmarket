package edu.hust.truongvu.choviet.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by truon on 5/28/2018.
 */

public class DateHelper {
    public static ArrayList<Date> mListDate = new ArrayList<>();
    public DateHelper(){
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            c.set(Calendar.MONTH, i);
            mListDate.add(c.getTime());
        }
    }

    private static long getFirstDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTimeInMillis();
    }

    private static long getLastDateOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTimeInMillis();
    }

    public ArrayList<Long> getListFirstDate(){
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < mListDate.size(); i++) {
            long date = getFirstDateOfMonth(mListDate.get(i));
            list.add(date);
        }
        return list;
    }

    public ArrayList<Long> getListLastDate(){
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < mListDate.size(); i++) {
            long date = getLastDateOfMonth(mListDate.get(i));
            list.add(date);
        }
        return list;
    }
}
