package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.contacts.ContactsActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String CHANNEL_ID = "my_application_channel_notification";
    private static final String CONTENT_SAVED = "content_saved";
    Button btnShowNotification;
    EditText edtInputContent;
    Button btnDisplayContactsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowNotification = findViewById(R.id.btn_show_notification);
        edtInputContent = findViewById(R.id.edt_input_content);
        btnDisplayContactsActivity = findViewById(R.id.btn_display_contacts_activity);

        createNotificationChannel();

        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Hello")
                        .setContentText("Xin chào các bạn!")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify((int) Calendar.getInstance().getTimeInMillis(), builder.build());
            }
        });

        btnDisplayContactsActivity.setOnClickListener(view -> {
            //OnClick function
            Intent intentToContacts = new Intent(MainActivity.this, ContactsActivity.class);
            startActivity(intentToContacts);
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "My Application Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("my_app", MODE_PRIVATE);
        String contentSaved = sharedPreferences.getString(CONTENT_SAVED,"");
        edtInputContent.setText(contentSaved);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String inputContent=edtInputContent.getText().toString();
         SharedPreferences sharedPreferences= MainActivity.this.getSharedPreferences("my_app", MODE_PRIVATE);
          SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CONTENT_SAVED, inputContent);
        editor.commit();
    }
}