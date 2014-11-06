package com.spartwifi;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SmartActivity extends Activity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart);
        Button SettingsB = (Button)findViewById(R.id.SettingsB);
        SettingsB.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.SettingsB){
            final Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
            intent.setComponent(cn);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

                //  for showing Net Info(if Wifi enabled)
                Intent info = new Intent(this, InfoActivity.class);
                info.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(info);


        }
    }

}
