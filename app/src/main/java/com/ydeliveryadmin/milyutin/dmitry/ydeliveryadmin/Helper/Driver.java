package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin.Helper;

public class Driver {

    private String _id;
    private String nameDriver;
    private String lastNameDriver;
    private String phoneNumber;
    private String balanceDriver;
    private String statusWorked;


    public Driver(String _id, String nameDriver, String lastNameDriver, String phoneNumber,
                  String balanceDriver) {
        this._id = _id;
        this.nameDriver = nameDriver;
        this.lastNameDriver = lastNameDriver;
        this.phoneNumber = phoneNumber;
        this.balanceDriver = balanceDriver;
    }

    public String get_id() {
        return _id;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public String getLastNameDriver() {
        return lastNameDriver;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBalanceDriver() {
        return balanceDriver;
    }

    public String getStatusWorked() {
        return statusWorked;
    }
}
