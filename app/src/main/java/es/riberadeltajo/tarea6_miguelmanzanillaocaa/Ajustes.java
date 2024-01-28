package es.riberadeltajo.tarea6_miguelmanzanillaocaa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

public class Ajustes extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        try {
            ListaAudios.loadAudiosJson(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        SharedPreferences misprefesDefault = PreferenceManager.getDefaultSharedPreferences(this);
        misprefesDefault.registerOnSharedPreferenceChangeListener(this);
        boolean audio = misprefesDefault.getBoolean("audio", true);
        ListaAudios.media[0] = audio;
        boolean video = misprefesDefault.getBoolean("video", true);
        ListaAudios.media[1] = video;
        boolean stream = misprefesDefault.getBoolean("stream", true);
        ListaAudios.media[2] = stream;
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaAudios.FiltrarArray();
                SharedPreferences misprefes=getSharedPreferences("prefeAudio",MODE_PRIVATE);
                SharedPreferences.Editor ed =misprefes.edit();
                ed.putBoolean("audio",audio);
                ed.putBoolean("video",video);
                ed.putBoolean("stream",stream);
                ed.apply();
                finish();
            }
        });
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, @Nullable String key) {
        if (key != null) {
            switch (key) {
                case "audio":
                    ListaAudios.media[0] = sharedPreferences.getBoolean("audio", true);
                    break;
                case "video":
                    ListaAudios.media[1] = sharedPreferences.getBoolean("video", true);
                    break;
                case "stream":
                    ListaAudios.media[2] = sharedPreferences.getBoolean("stream", true);
                    break;
            }
        }
    }
}
