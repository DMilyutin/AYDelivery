package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.AdapterOrderActivity;
import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.Order;

import java.util.ArrayList;
import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

import static android.app.Notification.PRIORITY_MAX;

public class OrdersActivity extends AppCompatActivity {

    private static final String COLLECTION_FOR_WORK_BALASHIHA = "for_work_balashiha";

    private static final int NOTIFY_ID = 101;

    private ListView ordersList;
    private AdapterOrderActivity adapterOrderActivity;
    private Adapter adapterT;

    private MyRunnable mRun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        ordersList = findViewById(R.id.ordersList);

        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order order = adapterOrderActivity.newOrder(i);
                Log.i("Loog", order.getOrderStatus());
                startDetailOrder(order);
            }
        });
    }

    private void startDetailOrder(Order order) {
        Intent intent = new Intent(OrdersActivity.this, DetailOrderActivity.class);


        intent.putExtra("Order",  order);
        startActivity(intent);
    }


    private void getOllOrders(){
        Query query = new Query(COLLECTION_FOR_WORK_BALASHIHA);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
               setAdapter(documentInfos);
               //notifyl();
                //not2();
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                ordersList.setAdapter((ListAdapter) adapterT);
                Toast.makeText(OrdersActivity.this, "Скорее всего ошибка! Сообщи об этом!!!"
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public Context getApplicationContext() {
        return this;
    }

    private void setAdapter(List<DocumentInfo> documentInfos) {
        List<DocumentInfo> dock = new ArrayList<>();

        int z = documentInfos.size()-1;
        for(int i = 0; i<= z; i++){
            if((documentInfos.get(i).getFields().get("statusOrder").toString()).equals("Поиск курьера"))
                not2();
            dock.add(i, documentInfos.get(z-i));

        }
        adapterOrderActivity = new AdapterOrderActivity(this, dock);
        ordersList.setAdapter(adapterOrderActivity);
    }




    private void not2(){
        Uri ringURI =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setContentTitle("YouDelivery")
                        .setContentText("Есть доступные заказы")
                        .setLights(Color.RED, 1000,500)
                        .setPriority(PRIORITY_MAX)
                        .setSound(ringURI);


        Notification notification = builder.build();



        if(Build.VERSION.SDK_INT >= 26){
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        }else {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(1000);
        }

        //Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//
        //long[] vibro = new long[]{1000, 1000};
        //notification.vibrate = vibro;



        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mRun = new MyRunnable();
    }


    @Override
    protected void onDestroy() {
        try {
            mRun.stopp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    class MyRunnable implements Runnable{
        Thread thread;


        MyRunnable(){
            thread = new Thread(this, "Поток обновления");
            Log.i("Loog", "Второй поток");
            thread.start();
        }

        private void stopp() throws InterruptedException {
            thread.interrupt();
        }
        @Override
        public void run() {
            // int i;
            try {
                for (; ; ) {
                    if(!thread.isInterrupted()){
                        Log.i("Loog", "Второй поток: ");
                        getOllOrders();
                        thread.sleep(30000);
                    }else{
                        throw new InterruptedException();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}


