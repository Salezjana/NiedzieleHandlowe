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
    private String TAG = getClass().getSimpleName() + "flag";
    private final Date date = new Date();
    private final Drawable highlightDrawable;
    private Calendar calendar;
    private final Data data = MainActivity.getData();
    private static final int color = Color.parseColor("#e53935");

    public CloseSundayDecorator() {
        highlightDrawable = new ColorDrawable(color);
        calendar = Calendar.getInstance();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        boolean isClose = false;
        return isClose;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);
    }
}
