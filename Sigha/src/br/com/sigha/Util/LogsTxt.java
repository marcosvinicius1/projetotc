/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MarcosVinicius
 */
public class LogsTxt {
String data=new DataSistema().getDia()+""+new DataSistema().getMes()+""+new DataSistema().getAno();
    public void setTxt(String texto) {
        FileWriter arquivo;
        String artexto = getTxt() + "\n";
        
        try {
            arquivo = new FileWriter(new File("./logs/"+data+".txt"));
            arquivo.write(artexto + "" + texto);
            arquivo.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Gravar Logs\n" + e, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getTxt() {
        File arquivo;
        String texto = "";
        arquivo = new File("./logs/"+data+".txt");
        if (arquivo.exists()) {
            try {
                FileInputStream fis = new FileInputStream(arquivo);
                int ln;
                while ((ln = fis.read()) != -1) {
                    texto = texto + "" + String.valueOf((char) ln);
                }
                fis.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Ler Logs\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }

        return texto;
    }
}
