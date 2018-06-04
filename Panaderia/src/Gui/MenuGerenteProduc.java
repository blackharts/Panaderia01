/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/**
 *
 * @author luisa
 */
public class MenuGerenteProduc extends javax.swing.JFrame {

    /**
     * Creates new form MenuGerenteProduc
     */
    public MenuGerenteProduc() {
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
        j_menu_bar_1_gerente = new javax.swing.JMenu();
        item_menu_costoPan = new javax.swing.JMenuItem();
        item_menu_salir = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        j_menu_bar_1_gerente.setMnemonic('e');
        j_menu_bar_1_gerente.setText("Visualizar");

        item_menu_costoPan.setMnemonic('t');
        item_menu_costoPan.setText("Analisis de costo pan");
        item_menu_costoPan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_menu_costoPanActionPerformed(evt);
            }
        });
        j_menu_bar_1_gerente.add(item_menu_costoPan);

        item_menu_salir.setMnemonic('y');
        item_menu_salir.setText("Analisis de costo jornada laboral");
        item_menu_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_menu_salirActionPerformed(evt);
            }
        });
        j_menu_bar_1_gerente.add(item_menu_salir);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Salir");
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuItemActionPerformed(evt);
            }
        });
        j_menu_bar_1_gerente.add(deleteMenuItem);

        menuBar.add(j_menu_bar_1_gerente);

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

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void item_menu_costoPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_menu_costoPanActionPerformed
        VisualizarCostosPan vis = new VisualizarCostosPan();
        this.panel.add(vis);
        vis.show();
    }//GEN-LAST:event_item_menu_costoPanActionPerformed

    private void item_menu_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_menu_salirActionPerformed
     VisualizarCostosJornLabor vis = new VisualizarCostosJornLabor();
        this.panel.add(vis);
        vis.show();
    }//GEN-LAST:event_item_menu_salirActionPerformed

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
            java.util.logging.Logger.getLogger(MenuGerenteProduc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuGerenteProduc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuGerenteProduc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuGerenteProduc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuGerenteProduc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenuItem item_menu_costoPan;
    private javax.swing.JMenuItem item_menu_salir;
    private javax.swing.JMenu j_menu_bar_1_gerente;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JDesktopPane panel;
    // End of variables declaration//GEN-END:variables

}
