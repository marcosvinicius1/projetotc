/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.dao;

import br.com.sigha.beans.CursoBeans;
import br.com.sigha.util.LogsTxt;
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
public class CursoDao {

   // private Connection conexao;
    public CursoDao() throws SQLException {
        //   conexao = new ConexaoBanco().getConnect();
    }

    //cadastra curso
    public void CadastraCurso(CursoBeans cb) throws SQLException {

        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "insert into curso (nome,idprofessor,turno,idunidade,ativo)values(?,?,?,?,?)";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cb.getNome());
            pst.setInt(2, cb.getIdprofessor());
            pst.setInt(3, cb.getTurno());
            pst.setInt(4, cb.getIdunidade());
            pst.setString(5, cb.getAtivo());
            pst.execute();
            pst.close();
        }
    }

    public List<CursoBeans> BuscaCurso(int idunidade) throws SQLException {

        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select curso.*,professor.nome as nomecoordenador from curso left join professor on(curso.idprofessor=professor.id) where curso.idunidade=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idunidade);
            ResultSet rs = pst.executeQuery();
            List<CursoBeans> lcb = new ArrayList<CursoBeans>();
            while (rs.next()) {
                CursoBeans cb = new CursoBeans();
                cb.setId(rs.getInt("id"));
                cb.setNome(rs.getString("nome"));
                cb.setIdprofessor(rs.getInt("idprofessor"));
                cb.setNomecoordenador(rs.getString("nomecoordenador"));
                cb.setTurno(rs.getInt("turno"));
                cb.setAtivo(rs.getString("ativo"));
                lcb.add(cb);
            }
            rs.close();
            pst.close();
            return lcb;
        }
    }

    //utilizado na tela de professores para buscar os cursos ativos
    public List<CursoBeans> BuscaCursoAtivo(int idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select curso.*,professor.nome as nomecoordenador from curso left join professor on(curso.idprofessor=professor.id) where curso.idunidade=? and curso.ativo='true'";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idunidade);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            List<CursoBeans> lcb = new ArrayList<CursoBeans>();
            while (rs.next()) {
                CursoBeans cb = new CursoBeans();
                cb.setId(rs.getInt("id"));
                cb.setNome(rs.getString("nome"));
                cb.setIdprofessor(rs.getInt("idprofessor"));
                cb.setNomecoordenador(rs.getString("nomecoordenador"));
                cb.setTurno(rs.getInt("turno"));
                cb.setAtivo(rs.getString("ativo"));
                lcb.add(cb);
            }
            rs.close();
            pst.close();

            return lcb;
        }
    }

    public void AlteraCurso(CursoBeans cb) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "update curso set nome=?,idprofessor=?,turno=?,idunidade=?,ativo=? where id=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cb.getNome());
            pst.setInt(2, cb.getIdprofessor());
            pst.setInt(3, cb.getTurno());
            pst.setInt(4, cb.getIdunidade());
            pst.setString(5, cb.getAtivo());
            pst.setInt(6, cb.getId());
            pst.execute();
            pst.close();
        }
    }

    //metodo exclui curso
    public void Excluicurso(int id) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "delete from curso where id=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            pst.close();
        }
    }

    public int UltimoCursoAdicionado() throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select max(id) as id from curso";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            stmt.close();
            return id;
        }
    }

}
