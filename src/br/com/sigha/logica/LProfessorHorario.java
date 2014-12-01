/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.logica;

import br.com.sigha.beans.AuxHorarioCursoBeans;
import br.com.sigha.beans.HorarioAulaBeans;
import br.com.sigha.beans.HorarioAulaDiaBeans;
import br.com.sigha.beans.HorarioVazioBeans;
import br.com.sigha.beans.MateriaBeans;
import br.com.sigha.beans.ProfessorMateriaBeans;
import br.com.sigha.beans.UnidadeLogadoBeans;
import br.com.sigha.dao.AuxHorarioCursoDao;
import br.com.sigha.dao.HorarioAulaDao;
import br.com.sigha.dao.MateriaDao;
import br.com.sigha.dao.ProfessorMateriaDao;
import br.com.sigha.util.LogsTxt;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author MarcosVinicius
 */
public class LProfessorHorario {

    int idcurso;
    List<Integer> erroperiodo = new ArrayList<>();
    int respexevqtdeauh = 0;

    public LProfessorHorario(int idcurso) {
        this.idcurso = idcurso;
    }

    //cadastra horario dos cursos na tabela horario aula
    public void CadastraHorarioAula(String anoletivo, Date datageracao) {
        try {
            //cadastra horario dos cursos na tabela horario aula
            CadastraHorario(this.idcurso, anoletivo, datageracao);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Horario do Curso\n" + ex, "Gera Horario", JOptionPane.ERROR_MESSAGE);
        }
    }

    //METODO PARA ESCOLHER UM PROFESSOR ONDE A PESQUISA RETORNOU MAIS DE UM
    public MateriaBeans EscolheMateria(List<MateriaBeans> lmateria) {
        List<MateriaBeans> laux = new ArrayList<>();
        List<MateriaBeans> laux2 = new ArrayList<>();
        laux.clear();
        laux2.clear();
        laux2 = CopiaList(lmateria);
        while (laux.size() != 1) {
            laux.clear();
            for (int i = 0; i < laux2.size(); i++) {
                if (new Random().nextBoolean()) {
                    laux.add(laux2.get(i));
                } else {
                    laux2.remove(i);
                    i--;
                }
            }
            if (laux2.isEmpty()) {
                laux2.clear();
                laux2 = CopiaList(lmateria);
            }

        }
        return laux.get(0);
    }

    //METODO QUE COPIA UMA LISTA
    public List<MateriaBeans> CopiaList(List<MateriaBeans> lista) {
        List<MateriaBeans> copy = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            copy.add(lista.get(i));
        }
        return copy;
    }

    private void CadastraHorario(int idcurso, String anoletivo, Date datageracao) throws SQLException {
        List<MateriaBeans> lmateria = new ArrayList<>();
        lmateria = new MateriaDao().BuscaMateriaHorarioCurso(idcurso);
        new HorarioAulaDao().CadastraHorario(lmateria, anoletivo, datageracao, new UnidadeLogadoBeans().getId());

    }
//DESATIVADO TEMPORIARIAMENTE
    //metodo escolhe o professor da materia de acordo com tabela auxhorariocurso e cadastra na tabela horario curso
//    public void FiltraGrava(String anoletivo, String inicio, String termino, String segunda, Date datageracao) {
//        try {
//            //lista de sigla de cursos que esta no campo segunda da tabela auxiliar
//            List<String> lcurso = new QtdeOcorrenciaCaracter().ListPalavra('|', segunda);
//            //metodo busca o horario do curso na tabela horarioaula
//            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
//            //percorre a lista de horarioaula
//            //metodo busaca os periodos de um curso na tabela horarioaula
//            List<HorarioAulaBeans> lhorariop=new HorarioAulaDao().HorarioGroupPeriodo(idcurso, anoletivo, datageracao);
//            
//            for(int i=0;i<lhorario.size();i++){
//                //compara o horario passado para a funcao e com o horario de aula 
//                if(inicio.equals(lhorario.get(i).getInicio()) && termino.equals(lhorario.get(i).getTermino())){
//                    for(int j=0;j<lcurso.size();j++){
//                        //lista com as materias referente a sigla passada
//                        List<MateriaBeans> lmateria=new MateriaDao().BuscaIdMateria(lcurso.get(j).replace("|", ""));
//                        for(int l=0;l<lmateria.size();l++){
//                            //compara o periodo da materia com o periodo do horario
//                            if(lmateria.get(l).getPeriodo()==lhorario.get(i).getPeriodo()){
//                               // JOptionPane.showMessageDialog(null, inicio+"\n"+termino+"\n"+lcurso.get(j)+"\n"+lmateria.get(l).getPeriodo()+"\n"+lhorario.get(i).getPeriodo());
//                                //List<ProfessorMateriaBeans> lprofessor=new ProfessorMateriaDao().BuscaProfessor(lmateria.get(l).getId(), new UnidadeLogadoBeans().getId());
//                                
//                            }
//                        }
//                        
//                    }
//                    
//                }
//            }
//            
//            
//
//        
//        } catch (SQLException ex) {
//            Logger.getLogger(LProfessorHorario.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//busca horario do dia e verifica se pode ser usado

    public void FiltraGrava(String anoletivo, String inicio, String termino, String materias, Date datageracao, String dia) {
        try {
            //lista de sigla de cursos que esta no campo segunda da tabela auxiliar
            //List<String> lcurso = new QtdeOcorrenciaCaracter().ListPalavra('|', materias);
            //metodo busca o horario do curso na tabela horarioaula
            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
            //metodo busca os periodos de um curso na tabela horarioaula
            // List<HorarioAulaBeans> lhorariop = new HorarioAulaDao().HorarioGroupPeriodo(idcurso, anoletivo, datageracao);
            for (int i = 0; i < lhorario.size(); i++) {
                if (inicio.equals(lhorario.get(i).getInicio()) && termino.equals(lhorario.get(i).getTermino())) {
                    //lista com as materias referente a id da materia e ao periodo passada 
                    List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria("0" + materias + "0", lhorario.get(i).getPeriodo());
                    //seleciona uma materia do periodo passado como parametro   
                    // compara o horario passado para a funcao e com o horario de aula se tem materia usando o dia
                    if (verificadia(dia, inicio, termino, lhorario.get(i).getPeriodo(), anoletivo, datageracao, lhorario)) {
                        MateriaBeans materia = FiltraMateria(lmateria, lhorario.get(i).getPeriodo(), lhorario.get(i).getInicio(), lhorario.get(i).getTermino(), anoletivo, datageracao, dia, lhorario.get(i).getIdcurso());
                        //objeto recebe o professor referente a materia para inserção                        
                        ProfessorMateriaBeans lprofessor = new ProfessorMateriaDao().BuscaProfessorMateriaAtivo(materia.getId(), new UnidadeLogadoBeans().getId());
                        //metodo para cadastro de materia
                        if (materia.getId() > 0 && lprofessor.getId() > 0) {
                            CadastraMateria(lhorario, lprofessor.getId(), anoletivo, inicio, termino, datageracao, dia, materia, this.idcurso);
                        }

                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro FiltraGrava\n" + ex, "Gera Horario", JOptionPane.ERROR_MESSAGE);
        }

    }

    //metodo verifica a ocorencia de aula de um professor em um horario de aula
    public int VerOcorrencia(int idprofessormat, String anoletivo, Date datageracao) {
        List<HorarioAulaBeans> horariob = new ArrayList<>();
        int contocor = 0;
        try {
            //metodo retorna a ocorrencia de aula de professor
            horariob = new HorarioAulaDao().BuscaOcorrenciaMater(idprofessormat, anoletivo, datageracao);
            //verifica se a lista esta vazia
            if (!horariob.isEmpty()) {
                //percorre a lista contando as ocorrencias
                for (int i = 0; i < horariob.size(); i++) {
                    if (horariob.get(i).getSegunda() == idprofessormat) {
                        contocor++;
                    }
                    if (horariob.get(i).getTerca() == idprofessormat) {
                        contocor++;
                    }
                    if (horariob.get(i).getQuarta() == idprofessormat) {
                        contocor++;
                    }
                    if (horariob.get(i).getQuinta() == idprofessormat) {
                        contocor++;
                    }
                    if (horariob.get(i).getSexta() == idprofessormat) {
                        contocor++;
                    }
                    if (horariob.get(i).getSabado() == idprofessormat) {
                        contocor++;
                    }
                    if (horariob.get(i).getDomingo() == idprofessormat) {
                        contocor++;
                    }
                }
                return contocor;
            } else {
                return contocor;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Verificar Ocorencia\n" + ex, "Gera Horario", JOptionPane.ERROR_MESSAGE);
        }
        return contocor;
    }

    //metodo cadastra materia no horario de aula
    private void CadastraMateria(List<HorarioAulaBeans> lhorario, int idprofessormateria, String anoletivo, String inicio, String termino, Date datageracao, String dia, MateriaBeans materia, int idcurso) throws SQLException {
        //faz o cadastro da materia no horario de aula
        //JOptionPane.showMessageDialog(null, materia);
        if (materia.getSequencial().equals("true")) {
            // for(int i=0;i<Integer.valueOf(materia.getQtdeauladia());i++){
            for (int j = 0; j < lhorario.size(); j++) {
                if (lhorario.get(j).getInicio().equals(inicio) && lhorario.get(j).getTermino().equals(termino) & lhorario.get(j).getPeriodo() == materia.getPeriodo()) {
                    for (int i = 0; i < Integer.valueOf(materia.getQtdeauladia()); i++) {
                        // JOptionPane.showMessageDialog(null, "Mandou"+dia+"\n"+idprofessor+"\n"+inicio+"\n"+termino+"\nValor poisica:"+i);
                        // System.out.println("Mandou"+dia+"\n"+idprofessor+"\n"+inicio+"\n"+termino+"\nValor poisica:"+i);
                        // JOptionPane.showMessageDialog(null, "Vai incerir"+lhorario.get(j+i).getInicio()+"\n"+lhorario.get(j+i).getTermino());                            
                        // System.out.println("Vai incerir"+lhorario.get(j+i).getInicio()+"\n"+lhorario.get(j+i).getTermino());
                        new HorarioAulaDao().CadastraMateriaAula(idprofessormateria, anoletivo, lhorario.get(j + i).getInicio(), lhorario.get(j + i).getTermino(), datageracao, dia, materia.getPeriodo(), idcurso, new UnidadeLogadoBeans().getId());
                    }
                }
                //    }

            }
        } else {
            new HorarioAulaDao().CadastraMateriaAula(idprofessormateria, anoletivo, inicio, termino, datageracao, dia, materia.getPeriodo(), idcurso, new UnidadeLogadoBeans().getId());
        }
//        JOptionPane.showMessageDialog(null, "Mandou" + dia + "\n" + idprofessor + "\n" + inicio + "\n" + termino + "\nValor poisica:");
//        new HorarioAulaDao().CadastraMateriaAula(idprofessor, anoletivo, inicio, termino, datageracao, dia, materia.getPeriodo());
    }
//verifica se tem materia utilizando o dia

    private boolean verificadia(String dia, String inicio, String termino, int periodo, String anoletivo, Date datageracao, List<HorarioAulaBeans> lhorariotext) throws SQLException {
        Boolean resp = true;
        // List<HorarioAulaBeans> lhorariotext = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
        if (dia.equals("segunda")) {
            for (int i = 0; i < lhorariotext.size(); i++) {
                if (lhorariotext.get(i).getInicio().equals(inicio) && lhorariotext.get(i).getTermino().equals(termino) && lhorariotext.get(i).getPeriodo() == periodo) {
                    if (lhorariotext.get(i).getSegunda() > 0) {
                        resp = false;
                    }
                }
            }
        } else if (dia.equals("terca")) {
            for (int i = 0; i < lhorariotext.size(); i++) {
                if (lhorariotext.get(i).getInicio().equals(inicio) && lhorariotext.get(i).getTermino().equals(termino) && lhorariotext.get(i).getPeriodo() == periodo) {
                    if (lhorariotext.get(i).getTerca() > 0) {
                        resp = false;
                    }
                }
            }
        } else if (dia.equals("quarta")) {
            for (int i = 0; i < lhorariotext.size(); i++) {
                if (lhorariotext.get(i).getInicio().equals(inicio) && lhorariotext.get(i).getTermino().equals(termino) && lhorariotext.get(i).getPeriodo() == periodo) {
                    if (lhorariotext.get(i).getQuarta() > 0) {
                        resp = false;
                    }
                }
            }
        } else if (dia.equals("quinta")) {
            for (int i = 0; i < lhorariotext.size(); i++) {
                if (lhorariotext.get(i).getInicio().equals(inicio) && lhorariotext.get(i).getTermino().equals(termino) && lhorariotext.get(i).getPeriodo() == periodo) {
                    if (lhorariotext.get(i).getQuinta() > 0) {
                        resp = false;
                    }
                }
            }
        } else if (dia.equals("sexta")) {
            for (int i = 0; i < lhorariotext.size(); i++) {
                if (lhorariotext.get(i).getInicio().equals(inicio) && lhorariotext.get(i).getTermino().equals(termino) && lhorariotext.get(i).getPeriodo() == periodo) {
                    if (lhorariotext.get(i).getSexta() > 0) {
                        resp = false;
                    }
                }
            }
        } else if (dia.equals("sabado")) {
            for (int i = 0; i < lhorariotext.size(); i++) {
                if (lhorariotext.get(i).getInicio().equals(inicio) && lhorariotext.get(i).getTermino().equals(termino) && lhorariotext.get(i).getPeriodo() == periodo) {
                    if (lhorariotext.get(i).getSabado() > 0) {
                        resp = false;
                    }
                }
            }
        } else if (dia.equals("domingo")) {
            for (int i = 0; i < lhorariotext.size(); i++) {
                if (lhorariotext.get(i).getInicio().equals(inicio) && lhorariotext.get(i).getTermino().equals(termino) && lhorariotext.get(i).getPeriodo() == periodo) {
                    if (lhorariotext.get(i).getDomingo() > 0) {
                        resp = false;
                    }
                }
            }
        }
        return resp;
    }

//verifica se o horario gerado tem erros
    public Boolean verificaErroHorario(String anoletivo, Date datageracao) {
        //JOptionPane.showMessageDialog(null, "Foi");
        Boolean resp = false;
        Boolean troca = true;
        LogsTxt logs = new LogsTxt();
        try {
            List<HorarioVazioBeans> horariovazio = new ArrayList<>();
            //busca horario auxiliar
            List<AuxHorarioCursoBeans> lauxhorario = new AuxHorarioCursoDao().BuscaAuxHorarioCurso(this.idcurso, anoletivo);
            //verifica a soma de quantidade de aula por semana somando no cadastro de materia com a quantidade
            //de materia utilizado no horario
            if (verQtdeAulaMatEDia(anoletivo, datageracao)) {
                for (int i = 0; i < erroperiodo.size(); i++) {
                    logs.setTxt(new Date() + " Periodos com quantidade de mat menor que tem que haver no horario "
                            + String.valueOf(erroperiodo.size()) + " Curso: " + this.idcurso, "Erro");
                }
                logs.setTxt(new Date() + "  -------------------------", "Erro");
                //verifica se tem dia sem aula e se as materia do dia sem aula esta todas utilizadas
                //foi auterado onde retorna os horario sem aula que tem materia possivel de dar aula
                if ((horariovazio = verificaQtdeDiaProfHor(anoletivo, datageracao)).size() > 0) {
                    logs.setTxt(new Date() + " Dia da semana com erro: ", "Erro");
                    logs.setTxt(new Date() + "  -------------------------", "Erro");

                    //percorre os horario vazios e verifica se todas as materias ja forao utilizadas
                    for (int i = 0; i < horariovazio.size(); i++) {
                        //retorna lista das materias do periodo
                        System.out.println("\n-------------------\nHorario vazio" + horariovazio.size() + "|" + horariovazio.get(i).getDia());
                        List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria("0" + horariovazio.get(i).getMaterias() + "0", horariovazio.get(i).getPeriodo());
                        //retorna as materia que nao forao utilizadas totalmente
                        List<MateriaBeans> lmaterianutilizada = qtdeOcorrenciaMateria(lmateria, horariovazio.get(i).getAnoletivo(), horariovazio.get(i).getDatageracao(), horariovazio.get(i).getPeriodo());
                        //verifica se ha materia nao utilizada
                        if (lmaterianutilizada.size() > 0) {
                            troca=true;
                            //  JOptionPane.showMessageDialog(null, "QTDE de Materia:"+lmaterianutilizada.size());
                            System.out.println("qtde de Materia Nao utilizada" + lmaterianutilizada.size());
                            //no metodo que for utilizar analizar a quantidade que tem que ser inserida no dia
                            //metodo percorre as materias que nao foi utilizada
                            for (int j = 0; j < lmaterianutilizada.size(); j++) {
                                System.out.println("Materia nao utilizado" + lmaterianutilizada.get(j).getId());
                                System.out.println("Horario curso:" + horariovazio.get(i).getInicio() + "|" + horariovazio.get(i).getTermino());
                                //busca o id do professor da materia nao utilizada
                                ProfessorMateriaBeans lprofessormateria = new ProfessorMateriaDao().BuscaProfessorMateriaAtivo(lmaterianutilizada.get(j).getId(), new UnidadeLogadoBeans().getId());
                                //metodo percorre em busca de dia que a materia e utilizada na segunda                                
                                for (int l = 0; l < lauxhorario.size(); l++) {
                                    System.out.println("percorrendo horario auxiliar");
                                    //se nao trocou nenhuma vez entra
                                    
                                    if (troca) {
                                        if (!"false".equals(lauxhorario.get(l).getSegunda()) && !"".equals(lauxhorario.get(l).getSegunda())) {
                                            if (lauxhorario.get(l).getSegunda().contains(String.valueOf(lmaterianutilizada.get(j).getId()))) {
                                                System.out.println(lauxhorario.get(l).getSegunda() + "|" + lauxhorario.get(l).getInicio());
                                            //buscar materia do dia e verificar se ela pode ser usada no dia que nao foi usado    
                                                //fazer um metodo que verifica a materia do dia se pode ser usada no dia sem aula 

                                            //busca o dia com a materia que esta sendo utilizada na tabela horario aula do dia
                                                //onde podera ter a materia nao utilizada
                                                HorarioAulaDiaBeans horariodi = new HorarioAulaDao().BuscaHorarioIntervalo(lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), lauxhorario.get(l).getAnoletivo(), lauxhorario.get(l).getIdcurso(), lmaterianutilizada.get(j).getPeriodo(), "segunda");
                                            //verifica se materia esta marcado como dia provavel de aula onde podera ser utilizada
                                                //no dia vazio
                                                System.out.println("Materia dia que esta veficando" + horariodi.getIdmateriap() + "|" + horariodi.getIdmateria() + "-" + horariovazio.get(i).getDia());
                                                if (verificaMateriaDiaVazio(horariodi.getIdmateria(), lauxhorario, horariovazio.get(i).getDia(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino())) {
                                                    System.out.println("Pode dar aula no dia vazio" + horariodi.getIdmateria() + "|" + horariodi.getPeriodo() + "|" + horariovazio.get(i).getInicio() + "|" + horariovazio.get(i).getTermino() + "|" + anoletivo + "|" + datageracao + "|" + horariovazio.get(i).getDia() + "|" + new UnidadeLogadoBeans().getId() + "|" + horariodi.getSequencial() + "|" + horariodi.getQtdeauladia());
                                                    //verifica se o professor da materia que ira para o dia sem materia nao esta dando aula em outra turma do dia provavel da mudanca
                                                    if (FiltraMateriaTroca(horariodi.getIdmateria(), horariodi.getPeriodo(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), anoletivo, datageracao, horariovazio.get(i).getDia(), new UnidadeLogadoBeans().getId(), horariodi.getSequencial(), horariodi.getQtdeauladia())) {
                                                        System.out.println("Professor nao esta dando aula em outra sala no mesmo horario" + horariodi.getIdmateria());
                                                        //verifica se o professor da materia na utilizada nao esta dando aula em outra turma do dia provavel da mudanca
                                                        System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId() + "|" + lauxhorario.get(l).getInicio());
                                                        if (FiltraMateriaTroca(lprofessormateria.getIdmateria(), lmaterianutilizada.get(j).getPeriodo(), lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), anoletivo, datageracao, "segunda", new UnidadeLogadoBeans().getId(), lmaterianutilizada.get(j).getSequencial(), Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()))) {
                                                            System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId());
                                                            //se der tudo certo e so fazer o metodo para cadastrar                                                            
                                                            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
                                                            //verifica se o dia nao utilizado tem qtde de repeticao no dia maior doque que sera trocado
                                                            if (Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()) >= horariodi.getQtdeauladia()) {
                                                                //cadastra materia que nao foi utilizada na segunda
                                                                CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "segunda", lmaterianutilizada.get(j), this.idcurso);
                                                                // busca os dados da materia que sera trocada para o dia vazio
                                                                MateriaBeans materiadia = new MateriaDao().BuscaMateriaidprof(horariodi.getIdmateriap());
                                                                //cadastra materia no dia que nao foi utilizado
                                                                CadastraMateria(lhorario, horariodi.getIdmateriap(), anoletivo, horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), datageracao, horariovazio.get(i).getDia(), materiadia, this.idcurso);
                                                                //avanca os contadores
                                                                l = lauxhorario.size();
                                                                i = i + (Integer.valueOf(materiadia.getQtdeauladia()) - 1);
                                                                troca=false;
                                                            }
//                                                        //dia nao usado cadastrado no dia da troca
//                                                        CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "segunda", lmaterianutilizada.get(j), this.idcurso);
//                                                      
//                                                        //dia que foi removido indo para dia nao usado
//                                                        MateriaBeans mb=new MateriaBeans();
//                                                        mb.setQtdeauladia(String.valueOf(horariodi.getQtdeauladia()));
//                                                        mb.setSequencial(horariodi.getSequencial());
//                                                        CadastraMateria(lhorario, horariodi.getIdmateria(), anoletivo, lmaterianutilizada.get(j).getInicio(), lmaterianutilizada.get(j).getTermino(), datageracao, horariovazio.get(i).getDia(), mb, this.idcurso);

                                                          //i=horariodi.getQtdeauladia()+i;
                                                            //implementar se a quantidade de aula vazia cabe na aula de troca e virse e versa
                                                            //verificar tambem se for materia se bustituira mais de uma verificar todos os 
                                                            //horarios
                                                            System.out.println("\nCurso que foi para " + horariodi.getIdmateriap() + "|" + horariovazio.get(i).getDia());
                                                            System.out.println("\n Curso foi para segunda" + lprofessormateria.getId() + "------------------------------\n");
                                                       // JOptionPane.showMessageDialog(null, "\nTrocou\n---------------------------\n");                                                      
                                                            //   l=lauxhorario.size();//verificar por que esta sempre que insere ele esta começão do inicio no
                                                            //objeto horariodi sendo que tem que pegar nova materia dando continuidade

                                                        }

                                                    }
                                                }

                                            //fazer um metodo onde verifica se tem qtde de horario para incerir materia
                                                //nao utilizada e depois verificar se elas pode ser usada no horario vago
                                                //verifica se tem horario suficiente para adicionar a materia        
                                            }
                                        }
                                    }
                                    //se nao trocou nenhuma vez entra
                                    if (troca) {
                                        if (!"false".equals(lauxhorario.get(l).getTerca()) && !"".equals(lauxhorario.get(l).getTerca())) {
                                            if (lauxhorario.get(l).getTerca().contains(String.valueOf(lmaterianutilizada.get(j).getId()))) {
                                                System.out.println(lauxhorario.get(l).getTerca() + "|" + lauxhorario.get(l).getInicio());
                                            //buscar materia do dia e verificar se ela pode ser usada no dia que nao foi usado    
                                                //fazer um metodo que verifica a materia do dia se pode ser usada no dia sem aula 

                                            //busca o dia com a materia que esta sendo utilizada na tabela horario aula do dia
                                                //onde podera ter a materia nao utilizada
                                                HorarioAulaDiaBeans horariodi = new HorarioAulaDao().BuscaHorarioIntervalo(lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), lauxhorario.get(l).getAnoletivo(), lauxhorario.get(l).getIdcurso(), lmaterianutilizada.get(j).getPeriodo(), "terca");
                                            //verifica se materia esta marcado como dia provavel de aula onde podera ser utilizada
                                                //no dia vazio
                                                System.out.println("Materia dia que esta veficando" + horariodi.getIdmateriap() + "|" + horariodi.getIdmateria() + "-" + horariovazio.get(i).getDia());
                                                if (verificaMateriaDiaVazio(horariodi.getIdmateria(), lauxhorario, horariovazio.get(i).getDia(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino())) {
                                                    System.out.println("Pode dar aula no dia vazio" + horariodi.getIdmateria() + "|" + horariodi.getPeriodo() + "|" + horariovazio.get(i).getInicio() + "|" + horariovazio.get(i).getTermino() + "|" + anoletivo + "|" + datageracao + "|" + horariovazio.get(i).getDia() + "|" + new UnidadeLogadoBeans().getId() + "|" + horariodi.getSequencial() + "|" + horariodi.getQtdeauladia());
                                                    //verifica se o professor da materia que ira para o dia sem materia nao esta dando aula em outra turma do dia provavel da mudanca
                                                    if (FiltraMateriaTroca(horariodi.getIdmateria(), horariodi.getPeriodo(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), anoletivo, datageracao, horariovazio.get(i).getDia(), new UnidadeLogadoBeans().getId(), horariodi.getSequencial(), horariodi.getQtdeauladia())) {
                                                        System.out.println("Professor nao esta dando aula em outra sala no mesmo horario" + horariodi.getIdmateria());
                                                        //verifica se o professor da materia na utilizada nao esta dando aula em outra turma do dia provavel da mudanca
                                                        System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId() + "|" + lauxhorario.get(l).getInicio());
                                                        if (FiltraMateriaTroca(lprofessormateria.getIdmateria(), lmaterianutilizada.get(j).getPeriodo(), lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), anoletivo, datageracao, "terca", new UnidadeLogadoBeans().getId(), lmaterianutilizada.get(j).getSequencial(), Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()))) {
                                                            System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId());
                                                            //se der tudo certo e so fazer o metodo para cadastrar                                                            
                                                            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
                                                            //verifica se o dia nao utilizado tem qtde de repeticao no dia maior doque que sera trocado
                                                            if (Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()) >= horariodi.getQtdeauladia()) {
                                                                //cadastra materia que nao foi utilizada na segunda
                                                                CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "terca", lmaterianutilizada.get(j), this.idcurso);
                                                                // busca os dados da materia que sera trocada para o dia vazio
                                                                MateriaBeans materiadia = new MateriaDao().BuscaMateriaidprof(horariodi.getIdmateriap());
                                                                //cadastra materia no dia que nao foi utilizado
                                                                CadastraMateria(lhorario, horariodi.getIdmateriap(), anoletivo, horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), datageracao, horariovazio.get(i).getDia(), materiadia, this.idcurso);
                                                                //avanca os contadores
                                                                l = lauxhorario.size();
                                                                i = i + (Integer.valueOf(materiadia.getQtdeauladia()) - 1);
                                                                troca=false;
                                                            }
//                                                        //dia nao usado cadastrado no dia da troca
//                                                        CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "segunda", lmaterianutilizada.get(j), this.idcurso);
//                                                      
//                                                        //dia que foi removido indo para dia nao usado
//                                                        MateriaBeans mb=new MateriaBeans();
//                                                        mb.setQtdeauladia(String.valueOf(horariodi.getQtdeauladia()));
//                                                        mb.setSequencial(horariodi.getSequencial());
//                                                        CadastraMateria(lhorario, horariodi.getIdmateria(), anoletivo, lmaterianutilizada.get(j).getInicio(), lmaterianutilizada.get(j).getTermino(), datageracao, horariovazio.get(i).getDia(), mb, this.idcurso);

                                                          //i=horariodi.getQtdeauladia()+i;
                                                            //implementar se a quantidade de aula vazia cabe na aula de troca e virse e versa
                                                            //verificar tambem se for materia se bustituira mais de uma verificar todos os 
                                                            //horarios
                                                            System.out.println("\nCurso que foi para " + horariodi.getIdmateriap() + "|" + horariovazio.get(i).getDia());
                                                            System.out.println("\n Curso foi para terca" + lprofessormateria.getId() + "------------------------------\n");
                                                       // JOptionPane.showMessageDialog(null, "\nTrocou\n---------------------------\n");                                                      
                                                            //   l=lauxhorario.size();//verificar por que esta sempre que insere ele esta começão do inicio no
                                                            //objeto horariodi sendo que tem que pegar nova materia dando continuidade

                                                        }

                                                    }
                                                }

                                            //fazer um metodo onde verifica se tem qtde de horario para incerir materia
                                                //nao utilizada e depois verificar se elas pode ser usada no horario vago
                                                //verifica se tem horario suficiente para adicionar a materia        
                                            }
                                        }
                                    }
                                    //se nao trocou nenhuma vez entra
                                    if (troca) {
                                        if (!"false".equals(lauxhorario.get(l).getQuarta()) && !"".equals(lauxhorario.get(l).getQuarta())) {
                                            if (lauxhorario.get(l).getQuarta().contains(String.valueOf(lmaterianutilizada.get(j).getId()))) {
                                                System.out.println(lauxhorario.get(l).getQuarta() + "|" + lauxhorario.get(l).getInicio());
                                            //buscar materia do dia e verificar se ela pode ser usada no dia que nao foi usado    
                                                //fazer um metodo que verifica a materia do dia se pode ser usada no dia sem aula 

                                            //busca o dia com a materia que esta sendo utilizada na tabela horario aula do dia
                                                //onde podera ter a materia nao utilizada
                                                HorarioAulaDiaBeans horariodi = new HorarioAulaDao().BuscaHorarioIntervalo(lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), lauxhorario.get(l).getAnoletivo(), lauxhorario.get(l).getIdcurso(), lmaterianutilizada.get(j).getPeriodo(), "quarta");
                                            //verifica se materia esta marcado como dia provavel de aula onde podera ser utilizada
                                                //no dia vazio
                                                System.out.println("Materia dia que esta veficando" + horariodi.getIdmateriap() + "|" + horariodi.getIdmateria() + "-" + horariovazio.get(i).getDia());
                                                if (verificaMateriaDiaVazio(horariodi.getIdmateria(), lauxhorario, horariovazio.get(i).getDia(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino())) {
                                                    System.out.println("Pode dar aula no dia vazio" + horariodi.getIdmateria() + "|" + horariodi.getPeriodo() + "|" + horariovazio.get(i).getInicio() + "|" + horariovazio.get(i).getTermino() + "|" + anoletivo + "|" + datageracao + "|" + horariovazio.get(i).getDia() + "|" + new UnidadeLogadoBeans().getId() + "|" + horariodi.getSequencial() + "|" + horariodi.getQtdeauladia());
                                                    //verifica se o professor da materia que ira para o dia sem materia nao esta dando aula em outra turma do dia provavel da mudanca
                                                    if (FiltraMateriaTroca(horariodi.getIdmateria(), horariodi.getPeriodo(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), anoletivo, datageracao, horariovazio.get(i).getDia(), new UnidadeLogadoBeans().getId(), horariodi.getSequencial(), horariodi.getQtdeauladia())) {
                                                        System.out.println("Professor nao esta dando aula em outra sala no mesmo horario" + horariodi.getIdmateria());
                                                        //verifica se o professor da materia na utilizada nao esta dando aula em outra turma do dia provavel da mudanca
                                                        System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId() + "|" + lauxhorario.get(l).getInicio());
                                                        if (FiltraMateriaTroca(lprofessormateria.getIdmateria(), lmaterianutilizada.get(j).getPeriodo(), lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), anoletivo, datageracao, "quarta", new UnidadeLogadoBeans().getId(), lmaterianutilizada.get(j).getSequencial(), Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()))) {
                                                            System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId());
                                                            //se der tudo certo e so fazer o metodo para cadastrar                                                            
                                                            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
                                                            //verifica se o dia nao utilizado tem qtde de repeticao no dia maior doque que sera trocado
                                                            if (Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()) >= horariodi.getQtdeauladia()) {
                                                                //cadastra materia que nao foi utilizada na quarta
                                                                CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "quarta", lmaterianutilizada.get(j), this.idcurso);
                                                                // busca os dados da materia que sera trocada para o dia vazio
                                                                MateriaBeans materiadia = new MateriaDao().BuscaMateriaidprof(horariodi.getIdmateriap());
                                                                //cadastra materia no dia que nao foi utilizado
                                                                CadastraMateria(lhorario, horariodi.getIdmateriap(), anoletivo, horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), datageracao, horariovazio.get(i).getDia(), materiadia, this.idcurso);
                                                                //avanca os contadores
                                                                l = lauxhorario.size();
                                                                i = i + (Integer.valueOf(materiadia.getQtdeauladia()) - 1);
                                                                troca=false;
                                                            }
//                                                        //dia nao usado cadastrado no dia da troca
//                                                        CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "quarta", lmaterianutilizada.get(j), this.idcurso);
//                                                      
//                                                        //dia que foi removido indo para dia nao usado
//                                                        MateriaBeans mb=new MateriaBeans();
//                                                        mb.setQtdeauladia(String.valueOf(horariodi.getQtdeauladia()));
//                                                        mb.setSequencial(horariodi.getSequencial());
//                                                        CadastraMateria(lhorario, horariodi.getIdmateria(), anoletivo, lmaterianutilizada.get(j).getInicio(), lmaterianutilizada.get(j).getTermino(), datageracao, horariovazio.get(i).getDia(), mb, this.idcurso);

                                                          //i=horariodi.getQtdeauladia()+i;
                                                            //implementar se a quantidade de aula vazia cabe na aula de troca e virse e versa
                                                            //verificar tambem se for materia se bustituira mais de uma verificar todos os 
                                                            //horarios
                                                            System.out.println("\nCurso que foi para " + horariodi.getIdmateriap() + "|" + horariovazio.get(i).getDia());
                                                            System.out.println("\n Curso foi para quarta" + lprofessormateria.getId() + "------------------------------\n");
                                                       // JOptionPane.showMessageDialog(null, "\nTrocou\n---------------------------\n");                                                      
                                                            //   l=lauxhorario.size();//verificar por que esta sempre que insere ele esta começão do inicio no
                                                            //objeto horariodi sendo que tem que pegar nova materia dando continuidade

                                                        }

                                                    }
                                                }

                                            //fazer um metodo onde verifica se tem qtde de horario para incerir materia
                                                //nao utilizada e depois verificar se elas pode ser usada no horario vago
                                                //verifica se tem horario suficiente para adicionar a materia        
                                            }
                                        }
                                    }
                                    //se nao trocou nenhuma vez entra
                                    if (troca) {
                                        if (!"false".equals(lauxhorario.get(l).getQuinta()) && !"".equals(lauxhorario.get(l).getQuinta())) {
                                            if (lauxhorario.get(l).getQuinta().contains(String.valueOf(lmaterianutilizada.get(j).getId()))) {
                                                System.out.println(lauxhorario.get(l).getQuinta() + "|" + lauxhorario.get(l).getInicio());
                                            //buscar materia do dia e verificar se ela pode ser usada no dia que nao foi usado    
                                                //fazer um metodo que verifica a materia do dia se pode ser usada no dia sem aula 

                                            //busca o dia com a materia que esta sendo utilizada na tabela horario aula do dia
                                                //onde podera ter a materia nao utilizada
                                                HorarioAulaDiaBeans horariodi = new HorarioAulaDao().BuscaHorarioIntervalo(lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), lauxhorario.get(l).getAnoletivo(), lauxhorario.get(l).getIdcurso(), lmaterianutilizada.get(j).getPeriodo(), "quinta");
                                            //verifica se materia esta marcado como dia provavel de aula onde podera ser utilizada
                                                //no dia vazio
                                                System.out.println("Materia dia que esta veficando" + horariodi.getIdmateriap() + "|" + horariodi.getIdmateria() + "-" + horariovazio.get(i).getDia());
                                                if (verificaMateriaDiaVazio(horariodi.getIdmateria(), lauxhorario, horariovazio.get(i).getDia(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino())) {
                                                    System.out.println("Pode dar aula no dia vazio" + horariodi.getIdmateria() + "|" + horariodi.getPeriodo() + "|" + horariovazio.get(i).getInicio() + "|" + horariovazio.get(i).getTermino() + "|" + anoletivo + "|" + datageracao + "|" + horariovazio.get(i).getDia() + "|" + new UnidadeLogadoBeans().getId() + "|" + horariodi.getSequencial() + "|" + horariodi.getQtdeauladia());
                                                    //verifica se o professor da materia que ira para o dia sem materia nao esta dando aula em outra turma do dia provavel da mudanca
                                                    if (FiltraMateriaTroca(horariodi.getIdmateria(), horariodi.getPeriodo(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), anoletivo, datageracao, horariovazio.get(i).getDia(), new UnidadeLogadoBeans().getId(), horariodi.getSequencial(), horariodi.getQtdeauladia())) {
                                                        System.out.println("Professor nao esta dando aula em outra sala no mesmo horario" + horariodi.getIdmateria());
                                                        //verifica se o professor da materia na utilizada nao esta dando aula em outra turma do dia provavel da mudanca
                                                        System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId() + "|" + lauxhorario.get(l).getInicio());
                                                        if (FiltraMateriaTroca(lprofessormateria.getIdmateria(), lmaterianutilizada.get(j).getPeriodo(), lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), anoletivo, datageracao, "quinta", new UnidadeLogadoBeans().getId(), lmaterianutilizada.get(j).getSequencial(), Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()))) {
                                                            System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId());
                                                            //se der tudo certo e so fazer o metodo para cadastrar                                                            
                                                            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
                                                            //verifica se o dia nao utilizado tem qtde de repeticao no dia maior doque que sera trocado
                                                            if (Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()) >= horariodi.getQtdeauladia()) {
                                                                //cadastra materia que nao foi utilizada na quinta
                                                                CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "quinta", lmaterianutilizada.get(j), this.idcurso);
                                                                // busca os dados da materia que sera trocada para o dia vazio
                                                                MateriaBeans materiadia = new MateriaDao().BuscaMateriaidprof(horariodi.getIdmateriap());
                                                                //cadastra materia no dia que nao foi utilizado
                                                                CadastraMateria(lhorario, horariodi.getIdmateriap(), anoletivo, horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), datageracao, horariovazio.get(i).getDia(), materiadia, this.idcurso);
                                                                //avanca os contadores
                                                                l = lauxhorario.size();
                                                                i = i + (Integer.valueOf(materiadia.getQtdeauladia()) - 1);
                                                                troca=false;
                                                            }
//                                                        //dia nao usado cadastrado no dia da troca
//                                                        CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "quinta", lmaterianutilizada.get(j), this.idcurso);
//                                                      
//                                                        //dia que foi removido indo para dia nao usado
//                                                        MateriaBeans mb=new MateriaBeans();
//                                                        mb.setQtdeauladia(String.valueOf(horariodi.getQtdeauladia()));
//                                                        mb.setSequencial(horariodi.getSequencial());
//                                                        CadastraMateria(lhorario, horariodi.getIdmateria(), anoletivo, lmaterianutilizada.get(j).getInicio(), lmaterianutilizada.get(j).getTermino(), datageracao, horariovazio.get(i).getDia(), mb, this.idcurso);

                                                          //i=horariodi.getQtdeauladia()+i;
                                                            //implementar se a quantidade de aula vazia cabe na aula de troca e virse e versa
                                                            //verificar tambem se for materia se bustituira mais de uma verificar todos os 
                                                            //horarios
                                                            System.out.println("\nCurso que foi para " + horariodi.getIdmateriap() + "|" + horariovazio.get(i).getDia());
                                                            System.out.println("\n Curso foi para quinta" + lprofessormateria.getId() + "------------------------------\n");
                                                       // JOptionPane.showMessageDialog(null, "\nTrocou\n---------------------------\n");                                                      
                                                            //   l=lauxhorario.size();//verificar por que esta sempre que insere ele esta começão do inicio no
                                                            //objeto horariodi sendo que tem que pegar nova materia dando continuidade

                                                        }

                                                    }
                                                }

                                            //fazer um metodo onde verifica se tem qtde de horario para incerir materia
                                                //nao utilizada e depois verificar se elas pode ser usada no horario vago
                                                //verifica se tem horario suficiente para adicionar a materia        
                                            }
                                        }
                                    }
                                   //se nao trocou nenhuma vez entra
                                    if (troca) {
                                        if (!"false".equals(lauxhorario.get(l).getSexta()) && !"".equals(lauxhorario.get(l).getSexta())) {
                                            if (lauxhorario.get(l).getSexta().contains(String.valueOf(lmaterianutilizada.get(j).getId()))) {
                                                System.out.println(lauxhorario.get(l).getSexta() + "|" + lauxhorario.get(l).getInicio());
                                            //buscar materia do dia e verificar se ela pode ser usada no dia que nao foi usado    
                                                //fazer um metodo que verifica a materia do dia se pode ser usada no dia sem aula 

                                            //busca o dia com a materia que esta sendo utilizada na tabela horario aula do dia
                                                //onde podera ter a materia nao utilizada
                                                HorarioAulaDiaBeans horariodi = new HorarioAulaDao().BuscaHorarioIntervalo(lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), lauxhorario.get(l).getAnoletivo(), lauxhorario.get(l).getIdcurso(), lmaterianutilizada.get(j).getPeriodo(), "sexta");
                                            //verifica se materia esta marcado como dia provavel de aula onde podera ser utilizada
                                                //no dia vazio
                                                System.out.println("Materia dia que esta veficando" + horariodi.getIdmateriap() + "|" + horariodi.getIdmateria() + "-" + horariovazio.get(i).getDia());
                                                if (verificaMateriaDiaVazio(horariodi.getIdmateria(), lauxhorario, horariovazio.get(i).getDia(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino())) {
                                                    System.out.println("Pode dar aula no dia vazio" + horariodi.getIdmateria() + "|" + horariodi.getPeriodo() + "|" + horariovazio.get(i).getInicio() + "|" + horariovazio.get(i).getTermino() + "|" + anoletivo + "|" + datageracao + "|" + horariovazio.get(i).getDia() + "|" + new UnidadeLogadoBeans().getId() + "|" + horariodi.getSequencial() + "|" + horariodi.getQtdeauladia());
                                                    //verifica se o professor da materia que ira para o dia sem materia nao esta dando aula em outra turma do dia provavel da mudanca
                                                    if (FiltraMateriaTroca(horariodi.getIdmateria(), horariodi.getPeriodo(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), anoletivo, datageracao, horariovazio.get(i).getDia(), new UnidadeLogadoBeans().getId(), horariodi.getSequencial(), horariodi.getQtdeauladia())) {
                                                        System.out.println("Professor nao esta dando aula em outra sala no mesmo horario" + horariodi.getIdmateria());
                                                        //verifica se o professor da materia na utilizada nao esta dando aula em outra turma do dia provavel da mudanca
                                                        System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId() + "|" + lauxhorario.get(l).getInicio());
                                                        if (FiltraMateriaTroca(lprofessormateria.getIdmateria(), lmaterianutilizada.get(j).getPeriodo(), lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), anoletivo, datageracao, "sexta", new UnidadeLogadoBeans().getId(), lmaterianutilizada.get(j).getSequencial(), Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()))) {
                                                            System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId());
                                                            //se der tudo certo e so fazer o metodo para cadastrar                                                            
                                                            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
                                                            //verifica se o dia nao utilizado tem qtde de repeticao no dia maior doque que sera trocado
                                                            if (Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()) >= horariodi.getQtdeauladia()) {
                                                                //cadastra materia que nao foi utilizada na sexta
                                                                CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "sexta", lmaterianutilizada.get(j), this.idcurso);
                                                                // busca os dados da materia que sera trocada para o dia vazio
                                                                MateriaBeans materiadia = new MateriaDao().BuscaMateriaidprof(horariodi.getIdmateriap());
                                                                //cadastra materia no dia que nao foi utilizado
                                                                CadastraMateria(lhorario, horariodi.getIdmateriap(), anoletivo, horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), datageracao, horariovazio.get(i).getDia(), materiadia, this.idcurso);
                                                                //avanca os contadores
                                                                l = lauxhorario.size();
                                                                i = i + (Integer.valueOf(materiadia.getQtdeauladia()) - 1);
                                                                troca=false;
                                                            }
//                                                        //dia nao usado cadastrado no dia da troca
//                                                        CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "sexta", lmaterianutilizada.get(j), this.idcurso);
//                                                      
//                                                        //dia que foi removido indo para dia nao usado
//                                                        MateriaBeans mb=new MateriaBeans();
//                                                        mb.setQtdeauladia(String.valueOf(horariodi.getQtdeauladia()));
//                                                        mb.setSequencial(horariodi.getSequencial());
//                                                        CadastraMateria(lhorario, horariodi.getIdmateria(), anoletivo, lmaterianutilizada.get(j).getInicio(), lmaterianutilizada.get(j).getTermino(), datageracao, horariovazio.get(i).getDia(), mb, this.idcurso);

                                                          //i=horariodi.getQtdeauladia()+i;
                                                            //implementar se a quantidade de aula vazia cabe na aula de troca e virse e versa
                                                            //verificar tambem se for materia se bustituira mais de uma verificar todos os 
                                                            //horarios
                                                            System.out.println("\nCurso que foi para " + horariodi.getIdmateriap() + "|" + horariovazio.get(i).getDia());
                                                            System.out.println("\n Curso foi para sexta" + lprofessormateria.getId() + "------------------------------\n");
                                                       // JOptionPane.showMessageDialog(null, "\nTrocou\n---------------------------\n");                                                      
                                                            //   l=lauxhorario.size();//verificar por que esta sempre que insere ele esta começão do inicio no
                                                            //objeto horariodi sendo que tem que pegar nova materia dando continuidade

                                                        }

                                                    }
                                                }

                                            //fazer um metodo onde verifica se tem qtde de horario para incerir materia
                                                //nao utilizada e depois verificar se elas pode ser usada no horario vago
                                                //verifica se tem horario suficiente para adicionar a materia        
                                            }
                                        }
                                    }
                                    //se nao trocou nenhuma vez entra
                                    if (troca) {
                                        if (!"false".equals(lauxhorario.get(l).getSabado()) && !"".equals(lauxhorario.get(l).getSabado())) {
                                            if (lauxhorario.get(l).getSabado().contains(String.valueOf(lmaterianutilizada.get(j).getId()))) {
                                                System.out.println(lauxhorario.get(l).getSabado() + "|" + lauxhorario.get(l).getInicio());
                                            //buscar materia do dia e verificar se ela pode ser usada no dia que nao foi usado    
                                                //fazer um metodo que verifica a materia do dia se pode ser usada no dia sem aula 

                                            //busca o dia com a materia que esta sendo utilizada na tabela horario aula do dia
                                                //onde podera ter a materia nao utilizada
                                                HorarioAulaDiaBeans horariodi = new HorarioAulaDao().BuscaHorarioIntervalo(lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), lauxhorario.get(l).getAnoletivo(), lauxhorario.get(l).getIdcurso(), lmaterianutilizada.get(j).getPeriodo(), "sabado");
                                            //verifica se materia esta marcado como dia provavel de aula onde podera ser utilizada
                                                //no dia vazio
                                                System.out.println("Materia dia que esta veficando" + horariodi.getIdmateriap() + "|" + horariodi.getIdmateria() + "-" + horariovazio.get(i).getDia());
                                                if (verificaMateriaDiaVazio(horariodi.getIdmateria(), lauxhorario, horariovazio.get(i).getDia(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino())) {
                                                    System.out.println("Pode dar aula no dia vazio" + horariodi.getIdmateria() + "|" + horariodi.getPeriodo() + "|" + horariovazio.get(i).getInicio() + "|" + horariovazio.get(i).getTermino() + "|" + anoletivo + "|" + datageracao + "|" + horariovazio.get(i).getDia() + "|" + new UnidadeLogadoBeans().getId() + "|" + horariodi.getSequencial() + "|" + horariodi.getQtdeauladia());
                                                    //verifica se o professor da materia que ira para o dia sem materia nao esta dando aula em outra turma do dia provavel da mudanca
                                                    if (FiltraMateriaTroca(horariodi.getIdmateria(), horariodi.getPeriodo(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), anoletivo, datageracao, horariovazio.get(i).getDia(), new UnidadeLogadoBeans().getId(), horariodi.getSequencial(), horariodi.getQtdeauladia())) {
                                                        System.out.println("Professor nao esta dando aula em outra sala no mesmo horario" + horariodi.getIdmateria());
                                                        //verifica se o professor da materia na utilizada nao esta dando aula em outra turma do dia provavel da mudanca
                                                        System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId() + "|" + lauxhorario.get(l).getInicio());
                                                        if (FiltraMateriaTroca(lprofessormateria.getIdmateria(), lmaterianutilizada.get(j).getPeriodo(), lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), anoletivo, datageracao, "sabado", new UnidadeLogadoBeans().getId(), lmaterianutilizada.get(j).getSequencial(), Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()))) {
                                                            System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId());
                                                            //se der tudo certo e so fazer o metodo para cadastrar                                                            
                                                            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
                                                            //verifica se o dia nao utilizado tem qtde de repeticao no dia maior doque que sera trocado
                                                            if (Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()) >= horariodi.getQtdeauladia()) {
                                                                //cadastra materia que nao foi utilizada na sabado
                                                                CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "sabado", lmaterianutilizada.get(j), this.idcurso);
                                                                // busca os dados da materia que sera trocada para o dia vazio
                                                                MateriaBeans materiadia = new MateriaDao().BuscaMateriaidprof(horariodi.getIdmateriap());
                                                                //cadastra materia no dia que nao foi utilizado
                                                                CadastraMateria(lhorario, horariodi.getIdmateriap(), anoletivo, horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), datageracao, horariovazio.get(i).getDia(), materiadia, this.idcurso);
                                                                //avanca os contadores
                                                                l = lauxhorario.size();
                                                                i = i + (Integer.valueOf(materiadia.getQtdeauladia()) - 1);
                                                                troca=false;
                                                            }
//                                                        //dia nao usado cadastrado no dia da troca
//                                                        CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "sabado", lmaterianutilizada.get(j), this.idcurso);
//                                                      
//                                                        //dia que foi removido indo para dia nao usado
//                                                        MateriaBeans mb=new MateriaBeans();
//                                                        mb.setQtdeauladia(String.valueOf(horariodi.getQtdeauladia()));
//                                                        mb.setSequencial(horariodi.getSequencial());
//                                                        CadastraMateria(lhorario, horariodi.getIdmateria(), anoletivo, lmaterianutilizada.get(j).getInicio(), lmaterianutilizada.get(j).getTermino(), datageracao, horariovazio.get(i).getDia(), mb, this.idcurso);

                                                          //i=horariodi.getQtdeauladia()+i;
                                                            //implementar se a quantidade de aula vazia cabe na aula de troca e virse e versa
                                                            //verificar tambem se for materia se bustituira mais de uma verificar todos os 
                                                            //horarios
                                                            System.out.println("\nCurso que foi para " + horariodi.getIdmateriap() + "|" + horariovazio.get(i).getDia());
                                                            System.out.println("\n Curso foi para sabado" + lprofessormateria.getId() + "------------------------------\n");
                                                       // JOptionPane.showMessageDialog(null, "\nTrocou\n---------------------------\n");                                                      
                                                            //   l=lauxhorario.size();//verificar por que esta sempre que insere ele esta começão do inicio no
                                                            //objeto horariodi sendo que tem que pegar nova materia dando continuidade

                                                        }

                                                    }
                                                }

                                            //fazer um metodo onde verifica se tem qtde de horario para incerir materia
                                                //nao utilizada e depois verificar se elas pode ser usada no horario vago
                                                //verifica se tem horario suficiente para adicionar a materia        
                                            }
                                        }
                                    }
                                    //se nao trocou nenhuma vez entra
                                    if (troca) {
                                        if (!"false".equals(lauxhorario.get(l).getDomingo()) && !"".equals(lauxhorario.get(l).getDomingo())) {
                                            if (lauxhorario.get(l).getDomingo().contains(String.valueOf(lmaterianutilizada.get(j).getId()))) {
                                                System.out.println(lauxhorario.get(l).getDomingo() + "|" + lauxhorario.get(l).getInicio());
                                            //buscar materia do dia e verificar se ela pode ser usada no dia que nao foi usado    
                                                //fazer um metodo que verifica a materia do dia se pode ser usada no dia sem aula 

                                            //busca o dia com a materia que esta sendo utilizada na tabela horario aula do dia
                                                //onde podera ter a materia nao utilizada
                                                HorarioAulaDiaBeans horariodi = new HorarioAulaDao().BuscaHorarioIntervalo(lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), lauxhorario.get(l).getAnoletivo(), lauxhorario.get(l).getIdcurso(), lmaterianutilizada.get(j).getPeriodo(), "domingo");
                                            //verifica se materia esta marcado como dia provavel de aula onde podera ser utilizada
                                                //no dia vazio
                                                System.out.println("Materia dia que esta veficando" + horariodi.getIdmateriap() + "|" + horariodi.getIdmateria() + "-" + horariovazio.get(i).getDia());
                                                if (verificaMateriaDiaVazio(horariodi.getIdmateria(), lauxhorario, horariovazio.get(i).getDia(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino())) {
                                                    System.out.println("Pode dar aula no dia vazio" + horariodi.getIdmateria() + "|" + horariodi.getPeriodo() + "|" + horariovazio.get(i).getInicio() + "|" + horariovazio.get(i).getTermino() + "|" + anoletivo + "|" + datageracao + "|" + horariovazio.get(i).getDia() + "|" + new UnidadeLogadoBeans().getId() + "|" + horariodi.getSequencial() + "|" + horariodi.getQtdeauladia());
                                                    //verifica se o professor da materia que ira para o dia sem materia nao esta dando aula em outra turma do dia provavel da mudanca
                                                    if (FiltraMateriaTroca(horariodi.getIdmateria(), horariodi.getPeriodo(), horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), anoletivo, datageracao, horariovazio.get(i).getDia(), new UnidadeLogadoBeans().getId(), horariodi.getSequencial(), horariodi.getQtdeauladia())) {
                                                        System.out.println("Professor nao esta dando aula em outra sala no mesmo horario" + horariodi.getIdmateria());
                                                        //verifica se o professor da materia na utilizada nao esta dando aula em outra turma do dia provavel da mudanca
                                                        System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId() + "|" + lauxhorario.get(l).getInicio());
                                                        if (FiltraMateriaTroca(lprofessormateria.getIdmateria(), lmaterianutilizada.get(j).getPeriodo(), lauxhorario.get(l).getInicio(), lauxhorario.get(l).getTermino(), anoletivo, datageracao, "domingo", new UnidadeLogadoBeans().getId(), lmaterianutilizada.get(j).getSequencial(), Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()))) {
                                                            System.out.println("Professor do dia vazio pode dar aula" + lprofessormateria.getId());
                                                            //se der tudo certo e so fazer o metodo para cadastrar                                                            
                                                            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
                                                            //verifica se o dia nao utilizado tem qtde de repeticao no dia maior doque que sera trocado
                                                            if (Integer.parseInt(lmaterianutilizada.get(j).getQtdeauladia()) >= horariodi.getQtdeauladia()) {
                                                                //cadastra materia que nao foi utilizada na domingo
                                                                CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "domingo", lmaterianutilizada.get(j), this.idcurso);
                                                                // busca os dados da materia que sera trocada para o dia vazio
                                                                MateriaBeans materiadia = new MateriaDao().BuscaMateriaidprof(horariodi.getIdmateriap());
                                                                //cadastra materia no dia que nao foi utilizado
                                                                CadastraMateria(lhorario, horariodi.getIdmateriap(), anoletivo, horariovazio.get(i).getInicio(), horariovazio.get(i).getTermino(), datageracao, horariovazio.get(i).getDia(), materiadia, this.idcurso);
                                                                //avanca os contadores
                                                                l = lauxhorario.size();
                                                                i = i + (Integer.valueOf(materiadia.getQtdeauladia()) - 1);
                                                                troca=false;
                                                            }
//                                                        //dia nao usado cadastrado no dia da troca
//                                                        CadastraMateria(lhorario, lprofessormateria.getId(), anoletivo, horariodi.getInicio(), horariodi.getTermino(), datageracao, "domingo", lmaterianutilizada.get(j), this.idcurso);
//                                                      
//                                                        //dia que foi removido indo para dia nao usado
//                                                        MateriaBeans mb=new MateriaBeans();
//                                                        mb.setQtdeauladia(String.valueOf(horariodi.getQtdeauladia()));
//                                                        mb.setSequencial(horariodi.getSequencial());
//                                                        CadastraMateria(lhorario, horariodi.getIdmateria(), anoletivo, lmaterianutilizada.get(j).getInicio(), lmaterianutilizada.get(j).getTermino(), datageracao, horariovazio.get(i).getDia(), mb, this.idcurso);

                                                          //i=horariodi.getQtdeauladia()+i;
                                                            //implementar se a quantidade de aula vazia cabe na aula de troca e virse e versa
                                                            //verificar tambem se for materia se bustituira mais de uma verificar todos os 
                                                            //horarios
                                                            System.out.println("\nCurso que foi para " + horariodi.getIdmateriap() + "|" + horariovazio.get(i).getDia());
                                                            System.out.println("\n Curso foi para domingo" + lprofessormateria.getId() + "------------------------------\n");
                                                       // JOptionPane.showMessageDialog(null, "\nTrocou\n---------------------------\n");                                                      
                                                            //   l=lauxhorario.size();//verificar por que esta sempre que insere ele esta começão do inicio no
                                                            //objeto horariodi sendo que tem que pegar nova materia dando continuidade

                                                        }

                                                    }
                                                }

                                            //fazer um metodo onde verifica se tem qtde de horario para incerir materia
                                                //nao utilizada e depois verificar se elas pode ser usada no horario vago
                                                //verifica se tem horario suficiente para adicionar a materia        
                                            }
                                        }
                                    }                                    
                                }
                            }
                        }

                    }

                    resp = false;

                } else if (respexevqtdeauh == 3) {
                    //verifica se as materias utilizada esta sendo utilizada totalmente
                    if (verificaQteAulaHorario(anoletivo, datageracao)) {
                        JOptionPane.showMessageDialog(null, "Conflito de horario de Mais de uma materia\nProblema "
                                + "Esta Sendo Resolvido pela Programação\nCausa do Problema:Mais de Uma materia escolhido o mesmo dia e horario e com a quantidade exata somente para ministrar as aulas");
                        logs.setTxt(new Date() + "  Conflito de horario de Mais de uma materia\nProblema "
                                + "Esta Sendo Resolvido pela Programação\nCausa do Problema:Mais de Uma materia escolhido o mesmo dia e horario e com a quantidade exata somente para ministrar as aulas", "Erro");
                    }
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Verificar Erro em Horario Gerado\n" + ex, "Gera Horario", JOptionPane.ERROR_MESSAGE);
        }
        return resp;

    }

    //verifica a soma de quantidade de aula por semana somando no cadastro de materia com a quantidade
    //de materia utilizado no horario
    private Boolean verQtdeAulaMatEDia(String anoletivo, Date datageracao) {
        Boolean resp = false;
        int hqtdeocorr = 0;
        int pqtdeocorr = 0;
        int periodo = 0;

        try {
            //verifica a quantidade de dia disponivel e igual a quantidade de aulas da materia
            // if (verificaQtdeDiaProfHor(anoletivo, datageracao)) {
            List<HorarioAulaBeans> lhaula = new HorarioAulaDao().BuscaHorario(idcurso, anoletivo, datageracao);
            for (int i = 0; i < lhaula.size(); i++) {
                if (periodo != lhaula.get(i).getPeriodo()) {
                    if (hqtdeocorr != pqtdeocorr) {
                        erroperiodo.add(periodo);
                        resp = true;
                    }
                    periodo = lhaula.get(i).getPeriodo();
                    hqtdeocorr = 0;
                    pqtdeocorr = 0;
                    //retorna a soma da quantidade de aula por semana de acordo com cadastro de materia
                    pqtdeocorr = new ProfessorMateriaDao().buscaProfessorQtdeOcorr(idcurso, lhaula.get(i).getPeriodo());
                }
                if (lhaula.get(i).getSegunda() > 0) {
                    hqtdeocorr++;
                }
                if (lhaula.get(i).getTerca() > 0) {
                    hqtdeocorr++;
                }
                if (lhaula.get(i).getQuarta() > 0) {
                    hqtdeocorr++;
                }
                if (lhaula.get(i).getQuinta() > 0) {
                    hqtdeocorr++;
                }
                if (lhaula.get(i).getSexta() > 0) {
                    hqtdeocorr++;
                }
                if (lhaula.get(i).getSabado() > 0) {
                    hqtdeocorr++;
                }
                if (lhaula.get(i).getDomingo() > 0) {
                    hqtdeocorr++;
                }
                if (lhaula.size() == i + 1) {
                    if (hqtdeocorr != pqtdeocorr) {
                        erroperiodo.add(lhaula.get(i).getPeriodo());
                        resp = true;
                    }
                }
            }

            // }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Verificar Integridade do Horario\n" + ex, "Gera Horario", JOptionPane.ERROR_MESSAGE);
        }
        return resp;
    }

    //verifica se tem dia sem aula no horario se tiver verifica se todas as aulas provavel ja foi usada
    //retorna true para gerar o horario navamente
    //sera utilizado na nova estrutura
    private List<HorarioVazioBeans> verificaQtdeDiaProfHor(String anoletivo, Date datageracao) {
        Boolean resp = false;
        LogsTxt logs = new LogsTxt();
        List<HorarioVazioBeans> horariovazio = new ArrayList<>();
        try {
            List<AuxHorarioCursoBeans> lauxhorario = new AuxHorarioCursoDao().BuscaAuxHorarioCurso(idcurso, anoletivo);
            List<HorarioAulaBeans> lhaula = new HorarioAulaDao().BuscaHorario(idcurso, anoletivo, datageracao);
            for (int i = 0; i < lhaula.size(); i++) {//percorre o horario de aula
                if (lhaula.get(i).getSegunda() < 1 && !resp) {//acha dia que nao foi colocado aula na segunda
                    for (int j = 0; j < lauxhorario.size(); j++) {//percorre a tabela auxiliar
                        //acha o dia e horario referente ao dia sem aula                        
                        if (lhaula.get(i).getInicio().equals(lauxhorario.get(j).getInicio()) && lhaula.get(i).getTermino().equals(lauxhorario.get(j).getTermino())) {
                            if (!lauxhorario.get(j).getSegunda().equals("false") && !lauxhorario.get(j).getSegunda().equals("")) {//verifica se tem materia de provavel aula nesse dia
                                //retorna as materias refenrente aos id de cada materia
                                // List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getSegunda().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao retorna true se pode utilizar
                                //    resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date() + "  Segunda" + lauxhorario.get(j).getSegunda() + "resp:" + resp, "Erro");
                                HorarioVazioBeans hvb = new HorarioVazioBeans();
                                hvb.setId(lhaula.get(i).getId());
                                hvb.setAnoletivo(lhaula.get(i).getAnoletivo());
                                hvb.setDatageracao(lhaula.get(i).getDatageracao());
                                hvb.setIdcurso(lhaula.get(i).getIdcurso());
                                hvb.setInicio(lhaula.get(i).getInicio());
                                hvb.setTermino(lhaula.get(i).getTermino());
                                hvb.setPeriodo(lhaula.get(i).getPeriodo());
                                hvb.setMaterias(lauxhorario.get(j).getSegunda());
                                hvb.setDia("segunda");
                                horariovazio.add(hvb);
//                                if (resp) {
//                                    respexevqtdeauh = 2;
//                                } else {
//                                    respexevqtdeauh = 3;
//                                }
                                // resp=verificaQteAulaHorario(anoletivo, datageracao);
                            }
                        }
                    }
                }
                if (lhaula.get(i).getTerca() < 1 && !resp) {
                    for (int j = 0; j < lauxhorario.size(); j++) {//percorre a tabela auxiliar
                        //acha o dia e horario referente ao dia sem aula
                        if (lhaula.get(i).getInicio().equals(lauxhorario.get(j).getInicio()) && lhaula.get(i).getTermino().equals(lauxhorario.get(j).getTermino())) {
                            if (!lauxhorario.get(j).getTerca().equals("false") && !lauxhorario.get(j).getTerca().equals("")) {//verifica se tem materia de provavel aula nesse dia
                                //retorna as materias refenrente as siglas de cada materia
                                // List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getTerca().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                //  resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date() + "  terca" + lauxhorario.get(j).getTerca() + "resp:" + resp, "Erro");
                                HorarioVazioBeans hvb = new HorarioVazioBeans();
                                hvb.setId(lhaula.get(i).getId());
                                hvb.setAnoletivo(lhaula.get(i).getAnoletivo());
                                hvb.setDatageracao(lhaula.get(i).getDatageracao());
                                hvb.setIdcurso(lhaula.get(i).getIdcurso());
                                hvb.setInicio(lhaula.get(i).getInicio());
                                hvb.setTermino(lhaula.get(i).getTermino());
                                hvb.setPeriodo(lhaula.get(i).getPeriodo());
                                hvb.setMaterias(lauxhorario.get(j).getTerca());
                                hvb.setDia("terca");
                                horariovazio.add(hvb);
//                                if (resp) {
//                                    respexevqtdeauh = 2;
//                                } else {
//                                    respexevqtdeauh = 3;
//                                }
                            }
                        }
                    }
                }
                if (lhaula.get(i).getQuarta() < 1 && !resp) {
                    for (int j = 0; j < lauxhorario.size(); j++) {//percorre a tabela auxiliar
                        //acha o dia e horario referente ao dia sem aula
                        if (lhaula.get(i).getInicio().equals(lauxhorario.get(j).getInicio()) && lhaula.get(i).getTermino().equals(lauxhorario.get(j).getTermino())) {
                            if (!lauxhorario.get(j).getQuarta().equals("false") && !lauxhorario.get(j).getQuarta().equals("")) {//verifica se tem materia de provavel aula nesse dia
                                //retorna as materias refenrente as siglas de cada materia
                                //List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getQuarta().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                //   resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date() + " quarta" + lauxhorario.get(j).getQuarta() + "resp:" + resp, "Erro");
                                HorarioVazioBeans hvb = new HorarioVazioBeans();
                                hvb.setId(lhaula.get(i).getId());
                                hvb.setAnoletivo(lhaula.get(i).getAnoletivo());
                                hvb.setDatageracao(lhaula.get(i).getDatageracao());
                                hvb.setIdcurso(lhaula.get(i).getIdcurso());
                                hvb.setInicio(lhaula.get(i).getInicio());
                                hvb.setTermino(lhaula.get(i).getTermino());
                                hvb.setPeriodo(lhaula.get(i).getPeriodo());
                                hvb.setMaterias(lauxhorario.get(j).getQuarta());
                                hvb.setDia("quarta");
                                horariovazio.add(hvb);
//                                if (resp) {
//                                    respexevqtdeauh = 2;
//                                } else {
//                                    respexevqtdeauh = 3;
//                                }
                            }
                        }
                    }
                }
                if (lhaula.get(i).getQuinta() < 1 && !resp) {
                    for (int j = 0; j < lauxhorario.size(); j++) {//percorre a tabela auxiliar
                        //acha o dia e horario referente ao dia sem aula
                        if (lhaula.get(i).getInicio().equals(lauxhorario.get(j).getInicio()) && lhaula.get(i).getTermino().equals(lauxhorario.get(j).getTermino())) {
                            if (!lauxhorario.get(j).getQuinta().equals("false") && !lauxhorario.get(j).getQuinta().equals("")) {//verifica se tem materia de provavel aula nesse dia
                                //retorna as materias refenrente as siglas de cada materia
                                // List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getQuinta().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                //  resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date() + "  quinta" + lauxhorario.get(j).getQuinta() + "resp:" + resp, "Erro");
                                HorarioVazioBeans hvb = new HorarioVazioBeans();
                                hvb.setId(lhaula.get(i).getId());
                                hvb.setAnoletivo(lhaula.get(i).getAnoletivo());
                                hvb.setDatageracao(lhaula.get(i).getDatageracao());
                                hvb.setIdcurso(lhaula.get(i).getIdcurso());
                                hvb.setInicio(lhaula.get(i).getInicio());
                                hvb.setTermino(lhaula.get(i).getTermino());
                                hvb.setPeriodo(lhaula.get(i).getPeriodo());
                                hvb.setMaterias(lauxhorario.get(j).getQuinta());
                                hvb.setDia("quinta");
                                horariovazio.add(hvb);
//                                if (resp) {
//                                    respexevqtdeauh = 2;
//                                } else {
//                                    respexevqtdeauh = 3;
//                                }
                            }
                        }
                    }
                }
                if (lhaula.get(i).getSexta() < 1 && !resp) {
                    for (int j = 0; j < lauxhorario.size(); j++) {//percorre a tabela auxiliar
                        //acha o dia e horario referente ao dia sem aula
                        if (lhaula.get(i).getInicio().equals(lauxhorario.get(j).getInicio()) && lhaula.get(i).getTermino().equals(lauxhorario.get(j).getTermino())) {
                            if (!lauxhorario.get(j).getSexta().equals("false") && !lauxhorario.get(j).getSexta().equals("")) {//verifica se tem materia de provavel aula nesse dia
                                //retorna as materias refenrente as siglas de cada materia
                                // List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getSexta().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                //  resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date() + "  sexta" + lauxhorario.get(j).getSexta() + "resp:" + resp, "Erro");
                                HorarioVazioBeans hvb = new HorarioVazioBeans();
                                hvb.setId(lhaula.get(i).getId());
                                hvb.setAnoletivo(lhaula.get(i).getAnoletivo());
                                hvb.setDatageracao(lhaula.get(i).getDatageracao());
                                hvb.setIdcurso(lhaula.get(i).getIdcurso());
                                hvb.setInicio(lhaula.get(i).getInicio());
                                hvb.setTermino(lhaula.get(i).getTermino());
                                hvb.setPeriodo(lhaula.get(i).getPeriodo());
                                hvb.setMaterias(lauxhorario.get(j).getSexta());
                                hvb.setDia("sexta");
                                horariovazio.add(hvb);
//                                if (resp) {
//                                    respexevqtdeauh = 2;
//                                } else {
//                                    respexevqtdeauh = 3;
//                                }
                            }
                        }
                    }
                }
                if (lhaula.get(i).getSabado() < 1 && !resp) {
                    for (int j = 0; j < lauxhorario.size(); j++) {//percorre a tabela auxiliar
                        //acha o dia e horario referente ao dia sem aula
                        if (lhaula.get(i).getInicio().equals(lauxhorario.get(j).getInicio()) && lhaula.get(i).getTermino().equals(lauxhorario.get(j).getTermino())) {
                            if (!lauxhorario.get(j).getSabado().equals("false") && !lauxhorario.get(j).getSabado().equals("")) {//verifica se tem materia de provavel aula nesse dia
                                //retorna as materias refenrente as siglas de cada materia
                                // List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getSabado().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                //  resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date() + "  sabado" + lauxhorario.get(j).getSabado() + "resp:" + resp, "Erro");
                                HorarioVazioBeans hvb = new HorarioVazioBeans();
                                hvb.setId(lhaula.get(i).getId());
                                hvb.setAnoletivo(lhaula.get(i).getAnoletivo());
                                hvb.setDatageracao(lhaula.get(i).getDatageracao());
                                hvb.setIdcurso(lhaula.get(i).getIdcurso());
                                hvb.setInicio(lhaula.get(i).getInicio());
                                hvb.setTermino(lhaula.get(i).getTermino());
                                hvb.setPeriodo(lhaula.get(i).getPeriodo());
                                hvb.setMaterias(lauxhorario.get(j).getSabado());
                                hvb.setDia("sabado");
                                horariovazio.add(hvb);
//                                if (resp) {
//                                    respexevqtdeauh = 2;
//                                } else {
//                                    respexevqtdeauh = 3;
//                                }
                            }
                        }
                    }
                }
                if (lhaula.get(i).getDomingo() < 1 && !resp) {
                    for (int j = 0; j < lauxhorario.size(); j++) {//percorre a tabela auxiliar
                        //acha o dia e horario referente ao dia sem aula
                        if (lhaula.get(i).getInicio().equals(lauxhorario.get(j).getInicio()) && lhaula.get(i).getTermino().equals(lauxhorario.get(j).getTermino())) {
                            if (!lauxhorario.get(j).getDomingo().equals("false") && !lauxhorario.get(j).getDomingo().equals("")) {//verifica se tem materia de provavel aula nesse dia
                                //retorna as materias refenrente as siglas de cada materia
                                // List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getDomingo().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
//                              //  resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date() + "  domingo" + lauxhorario.get(j).getDomingo() + "resp:" + resp, "Erro");
                                HorarioVazioBeans hvb = new HorarioVazioBeans();
                                hvb.setId(lhaula.get(i).getId());
                                hvb.setAnoletivo(lhaula.get(i).getAnoletivo());
                                hvb.setDatageracao(lhaula.get(i).getDatageracao());
                                hvb.setIdcurso(lhaula.get(i).getIdcurso());
                                hvb.setInicio(lhaula.get(i).getInicio());
                                hvb.setTermino(lhaula.get(i).getTermino());
                                hvb.setPeriodo(lhaula.get(i).getPeriodo());
                                hvb.setMaterias(lauxhorario.get(j).getDomingo());
                                hvb.setDia("domingo");
                                horariovazio.add(hvb);
//                                if (resp) {
//                                    respexevqtdeauh = 2;
//                                } else {
//                                    respexevqtdeauh = 3;
//                                }
                            }
                        }
                    }
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Verifica Horario sem Aula\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
        }

        return horariovazio;

    }
//verifica se todas as materias foram utilizadas todas as vezes cadastrada

    private List<MateriaBeans> qtdeOcorrenciaMateria(List<MateriaBeans> lmateria, String anoletivo, Date datageracao, int periodo) {
        ProfessorMateriaBeans lprofessor;
        Boolean resp = false;
        List<MateriaBeans> materia = new ArrayList<>();
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
                        //resp = true;
                        materia.add(lmateria.get(l));
                    }
                    //se nao teve ocorrencia ele entra
                } else {
                    if (lprofessor.getId() > 0) {
                        //resp = true;
                        materia.add(lmateria.get(l));
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Verifica Ocorrencia Materia\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }
        return materia;
    }

    //verifica se as materias utilizada esta sendo utilizada totalmente
    private boolean verificaQteAulaHorario(String anoletivo, Date datageracao) {
        Boolean resp = false;
        int ocorrmath = 0;
        int idmat = 0;
        List<Integer> idmatprof = new ArrayList<>();
        for (int i = 0; i < erroperiodo.size(); i++) {
            try {
                List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorarioPeriodo(idcurso, anoletivo, datageracao, erroperiodo.get(i));
                for (int j = 0; j < lhorario.size(); j++) {
                    idmatprof.add(lhorario.get(j).getSegunda());
                    idmatprof.add(lhorario.get(j).getTerca());
                    idmatprof.add(lhorario.get(j).getQuarta());
                    idmatprof.add(lhorario.get(j).getQuinta());
                    idmatprof.add(lhorario.get(j).getSexta());
                    idmatprof.add(lhorario.get(j).getSabado());
                    idmatprof.add(lhorario.get(j).getDomingo());
                }
                //JOptionPane.showMessageDialog(null, erroperiodo.get(i));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Verifica Qtde Ocorrencia Materia\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }
        Collections.sort(idmatprof);
        int cont = 0;
        idmat = idmatprof.get(0);
        for (int i = 0; i < idmatprof.size(); i++) {
            try {
                if (idmat != idmatprof.get(i)) {
                    ocorrmath = new ProfessorMateriaDao().buscaProfessorQtdeOcorrHorario(idmat);
                    new LogsTxt().setTxt(new Date() + " Ocorrencia Materia: " + idmat + "|" + ocorrmath + "|" + cont);
                    if (ocorrmath != cont) {
                        resp = true;
                    }
                    idmat = idmatprof.get(i);
                    cont = 0;
                }
                cont++;
                if (idmatprof.size() == i + 1) {
                    ocorrmath = new ProfessorMateriaDao().buscaProfessorQtdeOcorrHorario(idmatprof.get(i));
                    new LogsTxt().setTxt(new Date() + " Ocorencia Materia: " + idmat + "|" + ocorrmath + "|" + cont);
                    if (ocorrmath != cont) {
                        resp = true;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Verifica Qtde Ocorrencia Materia\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resp;
    }

    public void HorarioProfessor(String anoletivo, Date datageracao) {
        try {
//            //metodo busca o horario do curso na tabela horarioaula
//            List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorario(idcurso, anoletivo, datageracao);
//            //metodo busaca os periodos de um curso na tabela horarioaula
//            List<HorarioAulaBeans> lhorariop=new HorarioAulaDao().HorarioGroupPeriodo(idcurso, anoletivo, datageracao);            
            //metodo busca horario na tabela auxhorariocurso
            List<AuxHorarioCursoBeans> lauxhorario = new AuxHorarioCursoDao().BuscaAuxHorarioCurso(this.idcurso, anoletivo);
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getSegunda()) && !"".equals(lauxhorario.get(i).getSegunda())) {
                    // JOptionPane.showMessageDialog(null, "segunda");
                    FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getSegunda(), datageracao, "segunda");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getTerca()) && !"".equals(lauxhorario.get(i).getTerca())) {
                    // JOptionPane.showMessageDialog(null, "terca");
                    FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getTerca(), datageracao, "terca");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getQuarta()) && !"".equals(lauxhorario.get(i).getQuarta())) {
                    FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getQuarta(), datageracao, "quarta");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getQuinta()) && !"".equals(lauxhorario.get(i).getQuinta())) {
                    FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getQuinta(), datageracao, "quinta");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getSexta()) && !"".equals(lauxhorario.get(i).getSexta())) {
                    FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getSexta(), datageracao, "sexta");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getSabado()) && !"".equals(lauxhorario.get(i).getSabado())) {
                    FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getSabado(), datageracao, "sabado");
                }
            }
            for (int i = 0; i < lauxhorario.size(); i++) {
                if (!"false".equals(lauxhorario.get(i).getDomingo()) && !"".equals(lauxhorario.get(i).getDomingo())) {
                    FiltraGrava(anoletivo, lauxhorario.get(i).getInicio(), lauxhorario.get(i).getTermino(), lauxhorario.get(i).getDomingo(), datageracao, "domingo");
                }
            }

            // new LAuxGradeHorario(anoletivo).SelecionaCursoHorario(lhorario.get(i).getInicio(), lhorario.get(i).getTermino(), idcurso);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Sincronizar Professor\n" + ex, "Gera Horario", JOptionPane.ERROR_MESSAGE);
        }

    }

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
                //List<HorarioAulaBeans> lhorario = new HorarioAulaDao().BuscaHorarioPeriodoDia(idcurso, anoletivo, datageracao, resmataux.get(i).getPeriodo(),dia);
                List<AuxHorarioCursoBeans> lhorario = new AuxHorarioCursoDao().BuscaAuxHorarioCursoDia(idcurso, anoletivo, dia);
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
//verifica se o professor da materia nao esta dando aula em outra turma

    public Boolean FiltraMateriaTroca(int idmateria, int periodo, String inicio, String termino, String anoletivo, Date datageracao, String dia, int idcurso, String sequencia, int qtdeaula) {
        ProfessorMateriaBeans lprofessor;
        Boolean resp = true;
        try {
            //busca id do professor relacionada a materia;
            lprofessor = new ProfessorMateriaDao().BuscaProfessorMateriaAtivo(idmateria, new UnidadeLogadoBeans().getId());
            //metodo verifica se o professor da materia da aula no mesmo horario independente da materia
            Boolean ocorrencia = new HorarioAulaDao().VerificaOcorrencProfHor(dia, inicio, termino, anoletivo, datageracao, lprofessor.getIdprofessor());
            //se o professor da materia tiver aula no horario passado no parametro e retornado false
            if (ocorrencia || (lprofessor.getId() == 0)) {
                resp = false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Filtrar a Troca Materia \n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
        }

        //verifica se tem horario suficiente para adicionar a materia                    
        if (resp) {
            try {
                List<AuxHorarioCursoBeans> lhorario = new AuxHorarioCursoDao().BuscaAuxHorarioCursoDia(idcurso, anoletivo, dia);
                //percorre o horario de aula
                for (int j = 0; j < lhorario.size(); j++) {
                    //verifica se esta no mesmo horario do parametro
                    if (lhorario.get(j).getInicio().equals(inicio) && lhorario.get(j).getTermino().equals(termino)) {
                        //recebe a quantidade de horario que falta
                        int qtdeh = lhorario.size() - j;
                        //compara que e maior que zero
                        if (qtdeh > 0) {
                            //verifica se tem sequencia
                            if (sequencia.equals("true")) {
                                //verifica se a quantidade de aula e maior que a que pode colocar no dia
                                if (qtdeaula > qtdeh) {
                                    //      System.out.println("Removel:"+resmataux.get(i).getSigla()+"|"+Integer.valueOf(resmataux.get(i).getQtdeauladia())+"|qtdeh:"+qtdeh);
                                    resp = false;
                                }
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Filtrar Materia Troca\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resp;
    }

    private boolean verificaMateriaDiaVazio(Integer idmateria, List<AuxHorarioCursoBeans> horario, String dia, String inicio, String termino) {
        Boolean resp = false;
        if ("segunda".equals(dia)) {
            for (int i = 0; i < horario.size(); i++) {
                if (horario.get(i).getInicio().equals(inicio) && horario.get(i).getTermino().equals(termino)) {
                    if (horario.get(i).getSegunda().contains("," + String.valueOf(idmateria) + ",")) {
                        resp = true;
                    }
                }
            }
        }
        if ("terca".equals(dia)) {
            for (int i = 0; i < horario.size(); i++) {
                if (horario.get(i).getInicio().equals(inicio) && horario.get(i).getTermino().equals(termino)) {
                    if (horario.get(i).getTerca().contains("," + String.valueOf(idmateria) + ",")) {
                        resp = true;
                    }
                }
            }
        }
        if ("quarta".equals(dia)) {
            for (int i = 0; i < horario.size(); i++) {
                if (horario.get(i).getInicio().equals(inicio) && horario.get(i).getTermino().equals(termino)) {
                    if (horario.get(i).getQuarta().contains("," + String.valueOf(idmateria) + ",")) {
                        resp = true;
                    }
                }
            }
        }
        if ("quinta".equals(dia)) {
            for (int i = 0; i < horario.size(); i++) {
                if (horario.get(i).getInicio().equals(inicio) && horario.get(i).getTermino().equals(termino)) {
                    if (horario.get(i).getQuinta().contains("," + String.valueOf(idmateria) + ",")) {
                        resp = true;
                    }
                }
            }
        }
        if ("sexta".equals(dia)) {
            for (int i = 0; i < horario.size(); i++) {
                if (horario.get(i).getInicio().equals(inicio) && horario.get(i).getTermino().equals(termino)) {
                    if (horario.get(i).getSexta().contains("," + String.valueOf(idmateria) + ",")) {
                        resp = true;
                    }
                }
            }
        }
        if ("sabado".equals(dia)) {
            for (int i = 0; i < horario.size(); i++) {
                if (horario.get(i).getInicio().equals(inicio) && horario.get(i).getTermino().equals(termino)) {
                    if (horario.get(i).getSabado().contains("," + String.valueOf(idmateria) + ",")) {
                        resp = true;
                    }
                }
            }
        }
        if ("domingo".equals(dia)) {
            for (int i = 0; i < horario.size(); i++) {
                if (horario.get(i).getInicio().equals(inicio) && horario.get(i).getTermino().equals(termino)) {
                    if (horario.get(i).getDomingo().contains("," + String.valueOf(idmateria) + ",")) {
                        resp = true;
                    }
                }
            }
        }
        return resp;
    }
}
