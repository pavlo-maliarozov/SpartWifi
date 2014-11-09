package com.spartwifi;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class SmartActivity extends Activity {

   // TextView tView = (TextView)findViewById(R.id.textView);

    /*private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiState = intent.getIntExtra(
                    WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiState) {
                case WifiManager.WIFI_STATE_ENABLED:
                    tView.setText("Connection Enabled");


                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    tView.setText("Connection Disabled");

                    break;
            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart);
       /* this.registerReceiver(this.receiver,
                new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
*/

    }

    public void showSettings(View view) {
        if (view.getId() == R.id.SettingsB) {
            final Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
            intent.setComponent(cn);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            showNetInfo();

        }
    }
    public void showNetInfo()
    {
        Intent info = new Intent(this, InfoActivity.class);
        info.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(info);

        this.finish();

    }
}
