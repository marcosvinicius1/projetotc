/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.conexao;



import br.com.sigha.Beans.ConfigxmlBeans;
import br.com.sigha.Config.Configxml;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vinicius
 */
public class ConexaoBanco {
    public Connection getConnect() throws SQLException{
        ConfigxmlBeans cxb;
        cxb=new Configxml().ConexaoBanco();
        return DriverManager.getConnection(cxb.getLocal(),cxb.getUsuario(),cxb.getSenha());
    }
}
