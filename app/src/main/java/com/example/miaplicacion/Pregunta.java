package com.example.miaplicacion;
import java.util.List;
public class Pregunta {
    private int id;
    private String pregunta;
    private List<Respuesta> opciones;
    private int resposta_correcta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<Respuesta> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Respuesta> opciones) {
        this.opciones = opciones;
    }

    public int getResposta_correcta() {
        return resposta_correcta;
    }

    public void setResposta_correcta(int resposta_correcta) {
        this.resposta_correcta = resposta_correcta;
    }
}
