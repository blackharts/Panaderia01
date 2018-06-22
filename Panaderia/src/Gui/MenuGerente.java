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
public class MenuGerente extends javax.swing.JFrame {

    /**
     * Creates new form MenuGerenteProduc
     */
    public MenuGerente() {
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
        j_menu_bar_1_administrador = new javax.swing.JMenu();
        it_volver = new javax.swing.JMenuItem();
        it_salir = new javax.swing.JMenuItem();
        j_menu_bar_1_gerente = new javax.swing.JMenu();
        item_menu_costoPan = new javax.swing.JMenuItem();
        item_menu_salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        j_menu_bar_1_administrador.setText("Menu");

        it_volver.setText("Volver al Login");
        it_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                it_volverActionPerformed(evt);
            }
        });
        j_menu_bar_1_administrador.add(it_volver);

        it_salir.setMnemonic('x');
        it_salir.setText("Salir");
        it_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                it_salirActionPerformed(evt);
            }
        });
        j_menu_bar_1_administrador.add(it_salir);

        menuBar.add(j_menu_bar_1_administrador);

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

    private void item_menu_costoPanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_menu_costoPanActionPerformed
        MostrarCostosPan vis = new MostrarCostosPan();
        this.panel.add(vis);
        vis.show();
    }//GEN-LAST:event_item_menu_costoPanActionPerformed

    private void item_menu_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_menu_salirActionPerformed
     MostrarCostosJornLaboral vis = new MostrarCostosJornLaboral();
        this.panel.add(vis);
        vis.show();
    }//GEN-LAST:event_item_menu_salirActionPerformed

    private void it_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_it_volverActionPerformed
        Login l = new Login();
        l.setVisible(true);
    }//GEN-LAST:event_it_volverActionPerformed

    private void it_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_it_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_it_salirActionPerformed

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
            java.util.logging.Logger.getLogger(MenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuGerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem it_salir;
    private javax.swing.JMenuItem it_volver;
    private javax.swing.JMenuItem item_menu_costoPan;
    private javax.swing.JMenuItem item_menu_salir;
    private javax.swing.JMenu j_menu_bar_1_administrador;
    private javax.swing.JMenu j_menu_bar_1_gerente;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JDesktopPane panel;
    // End of variables declaration//GEN-END:variables

}
