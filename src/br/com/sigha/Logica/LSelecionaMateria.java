/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Logica;

import br.com.sigha.Beans.HorarioAulaBeans;
import br.com.sigha.Beans.MateriaBeans;
import br.com.sigha.Beans.ProfessorMateriaBeans;
import br.com.sigha.Beans.UnidadeLogadoBeans;
import br.com.sigha.Dao.HorarioAulaDao;
import br.com.sigha.Dao.ProfessorMateriaDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MarcosVinicius
 */
public class LSelecionaMateria {

    //verifica as materias e escolhe uma para inserir
    public MateriaBeans FiltraMateria(List<MateriaBeans> lmateria, int periodo, String inicio, String termino, String anoletivo, Date datageracao, String dia, int idcurso) {
        ProfessorMateriaBeans lprofessor;
        //  JOptionPane.showMessageDialog(null, inicio + "|" + termino + "|" + periodo);
        List<MateriaBeans> resmataux = new ArrayList<>();//array de materia onde contem a que sera escolhida
        List<MateriaBeans> lauxmateria = new ArrayList<>();
        MateriaBeans resmat = new MateriaBeans();//objeto da materia escolhida
        MateriaBeans materiab = new MateriaBeans();//objeto da materia auxiliar

        //verifica a quantidade de ocorrencia e menor que a quantidade da semana
        for (int l = 0; l < lmateria.size(); l++) {
            try {
                //busca id do professor relacionada a materia;
                lprofessor = new ProfessorMateriaDao().BuscaProfessorMateriaAtivo(lmateria.get(l).getId(), new UnidadeLogadoBeans().getId());
                // metodo  verificar as ocorrencias de um professor com sua materia na grade de aula
                int ocorrmat = new LProfessorHorario(periodo).VerOcorrencia(lprofessor.getId(), anoletivo, datageracao);
                //se teve ocorrencia ele entra
                // JOptionPane.showMessageDialog(null, "Ocorrencia Semana:"+ocorrmat+"\nMateria:"+lmateria.get(l).getSigla());
                if (ocorrmat > 0) {
                    //se a quantidade de ocorrencia for menor que a quantidade que pode ter na semana ele entra
                    if (ocorrmat < Integer.valueOf(lmateria.get(l).getQtdeaulasem())) {
                        materiab = lmateria.get(l);
                        materiab.setOcorrencia(ocorrmat);//seta a ocorrencia na semana
                        resmataux.add(materiab);
                    }
                    //se nao teve ocorrencia ele entra
                } else {
                    if (lprofessor.getId() > 0) {
                        materiab = lmateria.get(l);
                        materiab.setOcorrencia(ocorrmat);//seta a ocorrencia na semana
                        resmataux.add(materiab);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Filtrar Materia\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }

        //verifica se o professor tem preferencia para dar aula no dia ou nao
        
        //verifica se o professor da materia esta sendo utilizado no intervalo do horario        
        for (int i = 0; i < resmataux.size(); i++) {
            try {
                //busca id do professor relacionada a materia;
                lprofessor = new ProfessorMateriaDao().BuscaProfessorMateriaAtivo(resmataux.get(i).getId(), new UnidadeLogadoBeans().getId());
                //metodo verifica se o professor da materia da aula no mesmo horario independente da materia
                Boolean ocorrencia = new HorarioAulaDao().VerificaOcorrencProfHor(dia, inicio, termino, anoletivo, datageracao, lprofessor.getIdprofessor());
                //se o professor da materia tiver aula no horario passado no parametro ele entra no metodo é retirado da lista
                //JOptionPane.showMessageDialog(null, "Ocorrencia Intervalo:"+ocorrencia);
                if (ocorrencia || lprofessor.getId() == 0) {
                    // JOptionPane.showMessageDialog(null, ""+ocorrencia+"\ninicio:"+inicio+"\ntermino:"+termino+"\nanoletivo:"+anoletivo+"\ndatageracao"+datageracao+"\nprofessor"+lprofessor.getIdprofessor()+"\nmateria:"+resmataux.get(i).getSigla());
                    resmataux.remove(i);
                    i--;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Filtrar Materia Verifica Professor Utilizado\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }
        //verifica se a materia ja foi utilizada no dia e se pode ser utilizada novamente
        for (int i = 0; i < resmataux.size(); i++) {
            try {
                //busca id do professor relacionada a materia;
                lprofessor = new ProfessorMateriaDao().BuscaProfessorMateriaAtivo(resmataux.get(i).getId(), new UnidadeLogadoBeans().getId());
                //verifica a quantidade de ocorrencia de uma materia no dia
                int ocorr = new HorarioAulaDao().VerificaOcorrencProfDia(dia, periodo, datageracao, anoletivo, lprofessor.getId());
                // JOptionPane.showMessageDialog(null, "Ocorrencia Dia:"+ocorr+"\nMateria:"+lprofessor.getIdprofessor()+"\ndia"+dia);
                if (ocorr >= Integer.valueOf(resmataux.get(i).getQtdeauladia())) {
                    resmataux.remove(i);
                    i--;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Filtrar Materia Verificar Materia Dia\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
            
        }

        //verifica se tem horario suficiente para adicionar a materia        
        for (int i = 0; i < resmataux.size(); i++) {
            try {
                List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorarioPeriodo(idcurso, anoletivo, datageracao, resmataux.get(i).getPeriodo());
                for (int j = 0; j < lhorario.size(); j++) {
                    if (lhorario.get(j).getInicio().equals(inicio) && lhorario.get(j).getTermino().equals(termino)) {
                        int qtdeh = lhorario.size() - j;
                        //System.out.println("inicio:"+inicio+"|termino:"+termino+"|dia:"+dia+"|periodo:"+resmataux.get(i).getPeriodo()+"|qtdeh:"+qtdeh);
                        if (qtdeh > 0) {
                            if (resmataux.get(i).getSequencial().equals("true")) {
                                if (Integer.valueOf(resmataux.get(i).getQtdeauladia()) > qtdeh) {
                                    //      System.out.println("Removel:"+resmataux.get(i).getSigla()+"|"+Integer.valueOf(resmataux.get(i).getQtdeauladia())+"|qtdeh:"+qtdeh);
                                    resmataux.remove(i);
                                    i--;
                                }
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Filtrar Materia\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }

        // verifica se a materia sera chamada futuramente 
        for (int i = 0; i < resmataux.size(); i++) {
            if (!new LAuxGradeHorario(anoletivo).verificaChamaMateria(dia, inicio, termino, String.valueOf(resmataux.get(i).getId()), idcurso)) {
                lauxmateria.add(resmataux.get(i));
            }
        }

        //se na lista tiver registro ele apaga a lista resposta e copia os dados para ela
        if (lauxmateria.size() > 0) {
            resmataux.clear();
            resmataux = new LProfessorHorario(idcurso).CopiaList(lauxmateria);
        }

        //se na lista tiver mais de uma materia escolhida ele escolhe uma randonicamente
        // JOptionPane.showMessageDialog(null, "Quantidade Materia"+resmataux.size());
        if (resmataux.size() > 1) {
            resmat = new LProfessorHorario(1).EscolheMateria(resmataux);
        } else if (resmataux.size() > 0) {
            resmat = resmataux.get(0);
        }
        // JOptionPane.showMessageDialog(null, resmat.getSigla());
        return resmat;
    }
    
}
