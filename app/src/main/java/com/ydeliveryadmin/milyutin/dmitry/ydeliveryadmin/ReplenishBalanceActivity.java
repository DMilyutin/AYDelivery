package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.AdapterReplenishActivity;

import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

public class ReplenishBalanceActivity extends AppCompatActivity {


    private ListView replenishList;
    private AdapterReplenishActivity adapterReplenishActivity;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replenish_balance);

        replenishList = findViewById(R.id.replenishList);

        getOllReplenish();
    }

    private void getOllReplenish() {
        Query query = new Query("replenish_balance");
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                setAdapter(documentInfos);
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                replenishList.setAdapter((ListAdapter) adapter);
                Toast.makeText(ReplenishBalanceActivity.this, "Скорее всего ошибка! Сообщи об этом!!!"
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void setAdapter(List<DocumentInfo> documentInfos){
        adapterReplenishActivity = new AdapterReplenishActivity(this, documentInfos);
        replenishList.setAdapter(adapterReplenishActivity);

    }
}
