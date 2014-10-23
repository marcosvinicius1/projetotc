/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.Config;

import br.com.sigha.Beans.ConfigBeans;
import br.com.sigha.view.ViewLogin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**8
 *
 * @author MarcosVinicius
 */
public class Config {
    //retorna objeto do configura
    public ConfigBeans getConfig() throws IOException{
       Properties prop= getProp();
       ConfigBeans cxb = new ConfigBeans();               
       cxb.setLocal(prop.getProperty("bancolocal"));//pega o valor da teg local
       cxb.setSenha(prop.getProperty("senha"));//pega o valor da tag senha
       cxb.setUsuario(prop.getProperty("usuario"));//pega o valor da tag usuario
       cxb.setEmpresa(Integer.valueOf(prop.getProperty("empresa")));
       cxb.setTema(Integer.valueOf(prop.getProperty("tema")));       
       return cxb;
    }
   //getor os valores do arquivo de configuração    
   public Properties getProp() throws IOException{
        Properties prop= new Properties();
        FileInputStream file = new FileInputStream("./config/config.properties");
        prop.load(file);
        return prop;
        
    }
  //cria arquivo de configuração
  public void createConfig(String banco,String usuario,String senha,String tema,String empresa) throws IOException {        
            File file = new File("./config");
            file.mkdir();
            file=new File("./config/config.properties");            
            file.createNewFile();
            FileInputStream finput=new FileInputStream("./config/config.properties");
            Properties prop=new Properties();
            prop.load(finput);
            prop.put("senha", senha);
            prop.put("bancolocal", banco);
            prop.put("usuario", usuario);
            prop.put("tema", tema);
            prop.put("empresa", empresa);            
            prop.store(new FileOutputStream("./config/config.properties"), null);        
    }  
}
