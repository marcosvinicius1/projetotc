/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Dao;

import br.com.sigha.Beans.HorarioCursoBeans;
import br.com.sigha.Util.LogsTxt;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MarcosVinicius
 */
public class HorarioCursoDao {

    // private Connection conexao;
    public HorarioCursoDao() throws SQLException {
        //   conexao=new ConexaoBanco().getConnect();
    }

    public void CadastraHorarioCurso(HorarioCursoBeans hcb) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "insert into horariocurso(idcurso,segunda,terca,quarta,quinta,sexta,sabado,domingo,inicio,termino)values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, hcb.getIdcurso());
            pst.setString(2, hcb.getSegunda());
            pst.setString(3, hcb.getTerca());
            pst.setString(4, hcb.getQuarta());
            pst.setString(5, hcb.getQuinta());
            pst.setString(6, hcb.getSexta());
            pst.setString(7, hcb.getSabado());
            pst.setString(8, hcb.getDomingo());
            pst.setString(9, hcb.getInicio());
            pst.setString(10, hcb.getTermino());
            pst.execute();
            pst.close();
        }
    }

    public List<HorarioCursoBeans> BuscaHorario(int idcurso) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT * FROM gerador.horariocurso where idcurso=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idcurso);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            List<HorarioCursoBeans> listahorario = new ArrayList<HorarioCursoBeans>();
            while (rs.next()) {
                HorarioCursoBeans hb = new HorarioCursoBeans();
                hb.setDomingo(rs.getString("domingo"));
                hb.setId(rs.getInt("id"));
                hb.setIdcurso(rs.getInt("idcurso"));
                hb.setInicio(rs.getString("inicio"));
                hb.setQuarta(rs.getString("quarta"));
                hb.setQuinta(rs.getString("quinta"));
                hb.setSabado(rs.getString("sabado"));
                hb.setSegunda(rs.getString("segunda"));
                hb.setSexta(rs.getString("sexta"));
                hb.setTerca(rs.getString("terca"));
                hb.setTermino(rs.getString("termino"));
                listahorario.add(hb);
            }
            rs.close();
            pst.close();
            return listahorario;
        }
    }

    //metodo utilizado no cadastro de professor para buscar cursos que o professor ministra aula
    public List<HorarioCursoBeans> BuscaHorario(String idcurso) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT * FROM gerador.horariocurso where idcurso in(" + idcurso + ") group by inicio,termino order by inicio";
            PreparedStatement pst = conexao.prepareStatement(sql);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            List<HorarioCursoBeans> listahorario = new ArrayList<HorarioCursoBeans>();
            while (rs.next()) {
                HorarioCursoBeans hb = new HorarioCursoBeans();
                hb.setDomingo(rs.getString("domingo"));
                hb.setId(rs.getInt("id"));
                hb.setIdcurso(rs.getInt("idcurso"));
                hb.setInicio(rs.getString("inicio"));
                hb.setQuarta(rs.getString("quarta"));
                hb.setQuinta(rs.getString("quinta"));
                hb.setSabado(rs.getString("sabado"));
                hb.setSegunda(rs.getString("segunda"));
                hb.setSexta(rs.getString("sexta"));
                hb.setTerca(rs.getString("terca"));
                hb.setTermino(rs.getString("termino"));
                listahorario.add(hb);
            }
            rs.close();
            pst.close();
            return listahorario;
        }
    }

    public void DeletaHorario(int idcurso) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "delete from horariocurso where idcurso=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idcurso);
            pst.execute();
            pst.close();
        }
    }
}
