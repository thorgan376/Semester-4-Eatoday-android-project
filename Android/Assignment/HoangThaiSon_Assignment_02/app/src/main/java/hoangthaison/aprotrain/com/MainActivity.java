package hoangthaison.aprotrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import hoangthaison.aprotrain.com.adapters.TaskAdapter;
import hoangthaison.aprotrain.com.database.DBHelper;
import hoangthaison.aprotrain.com.models.MyTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText edtTaskTitle;
    private EditText edtTaskDescription;
    private CheckBox cbIsImportant;
    private Button btnAddNewTask;
    private ListView lvTasks;
    private TaskAdapter taskAdapter;
    private List<MyTask> allTask;
    private DBHelper taskDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configView();
        configListView();
        addListener();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (allTask.size() == 0) {
            allTask.addAll(taskDatabase.getAllTasks());
            taskAdapter.notifyDataSetChanged();
        }

    }

    private void configView() {
        edtTaskTitle = findViewById(R.id.edt_task_title);
        edtTaskDescription = findViewById(R.id.edt_task_description);
        btnAddNewTask = findViewById(R.id.btn_add_new_task);
        lvTasks = findViewById(R.id.lv_tasks);
        cbIsImportant = findViewById(R.id.ch_box_important);

        taskDatabase = DBHelper.getInstance(this);

    }

    private void configListView() {
        allTask = new ArrayList<>();
        taskAdapter = new TaskAdapter(allTask);
        lvTasks.setAdapter(taskAdapter);
    }

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.ch_box_important:
                if(checked) {
                    Toast.makeText(MainActivity.this, "Đã nhấn checkbox", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Bỏ nhấn checkbox", Toast.LENGTH_SHORT).show();
                }
        }
    }//Check checkbox has clicked or not - need onClick in layout.xml

    private void addListener() {
        btnAddNewTask.setOnClickListener(view -> {
            MyTask task = new MyTask();
            task.setTaskTitle(edtTaskTitle.getText().toString());
            task.setTaskContent(edtTaskDescription.getText().toString());
            task.setImportant(cbIsImportant.isChecked() ? true : false);
            task.setTaskId(Math.toIntExact((Calendar.getInstance().getTimeInMillis())/1000));

            if (edtTaskTitle.getText().toString().isEmpty()) {
                Toast.makeText(
                        MainActivity.this,
                        "Tiêu đề không được bỏ trống",
                        Toast.LENGTH_SHORT).show();

            } else if (edtTaskDescription.getText().toString().isEmpty()){
                Toast.makeText(
                        MainActivity.this,
                        "Nội dung không được bỏ trống",
                        Toast.LENGTH_SHORT).show();
            } else {
                DBHelper taskDatabase = DBHelper.getInstance(MainActivity.this);
                taskDatabase.addTask(task);

                allTask.add(task);
                taskAdapter.notifyDataSetChanged();

                edtTaskTitle.setText("");
                edtTaskDescription.setText("");
                cbIsImportant.setChecked(false);
            }
        });
    }

}