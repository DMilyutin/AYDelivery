package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.R;

public class Notify {

    private static final int NOTIFY_ID = 101;
    Context context;

    Notify(Context context){
        this.context = context;

    }

    public void notifyl(){

        Intent notificationIntent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        long[] vibro = new long[]{1000};
        builder.setVibrate(vibro);
        builder.setLights(Color.RED,0,1);

        //builder.flags = notification.flags | Notification.FLAG_SHOW_LIGHTS;

        builder.setContentIntent(contentIntent)
                // обязательные настройки
                .setSmallIcon(R.drawable.ic_search_blue_24dp)
                .setContentTitle("YouDelivery")
                .setContentText("Есть доступные заказы")
                // необязательные настройки

                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true); // автоматически закрыть уведомление после нажатия

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFY_ID, builder.build());
    }

}
