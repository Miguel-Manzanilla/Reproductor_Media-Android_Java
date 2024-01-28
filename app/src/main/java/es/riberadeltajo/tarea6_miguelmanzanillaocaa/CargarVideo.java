package es.riberadeltajo.tarea6_miguelmanzanillaocaa;

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

public class CargarVideo extends AppCompatActivity implements MediaController.MediaPlayerControl {
    public static MediaController mc;
    VideoView vidio;
    public static int version;
    public static String recurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_video);
        mc = new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.constraint));
        ImageButton btn=findViewById(R.id.btnVolver);
        vidio = findViewById(R.id.videoView);
        vidio.setMediaController(mc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (version==1){
            //video
            int resourceId = getResources().getIdentifier(recurso, "raw", getPackageName());
            vidio.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+resourceId));
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
        } else if (version==2) {
            //streaming
            vidio.setVideoURI(Uri.parse(recurso));
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
        }
    }
    @Override
    public void start() {
        if (!ListaAudios.mp.isPlaying()) {
            ListaAudios.mp.start();
        }

    }

    @Override
    public void pause() {
        if (ListaAudios.mp.isPlaying()) {
            ListaAudios.mp.pause();
        }
    }

    @Override
    public int getDuration() {
        //duracion del medio que esta reproduciendo
        return ListaAudios.mp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        //duracion exacta al momento
        return ListaAudios.mp.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        ListaAudios.mp.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return ListaAudios.mp.isPlaying();
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
        return ListaAudios.mp.getAudioSessionId();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mc.show(0);
        return super.onTouchEvent(event);
    }
}