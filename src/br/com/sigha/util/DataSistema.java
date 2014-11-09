/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.util;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author MARCOS
 */
public class DataSistema {
    GregorianCalendar gc = new GregorianCalendar();
       private String dia = String.valueOf(gc.get(GregorianCalendar.DAY_OF_MONTH));
       private String mes = String.valueOf(gc.get(GregorianCalendar.MONTH) + 1);
       private String ano = String.valueOf(gc.get(GregorianCalendar.YEAR));
       private String hora = String.valueOf(gc.get(GregorianCalendar.HOUR_OF_DAY));
       private String minuto = String.valueOf(gc.get(GregorianCalendar.MINUTE));
       private String segundo = String.valueOf(gc.get(GregorianCalendar.SECOND));

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
   
    public String getDia() { 
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
     
    public String getMes() {
        return zeromes(mes);
    }
   
    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }

     private String zeromes(String mes){//acresenta zero se o mes for menor de 9
        int m=Integer.parseInt(mes);
        if(m<10){
            return "0"+String.valueOf(m);
        }
        return mes;
    }  
}

