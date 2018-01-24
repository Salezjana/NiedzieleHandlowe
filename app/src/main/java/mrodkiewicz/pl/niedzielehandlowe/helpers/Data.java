package mrodkiewicz.pl.niedzielehandlowe.helpers;

import android.util.Log;

import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;

import mrodkiewicz.pl.niedzielehandlowe.BuildConfig;


public class Data {
    private Boolean DEBUG = BuildConfig.DEBUG;
    private String TAG = getClass().getSimpleName() + " flag";

    private LocalDate today = LocalDate.now();
    private LocalDate closestSunday;
    private LocalDate closestCloseSunday;
    public ArrayList<LocalDate> dateCloseList = new ArrayList<LocalDate>();

    public Data() {
        init();
    }

    public void init() {
        dateCloseList.add(new LocalDate(2018, 3, 11));
        dateCloseList.add(new LocalDate(2018, 3, 18));
        dateCloseList.add(new LocalDate(2018, 4, 1));
        dateCloseList.add(new LocalDate(2018, 4, 8));
        dateCloseList.add(new LocalDate(2018, 4, 15));
        dateCloseList.add(new LocalDate(2018, 4, 22));
        dateCloseList.add(new LocalDate(2018, 5, 13));
        dateCloseList.add(new LocalDate(2018, 5, 20));
        dateCloseList.add(new LocalDate(2018, 6, 10));
        dateCloseList.add(new LocalDate(2018, 6, 17));
        dateCloseList.add(new LocalDate(2018, 7, 8));
        dateCloseList.add(new LocalDate(2018, 7, 15));
        dateCloseList.add(new LocalDate(2018, 7, 22));
        dateCloseList.add(new LocalDate(2018, 8, 12));
        dateCloseList.add(new LocalDate(2018, 8, 19));
        dateCloseList.add(new LocalDate(2018, 9, 9));
        dateCloseList.add(new LocalDate(2018, 9, 16));
        dateCloseList.add(new LocalDate(2018, 9, 23));
        dateCloseList.add(new LocalDate(2018, 10, 14));
        dateCloseList.add(new LocalDate(2018, 10, 21));
        dateCloseList.add(new LocalDate(2018, 11, 11));
        dateCloseList.add(new LocalDate(2018, 11, 18));
        dateCloseList.add(new LocalDate(2018, 12, 9));

        if (DEBUG) {
            Log.d(TAG,"Today " +  today);
            for (LocalDate localDate : dateCloseList) {
                Log.d(TAG, "LocalDate dateCloseList " + localDate);
                Log.d(TAG, "daysBetween dateCloseList " + Days.daysBetween(localDate, today).getDays());
            }
        }

        initClosestSunday();
        initClosestCloseSunday();


    }

    private void initClosestSunday() {
        closestSunday = today.withDayOfWeek(DateTimeConstants.SUNDAY);
        if (DEBUG){
            Log.d(TAG,"initClosestSunday" + closestSunday);
        }
    }

    private void initClosestCloseSunday() {
        int i,j = -10000000;
        for (LocalDate localDate : dateCloseList) {
            i = Days.daysBetween(localDate, today).getDays();
            if (i < 0) {
                if (j < i){
                    j = i;
                    closestCloseSunday = localDate;
                }

            }else if (i == 0){
                closestCloseSunday = today;
            }

        }

        if (DEBUG){
            Log.d(TAG,"initClosestCloseSunday " + closestCloseSunday);
        }
    }

    public boolean isNextSundayClose() {
        if (Days.daysBetween(closestCloseSunday,closestSunday).getDays() == 0) return true;
        else return false;
    }

    public ArrayList<LocalDate> getDateCloseList() {
        return dateCloseList;
    }
}