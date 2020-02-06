package com.example.sockettest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketService extends Service {

    public boolean ifSocketThreadStart = false;
    public Runnable socketThread = new TcpServer();

    private int socket_port=8540;
    private boolean ifListen  =true;
    ServerSocket serverSocket;
    String dataString;
    Socket socket;

    public SocketService() {
        Log.i("ldld","SocketService 的构造函数");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ldld","SocketService 的oncreate方法");

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class TcpServer implements Runnable{

        @Override
        public void run() {

            while (ifListen) {
                try {
                    if (serverSocket == null && ifListen) {
                        serverSocket = new ServerSocket(socket_port);
//                        serverSocket.setSoTimeout(60*1000);
                    } else if (serverSocket != null) {
                        Log.i("ldld","生成socket开始监听");
                        socket = serverSocket.accept();
                        if (socket != null) {
                            DataInputStream in = new DataInputStream(new BufferedInputStream(socket
                                    .getInputStream()));
                            try {
                                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                                byte[] buffer = new byte[1024];
                                int len = 0;
                                while ((len = in.read(buffer)) != -1) {
                                    outStream.write(buffer, 0, len);
                                }
                                byte[] data = outStream.toByteArray();
                                dataString = new String(data, "utf-8");
                                Log.i("ldld",dataString);
                            } catch (Exception e) {
                                Log.i("ldld", "DataService read: " + e);
                                destorySocket();
                            }
                        }
                    }
                } catch (IOException e1) {
                    Log.i("ldld", "DataService accept: " + e1);
                    stopListen();
                }
                try {
                    Log.i("ldld", "socket监听 链接失败 1s后重连. " );
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.i("ldld",e.toString());
                }
            }

        }
    }

    private void destorySocket() {
        Log.i("ldld","destorySocket");
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            Log.i("ldld",e.toString());
        } finally {
            serverSocket = null;
        }
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            Log.i("ldld",e.toString());
        } finally {
            socket = null;
        }
    }

    public void startListen() {
        ifListen = true;
        if (!ifSocketThreadStart) {
            ifSocketThreadStart = true;
            new Thread(socketThread).start();
        }
    }

    public void stopListen() {
        ifListen = false;
        destorySocket();
    }
}
