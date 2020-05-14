package com.administra.feriaCaballo.Model;

import java.io.Serializable;

public class Speakers implements Serializable {

    String nombre, titulo, id, imagenSpeaker, plecaSpeaker, biografia;
    int orden;
    Boolean header;

    public Speakers(String nombre, String titulo, String id, String imagenSpeaker, String plecaSpeaker, String biografia, int orden) {
        this.nombre = nombre;
        this.titulo = titulo;
        this.id = id;
        this.imagenSpeaker = imagenSpeaker;
        this.plecaSpeaker = plecaSpeaker;
        this.biografia = biografia;
        this.orden = orden;
        this.header = false;
    }

    public Boolean getHeader() {
        return header;
    }

    public void setHeader(Boolean header) {
        this.header = header;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagenSpeaker() {
        return imagenSpeaker;
    }

    public void setImagenSpeaker(String imagenSpeaker) {
        this.imagenSpeaker = imagenSpeaker;
    }

    public String getPlecaSpeaker() {
        return plecaSpeaker;
    }

    public void setPlecaSpeaker(String plecaSpeaker) {
        this.plecaSpeaker = plecaSpeaker;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
