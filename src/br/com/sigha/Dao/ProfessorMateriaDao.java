/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Dao;

import br.com.sigha.Beans.ProfessorBeans;
import br.com.sigha.Beans.ProfessorMateriaBeans;
import br.com.sigha.Util.LogsTxt;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author MarcosVinicius
 */
public class ProfessorMateriaDao {

   // private Connection conexao;
    public ProfessorMateriaDao() throws SQLException {
        //   conexao = new ConexaoBanco().getConnect();
    }

    public int AdicionaMateria(ProfessorMateriaBeans pmb) throws SQLException  {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            int idmateriaprofessor = 0;
            String sql = "insert into professormateria (idprofessor,idmateria,ativo)value(?,?,?)";
            PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, pmb.getIdprofessor());
            pstm.setInt(2, pmb.getIdmateria());
            pstm.setString(3, pmb.getAtivo());
            pstm.execute();
            ResultSet rs = pstm.getGeneratedKeys();
            while (rs.next()) {
                idmateriaprofessor = rs.getInt(1);
            }
            rs.close();
            pstm.close();
            return idmateriaprofessor;
        }
    }

    public List<ProfessorMateriaBeans> BuscaMateria(int idprofessor) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT professormateria.*,materia.nome as nomemateria,curso.nome as nomecurso,curso.id as idcurso\n"
                    + "FROM professormateria,materia,curso \n"
                    + "where professormateria.idmateria=materia.id and materia.idcurso=curso.id and professormateria.idprofessor=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, idprofessor);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
            ResultSet rs = pstm.executeQuery();
            List<ProfessorMateriaBeans> lprofessormateria = new ArrayList<ProfessorMateriaBeans>();
            while (rs.next()) {
                ProfessorMateriaBeans pfb = new ProfessorMateriaBeans();
                pfb.setId(rs.getInt("id"));
                pfb.setIdmateria(rs.getInt("idmateria"));
                pfb.setIdprofessor(rs.getInt("idprofessor"));
                pfb.setNomecurso(rs.getString("nomecurso"));
                pfb.setNomemateria(rs.getString("nomemateria"));
                pfb.setIdcurso(rs.getInt("idcurso"));
                pfb.setAtivo(rs.getString("ativo"));
                lprofessormateria.add(pfb);
            }
            pstm.close();
            rs.close();
            return lprofessormateria;
        }
    }

    //METODO BUSCA PROFESSOR DE ACORDO COM A MATERIA, utilizado na geracao de materia
    public List<ProfessorMateriaBeans> BuscaProfessorMateria(int idmateria, int idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT professormateria.* from professormateria "
                    + "left join professor on(professormateria.idprofessor=professor.id) "
                    + "where idmateria=? and professor.idunidade=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, idmateria);
            pstm.setInt(2, idunidade);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
            ResultSet rs = pstm.executeQuery();
            List<ProfessorMateriaBeans> lprofessormateria = new ArrayList<ProfessorMateriaBeans>();
            while (rs.next()) {
                ProfessorMateriaBeans pfb = new ProfessorMateriaBeans();
                pfb.setId(rs.getInt("id"));
                pfb.setIdmateria(rs.getInt("idmateria"));
                pfb.setIdprofessor(rs.getInt("idprofessor"));
                lprofessormateria.add(pfb);
            }
            pstm.close();
            rs.close();
            return lprofessormateria;
        }
    }

    //metodo utilizado na classe guloso
    public ProfessorBeans BuscaProfessorAtivo(int idmateria, int idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT professor.* from professormateria "
                    + "left join professor on(professormateria.idprofessor=professor.id) "
                    + "where idmateria=? and professor.idunidade=? and ativo='true'";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, idmateria);
            pstm.setInt(2, idunidade);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
            ResultSet rs = pstm.executeQuery();
            ProfessorBeans pb = new ProfessorBeans();
            while (rs.next()) {
                pb.setId(rs.getInt("id"));
                pb.setIdunidade(rs.getInt("idunidade"));
                pb.setNome(rs.getString("nome"));
                pb.setAtivo(rs.getString("ativo"));
                pb.setDatacadastro(rs.getDate("datacadastro"));
                pb.setTipo(rs.getString("tipo"));
                pb.setEmail(rs.getString("email"));
                pb.setCpf(rs.getString("cpf"));
                pb.setDatanascimento(rs.getDate("datanascimento"));
                pb.setEstadocivil(rs.getString("estadocivil"));
                pb.setRegistro(rs.getString("registro"));
                pb.setTelfixo(rs.getString("telfixo"));
                pb.setTelcel(rs.getString("telcel"));
                pb.setRua(rs.getString("rua"));
                pb.setNumero(rs.getString("numero"));
                pb.setBairro(rs.getString("bairro"));
                pb.setCidade(rs.getString("cidade"));
                pb.setEstado(rs.getString("estado"));
            }
            pstm.close();
            rs.close();
            return pb;
        }
    }

    //metodo utilizado na geração de horario
    public ProfessorMateriaBeans BuscaProfessorMateriaAtivo(int idmateria, int idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "SELECT professormateria.* from professormateria "
                    + "left join professor on(professormateria.idprofessor=professor.id) "
                    + "where idmateria=? and professor.idunidade=? and professor.ativo='true' and professormateria.ativo='true'";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, idmateria);
            pstm.setInt(2, idunidade);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pstm.toString());
            ResultSet rs = pstm.executeQuery();
            ProfessorMateriaBeans pfb = new ProfessorMateriaBeans();
            while (rs.next()) {
                pfb.setId(rs.getInt("id"));
                pfb.setIdmateria(rs.getInt("idmateria"));
                pfb.setIdprofessor(rs.getInt("idprofessor"));
            }            
            rs.close();
            pstm.close();
            return pfb;
        }
    }

    public void DeletaMateria(String id, int idprofessor) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "delete from professormateria where idprofessor=? and id not in(" + id + ")";
            
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, idprofessor);
            pstm.execute();
            pstm.close();
        }
    }

    public void AlteraStatusMateria(int idmateria, int idprofessor, String ativo) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "update professormateria set ativo=? where idprofessor=? and idmateria=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, ativo);
            pstm.setInt(2, idprofessor);
            pstm.setInt(3, idmateria);
            pstm.execute();
            pstm.close();
        }
    }

    public int buscaProfessorQtdeOcorr(int idcurso, int periodo) throws SQLException {
        int res=0;
        try(Connection conexao=new ConexaoBanco().getConnect()){
            String sql="SELECT periodo,sum(qtdeaulasem) as qtde FROM gerador.professormateria pm left join materia m on(pm.idmateria=m.id) "
                    +"left join professor p on(p.id=pm.idprofessor) "
                    + "where pm.ativo='true' and m.ativo='true' and p.ativo='true' and m.idcurso=? and m.periodo=? group by 1;";
            PreparedStatement pst=conexao.prepareCall(sql);
            pst.setInt(1, idcurso);
            pst.setInt(2, periodo);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                res=rs.getInt("qtde");
            }
            rs.close();
            pst.close();
        }
        return res;
    }
    
    public int buscaProfessorQtdeOcorrHorario(int idprofessormateria) throws SQLException {
        int res=0;
        try(Connection conexao=new ConexaoBanco().getConnect()){
            String sql="select m.qtdeaulasem from professormateria pm left "
                    + "join professor p on(pm.idprofessor=p.id) left join materia m on(m.id=pm.idmateria) where pm.id=?";
            PreparedStatement pst=conexao.prepareStatement(sql);
            pst.setInt(1, idprofessormateria);
            new LogsTxt().setTxt(new Date()+"Sql Execultada"+pst.toString());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                res=rs.getInt("qtdeaulasem");
            }
            rs.close();
            pst.close();
        }
        return res;
    }

}
