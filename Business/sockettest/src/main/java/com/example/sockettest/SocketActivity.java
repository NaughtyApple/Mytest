package com.example.sockettest;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class SocketActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);

        findViewById(R.id.find_ip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SocketActivity.this,getLocalIpV4Address(),Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.build_socket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketService socketService = new SocketService();
                socketService.startListen();
            }
        });

        findViewById(R.id.send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


     //写法三（推荐）：
      public String getLocalIpV4Address() {
         try {
                 String ipv4;
               ArrayList<NetworkInterface> nilist = Collections.list(NetworkInterface.getNetworkInterfaces());
               for (NetworkInterface ni: nilist)
                    {
                        ArrayList<InetAddress>  ialist = Collections.list(ni.getInetAddresses());
                        for (InetAddress address: ialist){
                               if (!address.isLoopbackAddress() && !address.isLinkLocalAddress())
                                    {
                                        ipv4=address.getHostAddress();
                                        return ipv4;
                                    }
                            }

                    }

            } catch (SocketException ex) {
                Log.e("localip", ex.toString());
            }
        return null;
    }
}
