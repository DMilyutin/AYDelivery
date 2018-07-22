package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btCustomers;
        Button btDrivers;
        Button btOrders;
        Button btReplenishBalance;

        btCustomers =  findViewById(R.id.btCustomers);
        btDrivers =  findViewById(R.id.btDrivers);
        btOrders =  findViewById(R.id.btOrders);
        btReplenishBalance =  findViewById(R.id.btReplenishBalance);


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

        btReplenishBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRepl = new Intent(MainActivity.this, ReplenishBalanceActivity.class);
                startActivity(intentRepl);
            }
        });
    }


}
