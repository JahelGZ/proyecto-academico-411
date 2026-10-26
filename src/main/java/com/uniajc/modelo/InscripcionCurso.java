package com.uniajc.modelo;

public class InscripcionCurso {

    private int idInscripcion;
    private Estudiante estudiante;
    private Grupo grupo;
    private float notaFinal;
    private String estado;

    public InscripcionCurso() {}

    public InscripcionCurso(int idInscripcion, Estudiante estudiante, Grupo grupo, float notaFinal, String estado) {
        this.idInscripcion = idInscripcion;
        this.estudiante = estudiante;
        this.grupo = grupo;
        this.notaFinal = notaFinal;
        this.estado = estado;
    }
    // Getters and Setters
    public int getIdInscripcion() {
        return idInscripcion;
    }
    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    public Grupo getGrupo() {
        return grupo;
    }
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    public float getNotaFinal() {
        return notaFinal;
    }
    public void setNotaFinal(float notaFinal) {
        this.notaFinal = notaFinal;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
