/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class MenuAdminBodega extends javax.swing.JFrame {

    /**
     * Creates new form AdministradorBodega
     */
    public MenuAdminBodega() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        j_menu_bar_1_bodeda = new javax.swing.JMenu();
        item_menu_salir = new javax.swing.JMenuItem();
        j_menu_bar_2_bodega = new javax.swing.JMenu();
        item_menu_costo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        j_menu_bar_1_bodeda.setMnemonic('f');
        j_menu_bar_1_bodeda.setText("Menu");

        item_menu_salir.setMnemonic('x');
        item_menu_salir.setText("Salir");
        item_menu_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_menu_salirActionPerformed(evt);
            }
        });
        j_menu_bar_1_bodeda.add(item_menu_salir);

        menuBar.add(j_menu_bar_1_bodeda);

        j_menu_bar_2_bodega.setMnemonic('e');
        j_menu_bar_2_bodega.setText("Articulos");

        item_menu_costo.setMnemonic('t');
        item_menu_costo.setText("Ingresar costo");
        item_menu_costo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_menu_costoActionPerformed(evt);
            }
        });
        j_menu_bar_2_bodega.add(item_menu_costo);

        menuBar.add(j_menu_bar_2_bodega);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void item_menu_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_menu_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_item_menu_salirActionPerformed

    private void item_menu_costoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_menu_costoActionPerformed
        IngresarCosto Ing = new IngresarCosto();
        this.panel.add(Ing);
        Ing.show();
        try {
            Ing.cbUnidadMedida();
            Ing.cbFamilia();
            Ing.cbLinea();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenuAdminBodega.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MenuAdminBodega.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_item_menu_costoActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuAdminBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdminBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdminBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdminBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAdminBodega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem item_menu_costo;
    private javax.swing.JMenuItem item_menu_salir;
    private javax.swing.JMenu j_menu_bar_1_bodeda;
    private javax.swing.JMenu j_menu_bar_2_bodega;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JDesktopPane panel;
    // End of variables declaration//GEN-END:variables

}
