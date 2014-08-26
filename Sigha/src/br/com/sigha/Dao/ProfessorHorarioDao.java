/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Dao;

import br.com.sigha.Beans.ProfessorHorarioBeans;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcosVinicius
 */
public class ProfessorHorarioDao {

    //private Connection conexao;
    public ProfessorHorarioDao() throws SQLException {
        //  conexao = new ConexaoBanco().getConnect();
    }

    public void CadastraHorarioProfessor(ProfessorHorarioBeans hpb) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "insert into horarioprofessor(idprofessormateria,segunda,terca,quarta,quinta,sexta,sabado,domingo,inicio,termino)values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, hpb.getIdprofessormateria());
            pst.setString(2, hpb.getSegunda());
            pst.setString(3, hpb.getTerca());
            pst.setString(4, hpb.getQuarta());
            pst.setString(5, hpb.getQuinta());
            pst.setString(6, hpb.getSexta());
            pst.setString(7, hpb.getSabado());
            pst.setString(8, hpb.getDomingo());
            pst.setString(9, hpb.getInicio());
            pst.setString(10, hpb.getTermino());
            pst.execute();
            pst.close();
        }
    }

    public List<ProfessorHorarioBeans> BuscaHorarioProfessor(int idprofessor) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT professormateria.idprofessor,horarioprofessor.* FROM gerador.professormateria left join horarioprofessor \n"
                    + "on(professormateria.id=horarioprofessor.idprofessormateria) where idprofessor=? group by inicio,termino ";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idprofessor);
            ResultSet rs = pst.executeQuery();
            List<ProfessorHorarioBeans> listahorario = new ArrayList<>();
            while (rs.next()) {
                ProfessorHorarioBeans hb = new ProfessorHorarioBeans();
                hb.setIdhorario(rs.getInt("id"));
                hb.setIdprofessormateria(rs.getInt("idprofessormateria"));
                hb.setInicio(rs.getString("inicio"));
                hb.setQuarta(rs.getString("quarta"));
                hb.setQuinta(rs.getString("quinta"));
                hb.setSabado(rs.getString("sabado"));
                hb.setSegunda(rs.getString("segunda"));
                hb.setSexta(rs.getString("sexta"));
                hb.setDomingo(rs.getString("domingo"));
                hb.setTerca(rs.getString("terca"));
                hb.setTermino(rs.getString("termino"));
                listahorario.add(hb);
            }
            rs.close();
            pst.close();
            return listahorario;
        }
    }

    public void DeletaHorario(int idprofessormateria) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "delete FROM gerador.horarioprofessor where idprofessormateria=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idprofessormateria);
            pst.execute();
            pst.close();
        }
    }

    public List<ProfessorHorarioBeans> BuscaProfessorHorarioSigla(String sigla) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select hp.* from professor p left join professormateria pm on(p.id=pm.idprofessor) "
                    + "left join materia m on(pm.idmateria=m.id) "
                    + "left join horarioprofessor hp on(hp.idprofessormateria=pm.id) "
                    + "where m.ativo='true' and pm.ativo='true' and p.ativo='true' and m.sigla=?";
            PreparedStatement pstm=conexao.prepareStatement(sql);
            pstm.setString(1, sigla);
            ResultSet rs= pstm.executeQuery();
            List<ProfessorHorarioBeans> listahorario=new ArrayList<>();
            while (rs.next()) {                
                ProfessorHorarioBeans hb = new ProfessorHorarioBeans();
                hb.setIdhorario(rs.getInt("id"));
                hb.setIdprofessormateria(rs.getInt("idprofessormateria"));
                hb.setInicio(rs.getString("inicio"));
                hb.setQuarta(rs.getString("quarta"));
                hb.setQuinta(rs.getString("quinta"));
                hb.setSabado(rs.getString("sabado"));
                hb.setSegunda(rs.getString("segunda"));
                hb.setSexta(rs.getString("sexta"));
                hb.setDomingo(rs.getString("domingo"));
                hb.setTerca(rs.getString("terca"));
                hb.setTermino(rs.getString("termino"));
                listahorario.add(hb);
            }
            rs.close();
            pstm.close();
            return listahorario;
        }                
    }

}
