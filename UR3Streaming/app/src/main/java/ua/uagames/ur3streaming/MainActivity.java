package ua.uagames.ur3streaming;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public void play(View v) {
        if (!player.isPlaying()) {
            try {
                player.setDataSource("http://nrcu.gov.ua:8000/ur3-mp3-l");
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();
        }
    }

    public void stop(View v) {
        if (player.isPlaying()) {
            player.stop();
            player.reset();
        }
    }
}
