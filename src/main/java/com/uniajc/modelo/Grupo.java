package com.uniajc.modelo;

public class Grupo {
    private int idGrupo;
    private Materia materia;
    private Docente docente;
    private String aula;
    private String horario;

    public Grupo() {}

    public Grupo(int idGrupo, Materia materia, Docente docente, String aula, String horario) {
        this.idGrupo = idGrupo;
        this.materia = materia;
        this.docente = docente;
        this.aula = aula;
        this.horario = horario;
    }

    // Getters and Setters
    public int getIdGrupo() {
        return idGrupo;
    }   
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    public Materia getMateria() {
        return materia;
    }
    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    public Docente getDocente() {
        return docente;
    }
    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    public String getAula() {
        return aula;
    }
    public void setAula(String aula) {
        this.aula = aula;
    }
    public String getHorario() {
        return horario;
    }
    public void setHorario(String horario) {
        this.horario = horario;
    }
}
