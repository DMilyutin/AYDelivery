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

public class AdapterCustomerActivity extends BaseAdapter {

    private LayoutInflater inflater;
    private List<DocumentInfo> list;

    public AdapterCustomerActivity(Context context, List<DocumentInfo> list){
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
        if(view == null){view = inflater.inflate(R.layout.for_adapter_customer, viewGroup, false);}

        Customer customerForList = newCustomer(i);


        ((TextView)view.findViewById(R.id.tvAdapterCustomerNameCustomer))
                .setText(customerForList.getNameCustomer());
        ((TextView) view.findViewById(R.id.tvAdapterCustomerPhoneCustomer))
                .setText(customerForList.getPhoneCustomer());
        ((TextView) view.findViewById(R.id.tvAdapterCustomerAddressCustomer))
                .setText(customerForList.getAddressCustomer());

        return view;
    }

    private Customer newCustomer(int pass){
        Customer customer = new Customer(list.get(pass).getId(),
                list.get(pass).getFields().get("nameCustomer").toString(),
                list.get(pass).getFields().get("addressCustomer").toString(),
                list.get(pass).getFields().get("phoneCustomer").toString()
                );
        return customer;
    }
}
