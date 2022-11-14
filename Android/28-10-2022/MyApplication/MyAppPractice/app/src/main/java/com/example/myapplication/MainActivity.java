package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText edtFullName;
    private EditText edtDateOfBirth;
    private Button btnShowInfo;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configView();
        addListener();
    }

    private void configView() {
        edtFullName = findViewById(R.id.edt_full_name);
        edtDateOfBirth = findViewById(R.id.edt_date_of_birth);
        btnShowInfo = findViewById(R.id.btn_show_info);
        tvInfo = findViewById(R.id.tv_show_info);
    }

    private void addListener() {
        btnShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Log.println(Log.INFO, "thaison", "đã click button" );
                String fullname = edtFullName.getText().toString();
                String dateOfBirth = edtDateOfBirth.getText().toString();
//                Log.e(TAG, "fullname " + fullname);
//                Log.e(TAG, "dateOfBirth: " + dateOfBirth);

//                String displayValue = fullname + " - " + dateOfBirth;
                String displayValue = String.format("%s - %s", fullname, dateOfBirth);
                tvInfo.setText(displayValue);
            }
        });
    }
}