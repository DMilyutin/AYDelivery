package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

public class Order {


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

    public Order(String id, String nameCustomer, String addressCustomer,
                  String coastOrder, String numberOfAddress, String timeOrder) {
        this.id = id;
        this.nameCustomer = nameCustomer;
        this.addressCustomer = addressCustomer;
        this.coastOrder = coastOrder;
        this.numberOfAddress = numberOfAddress;
        this.timeOrder = timeOrder;
    }

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
}
