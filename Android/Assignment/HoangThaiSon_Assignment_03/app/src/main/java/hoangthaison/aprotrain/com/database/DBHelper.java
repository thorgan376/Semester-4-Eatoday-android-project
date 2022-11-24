package hoangthaison.aprotrain.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import hoangthaison.aprotrain.com.models.Note;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "note_list_database";

    private SQLiteDatabase database;
    private static DBHelper instance;

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        //create again
        onCreate(sqLiteDatabase);
    }

    private static final String TABLE_NOTE = "table_note";

    private static final String NOTE_ID = "note_id";
    private static final String NOTE_TITLE = "note_title";
    private static final String TIME_CREATED = "time_created";

    private static final String CREATE_TABLE_NOTE =
            "create table table_note ("
                    + "note_id integer default 0, "
                    + "note_title text, "
                    + "time_created text "
                    + ");";
    public void addNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NOTE_ID, note.getId());
        values.put(NOTE_TITLE, note.getNote());
        values.put(TIME_CREATED, note.getTime());

        this.database.insert(TABLE_NOTE, null, values);
        Log.e("Log: ", "row inserted, ID = " + values);
    }

    public void deleteNote(int note_id) {
        this.database.delete(TABLE_NOTE, NOTE_ID + " = " + note_id, null);
    }

    public List<Note> getAllNotes() {
        List<Note> listNotes = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NOTE;
        Cursor cursor = this.database.rawQuery(query,null);

        if (cursor != null && cursor.getCount() != 0 ) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setNote(cursor.getString(1));
                note.setTime(cursor.getString(2));
                listNotes.add(note);
                cursor.moveToNext();
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return listNotes;
    }

    public void updateNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NOTE_TITLE, note.getNote());
        values.put(TIME_CREATED, note.getTime());

        this.database.
                update(TABLE_NOTE, values, NOTE_ID + " = "+ note.getId(),null);
    }
}
