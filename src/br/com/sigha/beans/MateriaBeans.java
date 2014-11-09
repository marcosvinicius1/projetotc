/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.beans;

/**
 *
 * @author MarcosVinicius
 */
public class MateriaBeans extends HorarioCursoBeans {
 private int id;
 private int idcurso;
 private String nome;
 private int periodo;
 private String ativo;
 private String chanual;
 private String	chaula;
 private String	qtdeaulasem;
 private String qtdeauladia;
 private String	segunda;
 private String	terca;
 private String	quarta;
 private String	quinta;
 private String	sexta;
 private String sabado;
 private String domingo;
 private String nomecurso;
 private String sigla;
 private String sequencial;
 private int ocorrencia;

 @Override
    public int getId() {
        return id;
    }

 @Override
    public void setId(int id) {
        this.id = id;
    }

 @Override
    public int getIdcurso() {
        return idcurso;
    }

 @Override
    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getChanual() {
        return chanual;
    }

    public void setChanual(String chanual) {
        this.chanual = chanual;
    }

    public String getChaula() {
        return chaula;
    }

    public void setChaula(String chaula) {
        this.chaula = chaula;
    }

    public String getQtdeaulasem() {
        return qtdeaulasem;
    }

    public void setQtdeaulasem(String qtdeaulasem) {
        this.qtdeaulasem = qtdeaulasem;
    }

    public String getQtdeauladia() {
        return qtdeauladia;
    }

    public void setQtdeauladia(String qtdeauladia) {
        this.qtdeauladia = qtdeauladia;
    }

 @Override
    public String getSegunda() {
        return segunda;
    }

 @Override
    public void setSegunda(String segunda) {
        this.segunda = segunda;
    }

 @Override
    public String getTerca() {
        return terca;
    }

 @Override
    public void setTerca(String terca) {
        this.terca = terca;
    }

 @Override
    public String getQuarta() {
        return quarta;
    }

 @Override
    public void setQuarta(String quarta) {
        this.quarta = quarta;
    }

 @Override
    public String getQuinta() {
        return quinta;
    }

 @Override
    public void setQuinta(String quinta) {
        this.quinta = quinta;
    }

 @Override
    public String getSexta() {
        return sexta;
    }

 @Override
    public void setSexta(String sexta) {
        this.sexta = sexta;
    }

 @Override
    public String getSabado() {
        return sabado;
    }

 @Override
    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

 @Override
    public String getDomingo() {
        return domingo;
    }

 @Override
    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSequencial() {
        return sequencial;
    }

    public void setSequencial(String sequencial) {
        this.sequencial = sequencial;
    }

    public int getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(int ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    
    
}
