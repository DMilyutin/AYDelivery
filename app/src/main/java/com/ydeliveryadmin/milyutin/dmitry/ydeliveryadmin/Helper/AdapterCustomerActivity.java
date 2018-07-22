package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.R;

import java.util.List;
import java.util.stream.Collectors;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

public class AdapterCustomerActivity extends BaseAdapter {

    private LayoutInflater inflater;
    private List<DocumentInfo> listCustomers;
    private List<DocumentInfo> listBalance;

    public AdapterCustomerActivity(Context context, List<DocumentInfo> list1, List<DocumentInfo> list2){
        this.listCustomers = list1;
        listBalance = list2;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listCustomers.size();
    }

    @Override
    public Object getItem(int position) {
        return listCustomers.get(position);
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
        ((TextView) view.findViewById(R.id.tvAdapterCustomerBalance))
                .setText(customerForList.getBalanceCustomer());

        return view;
    }

    private Customer newCustomer(int pass){
        Customer customer = new Customer(listCustomers.get(pass).getId(),
                listCustomers.get(pass).getFields().get("nameCustomer").toString(),
                listCustomers.get(pass).getFields().get("addressCustomer").toString(),
                listCustomers.get(pass).getFields().get("phoneCustomer").toString()
                );

            customer.setBalanceCustomer(balanceCustomer(customer.get_id()));
        return customer;
    }


    private String balanceCustomer(String idCustomer){

        for (int i = 0; i < listBalance.size(); i++){
            DocumentInfo d  = listBalance.get(i);
            if(d.getFields().get("idCustomer").equals(idCustomer)){
                String balance = d.getFields().get("balanceCustomer").toString();
                return balance;
            }
        }
        return "Хз";
    }
}
