/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.RecetaJpaController;
import Data.Producto;
import Data.Receta;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author luisa
 */
public class IngresarReceta extends javax.swing.JInternalFrame {

    /**
     * Creates new form IngresarReceta
     */
    public IngresarReceta() {
        initComponents();
        List<Producto> p = q_producto.getResultList();
        cb_producto.removeAllItems();/*se limpia el combobox*/
        for (Iterator<Producto> it = p.iterator(); it.hasNext();) {
                Producto pro = it.next();
                // se recorre
                cb_producto.addItem(pro.getProdNombre());/*se muestra en el combobox*/

            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager1 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        q_producto = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT p FROM Producto p");
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_producto = new javax.swing.JComboBox();
        bt_guardar = new javax.swing.JButton();

        setClosable(true);
        setForeground(java.awt.Color.red);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Receta");

        jPanel1.setLayout(new java.awt.GridLayout(1, 1, 10, 10));

        jLabel1.setText("Seleccionar Producto:");
        jPanel1.add(jLabel1);

        cb_producto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(cb_producto);

        bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_guardar.setText("Guardar");
        bt_guardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(bt_guardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_guardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        RecetaJpaController r = new RecetaJpaController(entityManager1.getEntityManagerFactory());
        Receta re = new Receta();
        Producto p = new Producto();
        p.setProdId(cb_producto.getSelectedIndex() + 1);
        re.setReceProducto(p);
        r.create(re);
        JOptionPane.showMessageDialog(null, "Datos insertados");
    }//GEN-LAST:event_bt_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_guardar;
    private javax.swing.JComboBox cb_producto;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.persistence.Query q_producto;
    // End of variables declaration//GEN-END:variables
}
