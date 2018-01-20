package mrodkiewicz.pl.niedzielehandlowe.tools;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;
import java.util.Date;

import mrodkiewicz.pl.niedzielehandlowe.MainActivity;
import mrodkiewicz.pl.niedzielehandlowe.helpers.Data;


public class CloseSundayDecorator implements DayViewDecorator {
    private final Date date = new Date();
    private final Drawable highlightDrawable;
    private final Data data = MainActivity.getData();
    private static final int color = Color.parseColor("#000000");

    public CloseSundayDecorator() {
        highlightDrawable = new ColorDrawable(color);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        boolean isClose = false;

        for (Date date:data.dateCloseList){
            if (day.getDate().getDay() == date.getDay() && day.getDate().getMonth() == date.getMonth()){
                Log.d("WASZSZNE BARDZO",date.toString() + "");
                Log.d("WASZSZNE BARDZO",day.getDate().toString() + "");
                isClose= true;
            }

        }
        return isClose;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
    }
}
