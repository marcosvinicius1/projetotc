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
public class ConfigBeans {
    private String local;
    private String senha;
    private String usuario;
    private int tema;
    private int empresa;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getTema() {
        return tema;
    }

    public void setTema(int tema) {
        this.tema = tema;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }
   
    
    
}
