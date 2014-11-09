/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.dao;

import br.com.sigha.beans.HorarioAulaBeans;
import br.com.sigha.beans.HorarioAulaPesquisaBeans;
import br.com.sigha.beans.MateriaBeans;
import br.com.sigha.util.LogsTxt;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import sun.text.resources.FormatData;

/**
 *
 * @author MarcosVinicius
 */
public class HorarioAulaDao {

    // private Connection conexao;
    public HorarioAulaDao() throws SQLException {
        // conexao = new ConexaoBanco().getConnect();        
    }

    public void CadastraHorario(List<MateriaBeans> lmateria, String anoletivo, Date datageracao, int idunidade) throws SQLException {
        PreparedStatement pstm;
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            for (int i = 0; i < lmateria.size(); i++) {
                String sql = "insert into horarioaula(idcurso,periodo,inicio,termino,"
                        + "anoletivo,datageracao,idunidade)value(?,?,?,?,?,?,?)";
                pstm = conexao.prepareStatement(sql);
                pstm.setInt(1, lmateria.get(i).getIdcurso());
                pstm.setInt(2, lmateria.get(i).getPeriodo());
                pstm.setString(3, lmateria.get(i).getInicio());
                pstm.setString(4, lmateria.get(i).getTermino());
                pstm.setString(5, anoletivo);
                pstm.setString(6, new SimpleDateFormat("yyyy.MM.dd").format(datageracao));
                pstm.setInt(7, idunidade);
                new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
                pstm.execute();
                pstm.close();

            }
        }
    }

    //busca alguns campos da tabela horarioaula metodo utilizado na geracao de horario
    public List<HorarioAulaBeans> BuscaHorario(int idcurso, String anoletivo, Date datageracao) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select * from horarioaula where anoletivo=? and datageracao = ? and idcurso=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, anoletivo);
            pstm.setString(2, new SimpleDateFormat("yyyy.MM.dd").format(datageracao));
            pstm.setInt(3, idcurso);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
            ResultSet rs = pstm.executeQuery();
            List<HorarioAulaBeans> lhorario = new ArrayList<>();
            while (rs.next()) {
                HorarioAulaBeans horario = new HorarioAulaBeans();
                horario.setId(rs.getInt("id"));
                horario.setIdcurso(rs.getInt("idcurso"));
                horario.setPeriodo(rs.getInt("periodo"));
                horario.setInicio(rs.getString("inicio"));
                horario.setTermino(rs.getString("termino"));
                horario.setSegunda(rs.getInt("segunda"));
                horario.setTerca(rs.getInt("terca"));
                horario.setQuarta(rs.getInt("quarta"));
                horario.setQuinta(rs.getInt("quinta"));
                horario.setSexta(rs.getInt("sexta"));
                horario.setSabado(rs.getInt("sabado"));
                horario.setDomingo(rs.getInt("domingo"));
                horario.setAnoletivo(rs.getString("anoletivo"));
                horario.setDatageracao(rs.getDate("datageracao"));
                lhorario.add(horario);
            }
            rs.close();
            pstm.close();

            return lhorario;
        }
    }

    //busca alguns campos da tabela horarioaula metodo utilizado na geracao de horario
    public List<HorarioAulaBeans> BuscaHorarioPeriodo(int idcurso, String anoletivo, Date datageracao, int periodo) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select * from horarioaula where anoletivo=? and datageracao = ? and idcurso=? and periodo=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, anoletivo);
            pstm.setString(2, new SimpleDateFormat("yyyy.MM.dd").format(datageracao));
            pstm.setInt(3, idcurso);
            pstm.setInt(4, periodo);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
            ResultSet rs = pstm.executeQuery();
            List<HorarioAulaBeans> lhorario = new ArrayList<>();
            while (rs.next()) {
                HorarioAulaBeans horario = new HorarioAulaBeans();
                horario.setId(rs.getInt("id"));
                horario.setIdcurso(rs.getInt("idcurso"));
                horario.setPeriodo(rs.getInt("periodo"));
                horario.setInicio(rs.getString("inicio"));
                horario.setTermino(rs.getString("termino"));
                horario.setSegunda(rs.getInt("segunda"));
                horario.setTerca(rs.getInt("terca"));
                horario.setQuarta(rs.getInt("quarta"));
                horario.setQuinta(rs.getInt("quinta"));
                horario.setSexta(rs.getInt("sexta"));
                horario.setSabado(rs.getInt("sabado"));
                horario.setDomingo(rs.getInt("domingo"));
                horario.setAnoletivo(rs.getString("anoletivo"));
                horario.setDatageracao(rs.getDate("datageracao"));
                lhorario.add(horario);
            }
            rs.close();
            pstm.close();

            return lhorario;
        }
    }

    //metodo utilizado na geracao de horario
    public List<HorarioAulaBeans> HorarioGroupPeriodo(int idcurso, String anoletivo, Date datageracao) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select periodo from horarioaula where anoletivo=? and datageracao = ? and idcurso=? group by 1";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, anoletivo);
            pstm.setString(2, new SimpleDateFormat("yyyy.MM.dd").format(datageracao));
            pstm.setInt(3, idcurso);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
            ResultSet rs = pstm.executeQuery();
            List<HorarioAulaBeans> lhorario = new ArrayList<>();
            while (rs.next()) {
                HorarioAulaBeans horario = new HorarioAulaBeans();
                horario.setPeriodo(rs.getInt("periodo"));
                lhorario.add(horario);
            }
            rs.close();
            pstm.close();

            return lhorario;
        }
    }

    //metodo utilizado na geração de horario onde busca a ocorrencia de uma materia
    public List<HorarioAulaBeans> BuscaOcorrenciaMater(int idprofessor, String anoletivo, Date datageracao) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String datageracaof = new SimpleDateFormat("yyyy.MM.dd").format(datageracao);
            String sql = "select h.* from horarioaula h where \n"
                    + "h.segunda=" + idprofessor + " and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' or(h.terca=" + idprofessor + " and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "') or(h.quarta=" + idprofessor + " and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "') or(h.quinta=" + idprofessor + " and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "') "
                    + "or(h.sexta=" + idprofessor + " and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "') or(h.sabado=" + idprofessor + " and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "') or(h.domingo=" + idprofessor + " and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "') ";            
            PreparedStatement pstm = conexao.prepareStatement(sql);
//        pstm.setString(1, anoletivo);
//        pstm.setString(2, new SimpleDateFormat("yyyy.MM.dd").format(datageracao));
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
            ResultSet rs = pstm.executeQuery();
            List<HorarioAulaBeans> lhorario = new ArrayList<>();
            while (rs.next()) {
                HorarioAulaBeans horario = new HorarioAulaBeans();
                horario.setId(rs.getInt("id"));
                horario.setIdcurso(rs.getInt("idcurso"));
                horario.setPeriodo(rs.getInt("periodo"));
                horario.setInicio(rs.getString("inicio"));
                horario.setTermino(rs.getString("termino"));
                horario.setSegunda(rs.getInt("segunda"));
                horario.setTerca(rs.getInt("terca"));
                horario.setQuarta(rs.getInt("quarta"));
                horario.setQuinta(rs.getInt("quinta"));
                horario.setSexta(rs.getInt("sexta"));
                horario.setSabado(rs.getInt("sabado"));
                horario.setDomingo(rs.getInt("domingo"));
                horario.setAnoletivo(rs.getString("anoletivo"));
                horario.setDatageracao(rs.getDate("datageracao"));
                lhorario.add(horario);
            }
            rs.close();
            pstm.close();

            return lhorario;
        }
    }

//    public Boolean VerificaOcorrencProfHor(String inicio, String fim, String anoletivo, Date datageracao, int idprofessor) throws SQLException {
//        String datageracaof = new SimpleDateFormat("yyyy.MM.dd").format(datageracao);
//        int ocorrencia = 0;
//        String sql = "select * from (select h.* from horarioaula h , professormateria p where "
//                + "h.segunda=p.id and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' and p.idprofessor=" + idprofessor + " "
//                + "or(h.terca=p.id and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' and p.idprofessor=" + idprofessor + ") "
//                + "or(h.quarta=p.id and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' and p.idprofessor=" + idprofessor + ") "
//                + "or(h.quinta=p.id and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' and p.idprofessor=" + idprofessor + ") "
//                + "or(h.sexta=p.id and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' and p.idprofessor=" + idprofessor + ") "
//                + "or(h.sabado=p.id and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' and p.idprofessor=" + idprofessor + ") "
//                + "or(h.domingo=p.id and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' and p.idprofessor=" + idprofessor + ") "
//                + ") ho where inicio='" + inicio + "' and termino='" + fim + "'";
//        PreparedStatement pst = this.conexao.prepareStatement(sql);
//        ResultSet rs = pst.executeQuery();
//        // JOptionPane.showMessageDialog(null, sql); 
//        while (rs.next()) {
//            ocorrencia++;
//        }
//
//        if (ocorrencia > 0) {
//            rs.close();
//            pst.close();
//            return true;
//        } else {
//            rs.close();
//            pst.close();
//            return false;
//        }
//    }
    public Boolean VerificaOcorrencProfHor(String dia, String inicio, String fim, String anoletivo, Date datageracao, int idprofessor) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String datageracaof = new SimpleDateFormat("yyyy.MM.dd").format(datageracao);
            int ocorrencia = 0;
            String sql = "select * from (select h.* from horarioaula h , professormateria p where "
                    + "h." + dia + "=p.id and anoletivo='" + anoletivo + "' and datageracao='" + datageracaof + "' and p.idprofessor=" + idprofessor + " "
                    + ") ho where inicio='" + inicio + "' and termino='" + fim + "'";            
            PreparedStatement pst = conexao.prepareStatement(sql);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            // JOptionPane.showMessageDialog(null, sql);

            while (rs.next()) {
                ocorrencia++;
            }

            if (ocorrencia > 0) {
                rs.close();
                pst.close();

                return true;
            } else {
                rs.close();
                pst.close();

                return false;
            }

        }

    }

    //metodo utilizado no algoritmo guloso
    public int VerificaOcorrencProfDia(String dia, int periodo, Date datageracao, String anoletivo, int idprofessor) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select count(id) from horarioaula where " + dia + "=? and periodo=? and datageracao=? and anoletivo=?";            
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idprofessor);
            pst.setInt(2, periodo);
            pst.setString(3, new SimpleDateFormat("yyyy.MM.dd").format(datageracao));
            pst.setString(4, anoletivo);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            int res = 0;
            while (rs.next()) {
                res = rs.getInt("count(id)");
            }
            rs.close();
            pst.close();

            return res;
        }
    }

    public void CadastraMateriaAula(int idprofessor, String anoletivo, String inicio, String termino, Date datageracao, String dia, int periodo, int idcurso, int idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "update horarioaula set " + dia + "=? where anoletivo=? and inicio=? and termino=? and datageracao=? and periodo=? and idcurso=? and idunidade=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idprofessor);
            pst.setString(2, anoletivo);
            pst.setString(3, inicio);
            pst.setString(4, termino);
            pst.setString(5, new SimpleDateFormat("yyyy.MM.dd").format(datageracao));
            pst.setInt(6, periodo);
            pst.setInt(7, idcurso);
            pst.setInt(8, idunidade);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            pst.execute();
            pst.close();

        }
    }

    public List<HorarioAulaPesquisaBeans> BuscaHorario(String valor, String campo) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select * from (select c.nome curso,h.anoletivo,h.datageracao,u.razao unidade from horarioaula h left join curso c on(h.idcurso=c.id)"
                    + "left join unidade u on(h.idunidade=u.id) group by 1,2,3,4) sh where sh."+campo+" like '%"+valor+"%'";          
            PreparedStatement pst=conexao.prepareStatement(sql);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs=pst.executeQuery();
            List<HorarioAulaPesquisaBeans> lhap=new ArrayList<>();
            while(rs.next()){
                HorarioAulaPesquisaBeans hap=new HorarioAulaPesquisaBeans();
                hap.setAnoletivo(rs.getString("anoletivo"));
                hap.setDatageracao(rs.getDate("datageracao"));
                hap.setUnidade(rs.getString("unidade"));
                hap.setCurso(rs.getString("curso"));
                lhap.add(hap);
            }
            rs.close();
            pst.close();
            return lhap;
        }
    }

    public boolean DeletaHorario(String anoletivo, String datahorario) throws SQLException {
        boolean resp=false;
        try (Connection conexao = new ConexaoBanco().getConnect()) {
        String sql="delete from horarioaula where date_format(datageracao,'%d.%m.%Y')=? and anoletivo=?";
        PreparedStatement pstm=conexao.prepareStatement(sql);
        pstm.setString(1, datahorario);
        pstm.setString(2, anoletivo);
        new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
        pstm.execute();
        pstm.close();
        resp=true;
        }
        return resp;
    }
    
    public boolean DeletaHorarioCurso(String anoletivo, String datahorario,int idcurso) throws SQLException {
        boolean resp=false;
        try (Connection conexao = new ConexaoBanco().getConnect()) {
        String sql="delete from horarioaula where date_format(datageracao,'%d.%m.%Y')=? and anoletivo=? and idcurso=?";
        PreparedStatement pstm=conexao.prepareStatement(sql);
        pstm.setString(1, datahorario);
        pstm.setString(2, anoletivo);
        pstm.setInt(3, idcurso);
        new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
        pstm.execute();
        pstm.close();
        resp=true;
        }
        return resp;
    }
}
