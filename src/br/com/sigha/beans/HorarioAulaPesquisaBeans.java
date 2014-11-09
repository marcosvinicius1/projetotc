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
public class HorarioAulaPesquisaBeans {
    private String curso;
    private String anoletivo;
    private Date datageracao;
    private String unidade;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    
    
}
