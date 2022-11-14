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

import hoangthaison.aprotrain.com.models.MyTask;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "task_manager_database";

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
        sqLiteDatabase.execSQL(CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        //create again
        onCreate(sqLiteDatabase);
    }

    private static final String TABLE_TASK = "table_task";

    private static final String TASK_ID = "task_id";
    private static final String TASK_TITLE = "task_title";
    private static final String TASK_CONTENT = "task_content";
    private static final String IS_IMPORTANT = "is_important";

//    private static final String CREATE_TABLE_TASK =
//        "CREATE TABLE TABLE_TASK ("
//            + "TASK_ID INT DEFAULT 0, TASK_TITLE TEXT,"
//            + "TASK_CONTENT TEXT, IS_IMPORTANT BOOLEAN"
//            + ");";
    private static final String CREATE_TABLE_TASK =
            "create table table_task ("
                    + "task_id integer default 0, task_title text,"
                    + "task_content text, is_important boolean"
                    + ");";


    public void addTask(MyTask task) {
        ContentValues values = new ContentValues();
        values.put(TASK_ID, task.getTaskId());
        values.put(TASK_TITLE, task.getTaskTitle());
        values.put(TASK_CONTENT, task.getTaskContent());
        values.put(IS_IMPORTANT, task.isImportant());

        this.database.insert(TABLE_TASK, null, values);
        Log.e("Log: ", "row inserted, ID = " + values);
    }

    public void deleteTask(int task_id) {
        this.database.delete(TABLE_TASK, TASK_ID + " = " + task_id, null);
    }

    public List<MyTask> getAllTasks() {
        List<MyTask> allTasks = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_TASK;
        Cursor cursor = this.database.rawQuery(query,null);

        if (cursor != null && cursor.getCount() != 0 ) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                MyTask task = new MyTask();
                task.setTaskId(cursor.getInt(0));
                task.setTaskTitle(cursor.getString(1));
                task.setTaskContent(cursor.getString(2));
                task.setImportant(cursor.getInt(3) != 0); // == getBoolean()

                allTasks.add(task);
                cursor.moveToNext();
            }
        }

        if (cursor != null) {
            cursor.close();
        }

        return allTasks;
    }

    public void updateTask(MyTask task) {
        ContentValues values = new ContentValues();
        values.put(TASK_TITLE, task.getTaskTitle());
        values.put(TASK_CONTENT, task.getTaskContent());
        values.put(IS_IMPORTANT, task.isImportant());

        this.database.
            update(TABLE_TASK, values, TASK_ID + " = "+task.getTaskId(),null);
    }

}
