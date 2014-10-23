/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navegador;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;

/**
 *
 * @author MARCOS
 */
public class Navegador extends JFrame {

    private JTextField campo_status, campo_entrada;
    private JEditorPane area_saida;
    public String s_inicial = "www.cade.com.br";
    public String s_atual_URL;

    /**
     * @param args the command line arguments
     */
    public Navegador() {

        super("Titanic");

// Criar area_saida 
        area_saida = new JEditorPane();
        area_saida.setEditable(false);

// Tratar evento do Hyperlink (da Area de saida): 
        area_saida.addHyperlinkListener(
                new HyperlinkListener() {

                    public void hyperlinkUpdate(HyperlinkEvent evento) {
                        if (evento.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            obterPagina(evento.getURL().toString());
                        }
                    }
                } // fim da classe anonima 
        ); // fim de addHyperlinkListener 

// Criar Container Principal 
        Container cont_principal = getContentPane();
        cont_principal.add(new JScrollPane(area_saida), BorderLayout.CENTER);

// Criar barra de localizar 
        campo_entrada = new JTextField("Digite_aqui_o_endereço");
        cont_principal.add(campo_entrada, BorderLayout.NORTH);

// Tratar evento da barra de localizar: 
        campo_entrada.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent evento) {
                        obterPagina(evento.getActionCommand());
                    }
                } // fim da classe anonima 
        ); // fim de addActionListener 

// Criar barra de status 
        campo_status = new JTextField("Status");
        campo_status.setLayout(new BorderLayout());
        campo_status.setEditable(false);
        cont_principal.add(campo_status, BorderLayout.SOUTH);

        setSize(700, 500);
        setVisible(true);

    }	// fim do construtor 
private void obterPagina( String local ) 
{ 
s_atual_URL = local; 

campo_status.setText("Aguarde"); 

//Facilitar a introdução da URL 
if ( local.indexOf("http://") == -1 ) 
local = "http://" + local; 

// Abrir pagina em area_saida, e ,Atualizar a barra de endereço 
try { 
area_saida.setPage( local ); 
campo_entrada.setText( local ); 
campo_status.setText("Concluido"); 
} 

// Tratar erros vindos do Try 
catch ( IOException ioException ) { 

area_saida.setText("Houve um erro na localização da URL"); 

campo_status.setText("Erro no endereço da página"); 
} 

} // fim do metodo obterPagina 
    public static void main(String[] args) {
        // TODO code application logic here
        Navegador application = new Navegador(); 

application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

}
