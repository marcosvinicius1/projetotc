/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Logica;

import br.com.sigha.Beans.AuxHorarioCursoBeans;
import br.com.sigha.Beans.HorarioAulaBeans;
import br.com.sigha.Dao.AuxHorarioCursoDao;
import br.com.sigha.Dao.HorarioAulaDao;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import br.com.sigha.view.ViewHorarioAula;

/**
 *
 * @author MarcosVinicius
 */
public class LSincronizaProfessor {

    //metodo percorre a auxhorariocurso
    public void HorarioProfessor(int idcurso, String anoletivo, Date datageracao) {
        try {
//            //metodo busca o horario do curso na tabela horarioaula
//            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(idcurso, anoletivo, datageracao);
//            //metodo busaca os periodos de um curso na tabela horarioaula
//            List<HorarioAulaBeans> lhorariop=new HorarioAulaDao().HorarioGroupPeriodo(idcurso, anoletivo, datageracao);
            //metodo busca horario na tabela auxhorariocurso
            List<AuxHorarioCursoBeans> lauxhorario = new AuxHorarioCursoDao().BuscaAuxHorarioCurso(idcurso, anoletivo);            
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getSegunda())) {
                    // JOptionPane.showMessageDialog(null, "segunda");
                    new LProfessorHorario(idcurso).FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getSegunda(), datageracao, "segunda");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getTerca())) {
                    // JOptionPane.showMessageDialog(null, "terca");
                    new LProfessorHorario(idcurso).FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getTerca(), datageracao, "terca");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getQuarta())) {
                    new LProfessorHorario(idcurso).FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getQuarta(), datageracao, "quarta");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getQuinta())) {
                    new LProfessorHorario(idcurso).FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getQuinta(), datageracao, "quinta");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getSexta())) {
                    new LProfessorHorario(idcurso).FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getSexta(), datageracao, "sexta");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getSabado())) {
                    new LProfessorHorario(idcurso).FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getSabado(), datageracao, "sabado");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getDomingo())) {
                    new LProfessorHorario(idcurso).FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getDomingo(), datageracao, "domingo");
                }
            }

            // new LAuxGradeHorario(anoletivo).SelecionaCursoHorario(lhorario.get(i).getInicio(), lhorario.get(i).getTermino(), idcurso);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Sincronizar Professor\n" + ex, "Gera Horario", JOptionPane.ERROR_MESSAGE);
        }
    }

}
