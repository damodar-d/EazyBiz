package com.devdroid.easybiz;

import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.devdroid.easybiz.codes.RequestCodes;

import androidx.constraintlayout.widget.ConstraintLayout;

public class App extends Application {

    NotificationChannel deliveryNotificationChannel;
    NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();

        manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
        {
            deliveryNotificationChannel= new NotificationChannel(RequestCodes.DELIVERY_NOTIFICATION_CHANNEL_ID,"Delivery Notification", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(deliveryNotificationChannel);
        }
    }



}
