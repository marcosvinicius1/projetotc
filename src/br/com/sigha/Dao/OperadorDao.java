/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.Dao;

import br.com.sigha.Beans.OperadorBeans;
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
public class OperadorDao {

   // private Connection conexao;
    public OperadorDao() throws Exception {
        //this.conexao = new ConexaoBanco().getConnect();
    }

    //cadastra operadores
    public void CadastraOperadores(OperadorBeans op) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "insert into gerador.operador(ativo,nome,senha,senhaadm,tipo,usuario,idunidade)values(?,?,?,?,?,?,?)";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, op.getAtivo());
            pst.setString(2, op.getNome());
            pst.setString(3, op.getSenha());
            pst.setString(4, op.getSenhaadm());
            pst.setString(5, op.getTipo());
            pst.setString(6, op.getUsuario());
            pst.setInt(7, op.getIdunidade());

            pst.execute();
            pst.close();
        }
    }

    //busca todos operadores cadastrados
//    public List<OperadorBeans> BuscaOperadores() throws SQLException {
//        String sql = "select * from gerador.operador";
//        PreparedStatement pst=this.conexao.prepareStatement(sql);
//        ResultSet result=pst.executeQuery();
//        List<OperadorBeans> lop = new ArrayList<OperadorBeans>();
//        while (result.next()) {
//            OperadorBeans op = new OperadorBeans();
//            op.setId(result.getInt("id"));
//            op.setAtivo(result.getString("ativo"));
//            op.setNome(result.getString("nome"));
//            op.setSenha(result.getString("senha"));
//            op.setSenhaadm(result.getString("senhaadm"));
//            op.setTipo(result.getString("tipo"));
//            op.setUsuario(result.getString("usuario"));
//            lop.add(op);
//            
//        }
//        result.close();
//        pst.close();
//        return lop;
//    }
    public void ExcluirOperador(int id) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "DELETE FROM gerador.operador WHERE id=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            pst.close();
        }
    }

    public void AlterarOperador(OperadorBeans op) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "update gerador.operador SET ativo=?,nome=?,senha=?,senhaadm=?,tipo=?,usuario=?,idunidade=? where id=?";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, op.getAtivo());
            pst.setString(2, op.getNome());
            pst.setString(3, op.getSenha());
            pst.setString(4, op.getSenhaadm());
            pst.setString(5, op.getTipo());
            pst.setString(6, op.getUsuario());
            pst.setInt(7, op.getIdunidade());
            pst.setInt(8, op.getId());
            pst.execute();
            pst.close();
        }
    }

    public List<OperadorBeans> BuscaOperadores(int idunidade) throws SQLException {
        try (Connection conexao = new ConexaoBanco().getConnect()) {
            String sql = "select * from gerador.operador where idunidade=? and ativo='true'";
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, idunidade);
            ResultSet result = pst.executeQuery();
            List<OperadorBeans> lop = new ArrayList<OperadorBeans>();
            while (result.next()) {
                OperadorBeans op = new OperadorBeans();
                op.setId(result.getInt("id"));
                op.setAtivo(result.getString("ativo"));
                op.setNome(result.getString("nome"));
                op.setSenha(result.getString("senha"));
                op.setSenhaadm(result.getString("senhaadm"));
                op.setTipo(result.getString("tipo"));
                op.setUsuario(result.getString("usuario"));
                lop.add(op);

            }
            result.close();
            pst.close();
            return lop;
        }
    }
}
