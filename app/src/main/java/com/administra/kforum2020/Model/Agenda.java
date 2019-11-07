package com.administra.kforum2020.Model;

public class Agenda {
    public String hora, evento;
    public int id;
    public Boolean header;

    public Agenda(String hora, String evento) {
        this.hora = hora;
        this.evento = evento;
        this.header = false;
    }
}
