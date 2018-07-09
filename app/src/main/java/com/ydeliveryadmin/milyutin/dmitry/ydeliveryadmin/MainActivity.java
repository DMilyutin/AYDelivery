package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button btCustomers;
    private Button btDrivers;
    private Button btOrders;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCustomers =  findViewById(R.id.btCustomers);
        btDrivers =  findViewById(R.id.btDrivers);
        btOrders =  findViewById(R.id.btOrders);



        btCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCustomer = new Intent(MainActivity.this, CustomersActivity.class);
                startActivity(intentCustomer);
            }
        });


        btDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDrivers = new Intent(MainActivity.this, DriversActivity.class);
                startActivity(intentDrivers);
            }
        });

        btOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOrder = new Intent(MainActivity.this, OrdersActivity.class);
                startActivity(intentOrder);
            }
        });
    }


}
