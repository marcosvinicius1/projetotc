/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.beans;

import java.util.Date;

/**
 *
 * @author MarcosVinicius
 */
public class HorarioVazioBeans {

    private int id;
    private int idcurso;
    private int periodo;
    private String inicio;
    private String termino;
    private String anoletivo;
    private Date datageracao;
    private String materias;
    private String dia;
    private int professormateria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getAnoletivo() {
        return anoletivo;
    }

    public void setAnoletivo(String anoletivo) {
        this.anoletivo = anoletivo;
    }

    public Date getDatageracao() {
        return datageracao;
    }

    public void setDatageracao(Date datageracao) {
        this.datageracao = datageracao;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getProfessormateria() {
        return professormateria;
    }

    public void setProfessormateria(int professormateria) {
        this.professormateria = professormateria;
    }
   
    
    

}
