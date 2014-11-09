/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sigha.conexao;



import br.com.sigha.beans.ConfigBeans;
import br.com.sigha.config.Config;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class ConexaoBanco {
    public Connection getConnect() throws SQLException{
        ConfigBeans cxb;
        Connection conexao=null;
        try {
            cxb=new Config().getConfig();
            conexao=DriverManager.getConnection(cxb.getLocal(),cxb.getUsuario(),cxb.getSenha());
        } catch (IOException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }
}
