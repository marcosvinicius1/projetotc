/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.Util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcosVinicius
 */
public class QtdeOcorrenciaCaracter {
    private int ContCaracter(char caracter,String palavra){
        int cont=0;
        for(int i=0;i<palavra.length();i++){
            if(caracter==palavra.charAt(i)){
             cont++;   
            }
        }
       return cont;
    }
    public List<String> ListPalavra(char caracter,String palavra){
        List<String> lc = new ArrayList<>();
        for(int i=0;i<ContCaracter(caracter, palavra);i++){
            lc.add(palavra.split("\\"+caracter+"")[i]);
        }
        return lc;
    }
}
