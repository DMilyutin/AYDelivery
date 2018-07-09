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

public class AdapterDriverActivity extends BaseAdapter {

    private LayoutInflater inflater;
    private List<DocumentInfo> list;

    public AdapterDriverActivity(Context context, List<DocumentInfo> list){
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
        if(view == null){view = inflater.inflate(R.layout.for_adapter_driver, viewGroup, false);}

        Driver driverForList = newDriver(i);

        ((TextView) view.findViewById(R.id.tvAdapterDriverNameDriver)).setText(driverForList.getNameDriver());
        ((TextView) view.findViewById(R.id.tvAdapterDriverLastNameDriver)).setText(driverForList.getLastNameDriver());
        ((TextView) view.findViewById(R.id.tvAdapterDriverPhoneDriver)).setText(driverForList.getPhoneNumber());
        ((TextView) view.findViewById(R.id.tvAdapterDriverBalanceDriver)).setText(driverForList.getBalanceDriver());

        return view;
    }


    private Driver newDriver(int poss){
        Driver driver = new Driver(list.get(poss).getId(),
                list.get(poss).getFields().get("nameDriver").toString(),
                list.get(poss).getFields().get("lastNameDriver").toString(),
                list.get(poss).getFields().get("phoneNumber").toString(),
                list.get(poss).getFields().get("balanceDriver").toString()
                );
        return driver;
    }
}
