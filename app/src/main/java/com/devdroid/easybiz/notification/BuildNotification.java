package com.devdroid.easybiz.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.devdroid.easybiz.MainActivity;
import com.devdroid.easybiz.codes.RequestCodes;

public class BuildNotification {

    private Intent deliveryIntent;
    private PendingIntent pendingIntent;
    private Context context;




    private void buildDeliveryNotification()
    {
        deliveryIntent= new Intent(context, MainActivity.class);
        pendingIntent= PendingIntent.getActivity(context, RequestCodes.PENDING_INTENT_DELIVERY_REQUEST_CODE,deliveryIntent,0);


    }
}
