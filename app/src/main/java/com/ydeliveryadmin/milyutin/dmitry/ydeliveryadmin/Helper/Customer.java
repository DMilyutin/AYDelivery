package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

public class Customer {

    private String _id;
    private String nameCustomer;
    private String addressCustomer;
    private String phoneCustomer;
    private String balanceCustomer;

    public Customer(){}

    public Customer(String _id, String nameCustomer, String addressCustomer, String phoneCustomer) {
        this._id = _id;
        this.nameCustomer = nameCustomer;
        this.addressCustomer = addressCustomer;
        this.phoneCustomer = phoneCustomer;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) { this._id = _id; }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) { this.phoneCustomer = phoneCustomer; }

    public String getBalanceCustomer() { return balanceCustomer; }

    public void setBalanceCustomer(String balanceCustomer) { this.balanceCustomer = balanceCustomer; }
}
