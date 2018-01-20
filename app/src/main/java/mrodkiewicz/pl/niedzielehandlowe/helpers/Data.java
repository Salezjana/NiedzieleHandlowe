package mrodkiewicz.pl.niedzielehandlowe.helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Calendar.DAY_OF_WEEK;



public class Data {
    public ArrayList<Date> dateCloseList = new ArrayList<java.util.Date>();
    public ArrayList<Date> sundays = new ArrayList<java.util.Date>();
    public Date closestSunday, closestCloseSunday;

    public Data() {
        init();
    }

    public void init() {
        dateCloseList.add(new Date(2018, 3, 11));
        dateCloseList.add(new Date(2018, 3, 18));
        dateCloseList.add(new Date(2018, 4, 1));
        dateCloseList.add(new Date(2018, 4, 8));
        dateCloseList.add(new Date(2018, 4, 15));
        dateCloseList.add(new Date(2018, 4, 22));
        dateCloseList.add(new Date(2018, 5, 12));
        dateCloseList.add(new Date(2018, 5, 20));
        dateCloseList.add(new Date(2018, 6, 10));
        dateCloseList.add(new Date(2018, 6, 17));
        dateCloseList.add(new Date(2018, 7, 8));
        dateCloseList.add(new Date(2018, 7, 15));
        dateCloseList.add(new Date(2018, 7, 22));
        dateCloseList.add(new Date(2018, 8, 12));
        dateCloseList.add(new Date(2018, 8, 19));
        dateCloseList.add(new Date(2018, 9, 9));
        dateCloseList.add(new Date(2018, 9, 16));
        dateCloseList.add(new Date(2018, 9, 23));
        dateCloseList.add(new Date(2018, 10, 14));
        dateCloseList.add(new Date(2018, 10, 21));
        dateCloseList.add(new Date(2018, 11, 11));
        dateCloseList.add(new Date(2018, 11, 18));
        dateCloseList.add(new Date(2018, 12, 9));

        //pobieranie wszytkich niedziel
        int dayOfWeek = Calendar.SUNDAY;
        Calendar cal = new GregorianCalendar();
        cal.set(2018, 0, 0, 0, 0);
        cal.set(DAY_OF_WEEK, dayOfWeek);

        while (cal.get(Calendar.YEAR) == 2018) {
            sundays.add(cal.getTime());
            cal.add(Calendar.DAY_OF_MONTH, 7);
        }

        //pobieranie najblizszej niedzieli zamknietej

        //========== NIEUZYWANE BO SIE PSUJE ==========
        //jesli dzien zawiera sie do poniedzialku do srodu musimy zmenic go na czwartek aby nie wykryło nam poprzednich tygodni
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTime(Calendar.getInstance().getTime());
//        if (calendar1.get(DAY_OF_WEEK) == Calendar.MONDAY){
//            calendar1.setTime(new Date(calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH) + 3));
//        }else if (calendar1.get(DAY_OF_WEEK) == Calendar.TUESDAY){
//            calendar1.setTime(new Date(calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH) + 2));
//        }else if (calendar1.get(DAY_OF_WEEK) == Calendar.WEDNESDAY){
//            calendar1.setTime(new Date(calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH) + 1));
//        }
        //=============================================

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


        //poprawianie roku na 2018
        Date date1 = new Date(closestCloseSunday.getYear() - 1900,closestCloseSunday.getMonth(),closestCloseSunday.getDay());
        closestCloseSunday = date1;

        Log.d("DATA najblizsza",closestSunday.toString()+" closestSunday");
        Log.d("DATA zamknieta",closestCloseSunday.toString()+" closestCloseSunday");

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
