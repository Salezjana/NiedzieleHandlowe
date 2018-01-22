package mrodkiewicz.pl.niedzielehandlowe.helpers;

import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.FormatFlagsConversionMismatchException;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Calendar.DAY_OF_WEEK;



public class Data {
    final Long today = Calendar.getInstance().getTimeInMillis();

    public ArrayList<Date> dateCloseList = new ArrayList<java.util.Date>();
    public ArrayList<Date> nextSundays = new ArrayList<java.util.Date>();
    public ArrayList<Date> allSundays = new ArrayList<java.util.Date>();
    public Date closestSunday, closestCloseSunday;

    public Data() {
        init();
    }

    public void init() {
        dateCloseList.add(new Date(118, 2, 11));
        dateCloseList.add(new Date(118, 2, 18));
        dateCloseList.add(new Date(118, 3, 1));
        dateCloseList.add(new Date(118, 3, 8));
        dateCloseList.add(new Date(118, 3, 15));
        dateCloseList.add(new Date(118, 3, 22));
        dateCloseList.add(new Date(118, 4, 12));
        dateCloseList.add(new Date(118, 4, 20));
        dateCloseList.add(new Date(118, 5, 10));
        dateCloseList.add(new Date(118, 5, 17));
        dateCloseList.add(new Date(118, 6, 8));
        dateCloseList.add(new Date(118, 6, 15));
        dateCloseList.add(new Date(118, 6, 22));
        dateCloseList.add(new Date(118, 7, 12));
        dateCloseList.add(new Date(118, 7, 19));
        dateCloseList.add(new Date(118, 8, 9));
        dateCloseList.add(new Date(118, 8, 16));
        dateCloseList.add(new Date(118, 8, 23));
        dateCloseList.add(new Date(118, 9, 14));
        dateCloseList.add(new Date(118, 9, 21));
        dateCloseList.add(new Date(118, 10, 11));
        dateCloseList.add(new Date(118, 10, 18));
        dateCloseList.add(new Date(118, 11, 9));

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+1"));



        initAllSundaysInYear();
        initAllNextSundays();
        initClosestCloseSunday();
        initClosestSunday();

        //zabezpiecznie przed fck up
//        if (closestSunday.before(new Date(today))){
//            closestSunday = new Date(closestSunday.getYear(),closestCloseSunday.getMonth(),closestCloseSunday.getDay() + 7);
//        }

        Log.d("DATA timezone", TimeZone.getDefault().getDisplayName());
        Log.d("DATA najblizsza",closestSunday.toString()+" closestSunday");
        Log.d("DATA zamknieta",closestCloseSunday.toString()+" closestCloseSunday");



    }

    private void initClosestSunday() {
        //pobieranie najblizszej niedzieli
        // DZIALA !
        closestSunday = Collections.min(nextSundays, new Comparator<Date>() {
            public int compare(Date d1, Date d2) {
                long diff1 = Math.abs(d1.getTime() - today);
                long diff2 = Math.abs(d2.getTime() - today);
                return Long.compare(diff1, diff2);
            }
        });
    }

    private void initClosestCloseSunday() {
        //pobieranie najblizszej niedzieli zamknietej
        // NIE DZIALA !!!!
        closestCloseSunday = Collections.min(dateCloseList, new Comparator<Date>() {
            public int compare(Date d1, Date d2) {
                long diff1 = Math.abs(d1.getTime() - today);
                long diff2 = Math.abs(d2.getTime() - today);
                return Long.compare(diff1, diff2);
            }
        });
        Log.d("DATA 12wazna",closestCloseSunday.toString());
    }

    private void initAllNextSundays() {
        //pobieranie wszytkich niedziel
        int dayOfWeek = Calendar.SUNDAY;
        Calendar cal = new GregorianCalendar();
        cal.set(2018, 0, 0, 0, 0);
        cal.set(DAY_OF_WEEK, dayOfWeek);

        while (cal.get(Calendar.YEAR) == 2018) {
            allSundays.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 7);
        }

    }

    private void initAllSundaysInYear() {
        //pobieranie wszytkich W ROKU niedziel
        int i = Calendar.SUNDAY;
        Calendar cal1 = new GregorianCalendar();
        cal1.set(2018, 1, 1);
        cal1.set(DAY_OF_WEEK, i);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2018,1,1);
        Log.d("DATA 1wazna",cal1.getTime().toString());
        Log.d("DATA 1wazna",cal2.getTime().toString());

        while (cal1.get(Calendar.YEAR) == 2018) {
            nextSundays.add(cal1.getTime());
            long diff = cal1.getTimeInMillis() - cal2.getTimeInMillis();
            //Log.d("DATA dni",diff+"");
            Log.d("DATA wazna",cal1.getTime().toString());
            cal1.add(Calendar.DAY_OF_MONTH, 7);
        }
    }

    public boolean isNextSundayClose() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(closestCloseSunday);
        cal2.setTime(closestSunday);
        cal1.setTimeZone(TimeZone.getTimeZone("GTM+01"));
        cal1.setTimeZone(TimeZone.getTimeZone("GTM+01"));
        boolean isClose = cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
//        Log.d("WAZSZNE1 zamknieta",cal1.get(Calendar.DAY_OF_YEAR)+"");
//        Log.d("WAZSZNE najblizsza",cal2.get(Calendar.DAY_OF_YEAR) + "");
//        Log.d("WAZSZNE1 zamknieta",cal1.get(Calendar.DAY_OF_MONTH)+" " + cal1.get(Calendar.MONTH)+"");
//        Log.d("WAZSZNE najblizsza",cal2.get(Calendar.DAY_OF_MONTH) + "");
        return isClose;
    }

    public Date closestSunday() {
        return closestSunday;
    }

    public Date closestCloseSunday() {
        return closestCloseSunday;
    }

}