package com.example.myapplication.service_and_broadcast_receiver.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.myapplication.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getAction(), Toast.LENGTH_LONG).show();

        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {

        }else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {

        }

//        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.music);
//        mediaPlayer.start();
    }
}
