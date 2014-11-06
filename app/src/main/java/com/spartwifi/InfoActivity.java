package com.spartwifi;

import android.app.Activity;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class InfoActivity extends Activity implements View.OnClickListener {
    //private final WifiManager manager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
    private Boolean isClicked = true;
    Button ConB;
    TextView infoText;
    TextView pcInfo;
    EditText ipInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ConB = (Button)findViewById(R.id.ConB);
        infoText = (TextView)findViewById(R.id.infoT);
        pcInfo = (TextView)findViewById(R.id.pcInfo);
        ipInfo = (EditText)findViewById(R.id.ipInfo);
        ConB.setOnClickListener(this);

        NetInfo();
    }
    public void NetInfo(){

            WifiManager manager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = manager.getConnectionInfo();
            DhcpInfo dhcpInfo = manager.getDhcpInfo();
            String  ssid = info.getSSID();
            String ip = convertIp(dhcpInfo.ipAddress);
            String name = String.format("Info\nName: %s\n",ssid);
            infoText.setText(name);
            String ipNum = String.format("%s", ip);
            ipInfo.setText(ipNum);

    }
    //  convert IP to xxxx.xxxx.xxxx.xxxx
    private String convertIp(int adr) {
        int[] ip = new int [4];
        for(int i=0; i<4; i++) {
            ip[i] = adr & 0xff;
            adr >>= 8;
        }
        return String.format("%d.%d.%d.%d", ip[0], ip[1], ip[2], ip[3]);
    }


    @Override
    public void onClick(View v) {
        if(!(isClicked)){
            pcInfo.setText("Connection to PC:Disabled");
            ConB.setText("Connect to PC");
        isClicked=true;
        }
        else{
            pcInfo.setText("Connection to PC: Enabled");
            ConB.setText("Disconnect");
            isClicked=false;
        }
    }
}