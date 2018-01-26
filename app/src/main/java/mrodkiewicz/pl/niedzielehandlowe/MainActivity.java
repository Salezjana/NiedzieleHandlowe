package mrodkiewicz.pl.niedzielehandlowe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
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

import net.danlew.android.joda.JodaTimeAndroid;


import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mrodkiewicz.pl.niedzielehandlowe.helpers.Data;
import mrodkiewicz.pl.niedzielehandlowe.tools.CloseSundayDecorator;
import mrodkiewicz.pl.niedzielehandlowe.tools.OpenWeekendDecorator;
import mrodkiewicz.pl.niedzielehandlowe.tools.TodayDecorator;



public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName()+ " flag";
    public static Data data = new Data();

    @BindView(R.id.textView) TextView textView;
    @BindView(R.id.calendarView) MaterialCalendarView calendarView;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.linearLayout) LinearLayout linearLayout;
    private SharedPreferences preferences;
    private int openSundayColor,closeSundayColor,openSundayCalendarColor,closeSundayCalendarColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JodaTimeAndroid.init(this);
        ButterKnife.bind(this);


        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        openSundayColor = getResources().getColor(R.color.openSunday);
        closeSundayColor = getResources().getColor(R.color.closeSunday);
        openSundayCalendarColor = getResources().getColor(R.color.openSundayCalendar);
        closeSundayCalendarColor = getResources().getColor(R.color.closeSundayCalendar);

        ArrayList<CalendarDay> closeSundaysCollection = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (LocalDate localDate:data.dateCloseList){
            calendar.set(localDate.getYear(),localDate.getMonthOfYear() - 1,localDate.getDayOfMonth());
            CalendarDay day = CalendarDay.from(calendar);
            closeSundaysCollection.add(day);
            Log.d(TAG,day.toString());
        }


        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_NONE);
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2018, 0, 1))
                .setMaximumDate(CalendarDay.from(2018, 11, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        calendarView.addDecorators(
                new OpenWeekendDecorator(openSundayCalendarColor),
                new CloseSundayDecorator(closeSundayCalendarColor, closeSundaysCollection),
                new TodayDecorator()
        );


        if (data.isNextSundayClose()){
            if (Days.daysBetween(data.getClosestSunday(),data.getToday()).getDays() == 0){
                textView.setText(getString(R.string.state_today_close_text));
            }else {
                textView.setText(getString(R.string.state_close_text));
            }
            textView.setTextColor(closeSundayColor);
            imageView.setImageResource(R.drawable.ic_remove_shopping_cart_black_24dp);
            linearLayout.setBackgroundColor(closeSundayColor);

        }else{
            if (Days.daysBetween(data.getClosestSunday(),data.getToday()).getDays() == 0){
                textView.setText(getString(R.string.state_today_open_text));
            }else {
                textView.setText(getString(R.string.state_open_text));
            }
            textView.setTextColor(openSundayColor);
            imageView.setImageResource(R.drawable.ic_shopping_cart_black_24dp);
            linearLayout.setBackgroundColor(openSundayColor);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_settings:
                intent = new Intent(this,SettingsViewerActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Context getContext() {
        return getContext();
    }

    public static Data getData() {
        return data;
    }
}
