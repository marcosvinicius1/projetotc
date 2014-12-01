/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.dao;

import br.com.sigha.beans.MateriaBeans;
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
public class MateriaDao {

   // private Connection conexao;
    public MateriaDao() throws SQLException {
        //   this.conexao = new ConexaoBanco().getConnect();
    }

    public List<MateriaBeans> BuscaMateria(int idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT materia.*,curso.nome as nomecurso FROM gerador.materia"
                    + " left join curso on(materia.idcurso=curso.id) where curso.idunidade=?;";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idunidade);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            List<MateriaBeans> lmb = new ArrayList<MateriaBeans>();
            while (rs.next()) {
                MateriaBeans mb = new MateriaBeans();
                mb.setAtivo(rs.getString("ativo"));
                mb.setChanual(rs.getString("chanual"));
                mb.setChaula(rs.getString("chaula"));
                mb.setDomingo(rs.getString("domingo"));
                mb.setId(rs.getInt("id"));
                mb.setIdcurso(rs.getInt("idcurso"));
                mb.setNome(rs.getString("nome"));
                mb.setNomecurso(rs.getString("nomecurso"));
                mb.setPeriodo(rs.getInt("periodo"));
                mb.setQtdeauladia(rs.getString("qtdeauladia"));
                mb.setQtdeaulasem(rs.getString("qtdeaulasem"));
                mb.setQuarta(rs.getString("quarta"));
                mb.setQuinta(rs.getString("quinta"));
                mb.setSabado(rs.getString("sabado"));
                mb.setSegunda(rs.getString("segunda"));
                mb.setSexta(rs.getString("sexta"));
                mb.setTerca(rs.getString("terca"));
                mb.setSigla(rs.getString("sigla"));
                mb.setSequencial(rs.getString("sequencial"));
                lmb.add(mb);
            }
            rs.close();
            pst.close();
            return lmb;
        }
    }

    //metodo busca materia pela sigla metodo utilizado na geracao de horario
    public List<MateriaBeans> BuscaIdMateria(String sigla, int periodo) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT * FROM gerador.materia where id in(" + sigla + ") and periodo=? and ativo='true'";
            PreparedStatement pst = conexao.prepareStatement(sql);
            // pst.setString(1, sigla);
            pst.setInt(1, periodo);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            List<MateriaBeans> lmb = new ArrayList<MateriaBeans>();
            while (rs.next()) {
                MateriaBeans mb = new MateriaBeans();
                mb.setAtivo(rs.getString("ativo"));
                mb.setChanual(rs.getString("chanual"));
                mb.setChaula(rs.getString("chaula"));
                mb.setDomingo(rs.getString("domingo"));
                mb.setId(rs.getInt("id"));
                mb.setIdcurso(rs.getInt("idcurso"));
                mb.setNome(rs.getString("nome"));
                mb.setPeriodo(rs.getInt("periodo"));
                mb.setQtdeauladia(rs.getString("qtdeauladia"));
                mb.setQtdeaulasem(rs.getString("qtdeaulasem"));
                mb.setQuarta(rs.getString("quarta"));
                mb.setQuinta(rs.getString("quinta"));
                mb.setSabado(rs.getString("sabado"));
                mb.setSegunda(rs.getString("segunda"));
                mb.setSexta(rs.getString("sexta"));
                mb.setTerca(rs.getString("terca"));
                mb.setSigla(rs.getString("sigla"));
                mb.setSequencial(rs.getString("sequencial"));
                lmb.add(mb);
            }
            rs.close();
            pst.close();
            return lmb;
        }
    }
//    busca as materias referente ao id do curso passado metodo sendo utilizado na tela
//    de professores onde preenche um jcombobox    

    public List<MateriaBeans> BuscaMateriaCurso(int idcurso) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT materia.id,materia.nome,materia.idcurso,materia.segunda,"
                    + "materia.terca,materia.quarta,materia.quinta,materia.sexta,materia.sabado,"
                    + "materia.domingo,curso.nome as nomecurso,materia.sigla FROM gerador.materia left join curso on(materia.idcurso=curso.id) where materia.idcurso=? and materia.ativo='true';";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idcurso);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            List<MateriaBeans> lmb = new ArrayList<MateriaBeans>();
            while (rs.next()) {
                MateriaBeans mb = new MateriaBeans();
                mb.setId(rs.getInt("id"));
                mb.setIdcurso(rs.getInt("idcurso"));
                mb.setNome(rs.getString("nome"));
                mb.setSegunda(rs.getString("segunda"));
                mb.setTerca(rs.getString("terca"));
                mb.setQuarta(rs.getString("quarta"));
                mb.setQuinta(rs.getString("quinta"));
                mb.setSexta(rs.getString("sexta"));
                mb.setSabado(rs.getString("sabado"));
                mb.setDomingo(rs.getString("domingo"));
                mb.setNomecurso(rs.getString("nomecurso"));
                mb.setSigla(rs.getString("sigla"));
                lmb.add(mb);
            }
            rs.close();
            pst.close();
            return lmb;
        }
    }

    //METODO UTILIZADO NA TELA VIEWPHORARIOAULA ONDE BUSCA OS HORARIOS PARA SEREM INSERIDOS NA TABELA HORARIOAULA
    public List<MateriaBeans> BuscaMateriaHorarioCurso(int idcurso) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select materia.periodo,materia.idcurso,horariocurso.inicio,horariocurso.termino,horariocurso.segunda,horariocurso.terca,horariocurso.quarta,horariocurso.quinta,horariocurso.sexta,horariocurso.sabado,horariocurso.domingo from materia \n"
                    + "left join horariocurso on (materia.idcurso=horariocurso.idcurso) \n"
                    + "where materia.idcurso=? and materia.ativo='true' group by materia.periodo,horariocurso.inicio,horariocurso.termino";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idcurso);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            List<MateriaBeans> lmb = new ArrayList<MateriaBeans>();
            while (rs.next()) {
                MateriaBeans mb = new MateriaBeans();
                mb.setPeriodo(rs.getInt("periodo"));
                mb.setIdcurso(rs.getInt("idcurso"));
                mb.setInicio(rs.getString("inicio"));
                mb.setTermino(rs.getString("termino"));
                mb.setSegunda(rs.getString("segunda"));
                mb.setTerca(rs.getString("terca"));
                mb.setQuarta(rs.getString("quarta"));
                mb.setQuinta(rs.getString("quinta"));
                mb.setSexta(rs.getString("sexta"));
                mb.setSabado(rs.getString("sabado"));
                mb.setDomingo(rs.getString("domingo"));
                lmb.add(mb);
            }
            rs.close();
            pst.close();
            return lmb;
        }
    }

    //metodo utilizado na view cadastro professor busca materia por id da materia
    public MateriaBeans BuscaMateriaid(int idmateria) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT * FROM gerador.materia  where id=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idmateria);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            MateriaBeans mb = new MateriaBeans();
            while (rs.next()) {
                mb.setAtivo(rs.getString("ativo"));
                mb.setChanual(rs.getString("chanual"));
                mb.setChaula(rs.getString("chaula"));
                mb.setDomingo(rs.getString("domingo"));
                mb.setId(rs.getInt("id"));
                mb.setIdcurso(rs.getInt("idcurso"));
                mb.setNome(rs.getString("nome"));
                mb.setPeriodo(rs.getInt("periodo"));
                mb.setQtdeauladia(rs.getString("qtdeauladia"));
                mb.setQtdeaulasem(rs.getString("qtdeaulasem"));
                mb.setQuarta(rs.getString("quarta"));
                mb.setQuinta(rs.getString("quinta"));
                mb.setSabado(rs.getString("sabado"));
                mb.setSegunda(rs.getString("segunda"));
                mb.setSexta(rs.getString("sexta"));
                mb.setTerca(rs.getString("terca"));
            }
            rs.close();
            pst.close();
            return mb;
        }
    }
    
    public MateriaBeans BuscaMateriaidprof(int idprofmat) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT m.* FROM materia m left join professormateria pm on (m.id=pm.idmateria) where pm.id=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idprofmat);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            MateriaBeans mb = new MateriaBeans();
            while (rs.next()) {
                mb.setAtivo(rs.getString("ativo"));
                mb.setChanual(rs.getString("chanual"));
                mb.setChaula(rs.getString("chaula"));
                mb.setDomingo(rs.getString("domingo"));
                mb.setId(rs.getInt("id"));
                mb.setIdcurso(rs.getInt("idcurso"));
                mb.setNome(rs.getString("nome"));
                mb.setPeriodo(rs.getInt("periodo"));
                mb.setQtdeauladia(rs.getString("qtdeauladia"));
                mb.setQtdeaulasem(rs.getString("qtdeaulasem"));
                mb.setQuarta(rs.getString("quarta"));
                mb.setQuinta(rs.getString("quinta"));
                mb.setSabado(rs.getString("sabado"));
                mb.setSegunda(rs.getString("segunda"));
                mb.setSexta(rs.getString("sexta"));
                mb.setTerca(rs.getString("terca"));
                mb.setSequencial(rs.getString("sequencial"));
            }
            rs.close();
            pst.close();
            return mb;
        }
    }

    //
    public void CadastraMateria(MateriaBeans mb) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "insert into materia(ativo,chanual,chaula,domingo,idcurso,nome,periodo,qtdeauladia,"
                    + "qtdeaulasem,quarta,quinta,sabado,segunda,sexta,terca,sigla,sequencial)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, mb.getAtivo());
            pst.setString(2, mb.getChanual());
            pst.setString(3, mb.getChaula());
            pst.setString(4, mb.getDomingo());
            pst.setInt(5, mb.getIdcurso());
            pst.setString(6, mb.getNome());
            pst.setInt(7, mb.getPeriodo());
            pst.setString(8, mb.getQtdeauladia());
            pst.setString(9, mb.getQtdeaulasem());
            pst.setString(10, mb.getQuarta());
            pst.setString(11, mb.getQuinta());
            pst.setString(12, mb.getSabado());
            pst.setString(13, mb.getSegunda());
            pst.setString(14, mb.getSexta());
            pst.setString(15, mb.getTerca());
            pst.setString(16, mb.getSigla());
            pst.setString(17, mb.getSequencial());
            pst.execute();
            pst.close();
        }
    }

    public void AlteraMateria(MateriaBeans mb) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "update materia set ativo=?,chanual=?,chaula=?,domingo=?,idcurso=?,nome=?,periodo=?,qtdeauladia=?,"
                    + "qtdeaulasem=?,quarta=?,quinta=?,sabado=?,segunda=?,sexta=?,terca=?,sigla=?,sequencial=? where id=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, mb.getAtivo());
            pst.setString(2, mb.getChanual());
            pst.setString(3, mb.getChaula());
            pst.setString(4, mb.getDomingo());
            pst.setInt(5, mb.getIdcurso());
            pst.setString(6, mb.getNome());
            pst.setInt(7, mb.getPeriodo());
            pst.setString(8, mb.getQtdeauladia());
            pst.setString(9, mb.getQtdeaulasem());
            pst.setString(10, mb.getQuarta());
            pst.setString(11, mb.getQuinta());
            pst.setString(12, mb.getSabado());
            pst.setString(13, mb.getSegunda());
            pst.setString(14, mb.getSexta());
            pst.setString(15, mb.getTerca());
            pst.setString(16, mb.getSigla());
            pst.setString(17, mb.getSequencial());
            pst.setInt(18, mb.getId());
            pst.execute();
            pst.close();
        }
    }

    public void ExcluirMateria(int id) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "delete from materia where id=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            pst.close();
        }
    }
}
