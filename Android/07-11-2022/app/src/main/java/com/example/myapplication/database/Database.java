package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.contacts.model.ContactModel;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "my_application_db";

    private SQLiteDatabase database;
    private static Database instance;

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
        return instance;
    }

    private Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //backup data

        //drop table
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
        //create table again
        onCreate(sqLiteDatabase);
    }

    //Contact Table
    private static final String TABLE_CONTACT = "table_contact";
    //columns name
    private static final String CONTACT_ID = "contact_id";
    private static final String CONTACT_NAME = "contact_name";
    private static final String CONTACT_PHONE = "contact_phone";

    private static final String CREATE_TABLE_CONTACT = "CREATE TABLE " + TABLE_CONTACT
            + "(" + CONTACT_ID + " LONG DEFAULT 0," + CONTACT_NAME + " TEXT, "
            + CONTACT_PHONE + " TEXT )";

    public void addContact(ContactModel contactModel) {
        ContentValues values = new ContentValues();
        values.put(CONTACT_ID, contactModel.getId());
        values.put(CONTACT_NAME, contactModel.getName());
        values.put(CONTACT_PHONE, contactModel.getPhoneNumber());

        this.database.insert(TABLE_CONTACT, null, values);
    }

    public void updateContact(ContactModel contactModel) {
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME, contactModel.getName());
        values.put(CONTACT_PHONE, contactModel.getPhoneNumber());

        this.database.update(TABLE_CONTACT, values,
                CONTACT_ID + " = " + contactModel.getId(), null);
    }

    public List<ContactModel> getAllContacts() {
        List<ContactModel> contactModels = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_CONTACT;
        Cursor cursor = this.database.rawQuery(query, null);

        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ContactModel contactModel = new ContactModel();
                contactModel.setId(cursor.getLong(0));
                contactModel.setName(cursor.getString(1));
                contactModel.setPhoneNumber(cursor.getString(2));

                contactModels.add(contactModel);
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }

        return contactModels;
    }

    public void deleteContact(long contactId) {
        this.database.delete(TABLE_CONTACT, CONTACT_ID + " = " + contactId, null);
    }
}
