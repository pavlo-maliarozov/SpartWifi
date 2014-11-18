package com.spartwifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SmartActivity extends Activity {

    Button conB;
    Button settingsB;
    TextView text;
    TextView name;
    EditText ip;
    String ns;
    private boolean isClicked = true;
    BroadcastReceiver r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        r = new  BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager conn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = conn.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (networkInfo.isConnected()) {
                    WifiManager m = (WifiManager) getSystemService(WIFI_SERVICE);
                    WifiInfo info = m.getConnectionInfo();
                    DhcpInfo info1 = m.getDhcpInfo();

                    setContentView(R.layout.info);
                    text = (TextView) findViewById(R.id.text);
                    name = (TextView) findViewById(R.id.name);
                    ip = (EditText) findViewById(R.id.ip);



                    ns = info.getSSID();
                    name.setText("Name: " + ns);
                    ip.setText(convertIp(info1.ipAddress));
                    conB = (Button) findViewById(R.id.connect);
                    conB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!(isClicked)) {
                                text.setText("Connection to PC:Disabled");
                                conB.setText("Connect to PC");
                                isClicked = true;
                            } else {
                                text.setText("Connection to PC: Enabled");
                                conB.setText("Disconnect");
                                isClicked = false;
                            }
                        }
                    });


                } else {
                    setContentView(R.layout.activity_smart);
                    settingsB = (Button) findViewById(R.id.SettingsB);
                    settingsB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                            intent.addCategory(Intent.CATEGORY_LAUNCHER);
                            final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                            intent.setComponent(cn);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });

                }
            }
        };

            this.registerReceiver(r, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private String convertIp(int adr) {
        int[] ip = new int[4];
        for (int i = 0; i < 4; i++) {
            ip[i] = adr & 0xff;
            adr >>= 8;
        }
        return String.format("%d.%d.%d.%d", ip[0], ip[1], ip[2], ip[3]);
    }
}