/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Logica;

import br.com.sigha.Beans.AuxHorarioCursoBeans;
import br.com.sigha.Beans.HorarioAulaBeans;
import br.com.sigha.Beans.MateriaBeans;
import br.com.sigha.Beans.ProfessorMateriaBeans;
import br.com.sigha.Beans.UnidadeLogadoBeans;
import br.com.sigha.Dao.AuxHorarioCursoDao;
import br.com.sigha.Dao.HorarioAulaDao;
import br.com.sigha.Dao.MateriaDao;
import br.com.sigha.Dao.ProfessorMateriaDao;
import br.com.sigha.Util.LogsTxt;
import br.com.sigha.Util.QtdeOcorrenciaCaracter;
import static java.rmi.Naming.list;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.eclipse.persistence.internal.core.helper.CoreClassConstants;

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

    public LProfessorHorario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //cadastra horario dos cursos na tabela horario aula
    public void CadastraHorarioAula(String anoletivo, Date datageracao) {
        try {
            CadastraHorario(this.idcurso, anoletivo, datageracao);//cadastra horario dos cursos na tabela horario aula
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
                    List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(materias.replace("|", ",0"), lhorario.get(i).getPeriodo());
                    //seleciona uma materia do periodo passado como parametro   
                    // compara o horario passado para a funcao e com o horario de aula se tem materia usando o dia
                    if (verificadia(dia, inicio, termino, lhorario.get(i).getPeriodo(), anoletivo, datageracao)) {                        
                        MateriaBeans materia = new LSelecionaMateria().FiltraMateria(lmateria, lhorario.get(i).getPeriodo(), lhorario.get(i).getInicio(), lhorario.get(i).getTermino(), anoletivo, datageracao, dia, lhorario.get(i).getIdcurso());
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
    private void CadastraMateria(List<HorarioAulaBeans> lhorario, int idprofessor, String anoletivo, String inicio, String termino, Date datageracao, String dia, MateriaBeans materia, int idcurso) throws SQLException {
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
                        new HorarioAulaDao().CadastraMateriaAula(idprofessor, anoletivo, lhorario.get(j + i).getInicio(), lhorario.get(j + i).getTermino(), datageracao, dia, materia.getPeriodo(), idcurso, new UnidadeLogadoBeans().getId());
                    }
                }
                //    }

            }
        } else {
            new HorarioAulaDao().CadastraMateriaAula(idprofessor, anoletivo, inicio, termino, datageracao, dia, materia.getPeriodo(), idcurso, new UnidadeLogadoBeans().getId());
        }
//        JOptionPane.showMessageDialog(null, "Mandou" + dia + "\n" + idprofessor + "\n" + inicio + "\n" + termino + "\nValor poisica:");
//        new HorarioAulaDao().CadastraMateriaAula(idprofessor, anoletivo, inicio, termino, datageracao, dia, materia.getPeriodo());
    }
//verifica se tem materia utilizando o dia

    private boolean verificadia(String dia, String inicio, String termino, int periodo, String anoletivo, Date datageracao) throws SQLException {
        Boolean resp = true;
        List<HorarioAulaBeans> lhorariotext = new HorarioAulaDao().BuscaHorario(this.idcurso, anoletivo, datageracao);
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
        Boolean resp = false;
        LogsTxt logs=new LogsTxt();
        if (verQtdeAulaMatEDia(anoletivo, datageracao)) {
            for (int i = 0; i < erroperiodo.size(); i++) {
                logs.setTxt(new Date()+"  Periodos com Erros"+String.valueOf(erroperiodo.size()),"Erro");
            }
            logs.setTxt(new Date()+"  -------------------------","Erro");
            //verifica se tem dia sem aula e se as materia do dia sem aula esta todas utilizadas
            if (verificaQtdeDiaProfHor(anoletivo, datageracao)) {
                logs.setTxt(new Date()+"  Tem erro QtdeDiaProfHora","Erro");
                logs.setTxt(new Date()+"  -------------------------","Erro");
                resp = true;

            } else if (respexevqtdeauh == 3) {
                if (verificaQteAulaHorario(anoletivo, datageracao)) {
//                    System.out.println("Tem erroQtdeAulaHorario");
//                    System.out.println("-------------------------");
//                    JOptionPane.showMessageDialog(null, resp);
//                    resp = true;
                    JOptionPane.showMessageDialog(null, "Conflito de horario de Mais de uma materia\nProblema "
                            + "Esta Sendo Resolvido pela Programação\nCausa do Problema:Mais de Uma materia escolhido o mesmo dia e horario e com a quantidade exata somente para ministrar as aulas");
                    logs.setTxt(new Date()+"  Conflito de horario de Mais de uma materia\nProblema "
                            + "Esta Sendo Resolvido pela Programação\nCausa do Problema:Mais de Uma materia escolhido o mesmo dia e horario e com a quantidade exata somente para ministrar as aulas","Erro");
                } 
            }

        }
        return resp;
    }

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
    private boolean verificaQtdeDiaProfHor(String anoletivo, Date datageracao) {
        Boolean resp = false;
        LogsTxt logs=new LogsTxt();
        try {
            List<AuxHorarioCursoBeans> lauxhorario = new AuxHorarioCursoDao().BuscaAuxHorarioCurso(idcurso, anoletivo);
            List<HorarioAulaBeans> lhaula = new HorarioAulaDao().BuscaHorario(idcurso, anoletivo, datageracao);
            for (int i = 0; i < lhaula.size(); i++) {//percorre o horario de aula
                if (lhaula.get(i).getSegunda() < 1 && !resp) {//acha dia que nao foi colocado aula na segunda
                    for (int j = 0; j < lauxhorario.size(); j++) {//percorre a tabela auxiliar
                        //acha o dia e horario referente ao dia sem aula                        
                        if (lhaula.get(i).getInicio().equals(lauxhorario.get(j).getInicio()) && lhaula.get(i).getTermino().equals(lauxhorario.get(j).getTermino())) {
                            if (!lauxhorario.get(j).getSegunda().equals("false") && !lauxhorario.get(j).getSegunda().equals("")) {//verifica se tem materia de provavel aula nesse dia
                                //retorna as materias refenrente as siglas de cada materia
                                List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getSegunda().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao retorna true se pode utilizar
                                resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date()+"  Segunda" + lauxhorario.get(j).getSegunda() + "resp:" + resp,"Erro");
                                if (resp) {
                                    respexevqtdeauh = 2;
                                } else {
                                    respexevqtdeauh = 3;
                                }
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
                                List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getTerca().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                                logs.setTxt(new Date()+"  terca" + lauxhorario.get(j).getTerca() + "resp:" + resp,"Erro");
                                if (resp) {
                                    respexevqtdeauh = 2;
                                } else {
                                    respexevqtdeauh = 3;
                                }
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
                                List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getQuarta().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                             logs.setTxt(new Date()+" quarta" + lauxhorario.get(j).getQuarta() + "resp:" + resp,"Erro");
                                if (resp) {
                                    respexevqtdeauh = 2;
                                } else {
                                    respexevqtdeauh = 3;
                                }
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
                                List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getQuinta().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                            logs.setTxt(new Date()+"  quinta" + lauxhorario.get(j).getQuinta() + "resp:" + resp,"Erro");
                                if (resp) {
                                    respexevqtdeauh = 2;
                                } else {
                                    respexevqtdeauh = 3;
                                }
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
                                List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getSexta().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                            logs.setTxt(new Date()+"  sexta" + lauxhorario.get(j).getSexta() + "resp:" + resp,"Erro");
                                if (resp) {
                                    respexevqtdeauh = 2;
                                } else {
                                    respexevqtdeauh = 3;
                                }
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
                                List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getSabado().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                            logs.setTxt(new Date()+"  sabado" + lauxhorario.get(j).getSabado() + "resp:" + resp,"Erro");
                                if (resp) {
                                    respexevqtdeauh = 2;
                                } else {
                                    respexevqtdeauh = 3;
                                }
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
                                List<MateriaBeans> lmateria = new MateriaDao().BuscaIdMateria(lauxhorario.get(j).getDomingo().replace("|", ",0"), lhaula.get(i).getPeriodo());
                                //retorna se a materia ja foi utilizada todas as aulas ou nao
                                resp = qtdeOcorrenciaMateria(lmateria, anoletivo, datageracao, lhaula.get(i).getPeriodo());
                          logs.setTxt(new Date()+"  domingo" + lauxhorario.get(j).getDomingo() + "resp:" + resp,"Erro");
                                if (resp) {
                                    respexevqtdeauh = 2;
                                } else {
                                    respexevqtdeauh = 3;
                                }
                            }
                        }
                    }
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Verifica Horario sem Aula\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
        }

        return resp;

    }

    private Boolean qtdeOcorrenciaMateria(List<MateriaBeans> lmateria, String anoletivo, Date datageracao, int periodo) {
        ProfessorMateriaBeans lprofessor;
        Boolean resp = false;
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
                        resp = true;
                    }
                    //se nao teve ocorrencia ele entra
                } else {
                    if (lprofessor.getId() > 0) {
                        resp = true;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Verifica Ocorrencia Materia\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
            }
        }
        return resp;
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
                     System.out.println(idmat+"|"+ocorrmath+"|"+cont);
                    if (ocorrmath != cont) {
                        resp = true;
                    }
                    idmat = idmatprof.get(i);
                    cont = 0;
                }
                cont++;
                if (idmatprof.size() == i + 1) {
                    ocorrmath = new ProfessorMateriaDao().buscaProfessorQtdeOcorrHorario(idmatprof.get(i));
                     System.out.println(idmat+"|"+ocorrmath+"|"+cont);
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
}
