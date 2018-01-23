package mrodkiewicz.pl.niedzielehandlowe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import mrodkiewicz.pl.niedzielehandlowe.helpers.Data;
import mrodkiewicz.pl.niedzielehandlowe.tools.CloseSundayDecorator;
import mrodkiewicz.pl.niedzielehandlowe.tools.NotificationsDecorator;
import mrodkiewicz.pl.niedzielehandlowe.tools.OpenWeekendDecorator;
import mrodkiewicz.pl.niedzielehandlowe.tools.TodayDecorator;



public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName()+ " flag";
    public static Data data = new Data();
    private MaterialCalendarView calendarView;
    private TextView textView;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private int openSunday,closeSunday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);


        openSunday = getResources().getColor(R.color.openSunday);
        closeSunday = getResources().getColor(R.color.closeSunday);

        Log.d(TAG, "is next sunday close: " + String.valueOf(data.isNextSundayClose()));
//        Log.d(TAG, "closestSunday: " + String.valueOf(data.closestSunday().toString()));
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);

        // styczen = 0 grudzien = 11
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2018, 0, 1))
                .setMaximumDate(CalendarDay.from(2018, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calendarView.addDecorators(
                new NotificationsDecorator(),
                new OpenWeekendDecorator(),
                new CloseSundayDecorator(),
        new TodayDecorator()
        );

        if (data.isNextSundayClose()){
            textView.setText(getString(R.string.state_close_text));
            textView.setTextColor(closeSunday);
            imageView.setImageResource(R.drawable.ic_remove_shopping_cart_black_24dp);
            linearLayout.setBackgroundColor(closeSunday);

        }else{
            textView.setText(getString(R.string.state_open_text));
            textView.setTextColor(openSunday);
            imageView.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
            linearLayout.setBackgroundColor(openSunday);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
    public static Context getContext() {
        return getContext();
    }

    public static Data getData() {
        return data;
    }
}
