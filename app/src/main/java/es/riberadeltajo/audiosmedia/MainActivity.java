package es.riberadeltajo.audiosmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {
    MediaPlayer mp;
    MediaController mc;
    VideoView vidio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Imcompatible con lo nuevo
//        ImageButton btnplay=findViewById(R.id.btnPlay);
//        ImageButton btnstop=findViewById(R.id.btnStop);
//        ImageButton btnpause=findViewById(R.id.btnPause);
//        mp = MediaPlayer.create(getApplicationContext(), R.raw.eltiempopasara);

        mc = new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.constraint));

        vidio = findViewById(R.id.videoView);
        vidio.setMediaController(mc);
        //vidio.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.magia));
        vidio.setVideoURI(Uri.parse("https://riberadeltajo.es/PMDM_ut4/LME.mp4"));
        Handler h = new Handler(Looper.getMainLooper());
        vidio.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        mc.show(0);
                    }
                });
            }
        });
//
//        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                h.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mc.show();
//                    }
//                });
//            }
//        });
        //Imcompatible con lo nuevo
//        btnplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mp.start();
//            }
//        });
//        btnstop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mp.stop();
//            }
//        });
//        btnpause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mp.pause();
//            }
//        });
    }

    @Override
    public void start() {
        if (!mp.isPlaying()) {
            mp.start();
        }

    }

    @Override
    public void pause() {
        if (mp.isPlaying()) {
            mp.pause();
        }
    }

    @Override
    public int getDuration() {
        //duracion del medio que esta reproduciendo
        return mp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        //duracion exacta al momento
        return mp.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mp.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mp.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mp.getAudioSessionId();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mc.show(0);
        return super.onTouchEvent(event);
    }
}