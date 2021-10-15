package android.example.musiqa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static MediaPlayer myMediaPlayer;
    private ImageView previous, play_pause, next;
    private TextView songName, currentTime, totalTime;

    SeekBar seekBar;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        seekBar = (SeekBar) findViewById(R.id.seekbar);
        play_pause = findViewById(R.id.play_pause);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        songName = findViewById(R.id.songName);
        currentTime = findViewById(R.id.currentTime);
        totalTime = findViewById(R.id.totalTime);


        playSong();
        String duration = createTimerLabel(myMediaPlayer.getDuration());
        totalTime.setText(duration);

        play_pause.setOnClickListener(v -> playPauseSong());


        previous.setOnClickListener(v -> {
            if (myMediaPlayer.getCurrentPosition() > 0) {
                previousSong();
            }
        });


        next.setOnClickListener(v -> {
            if (myMediaPlayer.getCurrentPosition() > 0) {
                playNextSong();
            }
        });



    }

    private void playSong() {
        if (myMediaPlayer != null && myMediaPlayer.isPlaying()) {
            myMediaPlayer.pause();
            myMediaPlayer.stop();
            myMediaPlayer.release();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String songNames = intent.getStringExtra("name");
        songName.setText("Ring my bells");
        songName.setSelected(true);



        myMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ring_my_bells);
        myMediaPlayer.start();

        seekBar.setMax(myMediaPlayer.getDuration());
        playCycle();
        myMediaPlayer.start();


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input) {
                    myMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void playCycle() {
        seekBar.setProgress(myMediaPlayer.getCurrentPosition());

        if (myMediaPlayer.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }
    public String createTimerLabel(int duration) {
        String timer_label = "";
        int min = duration / 1000 / 60;
        int sec = duration / 1000 % 60;
        timer_label += min + ":";
        if (sec < 10) {
            timer_label += "0";
        }
        timer_label += sec;
        return timer_label;
    }

    private void previousSong() {
        myMediaPlayer.pause();
        myMediaPlayer.stop();
        myMediaPlayer.release();
        myMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.enrique);
        songName.setText("Iglesias");

        myMediaPlayer.start();
        if (myMediaPlayer.isPlaying()) {
            play_pause.setImageResource(R.drawable.pause);
        } else {
            play_pause.setImageResource(R.drawable.play);
        }
    }

    private void playNextSong() {
        myMediaPlayer.pause();
        myMediaPlayer.stop();
        myMediaPlayer.release();

        myMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.bailando);
        songName.setText("Enrique Iglesias");
        myMediaPlayer.start();
        if (myMediaPlayer.isPlaying()) {
            play_pause.setImageResource(R.drawable.pause);
        } else {
            play_pause.setImageResource(R.drawable.play);
        }
    }

    private void playPauseSong() {
        if (myMediaPlayer.isPlaying()) {
            play_pause.setImageResource(R.drawable.play);
            myMediaPlayer.pause();
        } else {
            play_pause.setImageResource(R.drawable.pause);
            myMediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myMediaPlayer.release();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myMediaPlayer.start();
        playCycle();
    }

    @Override
    public void onBackPressed() {
        myMediaPlayer.stop();
        myMediaPlayer.release();
        super.onBackPressed();

    }

}