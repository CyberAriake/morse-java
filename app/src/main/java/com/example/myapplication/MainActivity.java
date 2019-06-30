package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private SoundPool soundPool;
    private MediaPlayer mediaPlayer;
    private int soundYa;
    int streamId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();

        mediaPlayer = MediaPlayer.create(this,R.raw.radio1);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.1f,0.1f);
        mediaPlayer.start();

        soundYa = soundPool.load(this, R.raw.ya,1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug", "sampleId=" + sampleId);
                Log.d("debug", "status=" + status);
            }
        });


        textView = findViewById(R.id.text_view);

        Button button = findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    streamId = soundPool.play(soundYa,1.0f,1.0f,0,0,1);
                    textView.setText(R.string.hello);

                    return true;
                } else {
                    soundPool.stop(streamId);
                    textView.setText(R.string.world);
                    return false;
                }
            }
        });
    }
}



//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                if(flag){
//                    textView.setText(R.string.hello);
//                    flag = false;
//                }
//                else{
//                    textView.setText(R.string.world);
//                    flag = true;
//                }
//            }
//        });
//    }
//}
