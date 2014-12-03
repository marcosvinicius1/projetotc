/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MarcosVinicius
 */
public final class LogsTxt {

    public LogsTxt() {
        criaPasta(new DataSistema().getDia() + "." + new DataSistema().getMes() + "." + new DataSistema().getAno());
    }
    String tipo;
    String data = new DataSistema().getDia() + "." + new DataSistema().getMes() + "." + new DataSistema().getAno();
    String dataarquivo = new DataSistema().getDia() + "." + new DataSistema().getMes() + "." + new DataSistema().getAno() + "-" + new DataSistema().getHora() + "=" + new DataSistema().getMinuto() + "=" + new DataSistema().getSegundo();

    public void setTxt(String texto) {
        FileWriter arquivo;
        String artexto = getTxt() + "\n---------------------------\n";

        try {
            arquivo = new FileWriter(new File("./logs/" + data + "/" + dataarquivo + ".txt"));
            arquivo.write(artexto + "" + texto);
            arquivo.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Gravar Logs\n" + e, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setTxt(String texto, String tipo) {
        FileWriter arquivo;
        this.tipo = tipo;
        String artexto = getTxtTipo() + "\n---------------------------\n";

        try {
            arquivo = new FileWriter(new File("./logs/" + data + "/" + tipo + "-" + dataarquivo + ".txt"));
            arquivo.write(artexto + "" + texto);
            arquivo.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Gravar Logs de Erro\n" + e, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getTxt() {
        File arquivo;
        String texto = "";
        arquivo = new File("./logs/" + data + "/" + dataarquivo + ".txt");
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

    public String getTxtTipo() {
        File arquivo;
        String texto = "";
        arquivo = new File("./logs/" + data + "/" + this.tipo + "-" + dataarquivo + ".txt");
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

    public void criaPasta(String nome) {
        if (!new File("./logs/" + nome).exists()) {
            new File("./logs/" + nome).mkdir();
        }
    }
}
