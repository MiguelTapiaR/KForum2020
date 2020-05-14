package com.administra.feriaCaballo.Model;

import java.io.Serializable;

public class FAQS implements Serializable {

    public String pregunta,respuesta;

    public FAQS(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }
}
