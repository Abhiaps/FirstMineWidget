package com.example.secondtrywidget;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerView;

public class ExampleAppWidgetConfig extends AppCompatActivity {
    CheckBox checkbox;
    TextInputEditText hexcode;
    RadioGroup zonegroup;
    TextView demo;
    ColorPickerView colorPickerView;
    int init=R.color.clockcolor;

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_app_widget_config);
        zonegroup=findViewById(R.id.zonegroup);
        zonegroup.clearCheck();
        demo=findViewById(R.id.demo);
        zonegroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rdb=(RadioButton)radioGroup.findViewById(i);
            }
        });
        colorPickerView=findViewById(R.id.colorPickerView);

        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(ColorEnvelope colorEnvelope) {
                init=colorEnvelope.getColor();
                demo.setTextColor(Color.parseColor("#"+Integer.toHexString(init)));
            }
        });

        checkbox=findViewById(R.id.checkbox);
        hexcode=findViewById(R.id.hexcode);
        findViewById(R.id.color1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color1;
                demo.setTextColor(getResources().getColor(R.color.color1));

            }
        });
        findViewById(R.id.color2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color2;
                demo.setTextColor(getResources().getColor(R.color.color2));
            }
        });
        findViewById(R.id.color3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color3;
                demo.setTextColor(getResources().getColor(R.color.color3));
            }
        });
        findViewById(R.id.color4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color4;
                demo.setTextColor(getResources().getColor(R.color.color4));
            }
        });
        findViewById(R.id.color5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color5;
                demo.setTextColor(getResources().getColor(R.color.color5));
            }
        });
        findViewById(R.id.color6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color6;
                demo.setTextColor(getResources().getColor(R.color.color6));
            }
        });
        findViewById(R.id.color7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color7;
                demo.setTextColor(getResources().getColor(R.color.color7));
            }
        });
        findViewById(R.id.color8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color8;
                demo.setTextColor(getResources().getColor(R.color.color8));
            }
        });
        findViewById(R.id.color9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init=R.color.color9;
                demo.setTextColor(getResources().getColor(R.color.color9));
            }
        });

        Intent configIntent = getIntent();
        Bundle extras = configIntent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_CANCELED, resultValue);
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
       // editTextButton = findViewById(R.id.edit_text_button);
    }
    public void confirmConfiguration(View v) {

        if(init<0)
        {
            init=R.color.clockcolor;
        }
        String color="#"+Integer.toHexString(getResources().getColor(init));
        if(checkbox.isChecked()&&!(hexcode.getText().toString().equalsIgnoreCase("")))
        {
            color=hexcode.getText().toString();
        }

        String hrtw=null;
        String hrtf=null;
        int selectedId=zonegroup.getCheckedRadioButtonId();
        if(selectedId==-1||selectedId==R.id.smallhour)hrtw="hh:mm";
        else if(selectedId==R.id.largehour)hrtf="k:mm";


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
       // String buttonText = editTextButton.getText().toString();
        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.example_widget);

        views.setTextColor(R.id.simpleDigitaldate, Color.parseColor(color));
        views.setTextColor(R.id.simpleDigitalClock, Color.parseColor(color));
        views.setCharSequence(R.id.simpleDigitalClock,"setFormat12Hour",hrtw);
        views.setCharSequence(R.id.simpleDigitalClock,"setFormat24Hour",hrtf);
        //Intent openClockIntent=new Intent(ExampleAppWidgetConfig.this,ExampleAppWidgetProvider.class);
        //openClockIntent.setAction(AlarmClock.ACTION_SHOW_ALARMS);
        Intent openClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, openClockIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.example_widget_button,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }

}