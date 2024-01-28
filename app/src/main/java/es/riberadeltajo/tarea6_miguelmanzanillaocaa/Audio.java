package es.riberadeltajo.tarea6_miguelmanzanillaocaa;

import android.graphics.Bitmap;

public class Audio {
    private String nombre;
    private String  descripcion;
    private int tipo;
    private String uri;
    private Bitmap img;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Audio(String nombre, String descripcion, int tipo, String uri, Bitmap img) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.uri = uri;
        this.img = img;
    }
    public Audio(){

    }
}
