/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.Beans;

import java.util.UUID;



/**
 *
 * @author MarcosVinicius
 */
public class OperadorBeans {
    private int id;
    private String ativo;
    private String nome;
    private String senha;
    private String senhaadm;
    private String tipo;
    private String usuario;
    private int idunidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaadm() {
        return senhaadm;
    }

    public void setSenhaadm(String senhaadm) {
        this.senhaadm = senhaadm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdunidade() {
        return idunidade;
    }

    public void setIdunidade(int idunidade) {
        this.idunidade = idunidade;
    }
    
}
