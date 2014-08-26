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
public class OperadorLogadoBeans {
    private static String logadonome;
    private static String logadosenhaadm;
    private static String logadotipo;

    public String getLogadonome() {
        return logadonome;
    }

    public void setLogadonome(String logadonome) {
        OperadorLogadoBeans.logadonome = logadonome;
    }

    public String getLogadosenhaadm() {
        return logadosenhaadm;
    }

    public void setLogadosenhaadm(String logadosenhaadm) {
        OperadorLogadoBeans.logadosenhaadm = logadosenhaadm;
    }

    public String getLogadotipo() {
        return logadotipo;
    }

    public void setLogadotipo(String logadotipo) {
        OperadorLogadoBeans.logadotipo = logadotipo;
    }
}
