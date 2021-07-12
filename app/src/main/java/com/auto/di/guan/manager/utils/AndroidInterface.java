package com.auto.di.guan.manager.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.just.agentweb.AgentWeb;


public class AndroidInterface {

    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Context context;

    public AndroidInterface(AgentWeb agent, Context context) {
        this.agent = agent;
        this.context = context;
    }


    @JavascriptInterface
    public void callAndroid(final String msg) {
        deliver.post(new Runnable() {
            @Override
            public void run() {
//                Log.i("Info", "main Thread:" + Thread.currentThread());
                Toast.makeText(context.getApplicationContext(), "111111111111" + msg, Toast.LENGTH_LONG).show();
//                context.startActivity(new Intent(context, MainActivity.class));
            }
        });
        Log.i("Info", "Thread:" + Thread.currentThread());

    }

}
