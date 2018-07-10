package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.R;

public class DetailOrderActivity extends AppCompatActivity {

    private Order order;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        textView = findViewById(R.id.tvDetailOrderNameCustomer);

        Intent intent = getIntent();
        order = intent.getParcelableExtra("Order");
        textView.setText(order.getNameCustomer());
    }
}
