package com.example.secondtrywidget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.LinearGradient;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends Activity {
    LinearLayout startlayout;
    LinearLayout showlayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startlayout=findViewById(R.id.startlayout);
        showlayout=findViewById(R.id.showlayout);
        SharedPreferences settings=getSharedPreferences("PREFERENCES", 0);
        boolean isLogged=settings.getBoolean("isLogged", false);
        if(isLogged)
        {
            if(startlayout.getVisibility()==View.VISIBLE)
            startlayout.setVisibility(View.GONE);
            if(showlayout.getVisibility()==View.GONE)
                showlayout.setVisibility(View.VISIBLE);
        }
        else
        {
            if(startlayout.getVisibility()==View.GONE)
                startlayout.setVisibility(View.VISIBLE);
            if(showlayout.getVisibility()==View.VISIBLE)
                showlayout.setVisibility(View.GONE);
            SharedPreferences settings1=getSharedPreferences("PREFERENCES", 0);
            SharedPreferences.Editor editor = settings1.edit();
            editor.putBoolean("isLogged", true);
            editor.apply();
        }
    findViewById(R.id.openclock).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openAlarmClock();
        }
    });
    findViewById(R.id.openwhatsapp).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openWhatsappChat(((EditText)findViewById(R.id.phone)).getText().toString());
        }
    });
    }
    public void openAlarmClock()
    {
        Intent openClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openClockIntent);
    }
    public void openWhatsappChat(String phone)
    {
        if(phone.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if(phone.charAt(0)!='0')
        {
            phone="+91"+phone;
        }
        String url = "https://api.whatsapp.com/send?phone=" + phone;
        try {
            PackageManager pm = getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(MainActivity.this, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}