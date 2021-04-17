package com.example.design_patterns_shop.model;

public class UserModel {

    String name;
    String phone;
    String address1;
    String address2;


    public UserModel(String name, String phone, String address1, String address2) {

        this.name = name;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
    }

    public UserModel() {

        this.name = null;
        this.phone = null;
        this.address1 = null;
        this.address2 = null;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

}
