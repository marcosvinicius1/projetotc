/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.Logica;

import br.com.sigha.Beans.UnidadeLogadoBeans;
import br.com.sigha.conexao.ConexaoBanco;
import br.com.sigha.view.ViewPHorarioAula;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MarcosVinicius
 */
public class ThreadRelatorioHorario implements Runnable{
    
    String anoletivo;
    String datageracao;
    int idunidade;
    ViewPHorarioAula content;
    public ThreadRelatorioHorario(String anoletivo,String datageracao,int idunidade,ViewPHorarioAula content){
        this.anoletivo=anoletivo;
        this.datageracao=datageracao;
        this.idunidade=idunidade;
        this.content=content;
    }
    @Override
    public void run() {
        try {
            Map map = new HashMap();
            map.put("anoletivo", this.anoletivo);
            map.put("datageracao",this.datageracao );
            map.put("idunidade", this.idunidade);

            JasperPrint jasperprint;

            jasperprint = JasperFillManager.fillReport("./Relatorio/HorarioAula.jasper", map, new ConexaoBanco().getConnect()); //chama relatorio

            JasperViewer jasperviewer = new JasperViewer(jasperprint, false);
            content.Aguarde.setVisible(false);
            jasperviewer.setVisible(true);
            jasperviewer.toFront();            
        } catch (JRException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatorio" + ex);
        }
    }
    
}
