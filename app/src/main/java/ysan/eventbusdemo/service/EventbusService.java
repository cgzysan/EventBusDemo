package ysan.eventbusdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import ysan.eventbusdemo.bean.Message;

/**
 * Created by YSAN on 2018/03/29
 */

public class EventbusService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Message msg = new Message("hello EventBus");
                    EventBus.getDefault().post(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
