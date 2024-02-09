package es.riberadeltajo.tarea6_miguelmanzanillaocaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.MediaController;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {
    private Menu miMenu;
    public static MediaController mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            ListaAudios.loadAudiosJson(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        mc = new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.constraint));
        ListaAudios.MiAdaptador=new MyaudioRecyclerViewAdapter(ListaAudios.audios);
        SharedPreferences misprefes= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (ListaAudios.MiAdaptador!=null){
            ListaAudios.media[0] = misprefes.getBoolean("audio",true);
            ListaAudios.media[1] = misprefes.getBoolean("video",true);
            ListaAudios.media[2] = misprefes.getBoolean("stream",true);
            ListaAudios.FiltrarArray();
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        miMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.filtro) {
            Intent i=new Intent(this, Ajustes.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
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
        if (ListaAudios.mp!=null){
            return ListaAudios.mp.getCurrentPosition();
        }
        return 0;
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