/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.dao;

import br.com.sigha.beans.ProfessorBeans;
import br.com.sigha.util.LogsTxt;
import br.com.sigha.conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Vinicius
 */
public class ProfessorDao {

   // private Connection conexao;
    public ProfessorDao() throws SQLException {
        //    conexao = new ConexaoBanco().getConnect();
    }

    public int cadastrar(ProfessorBeans pf) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            int idprofessor = 0;
            String sql = "insert into professor(idunidade,nome,ativo,datacadastro,tipo,email,cpf,"
                    + "datanascimento,estadocivil,registro,telfixo,telcel,rua,"
                    + "numero,bairro,idcidade) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, pf.getIdunidade());
            pstm.setString(2, pf.getNome());
            pstm.setString(3, pf.getAtivo());
            pstm.setString(4, new SimpleDateFormat("yyyy.MM.dd").format(pf.getDatacadastro()));
            pstm.setString(5, pf.getTipo());
            pstm.setString(6, pf.getEmail());
            pstm.setString(7, pf.getCpf());
            pstm.setString(8, new SimpleDateFormat("yyyy.MM.dd").format(pf.getDatanascimento()));
            pstm.setString(9, pf.getEstadocivil());
            pstm.setString(10, pf.getRegistro());
            pstm.setString(11, pf.getTelfixo());
            pstm.setString(12, pf.getTelcel());
            pstm.setString(13, pf.getRua());
            pstm.setString(14, pf.getNumero());
            pstm.setString(15, pf.getBairro());
            pstm.setInt(16, pf.getIdcidade());
            pstm.execute();
            ResultSet rs = pstm.getGeneratedKeys();
            while (rs.next()) {
                idprofessor = rs.getInt(1);
            }
            rs.close();
            pstm.close();
            return idprofessor;
        }
    }
//metodo utilizado na view cursos

    public List<ProfessorBeans> ListaProfessorAtivo(Integer idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select * from professor where ativo='true' and idunidade=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idunidade);
            ResultSet rs = pst.executeQuery();
            List<ProfessorBeans> lpb = new ArrayList<ProfessorBeans>();
            while (rs.next()) {
                ProfessorBeans pb = new ProfessorBeans();
                pb.setId(rs.getInt("id"));
                pb.setIdunidade(rs.getInt("idunidade"));
                pb.setNome(rs.getString("nome"));
                pb.setAtivo(rs.getString("ativo"));

                lpb.add(pb);
            }
            rs.close();
            pst.close();
            return lpb;
        }
    }

    public void Alterar(ProfessorBeans pb) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "update professor set idunidade=?,nome=?,ativo=?,datacadastro=?,tipo=?,email=?,cpf=?,"
                    + "datanascimento=?,estadocivil=?,registro=?,telfixo=?,telcel=?,rua=?,"
                    + "numero=?,bairro=?,idcidade=? where id=?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, pb.getIdunidade());
            pstm.setString(2, pb.getNome());
            pstm.setString(3, pb.getAtivo());
            pstm.setString(4, new SimpleDateFormat("yyyy.MM.dd").format(pb.getDatacadastro()));
            pstm.setString(5, pb.getTipo());
            pstm.setString(6, pb.getEmail());
            pstm.setString(7, pb.getCpf());
            pstm.setString(8, new SimpleDateFormat("yyyy.MM.dd").format(pb.getDatanascimento()));
            pstm.setString(9, pb.getEstadocivil());
            pstm.setString(10, pb.getRegistro());
            pstm.setString(11, pb.getTelfixo());
            pstm.setString(12, pb.getTelcel());
            pstm.setString(13, pb.getRua());
            pstm.setString(14, pb.getNumero());
            pstm.setString(15, pb.getBairro());
            pstm.setInt(16, pb.getIdcidade());
            pstm.setInt(17, pb.getId());
            pstm.execute();
            pstm.close();
        }
    }

    public List<ProfessorBeans> ListaProfessor(Integer idunidade, String campo, String valorcampo) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select professor.id,professor.nome,ativo,idunidade,datacadastro,tipo,email,cpf,datanascimento," +
"estadocivil,registro,telfixo,telcel,rua,numero,bairro,idcidade,cidade.nome cidade,estado.uf estado" +
" from professor left join cidade on (professor.idcidade=cidade.id)" +
" left join estado on (estado.id=cidade.estado) where professor."+campo+" like? and idunidade=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + valorcampo + "%");
            pst.setInt(2, idunidade);
            new LogsTxt().setTxt(new java.util.Date()+"Sql Execultada"+pst.toString());
            ResultSet rs = pst.executeQuery();
            List<ProfessorBeans> lpb = new ArrayList<ProfessorBeans>();
            while (rs.next()) {
                ProfessorBeans pb = new ProfessorBeans();
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
                pb.setIdcidade(rs.getInt("idcidade"));
                lpb.add(pb);
            }
            rs.close();
            pst.close();
            return lpb;
        }
    }

    public void ExcluirProfessor(int idprofessor, String idprofessormateria) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            Statement pst = conexao.createStatement();
            pst.addBatch("DELETE FROM horarioprofessor WHERE idprofessormateria in(" + idprofessormateria + ")");
            pst.addBatch("DELETE FROM professormateria WHERE idprofessor=" + idprofessor);
            pst.addBatch("DELETE FROM professor WHERE ID=" + idprofessor);
            pst.executeBatch();
            pst.close();
        }
    }
}
