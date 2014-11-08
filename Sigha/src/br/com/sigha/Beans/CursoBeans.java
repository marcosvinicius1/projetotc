/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.Beans;

/**
 *
 * @author MarcosVinicius
 */
public class CursoBeans {
    private int id;
    private int idprofessor;
    private String nome;
    private String nomecoordenador;
    private int turno;
    private int idunidade;
    private String ativo;

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getNomecoordenador() {
        return nomecoordenador;
    }

    public void setNomecoordenador(String nomecoordenador) {
        this.nomecoordenador = nomecoordenador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdprofessor() {
        return idprofessor;
    }

    public void setIdprofessor(int idprofessor) {
        this.idprofessor = idprofessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdunidade() {
        return idunidade;
    }

    public void setIdunidade(int idunidade) {
        this.idunidade = idunidade;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
    
    
    
}
