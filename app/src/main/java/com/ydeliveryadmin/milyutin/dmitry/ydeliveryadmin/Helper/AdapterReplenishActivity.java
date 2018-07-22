package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.R;

import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;

public class AdapterReplenishActivity extends BaseAdapter {

    private LayoutInflater inflater;
    private List<DocumentInfo> list;

    public AdapterReplenishActivity(Context context, List<DocumentInfo> list){
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
        if(view == null){view = inflater.inflate(R.layout.for_adapter_replenish, viewGroup, false);}

        Customer customer = newCustomer(i);

        ((TextView) view.findViewById(R.id.tvForReplenishIdCustomer))
                .setText(customer.get_id());
        ((TextView)view.findViewById(R.id.tvForReplenishBalance))
                .setText(customer.getBalanceCustomer());


        return view;
    }

    private Customer newCustomer(int poss){
        Customer customer = new Customer();
        customer.setBalanceCustomer(list.get(poss).getFields().get("balance").toString());
        customer.set_id(list.get(poss).getFields().get("idCustomer").toString());
        return customer;
    }
}
