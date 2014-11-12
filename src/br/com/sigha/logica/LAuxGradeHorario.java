/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.logica;

import br.com.sigha.beans.HorarioCursoBeans;
import br.com.sigha.beans.MateriaBeans;
import br.com.sigha.beans.ProfessorHorarioBeans;
import br.com.sigha.beans.ProfessorMateriaBeans;
import br.com.sigha.dao.AuxHorarioCursoDao;
import br.com.sigha.dao.HorarioCursoDao;
import br.com.sigha.dao.MateriaDao;
import br.com.sigha.dao.ProfessorHorarioDao;
import br.com.sigha.dao.ProfessorMateriaDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MarcosVinicius
 */
public class LAuxGradeHorario {

    List<HorarioCursoBeans> lhorariocurso;
    List<MateriaBeans> lmateria;
    String anoletivo;

    public LAuxGradeHorario(String anoletivo) {
        this.anoletivo = anoletivo;
    }

    //verifica se materia sera chamado futuramente
    public Boolean verificaChamaMateria(String diasemana, String hinicio, String htermino,String sigla,int idcurso) {
        List<String> ldias=new ArrayList<>();
        Boolean resp=false;
        ldias.add("segunda");
        ldias.add("terca");
        ldias.add("quarta");
        ldias.add("quinta");
        ldias.add("sexta");
        ldias.add("sabado");
        ldias.add("domingo");
        
        for(int i=0;i<ldias.size();i++){
            
            if(diasemana.equals(ldias.get(i)) && !diasemana.equals("domingo")){
                for(int j=i;j<ldias.size();j++){
                    try {
                        if(new AuxHorarioCursoDao().OcorrenciaMateria(ldias.get(i+1), idcurso, anoletivo, sigla)){
                            resp=true;
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro Verificar se Materia e Chamada Futuramente\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        return resp;
    }
    
//metodo busca e insere na tabela auxhorarioe do horario definitivo os dados referente horario do curso
    public void GradeHorario(int idcurso) throws SQLException {
        //try {
       
            HorarioCursoDao hcd = new HorarioCursoDao();
            AuxHorarioCursoDao ahcd = new AuxHorarioCursoDao();
            //lista de  horario do curso
            lhorariocurso = hcd.BuscaHorario(idcurso);
            
            for (int i = 0; i < lhorariocurso.size(); i++) {            
                //cadastra o horario do curso na tabela auxiliarhorario       
                ahcd.CadastraAuxHorarioCurso(lhorariocurso.get(i), this.anoletivo);
            }
            //ajusta auxhoario e coloca id dos curso
             AjustaHorarioComCurso(lhorariocurso.get(1).getIdcurso(),lhorariocurso);             
             LProfessorHorario lpo=new LProfessorHorario(idcurso);        
             //Cadastra horario dos cursos na tabela horario aula
             lpo.CadastraHorarioAula(this.anoletivo,new Date());
             

    }

    
    
    //metodo coloca a sigla no curso nos dias onde provavelmente devera ter aula
    private void AjustaHorarioComCurso(int idcurso,List<HorarioCursoBeans> hc){
        try {
           // lmateria=new MateriaDao().BuscaMateriaCurso(idcurso);//busca materia do curso
            List<ProfessorHorarioBeans> phb=new ArrayList<>();
            
            AuxHorarioCursoDao ahcd=new AuxHorarioCursoDao();
            
            for(int i=0;i<hc.size();i++){
                if(!"false".equals(hc.get(i).getSegunda())){
                    //busca horario da materia do professor                                        
                    String id=ConcatenaIdmateria(new ProfessorMateriaDao().BuscaIdMateria(hc.get(i).getInicio(),hc.get(i).getTermino(),hc.get(i).getIdcurso(),"segunda"));
//                    phb=new ProfessorHorarioDao().BuscaProfessorHorarioMateria(lmateria.get(i).getId());
//                    for(int j=0;j<phb.size();j++){                        
//                        if(phb.get(j).getSegunda().equals("true")){                            
                            ahcd.AtualizaAuxHoararioCurso(id, idcurso, this.anoletivo, "segunda",hc.get(i).getInicio(),hc.get(i).getTermino());
//                        }
//                    }
                    
                }
                if(!"false".equals(hc.get(i).getTerca())){
                    //busca horario da materia do professor                                        
                    String id=ConcatenaIdmateria(new ProfessorMateriaDao().BuscaIdMateria(hc.get(i).getInicio(),hc.get(i).getTermino(),hc.get(i).getIdcurso(),"terca"));
//                    phb=new ProfessorHorarioDao().BuscaProfessorHorarioMateria(lmateria.get(i).getId());
//                    for(int j=0;j<phb.size();j++){                        
//                        if(phb.get(j).getSegunda().equals("true")){                            
                            ahcd.AtualizaAuxHoararioCurso(id, idcurso, this.anoletivo, "terca",hc.get(i).getInicio(),hc.get(i).getTermino());
//                        }
//                    }
                    
                }                  
                
                if(!"false".equals(hc.get(i).getQuarta())){
                    //busca horario da materia do professor                                        
                    String id=ConcatenaIdmateria(new ProfessorMateriaDao().BuscaIdMateria(hc.get(i).getInicio(),hc.get(i).getTermino(),hc.get(i).getIdcurso(),"quarta"));
//                    phb=new ProfessorHorarioDao().BuscaProfessorHorarioMateria(lmateria.get(i).getId());
//                    for(int j=0;j<phb.size();j++){                        
//                        if(phb.get(j).getSegunda().equals("true")){                            
                            ahcd.AtualizaAuxHoararioCurso(id, idcurso, this.anoletivo, "quarta",hc.get(i).getInicio(),hc.get(i).getTermino());
//                        }
//                    }
                    
                }
                if(!"false".equals(hc.get(i).getQuinta())){
                    //busca horario da materia do professor                                        
                    String id=ConcatenaIdmateria(new ProfessorMateriaDao().BuscaIdMateria(hc.get(i).getInicio(),hc.get(i).getTermino(),hc.get(i).getIdcurso(),"quinta"));
//                    phb=new ProfessorHorarioDao().BuscaProfessorHorarioMateria(lmateria.get(i).getId());
//                    for(int j=0;j<phb.size();j++){                        
//                        if(phb.get(j).getSegunda().equals("true")){                            
                            ahcd.AtualizaAuxHoararioCurso(id, idcurso, this.anoletivo, "quinta",hc.get(i).getInicio(),hc.get(i).getTermino());
//                        }
//                    }
                    
                }
                if(!"false".equals(hc.get(i).getSexta())){
                    //busca horario da materia do professor                                        
                    String id=ConcatenaIdmateria(new ProfessorMateriaDao().BuscaIdMateria(hc.get(i).getInicio(),hc.get(i).getTermino(),hc.get(i).getIdcurso(),"sexta"));
//                    phb=new ProfessorHorarioDao().BuscaProfessorHorarioMateria(lmateria.get(i).getId());
//                    for(int j=0;j<phb.size();j++){                        
//                        if(phb.get(j).getSegunda().equals("true")){                            
                            ahcd.AtualizaAuxHoararioCurso(id, idcurso, this.anoletivo, "sexta",hc.get(i).getInicio(),hc.get(i).getTermino());
//                        }
//                    }
                    
                }
                if(!"false".equals(hc.get(i).getSabado())){
                    //busca horario da materia do professor                                        
                    String id=ConcatenaIdmateria(new ProfessorMateriaDao().BuscaIdMateria(hc.get(i).getInicio(),hc.get(i).getTermino(),hc.get(i).getIdcurso(),"sabado"));
//                    phb=new ProfessorHorarioDao().BuscaProfessorHorarioMateria(lmateria.get(i).getId());
//                    for(int j=0;j<phb.size();j++){                        
//                        if(phb.get(j).getSegunda().equals("true")){                            
                            ahcd.AtualizaAuxHoararioCurso(id, idcurso, this.anoletivo, "sabado",hc.get(i).getInicio(),hc.get(i).getTermino());
//                        }
//                    }
                    
                }
                if(!"false".equals(hc.get(i).getDomingo())){
                    //busca horario da materia do professor                                        
                    String id=ConcatenaIdmateria(new ProfessorMateriaDao().BuscaIdMateria(hc.get(i).getInicio(),hc.get(i).getTermino(),hc.get(i).getIdcurso(),"domingo"));
//                    phb=new ProfessorHorarioDao().BuscaProfessorHorarioMateria(lmateria.get(i).getId());
//                    for(int j=0;j<phb.size();j++){                        
//                        if(phb.get(j).getSegunda().equals("true")){                            
                            ahcd.AtualizaAuxHoararioCurso(id, idcurso, this.anoletivo, "domingo",hc.get(i).getInicio(),hc.get(i).getTermino());
//                        }
//                    }
                    
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Sincronizar Cursos na Tabela Auxiliar com Materias\n" + ex, "Gerar Horario", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    //cadastra professor no dia de aula
    void SelecionaCursoHorario(String inicio, String termino, int idcurso) {
        
    }

    private String ConcatenaIdmateria(List<ProfessorMateriaBeans> BsId) {
        String id="";
        for(int i=0;i<BsId.size();i++){
            id=id+BsId.get(i).getIdmateria()+"|";
        }
        return id;
    }
    
    
}
