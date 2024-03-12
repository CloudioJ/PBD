/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package academiagui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author claud
 */
public class SearchWindow extends javax.swing.JFrame {

    /**
     * Creates new form SearchWindow
     */
    
    public SQLConnection con = new SQLConnection();
    
    public SearchWindow() {
        initComponents();
        query_text.setEditable(false);
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
        query_text = new javax.swing.JTextArea();
        show_clients = new javax.swing.JButton();
        show_emp = new javax.swing.JButton();
        show_plans = new javax.swing.JButton();
        return_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        query_text.setColumns(20);
        query_text.setRows(5);
        jScrollPane1.setViewportView(query_text);

        show_clients.setText("Mostrar clientes");
        show_clients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_clientsActionPerformed(evt);
            }
        });

        show_emp.setText("Mostrar atendimentos");
        show_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_empActionPerformed(evt);
            }
        });

        show_plans.setText("Mostrar Planos");
        show_plans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_plansActionPerformed(evt);
            }
        });

        return_button.setText("Retornar");
        return_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                return_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(show_emp)
                        .addGap(18, 18, 18)
                        .addComponent(show_plans)
                        .addGap(18, 18, 18)
                        .addComponent(show_clients, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(return_button)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(show_emp)
                    .addComponent(show_plans)
                    .addComponent(return_button)
                    .addComponent(show_clients))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void show_clientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_clientsActionPerformed
        try {
            // TODO add your handling code here:
            showClienteAction();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SearchWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_show_clientsActionPerformed

    private void show_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_empActionPerformed
        showAtendimentos();
    }//GEN-LAST:event_show_empActionPerformed

    private void show_plansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_plansActionPerformed
        showClienteCondensed();        // TODO add your handling code here:
    }//GEN-LAST:event_show_plansActionPerformed

    private void return_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_return_buttonActionPerformed
        MainMenu mainMenu = new MainMenu();
        mainMenu.setLocationRelativeTo(this);
        mainMenu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_return_buttonActionPerformed

    private void showAtendimentos(){
        query_text.setEditable(true);
//        String columns = "ID\tInstrutor\tNutricionista\tNome\tPeso\tAltura\tData de Nascimento\tEmail\tTelefone\tPlano\n";
//        String info = columns;
        String info = con.showAtendimentos();
//            System.out.println("");
        query_text.setText(info);
        query_text.setEditable(false);
    }
    private void showClienteAction()throws SQLException, ClassNotFoundException{
        query_text.setEditable(true);
        String columns = "ID\t\tInstrutor\t\tNutricionista\t\tPlano\t\tNome\t\tPeso\t\tAltura\t\tData de Nascimento\t\tEmail\t\tTelefone\n";
        String info = columns;
        try {
            ArrayList<Cliente> clients = con.showClientes();
//            System.out.println("");
            for (var client: clients){
                System.out.println(client);
                info += String.valueOf(client.id) + "\t\t"
                        + client.instrutor + "\t\t"
                        + client.nutri + "\t\t"
                        + client.plan + "\t\t"
                        + client.name + "\t\t"
                        + String.valueOf(client.weight) + "\t\t"
                        + String.valueOf(client.height) + "\t\t"
                        + client.date + "\t\t"
                        + client.email + "\t\t"
                        + client.phone + "\n";
//                
            }
            query_text.setText(info);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SearchWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        query_text.setEditable(false);
    }
    
    private void showClienteCondensed(){
        query_text.setEditable(true);
        query_text.setText("Ola mundo");
//        String columns = "ID\tInstrutor\tNutricionista\tNome\tPeso\tAltura\tData de Nascimento\tEmail\tTelefone\tPlano\n";
//        String info = columns;
        String info = con.showClientesCondensed();
//            System.out.println("");
        query_text.setText(info);
        query_text.setEditable(false);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea query_text;
    private javax.swing.JButton return_button;
    private javax.swing.JButton show_clients;
    private javax.swing.JButton show_emp;
    private javax.swing.JButton show_plans;
    // End of variables declaration//GEN-END:variables
}
