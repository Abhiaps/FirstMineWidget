package com.example.secondtrywidget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.provider.AlarmClock;
import android.widget.RemoteViews;




public class ExampleAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.example_widget);
            //Intent openClockIntent=new Intent(context,ExampleAppWidgetProvider.class);
            //openClockIntent.setAction(AlarmClock.ACTION_SHOW_ALARMS);
            Intent openClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
            openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openClockIntent, 0);
            views.setOnClickPendingIntent(R.id.example_widget_button, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);

        }
    }

}