/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.Config;

import br.com.sigha.Beans.ConfigxmlBeans;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**8
 *
 * @author MarcosVinicius
 */
public class Configxml {
    public ConfigxmlBeans ConexaoBanco(){
    File f = new File("./Configuracao/config.xml");//busca o local do arquivo
        SAXBuilder builder = new SAXBuilder();
       ConfigxmlBeans cxb = new ConfigxmlBeans();
        try {
            Document doc = builder.build(f);
            Element root = (Element) doc.getRootElement();
            List pessoas = root.getChildren();
            Iterator i = pessoas.iterator();
            while (i.hasNext()) {//percorre o xml em quanto tiver informaçao
                Element banco = (Element) i.next();                
                cxb.setLocal(banco.getAttributeValue("local"));//pega o valor da teg local
                cxb.setSenha(banco.getChildText("senha"));//pega o valor da tag senha
                cxb.setUsuario(banco.getChildText("usuario"));//pega o valor da tag usuario
            }
        } catch (JDOMException | IOException ex) {
            JOptionPane.showMessageDialog(null,"Erro no Arquivo\n"+ex);
        }
        return cxb;
    }
    
    public int Idunidade(){
        File f = new File("./Configuracao/config.xml");//busca o local do arquivo
        SAXBuilder builder = new SAXBuilder();
       int idunidade=0;
        try {
            Document doc = builder.build(f);
            Element root = (Element) doc.getRootElement();
            List pessoas = root.getChildren();
            Iterator i = pessoas.iterator();
            while (i.hasNext()) {//percorre o xml em quanto tiver informaçao
                Element unidade = (Element) i.next();                                
               idunidade=Integer.valueOf(unidade.getChildText("id"));//pega o valor da tag id                
            }
        } catch (JDOMException | IOException ex) {
            JOptionPane.showMessageDialog(null,"Erro no Arquivo\n"+ex);
        }
        return idunidade;
    }
    
}
