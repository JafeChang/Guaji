package info.jafe.guaji.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import info.jafe.guaji.app.App;
import info.jafe.guaji.utils.Logs;

/**
 * Created by jianfei on 2016/2/17.
 */
public class ServiceImp extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logs.d("");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logs.d("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logs.d("");
    }
}
