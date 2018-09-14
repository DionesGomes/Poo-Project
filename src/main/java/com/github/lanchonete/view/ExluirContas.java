/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.github.lanchonete.view;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.java.com.github.Lanchonete.model.Usuario;
import main.java.com.github.lanchonete.controller.CadastroUsuarioArquivo;
import main.java.com.github.lanchonete.model.Table;

/**
 *
 * @author Diones Gomes
 */
public class ExluirContas extends javax.swing.JFrame {

    /**
     * Creates new form ExluirContas
     */
    public CadastroUsuarioArquivo cad = new CadastroUsuarioArquivo();
    public Table tabela = new Table();

    public ExluirContas() {
        initComponents();
        incializarTabela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listarUsuario = new javax.swing.JTable();
        ExcluirConta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Excluir contas de Usuários");
        setResizable(false);

        listarUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Email", "Telefone", "Data de Dascimento", "Setor", "Senha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(listarUsuario);

        ExcluirConta.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ExcluirConta.setText("Exluir Conta");
        ExcluirConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcluirContaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(269, Short.MAX_VALUE)
                .addComponent(ExcluirConta, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ExcluirConta, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ExcluirContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcluirContaActionPerformed
        deletarUsuario();
        incializarTabela();

    }//GEN-LAST:event_ExcluirContaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExcluirConta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listarUsuario;
    // End of variables declaration//GEN-END:variables

    private void incializarTabela() {

        try {
            List<Usuario> lista = null;

            lista = cad.listar();

            tabela.addList(lista);
            listarUsuario.setModel(tabela);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão com arquivo",
                    "Mensagem Erro", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void deletarUsuario() {

        int linha = listarUsuario.getSelectedRow();

        if (linha >= 0) {

            try {

                Object email = listarUsuario.getValueAt(linha, 1);
                String delEmail = email.toString();

                cad.deletar(delEmail);
                incializarTabela();

            } catch (IOException ex) {
                Logger.getLogger(ExluirContas.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ExluirContas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}