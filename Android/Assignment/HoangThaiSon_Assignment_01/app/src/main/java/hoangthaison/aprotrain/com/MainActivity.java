package hoangthaison.aprotrain.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;

import hoangthaison.aprotrain.com.students.adapter.StudentAdapter;
import hoangthaison.aprotrain.com.students.model.Student;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = AppCompatActivity.class.getSimpleName();
    private EditText edtFullName;
    private EditText edtStudentCode;
    private Button btnAddStudentInfo;
    private ListView lvStudents;
    private StudentAdapter studentAdapter;
    private List<Student> students;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configView();
        configListView();
        addListener();
    }

    private void configView() {
        edtFullName = findViewById(R.id.edt_full_name);
        edtStudentCode = findViewById(R.id.edt_student_code);
        btnAddStudentInfo = findViewById(R.id.btn_add_student_info);
        lvStudents = findViewById(R.id.lv_students);
    }

    private void configListView() {
        students = new ArrayList<>();
        studentAdapter = new StudentAdapter(students);
        lvStudents.setAdapter(studentAdapter);
    }

    private void addListener() {
        btnAddStudentInfo.setOnClickListener(view -> {
            String fullName= edtFullName.getText().toString();
            String rollNumber = edtStudentCode.getText().toString();
            if (!fullName.isEmpty() && !rollNumber.isEmpty()){
                students.add(new Student(fullName,rollNumber));
                studentAdapter.notifyDataSetChanged();
                Log.e(TAG, "data: " + fullName + rollNumber);
            }
        });
    }
}