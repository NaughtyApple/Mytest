package com.example.mytest;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ldld","MyApplication onCreate");
    }


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Log.i("ldld","MyApplication onActivityCreated:"+activity.toString());
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.i("ldld","MyApplication onActivityStarted:"+activity.toString());
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.i("ldld","MyApplication onActivityResumed:"+activity.toString());

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.i("ldld","MyApplication onActivityPaused:"+activity.toString());
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.i("ldld","MyApplication onActivityStopped:"+activity.toString());
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Log.i("ldld","MyApplication onActivitySaveInstanceState:"+activity.toString());
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.i("ldld","MyApplication onActivityDestroyed:"+activity.toString());
    }
}
