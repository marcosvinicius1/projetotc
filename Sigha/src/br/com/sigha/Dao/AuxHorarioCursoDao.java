/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Dao;

import br.com.sigha.Beans.AuxHorarioCursoBeans;
import br.com.sigha.Beans.HorarioCursoBeans;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MarcosVinicius
 */
public class AuxHorarioCursoDao {

    // private Connection conexao;
    public AuxHorarioCursoDao() {
        //  conexao = new ConexaoBanco().getConnect();
    }

    public void CadastraAuxHorarioCurso(HorarioCursoBeans hcb, String anoletivo) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "insert into auxhorariocurso(curso,segunda,terca,quarta,quinta,sexta,sabado,domingo,inicio,termino,anoletivo)values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, hcb.getIdcurso());
            pst.setString(2, hcb.getSegunda().equals("true") ? "" : "false");
            pst.setString(3, hcb.getTerca().equals("true") ? "" : "false");
            pst.setString(4, hcb.getQuarta().equals("true") ? "" : "false");
            pst.setString(5, hcb.getQuinta().equals("true") ? "" : "false");
            pst.setString(6, hcb.getSexta().equals("true") ? "" : "false");
            pst.setString(7, hcb.getSabado().equals("true") ? "" : "false");
            pst.setString(8, hcb.getDomingo().equals("true") ? "" : "false");
            pst.setString(9, hcb.getInicio());
            pst.setString(10, hcb.getTermino());
            pst.setString(11, anoletivo);
            pst.execute();
            pst.close();
        }
    }

    public void AtualizaAuxHoararioCurso(String sigla, int idcurso, String anoletivo, String camposemana,String inicio,String termino) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "update auxhorariocurso set " + camposemana + "=concat(" + camposemana + ",'" + sigla + "')"
                    + " where curso=" + idcurso + " and anoletivo='" + anoletivo + "' and " + camposemana + "<>'false' and inicio=? and termino=?";            
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, inicio);
            pst.setString(2, termino);
            pst.execute();
            pst.close();
        }
    }

    //busca horario na tabela auxhorariocurso utilizado na classe LSicronizaProfessor
    public List<AuxHorarioCursoBeans> BuscaAuxHorarioCurso(int curso, String anoletivo) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT * FROM auxhorariocurso WHERE  curso=? and anoletivo=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, curso);
            pstm.setString(2, anoletivo);
            ResultSet rs = pstm.executeQuery();
            List<AuxHorarioCursoBeans> lauxhorario = new ArrayList<>();
            while (rs.next()) {
                AuxHorarioCursoBeans auxhorario = new AuxHorarioCursoBeans();
                auxhorario.setId(rs.getInt("id"));
                auxhorario.setIdcurso(rs.getInt("curso"));
                auxhorario.setInicio(rs.getString("inicio"));
                auxhorario.setTermino(rs.getString("termino"));
                auxhorario.setSegunda(rs.getString("segunda"));
                auxhorario.setTerca(rs.getString("terca"));
                auxhorario.setQuarta(rs.getString("quarta"));
                auxhorario.setQuinta(rs.getString("quinta"));
                auxhorario.setSexta(rs.getString("sexta"));
                auxhorario.setSabado(rs.getString("sabado"));
                auxhorario.setDomingo(rs.getString("domingo"));
                auxhorario.setAnoletivo(rs.getString("anoletivo"));
                lauxhorario.add(auxhorario);
            }
            rs.close();
            pstm.close();
            return lauxhorario;
        }
    }

    public void DeletaHorarioAux() throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "delete from auxhorariocurso";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.execute();
            pstm.close();
        }
    }

    public Boolean OcorrenciaMateria(String dia, int curso, String anoletivo, String sigla) throws SQLException {
        Boolean resp = false;
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select * from auxhorariocurso where " + dia + " like '%"+sigla+"|%' and curso=? and anoletivo=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, curso);
            pstm.setString(2, anoletivo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {                
                resp = true;
            }
            rs.close();
            pstm.close();
        }
        return resp;
    }

}
