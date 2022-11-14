package com.example.myapplication.service_and_broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.service_and_broadcast_receiver.broadcast_receiver.MyBroadcastReceiver;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceSessionActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_session);

        configView();
    }

    private void configView() {
        ButterKnife.bind(this);

        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(myBroadcastReceiver, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
        registerReceiver(myBroadcastReceiver, new IntentFilter("my_class_android_aptech"));
    }

    @OnClick(R.id.btn_play_music)
    void onActionPlayMusic() {
        mediaPlayer = MediaPlayer.create(ServiceSessionActivity.this, R.raw.music);
        mediaPlayer.start();
        Toast.makeText(this, "Playing...", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_stop_music)
    void onActionStopMusic() {
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            Toast.makeText(this, "Stopped!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_play_music_with_service)
    void onActionPlayMusicWithService() {
        Intent intentToService = new Intent(ServiceSessionActivity.this, MyService.class);
        intentToService.putExtra("music_id", R.raw.music);
        startService(intentToService);
        Toast.makeText(this, "My service is started!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_stop_music_with_service)
    void onActionStopMusicWithService() {
        Intent intentToService = new Intent(ServiceSessionActivity.this, MyService.class);
        stopService(intentToService);
        Toast.makeText(this, "My service is stopped!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_send_broadcast)
    void onActionSendBroadcast() {
        Intent intentToBroadcast = new Intent("my_class_android_aptech");
        sendBroadcast(intentToBroadcast);
    }
}