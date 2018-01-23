package mrodkiewicz.pl.niedzielehandlowe.helpers;

import java.util.ArrayList;
import java.util.Date;



public class Data {
    private String TAG = getClass().getSimpleName() + " flag";

    public ArrayList<Date> dateCloseList = new ArrayList<java.util.Date>();

    public Data() {
        init();
    }

    public void init() {
        dateCloseList.add(new Date(118, 2, 11,0,0,0));
        dateCloseList.add(new Date(118, 2, 18,0,0,0));
        dateCloseList.add(new Date(118, 3, 1,0,0,0));
        dateCloseList.add(new Date(118, 3, 8,0,0,0));
        dateCloseList.add(new Date(118, 3, 15,0,0,0));
        dateCloseList.add(new Date(118, 3, 22,0,0,0));
        dateCloseList.add(new Date(118, 4, 12,0,0,0));
        dateCloseList.add(new Date(118, 4, 20,0,0,0));
        dateCloseList.add(new Date(118, 5, 10,0,0,0));
        dateCloseList.add(new Date(118, 5, 17,0,0,0));
        dateCloseList.add(new Date(118, 6, 8,0,0,0));
        dateCloseList.add(new Date(118, 6, 15,0,0,0));
        dateCloseList.add(new Date(118, 6, 22,0,0,0));
        dateCloseList.add(new Date(118, 7, 12,0,0,0));
        dateCloseList.add(new Date(118, 7, 19,0,0,0));
        dateCloseList.add(new Date(118, 8, 9,0,0,0));
        dateCloseList.add(new Date(118, 8, 16,0,0,0));
        dateCloseList.add(new Date(118, 8, 23,0,0,0));
        dateCloseList.add(new Date(118, 9, 14,0,0,0));
        dateCloseList.add(new Date(118, 9, 21,0,0,0));
        dateCloseList.add(new Date(118, 10, 11,0,0,0));
        dateCloseList.add(new Date(118, 10, 18,0,0,0));
        dateCloseList.add(new Date(118, 11, 9,0,0,0));

    }

    public boolean isNextSundayClose() {
        return true;
    }
}