package com.example.myapplication.contacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.contacts.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends BaseAdapter {
    private List<ContactModel> contactModels;

    public ContactsAdapter(List<ContactModel> contactModels) {
        this.contactModels = contactModels;
    }

    @Override
    public int getCount() {//Cho listview biết số lượng dòng sẽ được hiển thị
        return contactModels.size();
    }

    @Override
    public Object getItem(int i) {//Cho listview biết object data được hiển thị ở vị trí thứ i là object nào
        return contactModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Check nếu item listview tại vị trí i chưa được khởi tạo, thì khởi tạo view cho item tại vị trí này
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_contact_on_listview, viewGroup, false);
        }
        //Bind UI controls từ file giao diện xml của item listview vào code Java
        TextView txtName = view.findViewById(R.id.txt_name);
        TextView txtPhoneNumber = view.findViewById(R.id.txt_phone_number);

        //Lấy model data tương ứng với view này tại vị trí i
        ContactModel contactModel = contactModels.get(i);
        //Bind data từ model đã lấy ra các phần tử view của item trong listview
        txtName.setText(contactModel.getName());
        txtPhoneNumber.setText(contactModel.getPhoneNumber());

        return view;
    }
}
