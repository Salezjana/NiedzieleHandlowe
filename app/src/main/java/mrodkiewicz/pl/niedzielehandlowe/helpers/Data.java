package mrodkiewicz.pl.niedzielehandlowe.helpers;

import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by pc-mikolaj on 19.01.2018.
 */

public class Data {
    public ArrayList<Date> dateCloseList = new ArrayList<java.util.Date>();
    public ArrayList<Date> sundays = new ArrayList<java.util.Date>();
    public Date closestSunday,closestCloseSunday;
    public void init(){
        dateCloseList.add(new Date(2018,3,11));
        dateCloseList.add(new Date(2018,3,18));
        dateCloseList.add(new Date(2018,4,1));
        dateCloseList.add(new Date(2018,4,8));
        dateCloseList.add(new Date(2018,4,15));
        dateCloseList.add(new Date(2018,4,22));
        dateCloseList.add(new Date(2018,5,12));
        dateCloseList.add(new Date(2018,5,20));
        dateCloseList.add(new Date(2018,6,10));
        dateCloseList.add(new Date(2018,6,17));
        dateCloseList.add(new Date(2018,7,8));
        dateCloseList.add(new Date(2018,7,15));
        dateCloseList.add(new Date(2018,7,22));
        dateCloseList.add(new Date(2018,8,12));
        dateCloseList.add(new Date(2018,8,19));
        dateCloseList.add(new Date(2018,9,9));
        dateCloseList.add(new Date(2018,9,16));
        dateCloseList.add(new Date(2018,9,23));
        dateCloseList.add(new Date(2018,10,14));
        dateCloseList.add(new Date(2018,10,21));
        dateCloseList.add(new Date(2018,11,11));
        dateCloseList.add(new Date(2018,11,18));
        dateCloseList.add(new Date(2018,12,9));

        //pobieranie wszytkich niedziel
        int dayOfWeek = Calendar.SUNDAY;
        Calendar cal = new GregorianCalendar();
        cal.set(2018, 0, 1, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);

        while (cal.get(Calendar.YEAR) == 2018) {
            sundays.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 7);
        }

        //pobieranie najblizszej niedzieli zamknietej
        final Long today = Calendar.getInstance().getTimeInMillis();
        closestCloseSunday = Collections.min(dateCloseList, new Comparator<Date>() {
            public int compare(Date d1, Date d2) {
                long diff1 = Math.abs(d1.getTime() - today);
                long diff2 = Math.abs(d2.getTime() - today);
                return Long.compare(diff1, diff2);
            }
        });

        //pobieranie najblizszej niedzieli
        closestSunday = Collections.min(sundays, new Comparator<Date>() {
            public int compare(Date d1, Date d2) {
                long diff1 = Math.abs(d1.getTime() - today);
                long diff2 = Math.abs(d2.getTime() - today);
                return Long.compare(diff1, diff2);
            }
        });
    }
    public boolean isNextWeekOpen(){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(closestCloseSunday);
        cal2.setTime(closestSunday);
        boolean isOpen = cal1.get(Calendar.YEAR) != cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) != cal2.get(Calendar.DAY_OF_YEAR);

        return isOpen;
    }

    public ArrayList<Date> getDateCloseList() {
        return dateCloseList;
    }
}
