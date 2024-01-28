package es.riberadeltajo.tarea6_miguelmanzanillaocaa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListaAudios {
    public static ArrayList<Audio> audios = new ArrayList<Audio>();
    public static boolean[] media = new boolean[3];
    public static MyaudioRecyclerViewAdapter MiAdaptador;
    public  static MediaPlayer mp;
    public static void loadAudiosJson(Context c) throws IOException, JSONException {
        audios.clear();
        String json = null;

        InputStream is =
                c.getAssets().open("audios.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        JSONObject jsonObject = new JSONObject(json);
        JSONArray couchList = jsonObject.getJSONArray("recursos_list");
        for (int i = 0; i < couchList.length(); i++) {
            JSONObject jsonCouch = couchList.getJSONObject(i);
            Bitmap photo=null;
            try {
                photo = BitmapFactory.decodeStream(c.getAssets().open("drawable/" + jsonCouch.getString("imagen")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Audio a=new Audio();
            a.setImg(photo);
            a.setDescripcion(jsonCouch.getString("descripcion"));
            a.setNombre(jsonCouch.getString("nombre"));
            a.setTipo(jsonCouch.getInt("tipo"));
            a.setUri(jsonCouch.getString("URI"));
            audios.add(a);
        }


    }
    public static void FiltrarArray(){
        ArrayList<Audio> nuevosAudios = new ArrayList<Audio>();
        for (Audio a:audios) {
            if (media[a.getTipo()]){
                nuevosAudios.add(a);
            }
        }
        audios.clear();
        audios.addAll(nuevosAudios);
        MiAdaptador.notifyDataSetChanged();
    }
}
