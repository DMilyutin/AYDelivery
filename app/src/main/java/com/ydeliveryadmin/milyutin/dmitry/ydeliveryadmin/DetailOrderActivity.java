package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.AdapterDetailOrderActivity;
import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.InfoForDetail;
import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.Update;

public class DetailOrderActivity extends AppCompatActivity {

    private Order order;
    private TextView tvDetailOrderNameCustomer;
    private TextView tvDetailOrderAddressCustomer;
    private TextView tvDetailOrderMuchAddress;

    private ListView lvDetailOrderListCusters;

    private Button btDetailOrderActivityTakeOrder;

    private ImageView ivDetailOrderLocationCustomer;

    private AdapterDetailOrderActivity adapterDetailOrderActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        Intent intent = getIntent();
        order = intent.getParcelableExtra("Order");

        Order o = order;
        String s = o.getOrderStatus();
        Log.i("Loog", s);


        tvDetailOrderNameCustomer = findViewById(R.id.tvDetailOrderNameCustomer);
        tvDetailOrderAddressCustomer = findViewById(R.id.tvDetailOrderAddressCustomer);
        tvDetailOrderMuchAddress = findViewById(R.id.tvDetailOrderMuchAddress);
        lvDetailOrderListCusters = findViewById(R.id.lvDetailOrderListCusters);
        btDetailOrderActivityTakeOrder = findViewById(R.id.btDetailOrderActivityTakeOrder);
        ivDetailOrderLocationCustomer = findViewById(R.id.ivDetailOrderLocationCustomer);

       if(order.getOrderStatus().equals("Завершен") || order.getOrderStatus().equals("Выполняется")
               || order.getOrderStatus().equals("Отменен"))
           btDetailOrderActivityTakeOrder.setText("          ");


        tvDetailOrderNameCustomer.setText(order.getNameCustomer());
        tvDetailOrderAddressCustomer.setText(order.getAddressCustomer());
        tvDetailOrderMuchAddress.setText(order.getNumberOfAddress());



        getAdressForDriver(order.getNamesForDriver() ,order.getAddressForDriver(), order.getPhonesForDriver());

        btDetailOrderActivityTakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btDo();
            }
        });


    }

    private void btDo() {
        String textFromBr = btDetailOrderActivityTakeOrder.getText().toString();
        switch (textFromBr){
            case "Принять заказ" : {
                takeOrder(order.getId());
                break;
            }
            case "Завершить заказ" :{
                closeOrder(order.getId());
                break;
            }
        }
    }



    private void getAdressForDriver(String nameForDrive, String addressForDriver, String phoneForDriver) {
        ArrayList<InfoForDetail> listInfo = new ArrayList<>();
        int collAdress = Integer.parseInt(order.getNumberOfAddress());
        String[] names    ;
        String[] addresses ;
        String[] phones   ;
        names = nameForDrive.split(";");
        addresses= addressForDriver.split(";");
        phones= phoneForDriver.split(";");
        for(int i = 0; i<collAdress; i++){
            InfoForDetail infoForDetail = new InfoForDetail(names[i], addresses[i], phones[i] );
            Log.i("Loog", infoForDetail.toString());
            listInfo.add(infoForDetail);
        }

       adapterDetailOrderActivity = new AdapterDetailOrderActivity(this, listInfo);
       lvDetailOrderListCusters.setAdapter(adapterDetailOrderActivity);
    }

    private void takeOrder(final String idForWork){
        Query query = new Query("work_balashiha");
        query.equalTo("idForWorkBalashiha", idForWork);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {

                deleteOrderFromWork( documentInfos.get(0).getId(), idForWork);
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Toast.makeText(DetailOrderActivity.this, "Заказ умыкнули", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteOrderFromWork(String id, final String idForWork) {
        Document document = new Document("work_balashiha");
        document.getDocumentById(id);
        document.removeDocument(new CallbackRemoveDocument() {
            @Override
            public void onRemoveSucceed(ResponseRemove responseRemove) {
                updateForWork(idForWork);
            }

            @Override
            public void onRemoveFailed(String errorCode, String errorMessage) {
                Toast.makeText(DetailOrderActivity.this, "Заказ умыкнули", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void updateForWork(String id) {
        Query query = new Query("for_work_balashiha");
        query.equalTo("_id", id);

        Update update = new Update();
        update.set("idDriver", "gzF4gvBous");
        update.set("statusOrder","Выполняется");
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                btDetailOrderActivityTakeOrder.setText("Завершить заказ");
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                Toast.makeText(DetailOrderActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void closeOrder(String id) {
        Query query = new Query("for_work_balashiha");
        query.equalTo("_id", id);

        Update update = new Update();
        update.set("statusOrder", "Завершен");

        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                Toast.makeText(DetailOrderActivity.this, "Заказ завершен", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                Toast.makeText(DetailOrderActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
