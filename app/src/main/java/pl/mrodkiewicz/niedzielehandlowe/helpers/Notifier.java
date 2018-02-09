package pl.mrodkiewicz.niedzielehandlowe.helpers;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Date;

public class Notifier extends BroadcastReceiver {
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;

    public void init(Context context) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context, Notifier.class);
        pendingIntent = PendingIntent.getBroadcast(context, 12112, intent, 0);
    }

    public void setAlarm(Date date) {
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5 * 1000, pendingIntent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
    }
}
