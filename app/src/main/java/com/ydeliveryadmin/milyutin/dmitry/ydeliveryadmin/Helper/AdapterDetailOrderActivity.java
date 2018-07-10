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

public class AdapterDetailOrderActivity extends BaseAdapter {

    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private LayoutInflater inflater;
    private List<DocumentInfo> list;

    public AdapterDetailOrderActivity(Context context, List<DocumentInfo> list){
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view1, ViewGroup viewGroup) {
        View view = view1;
        if(view == null){view = inflater.inflate(R.layout.for_adapter_detail_order, viewGroup, false);}

        Order orderForList = getOrder(i);

        ((TextView)view.findViewById(R.id.tvForListDetailOrderAddress))
                .setText(orderForList.getAddressCustomer());
        view.findViewById(R.id.ivForListDetailOrderLocation);
        view.findViewById(R.id.ivForListDetailOrderPhone);


        return view;
    }


    public Order getOrder(int poss){
        String dat = format.format(list.get(poss).getDate("createdAt"));

        Order order = new Order(list.get(poss).getId(),
                list.get(poss).getFields().get("nameCustomer").toString(),
                list.get(poss).getFields().get("addressCustomer").toString(),
                list.get(poss).getFields().get("coastOrder").toString(),
                list.get(poss).getFields().get("numberOfAddresses").toString(),
                dat);
        order.setAddressForDriver(list.get(poss).getFields().get("addressForDriver").toString());
        order.setNamesForDriver(list.get(poss).getFields().get("nameForDriver").toString());
        order.setPhonesForDriver(list.get(poss).getFields().get("phoneForDriver").toString());
        return order;
    }

    public String getPhoneCustomer(int poss){
        return list.get(poss).getFields().get("phoneForDriver").toString();
    }
}
