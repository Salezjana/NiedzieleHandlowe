package mrodkiewicz.pl.niedzielehandlowe.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import mrodkiewicz.pl.niedzielehandlowe.MainActivity;
import mrodkiewicz.pl.niedzielehandlowe.R;


/**
 * Use a custom selector
 */
public class NotificationsDecorator implements DayViewDecorator {

    public NotificationsDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        Toast.makeText(MainActivity.getContext(),"KLIKNOLES NA" + day.getDate().toString(),Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void decorate(DayViewFacade view) {
    }
}