package com.example.myapplication.contacts.model;

import android.text.TextUtils;

public class ContactModel {
    private String name;
    private String phoneNumber;

    public ContactModel() {
        this.name = "Chưa xác định!";
        this.phoneNumber = "Chưa xác định!";
    }

    public ContactModel(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return TextUtils.isEmpty(name) ? "" : name; //nếu name == null thì trả về rỗng, ngược lại trả về name
    }

    public void setName(String name) {
        if(TextUtils.isEmpty(name)) {//nếu giá trị truyền vào là null hoặc rỗng thì bỏ qua không nhận
            return;
        }
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
