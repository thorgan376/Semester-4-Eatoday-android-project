package com.example.myapplication.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.contacts.adapter.ContactsAdapter;
import com.example.myapplication.contacts.model.ContactModel;
import com.example.myapplication.database.Database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private static final String TAG = ContactsActivity.class.getSimpleName();

    Button btnAddNewContact;
    ListView listView;
    ContactsAdapter contactsAdapter;
    List<ContactModel> contactModels;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        configView();
        configListView();
        addListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //lần đầu mở app lên sẽ lấy dữ liệu cũ đã lưu vào database SQLite để hiển thị
        if(contactModels.size() == 0) {
            contactModels.addAll(database.getAllContacts());
            contactsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void configView() {
        btnAddNewContact = findViewById(R.id.btn_add_new_contact);
        listView = findViewById(R.id.list_view_contacts);

        database = Database.getInstance(this);
    }

    private void configListView() {
        contactModels = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(contactModels);
        listView.setAdapter(contactsAdapter);
    }

    private void addListener() {
        btnAddNewContact.setOnClickListener(view -> {
            ContactModel contactModel = new ContactModel();
            contactModel.setName("Name " + Calendar.getInstance().getTimeInMillis());
            contactModel.setPhoneNumber("0987654321");
            contactModel.setId(Calendar.getInstance().getTimeInMillis()/1000);

            //Lưu lại vào database SQLite
            Database database = Database.getInstance(ContactsActivity.this);
            database.addContact(contactModel);

            //Thêm vào datasource của listview để hiển thị luôn ra màn hình
            contactModels.add(contactModel);
            contactsAdapter.notifyDataSetChanged();
        });
    }
}