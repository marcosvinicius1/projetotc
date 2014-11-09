/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.logica;

import br.com.sigha.dao.AuxHorarioCursoDao;
import br.com.sigha.view.ViewHorarioAula;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author MarcosVinicius
 */
public class ThreadGeraHorario implements Runnable {

    ViewHorarioAula content;
    String anoletivo;
    Date datageracao;

    public ThreadGeraHorario(ViewHorarioAula content, String anoletivo, Date datageracao) {
        this.content = content;
        this.anoletivo = anoletivo;
        this.datageracao = datageracao;
    }

    @Override
    public void run() {
        int ger = 0;
        int cont = 0;
        try {
            new AuxHorarioCursoDao().DeletaHorarioAux();
            content.GeraHorario(ger);
            content.BuscaHorarioAula(anoletivo, datageracao);

            JOptionPane.showMessageDialog(null, "Horario Gerado com Sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(ThreadGeraHorario.class.getName()).log(Level.SEVERE, null, ex);
        }

        //content.BuscaHorarioAula(anoletivo, datageracao);
        //gera a quantidade de horario passado pelo usuario
//        while (cont < 2) {
//            //verifica se tem erro no horario gerado se tiver gera novamente
////            while (!content.verificaHorario(datageracao)) {
////                content.GeraHorario(ger = 0);
////            }
//            cont++;
//            content.GeraHorario(ger = 1);
//        }
        content.Aguarde.setVisible(false);
        content.ativaNovoPesquisaExcluirEmprimir();
    }

}
