package com.devdroid.easybiz.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;


public class NotificationService extends IntentService {

    NotificationManager notificationManager;
    public NotificationService() {
        super("NotificationService");
        notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }


}