package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.AdapterOrderActivity;

import java.util.ArrayList;
import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

public class OrdersActivity extends AppCompatActivity {

    private static final String COLLECTION_FOR_WORK_BALASHIHA = "for_work_balashiha";

    private ListView ordersList;
    private AdapterOrderActivity adapterOrderActivity;
    private Adapter adapterT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        ordersList = findViewById(R.id.ordersList);
        getOllOrders();

    }


    private void getOllOrders(){
        Query query = new Query(COLLECTION_FOR_WORK_BALASHIHA);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
               setAdapter(documentInfos);
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                ordersList.setAdapter((ListAdapter) adapterT);
                Toast.makeText(OrdersActivity.this, "Скорее всего ошибка! Сообщи об этом!!!"
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAdapter(List<DocumentInfo> documentInfos) {
        List<DocumentInfo> dock = new ArrayList<>();

        int z = documentInfos.size()-1;
        for(int i = 0; i<= z; i++){
            dock.add(i, documentInfos.get(z-i));
        }
        adapterOrderActivity = new AdapterOrderActivity(this, dock);
        ordersList.setAdapter(adapterOrderActivity);
    }
}
