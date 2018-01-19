package mrodkiewicz.pl.niedzielehandlowe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Date;

import mrodkiewicz.pl.niedzielehandlowe.helpers.Data;
import mrodkiewicz.pl.niedzielehandlowe.tools.HighlightWeekendsDecorator;
import mrodkiewicz.pl.niedzielehandlowe.tools.NotificationsDecorator;
import mrodkiewicz.pl.niedzielehandlowe.tools.TodayDecorator;

public class MainActivity extends AppCompatActivity {

    private Data data;
    private MaterialCalendarView calendarView;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new Data();
        data.init();


        Log.d("TEST", String.valueOf(data.isNextWeekOpen()));

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
        // styczen = 0 grudzien = 11

        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2018, 0, 1))
                .setMaximumDate(CalendarDay.from(2018, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calendarView.addDecorators(
                new NotificationsDecorator(),
                new HighlightWeekendsDecorator(),
                new TodayDecorator()
        );
    }

    public static Context getContext(){
        return getContext();
    }
}
