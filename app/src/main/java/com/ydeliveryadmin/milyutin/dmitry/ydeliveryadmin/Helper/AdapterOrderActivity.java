package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.CustomersActivity;
import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.OrdersActivity;
import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.R;

import java.text.SimpleDateFormat;
import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;

public class AdapterOrderActivity extends BaseAdapter {

    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private LayoutInflater inflater;
    private List<DocumentInfo> list;

    public AdapterOrderActivity(Context context, List<DocumentInfo> list){
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {return list.size(); }

    @Override
    public Object getItem(int position) {return list.get(position); }

    @Override
    public long getItemId(int position) {return position; }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {

        View view = view1;
        if(view == null){view = inflater.inflate(R.layout.for_adapter_order, viewGroup, false);}





        Order orderForList = newOrder(i);

        //String status = list.get(i).getFields().get("statusOrder").toString();
        String status = orderForList.getOrderStatus();
        int res = statusOrder(status);

        ((TextView) view.findViewById(R.id.tvAdapterOrderNameCustomer)).setText(orderForList.getNameCustomer());
        ((TextView) view.findViewById(R.id.tvAdapterOrderMuchAddress)).setText(orderForList.getNumberOfAddress());
        ((TextView) view.findViewById(R.id.tvAdapterOrderTimeOrder)).setText(orderForList.getTimeOrder());
        ((TextView) view.findViewById(R.id.tvAdapterOrderCoastOrder)).setText(orderForList.getCoastOrder());
        ((ImageView) view.findViewById(R.id.ivAdapterOrderStatusOrder))
                    .setImageResource(res);


        return view;
    }

    private int statusOrder(String status){
        int res;

        switch (status){
            case "Поиск курьера" :
            {
                return res = R.drawable.ic_search_blue_24dp;

            }
            case "Отменен":{
                return res = R.drawable.ic_cancel_red_24dp;
            }
            case "Выполняется":{
                return res = R.drawable.ic_time_to_leave_green_24dp;
            }
            case "Завершен":{
                return res =  R.drawable.ic_check_green_24dp;
            }
        }


        return 0;
    }

    public Order newOrder(int poss){                                        //Todo дописать!
        String dat = format.format(list.get(poss).getDate("createdAt"));

        Order order = new Order(list.get(poss).getId(),
                list.get(poss).getFields().get("nameCustomer").toString(),
                list.get(poss).getFields().get("addressCustomer").toString(),
                list.get(poss).getFields().get("coastOrder").toString(),
                list.get(poss).getFields().get("numberOfAddresses").toString(),
                dat);

        order.setAddressForDriver(list.get(poss).getFields().get("addressForDriver").toString());
        order.setPhonesForDriver(list.get(poss).getFields().get("phoneForDriver").toString());
        order.setNamesForDriver(list.get(poss).getFields().get("nameForDriver").toString());
        order.setOrderStatus(list.get(poss).getFields().get("statusOrder").toString());
        return order;
    }

}
