package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {


    private String id;
    private String nameCustomer;
    private String addressCustomer;
    private String nameDriver;
    private String lastNameDriver;
    private String coastOrder;
    private String numberOfAddress;
    private String addressForDriver;
    private String namesForDriver;
    private String phonesForDriver;
    private String timeOrder;
    private String orderStatus;

    public Order(String id, String nameCustomer, String addressCustomer,
                  String coastOrder, String numberOfAddress, String timeOrder) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.addressCustomer = addressCustomer;
        this.coastOrder = coastOrder;
        this.numberOfAddress = numberOfAddress;
        this.timeOrder = timeOrder;
    }

    protected Order(Parcel in) {
        id = in.readString();
        nameCustomer = in.readString();
        addressCustomer = in.readString();
        nameDriver = in.readString();
        lastNameDriver = in.readString();
        coastOrder = in.readString();
        numberOfAddress = in.readString();
        addressForDriver = in.readString();
        namesForDriver = in.readString();
        phonesForDriver = in.readString();
        timeOrder = in.readString();
        orderStatus = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public String getLastNameDriver() {
        return lastNameDriver;
    }

    public String getCoastOrder() {
        return coastOrder;
    }

    public String getNumberOfAddress() {
        return numberOfAddress;
    }

    public String getAddressForDriver() {
        return addressForDriver;
    }

    public String getNamesForDriver() {
        return namesForDriver;
    }

    public String getPhonesForDriver() {
        return phonesForDriver;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setAddressForDriver(String addressForDriver) { this.addressForDriver = addressForDriver; }

    public void setNamesForDriver(String namesForDriver) { this.namesForDriver = namesForDriver; }

    public void setPhonesForDriver(String phonesForDriver) { this.phonesForDriver = phonesForDriver; }

    public String getOrderStatus() { return orderStatus; }

    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nameCustomer);
        parcel.writeString(addressCustomer);
        parcel.writeString(nameDriver);
        parcel.writeString(lastNameDriver);
        parcel.writeString(coastOrder);
        parcel.writeString(numberOfAddress);
        parcel.writeString(addressForDriver);
        parcel.writeString(namesForDriver);
        parcel.writeString(phonesForDriver);
        parcel.writeString(timeOrder);
        parcel.writeString(orderStatus);

    }
}
