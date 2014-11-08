/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sigha.view;
import br.com.sigha.Beans.OperadorBeans;
import br.com.sigha.Beans.UnidadeLogadoBeans;
import br.com.sigha.Dao.OperadorDao;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author MarcosVinicius
 */
public class ViewPCadOperador extends javax.swing.JPanel {

    /**
     * Creates new form ViewPCadOperador
     */
     List<OperadorBeans> listaoperadores;
    public ViewPCadOperador() {
        initComponents();
        Limpa();
        DesativaTela();
        ListaOperador();
        OcultaCampos();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTnome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTusuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPsenha = new javax.swing.JPasswordField();
        jPanel9 = new javax.swing.JPanel();
        jBnovo = new javax.swing.JButton();
        jBsalvar = new javax.swing.JButton();
        jBalterar = new javax.swing.JButton();
        jBexcluir = new javax.swing.JButton();
        jBcancelar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jToperador = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jRativo = new javax.swing.JRadioButton();
        jCtipo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPsenhaadm = new javax.swing.JPasswordField();
        jTid = new javax.swing.JTextField();

        jTnome.setToolTipText("Nome do Usuário");
        jTnome.setEnabled(false);
        jTnome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTnomeKeyReleased(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Usuario");

        jTusuario.setToolTipText("Usuário Usado para Login");
        jTusuario.setEnabled(false);
        jTusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTusuarioKeyReleased(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("Senha");

        jPsenha.setToolTipText("Senha Usado para Login");
        jPsenha.setEnabled(false);

        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jBnovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/NOVO32X32.png"))); // NOI18N
        jBnovo.setText("NOVO");
        jBnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBnovoActionPerformed(evt);
            }
        });

        jBsalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/SALVAR32X32.png"))); // NOI18N
        jBsalvar.setText("SALVAR");
        jBsalvar.setEnabled(false);
        jBsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBsalvarActionPerformed(evt);
            }
        });

        jBalterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/EDITAR32X32.png"))); // NOI18N
        jBalterar.setText("EDITAR");
        jBalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBalterarActionPerformed(evt);
            }
        });

        jBexcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/EXCLUIR32X32.png"))); // NOI18N
        jBexcluir.setText("EXCLUIR");
        jBexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBexcluirActionPerformed(evt);
            }
        });

        jBcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/CANCELAR.png"))); // NOI18N
        jBcancelar.setText("CANCELAR");
        jBcancelar.setEnabled(false);
        jBcancelar.setMaximumSize(new java.awt.Dimension(121, 32));
        jBcancelar.setMinimumSize(new java.awt.Dimension(121, 32));
        jBcancelar.setPreferredSize(new java.awt.Dimension(121, 32));
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBnovo)
                .addGap(8, 8, 8)
                .addComponent(jBsalvar)
                .addGap(8, 8, 8)
                .addComponent(jBcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBalterar)
                .addGap(9, 9, 9)
                .addComponent(jBexcluir)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel9Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBalterar, jBcancelar, jBexcluir, jBnovo, jBsalvar});

        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBsalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBalterar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBexcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToperador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "USUARIO", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jToperador.setDragEnabled(true);
        jToperador.getTableHeader().setReorderingAllowed(false);
        jToperador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToperadorMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jToperador);

        jLabel5.setText("Tipo");

        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Nome ");

        jRativo.setSelected(true);
        jRativo.setText("Ativo");
        jRativo.setEnabled(false);

        jCtipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "USUARIO", "SUPERVISSOR" }));
        jCtipo.setToolTipText("Tipo de Usuário");
        jCtipo.setEnabled(false);

        jLabel6.setText("Senha Adm.");

        jPsenhaadm.setToolTipText("Senha Administrativa");
        jPsenhaadm.setEnabled(false);

        jTid.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTnome, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jRativo))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jTusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jPsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jCtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPsenhaadm, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addComponent(jLabel1)
                            .addComponent(jTid, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jTid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRativo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPsenhaadm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTnomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTnomeKeyReleased
        // TODO add your handling code here:
        jTnome.setText(jTnome.getText().toUpperCase());
    }//GEN-LAST:event_jTnomeKeyReleased

    private void jTusuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTusuarioKeyReleased
        // TODO add your handling code here:
        jTusuario.setText(jTusuario.getText().toUpperCase());
    }//GEN-LAST:event_jTusuarioKeyReleased

    private void jBnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBnovoActionPerformed
        // TODO add your handling code here:
        Limpa();
        AtivaTela();
        AtivaSalvarCancelar();
    }//GEN-LAST:event_jBnovoActionPerformed

    private void jBsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBsalvarActionPerformed
        // TODO add your handling code here:
        if ("".equals(jTid.getText())) {
            if (!"".equals(jTnome.getText()) && !"".equals(jTusuario.getText()) && !"".equals(jPsenha.getText())) {
                CadastraOperador();
                Limpa();
                DesativaTela();
                AtivaNovoAlterarExcluir();
                ListaOperador();
            } else {
                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatorios", "Cadastro de Operadores", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            AlterarOperador();
            Limpa();
            DesativaTela();
            AtivaNovoAlterarExcluir();
            ListaOperador();
        }
    }//GEN-LAST:event_jBsalvarActionPerformed

    private void jBalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBalterarActionPerformed
        // TODO add your handling code here:
        AtivaTela();
        AtivaSalvarCancelar();
    }//GEN-LAST:event_jBalterarActionPerformed

    private void jBexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBexcluirActionPerformed
        // TODO add your handling code here:
        if (!"".equals(jTnome.getText())) {
            ExcluirOperador();
            Limpa();
            AtivaNovoAlterarExcluir();
            ListaOperador();
        } else {
            JOptionPane.showMessageDialog(null, "Não a Item Selecionado Para Excluir", "Cadastro de Operador", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jBexcluirActionPerformed

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        // TODO add your handling code here:
        Limpa();
        DesativaTela();
        AtivaNovoAlterarExcluir();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jToperadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToperadorMouseClicked
        // TODO add your handling code here:
        for (int i = 0; i < listaoperadores.size(); i++) {
            if (i == jToperador.getSelectedRow()) {
                jTnome.setText(listaoperadores.get(i).getNome());
                jTusuario.setText(listaoperadores.get(i).getUsuario());
                jPsenha.setText(listaoperadores.get(i).getSenha());
                jPsenhaadm.setText(listaoperadores.get(i).getSenhaadm());
                jCtipo.setSelectedItem(listaoperadores.get(i).getTipo());
                jRativo.setSelected(Boolean.valueOf(listaoperadores.get(i).getAtivo()));
                jTid.setText(String.valueOf(listaoperadores.get(i).getId()));
            }
        }
    }//GEN-LAST:event_jToperadorMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBalterar;
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBexcluir;
    private javax.swing.JButton jBnovo;
    private javax.swing.JButton jBsalvar;
    private javax.swing.JComboBox jCtipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPsenha;
    private javax.swing.JPasswordField jPsenhaadm;
    private javax.swing.JRadioButton jRativo;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTid;
    private javax.swing.JTextField jTnome;
    private javax.swing.JTable jToperador;
    private javax.swing.JTextField jTusuario;
    // End of variables declaration//GEN-END:variables
//limpa tela
    private void Limpa() {
        jTnome.setText(null);
        jTusuario.setText(null);
        jRativo.setSelected(true);
        jPsenha.setText(null);
        jPsenhaadm.setText(null);
        jCtipo.setSelectedIndex(0);
        jTid.setText("");
    }

    //ativa tela
    public void AtivaTela() {
        jTnome.setEnabled(true);
        jTusuario.setEnabled(true);
        jRativo.setEnabled(true);
        jPsenha.setEnabled(true);
        jPsenhaadm.setEnabled(true);
        jCtipo.setEnabled(true);

    }

    //desativa tela
    private void DesativaTela() {
        jTnome.setEnabled(false);
        jTusuario.setEnabled(false);
        jRativo.setEnabled(false);
        jPsenha.setEnabled(false);
        jPsenhaadm.setEnabled(false);
        jCtipo.setEnabled(false);

    }

    //ativa botoes
    public void AtivaNovoAlterarExcluir() {
        jBnovo.setEnabled(true);
        jBalterar.setEnabled(true);
        jBcancelar.setEnabled(false);
        jBexcluir.setEnabled(true);
        jBsalvar.setEnabled(false);
        jToperador.setEnabled(true);
    }

    //ativa botoes
    public void AtivaSalvarCancelar() {
        jBnovo.setEnabled(false);
        jBalterar.setEnabled(false);
        jBcancelar.setEnabled(true);
        jBexcluir.setEnabled(false);
        jBsalvar.setEnabled(true);
        jToperador.setEnabled(false);
    }

    //Lista na tabela os operadores
    private void ListaOperador() {
        try {
            OperadorDao opd = new OperadorDao();
            listaoperadores = opd.BuscaOperadores(new UnidadeLogadoBeans().getId());
            DefaultTableModel tboperador = (DefaultTableModel) jToperador.getModel();
            tboperador.setNumRows(0);
            for (int i = 0; i < listaoperadores.size(); i++) {
                tboperador.addRow(new Object[]{listaoperadores.get(i).getNome(), listaoperadores.get(i).getUsuario(), listaoperadores.get(i).getTipo()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro \n" + ex.toString(), "Cadastro Operadores", JOptionPane.WARNING_MESSAGE);

        }

    }
//cadastra operadores

    private void CadastraOperador() {
        OperadorBeans opb = new OperadorBeans();
        opb.setNome(jTnome.getText());
        opb.setSenha(jPsenha.getText());
        opb.setSenhaadm(jPsenhaadm.getText());
        opb.setTipo((String) jCtipo.getSelectedItem());
        opb.setUsuario(jTusuario.getText());
        opb.setAtivo(String.valueOf(jRativo.isSelected()));
        opb.setIdunidade(new UnidadeLogadoBeans().getId());
        try {
            OperadorDao opd = new OperadorDao();
            opd.CadastraOperadores(opb);
            JOptionPane.showMessageDialog(null, "Cadastro Efetuado Com Sucesso", "Cadastro de Operadores", WIDTH);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar \n" + ex.toString(), "Cadastro de Operadores", JOptionPane.ERROR_MESSAGE);
        }

    }
//exclui operador

    private void ExcluirOperador() {
        try {
            new OperadorDao().ExcluirOperador(Integer.valueOf(jTid.getText()));
            JOptionPane.showMessageDialog(null, "Registro Excluido Com Sucesso", "Cadastro de Operador", WIDTH);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir\n" + ex.toString(), "Cadastro de Operador", JOptionPane.WARNING_MESSAGE);
        }
    }

    //altera operador
    private void AlterarOperador() {
        OperadorBeans opb = new OperadorBeans();
        opb.setId(Integer.valueOf(jTid.getText()));
        opb.setNome(jTnome.getText());
        opb.setSenha(jPsenha.getText());
        opb.setSenhaadm(jPsenhaadm.getText());
        opb.setTipo((String) jCtipo.getSelectedItem());
        opb.setUsuario(jTusuario.getText());
        opb.setAtivo(String.valueOf(jRativo.isSelected()));
        opb.setIdunidade(new UnidadeLogadoBeans().getId());
        try {
            OperadorDao opd = new OperadorDao();
            opd.AlterarOperador(opb);
            JOptionPane.showMessageDialog(null, "Cadastro Alterado Com Sucesso", "Cadastro de Operadores", WIDTH);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar \n" + ex.toString(), "Cadastro de Operadores", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void OcultaCampos() {
        jTid.setVisible(false);
    }

}