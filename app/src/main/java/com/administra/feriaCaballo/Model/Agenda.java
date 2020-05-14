package com.administra.feriaCaballo.Model;

import java.io.Serializable;

public class Agenda implements Serializable {
    public String hora, evento,id;
    public Boolean header;
    public int abrir;

    public Agenda(String hora, String evento, String id,int abrir) {
        this.hora = hora;
        this.evento = evento;
        this.header = false;
        this.id = id;
        this.abrir = abrir;
    }
}
