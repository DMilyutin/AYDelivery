package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.AdapterDriverActivity;
import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.AdapterOrderActivity;

import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

public class DriversActivity extends AppCompatActivity {


    private static final String COLLECTION_DRIVERS_ALASHIHA = "drivers_balashiha";

    private ListView driversList;
    private AdapterDriverActivity adapterDriverActivity;
    private Adapter adapterT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        driversList = findViewById(R.id.driversList);
        getOllDriver();
    }

    private void getOllDriver(){
        Query query = new Query(COLLECTION_DRIVERS_ALASHIHA);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                setAdapter(documentInfos);
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                driversList.setAdapter((ListAdapter) adapterT);
                Toast.makeText(DriversActivity.this, "Скорее всего ошибка! Сообщи об этом!!!"
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAdapter(List<DocumentInfo> documentInfos) {
        adapterDriverActivity = new AdapterDriverActivity(this, documentInfos);
        driversList.setAdapter(adapterDriverActivity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.addItem :{
                Intent intent = new Intent(DriversActivity.this ,AddDriverActivity.class );
                startActivity(intent);
            }
        }
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getOllDriver();
    }
}
