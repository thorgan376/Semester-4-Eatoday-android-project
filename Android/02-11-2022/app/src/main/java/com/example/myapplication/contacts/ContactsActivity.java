package com.example.myapplication.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.contacts.adapter.ContactsAdapter;
import com.example.myapplication.contacts.model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private static final String TAG = ContactsActivity.class.getSimpleName();

    Button btnAddNewContact;
    ListView listView;
    ContactsAdapter contactsAdapter;
    List<ContactModel> contactModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        configView();
        configListView();
        addListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void configView() {
        btnAddNewContact = findViewById(R.id.btn_add_new_contact);
        listView = findViewById(R.id.list_view_contacts);
    }

    private void configListView() {
        contactModels = new ArrayList<>();
        contactsAdapter = new ContactsAdapter(contactModels);
        listView.setAdapter(contactsAdapter);
    }

    private void addListener() {
        btnAddNewContact.setOnClickListener(view -> {
            contactModels.add(new ContactModel("Nguyễn Văn B", "0987654322"));
            contactsAdapter.notifyDataSetChanged();
        });
    }
}