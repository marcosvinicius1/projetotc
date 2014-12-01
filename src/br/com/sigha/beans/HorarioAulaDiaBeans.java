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
public class HorarioAulaDiaBeans extends HorarioAulaBeans {
    private Integer idmateriap;
    private Integer idmateria;
    private String sequencial;
    private Integer qtdeauladia;

    public Integer getIdmateriap() {
        return idmateriap;
    }

    public void setIdmateriap(Integer idmateriap) {
        this.idmateriap = idmateriap;
    }

    public Integer getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(Integer idmateria) {
        this.idmateria = idmateria;
    }

    public String getSequencial() {
        return sequencial;
    }

    public void setSequencial(String sequencial) {
        this.sequencial = sequencial;
    }

    public Integer getQtdeauladia() {
        return qtdeauladia;
    }

    public void setQtdeauladia(Integer qtdeauladia) {
        this.qtdeauladia = qtdeauladia;
    }
    
}
