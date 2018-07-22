package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.R;

import java.text.SimpleDateFormat;
import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;

public class AdapterOrderActivity extends BaseAdapter {

    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private LayoutInflater inflater;
    private List<DocumentInfo> listOrders;
    private List<DocumentInfo> listDriver;

    public AdapterOrderActivity(Context context, List<DocumentInfo> list1,List<DocumentInfo> list2 ){
        this.listOrders = list1;
        listDriver = list2;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {return listOrders.size(); }

    @Override
    public Object getItem(int position) {return listOrders.get(position); }

    @Override
    public long getItemId(int position) {return position; }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {

        View view = view1;
        if(view == null){view = inflater.inflate(R.layout.for_adapter_order, viewGroup, false);}





        Order orderForList = newOrder(i);

        //String status = listOrders.get(i).getFields().get("statusOrder").toString();
        String status = orderForList.getOrderStatus();
        int res = statusOrder(status);

        ((TextView) view.findViewById(R.id.tvAdapterOrderNameCustomer)).setText(orderForList.getNameCustomer());
        ((TextView) view.findViewById(R.id.tvAdapterOrderMuchAddress)).setText(orderForList.getNumberOfAddress());
        ((TextView) view.findViewById(R.id.tvAdapterOrderTimeOrder)).setText(orderForList.getTimeOrder());
        ((TextView) view.findViewById(R.id.tvAdapterOrderCoastOrder)).setText(orderForList.getCoastOrder());
        ((TextView) view.findViewById(R.id.tvAdapterOrderLastNameDriver)).setText(orderForList.getLastNameDriver());
        ((TextView) view.findViewById(R.id.tvAdapterOrderNameDriver)).setText(orderForList.getNameDriver());

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
        String dat = format.format(listOrders.get(poss).getDate("createdAt"));

        Order order = new Order(listOrders.get(poss).getId(),
                listOrders.get(poss).getFields().get("nameCustomer").toString(),
                listOrders.get(poss).getFields().get("addressCustomer").toString(),
                listOrders.get(poss).getFields().get("coastOrder").toString(),
                listOrders.get(poss).getFields().get("numberOfAddresses").toString(),
                dat);

        order.setAddressForDriver(listOrders.get(poss).getFields().get("addressForDriver").toString());
        order.setPhonesForDriver(listOrders.get(poss).getFields().get("phoneForDriver").toString());
        order.setNamesForDriver(listOrders.get(poss).getFields().get("nameForDriver").toString());
        order.setOrderStatus(listOrders.get(poss).getFields().get("statusOrder").toString());

        DocumentInfo d = driverNameEndLastName(listOrders.get(poss).getFields().get("idDriver").toString());
        if(d  != null) {
            order.setNameDriver(d.get("nameDriver").toString());
            order.setLastNameDriver(d.get("lastNameDriver").toString());
        }
        else {
            order.setNameDriver("Ошибка");
            order.setLastNameDriver("Ошибка");
        }
        return order;
    }

    private DocumentInfo driverNameEndLastName(String idDriver){
        for (int i = 0; i < listDriver.size(); i++){
            DocumentInfo d  = listDriver.get(i);
            if(d.getId().equals(idDriver)){
                return d;
            }
        }
        return null;
    }
    }

