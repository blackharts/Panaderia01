/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.DetalleRecetaJpaController;
import Data.DetalleReceta;
import Data.Producto;
import Data.Receta;
import Data.UnidadMedida;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * jaime
 */
public class IngresarInsumosReceta extends javax.swing.JInternalFrame {

    DetalleReceta detreceta = new DetalleReceta();

    public IngresarInsumosReceta() {
        initComponents();
        List<Receta> receta = q_nombre_producto.getResultList(); // se obtienen los productos y almcenan en lista
        List<Producto> producto = q_ingreso_insumo.getResultList();
        List<UnidadMedida> unidadmedida = q_unidad_medida.getResultList();

        cb_prod_final.removeAllItems();//se limpia el combobox
        cb_insert_insumos.removeAllItems();
        cb_unidad_medida.removeAllItems();

        for (Receta r : receta) {// se recorre 
            cb_prod_final.addItem(r.getReceProducto().getProdNombre());//se muestra en el combobox    
        }
        for (Producto p : producto) {
            cb_insert_insumos.addItem(p.getProdNombre());
        }
        for (UnidadMedida u : unidadmedida) {
            cb_unidad_medida.addItem(u.getUnidDescripcion());
        }

    }

    void insertarReceta() {
        DetalleRecetaJpaController dreceta = new DetalleRecetaJpaController(entityManager1.getEntityManagerFactory());
        Receta r = new Receta();
        r.setReceId(cb_prod_final.getSelectedIndex() + 1);
        Producto p = new Producto();
        p.setProdId(cb_insert_insumos.getSelectedIndex() + 1);

        detreceta.setDrecReceta(r);
        detreceta.setDrecProducto(p);
        detreceta.setDrecCantidad(Integer.parseInt(tf_cantidad_insumos.getText()));
        dreceta.create(detreceta);

        JOptionPane.showMessageDialog(null, "Datos insertados");

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
        q_nombre_producto = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("select r from Receta r");
        q_ingreso_insumo = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("select p from Producto p");
        q_unidad_medida = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("Select u from UnidadMedida u");
        txt_nombre_producto = new javax.swing.JLabel();
        cb_prod_final = new javax.swing.JComboBox<>();
        txt_ingreso_insumos = new javax.swing.JLabel();
        txt_cantidad_insumos = new javax.swing.JLabel();
        bt_eliminar_fila = new javax.swing.JButton();
        tf_cantidad_insumos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_tabla_receta = new javax.swing.JTable();
        cb_insert_insumos = new javax.swing.JComboBox<>();
        bt_guardar_receta = new javax.swing.JButton();
        bt_agregar = new javax.swing.JButton();
        cb_unidad_medida = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Detalle de Producto");

        txt_nombre_producto.setText("Nombre del Producto");

        cb_prod_final.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_prod_final.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_prod_finalActionPerformed(evt);
            }
        });

        txt_ingreso_insumos.setText("Ingreso de Insumo");

        txt_cantidad_insumos.setText("Cantidad de Insumos");

        bt_eliminar_fila.setText("Eliminar Fila");
        bt_eliminar_fila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminar_filaActionPerformed(evt);
            }
        });

        tf_cantidad_insumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cantidad_insumosActionPerformed(evt);
            }
        });

        tb_tabla_receta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receta", "Insumo", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_tabla_receta);

        cb_insert_insumos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_insert_insumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_insert_insumosActionPerformed(evt);
            }
        });

        bt_guardar_receta.setText("Guardar Receta");
        bt_guardar_receta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardar_recetaActionPerformed(evt);
            }
        });

        bt_agregar.setText("Agregar");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        cb_unidad_medida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_nombre_producto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(201, 201, 201))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cb_insert_insumos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cb_prod_final, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_ingreso_insumos, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addGap(33, 33, 33)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(tf_cantidad_insumos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_cantidad_insumos))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(cb_unidad_medida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bt_eliminar_fila))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(bt_guardar_receta)))
                .addGap(0, 38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_nombre_producto)
                .addGap(16, 16, 16)
                .addComponent(cb_prod_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ingreso_insumos)
                    .addComponent(txt_cantidad_insumos))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_insert_insumos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_cantidad_insumos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_unidad_medida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_agregar)
                    .addComponent(bt_eliminar_fila))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_guardar_receta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_eliminar_filaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminar_filaActionPerformed
        DefaultTableModel modelo;
        int fila = 0;
        modelo = (DefaultTableModel) this.tb_tabla_receta.getModel();
        modelo.removeRow(this.tb_tabla_receta.getSelectedRow());
        fila--;

    }//GEN-LAST:event_bt_eliminar_filaActionPerformed

    private void cb_prod_finalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_prod_finalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_prod_finalActionPerformed

    private void cb_insert_insumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_insert_insumosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_insert_insumosActionPerformed

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) tb_tabla_receta.getModel();
        Object[] fila = new Object[6];

        fila[0] = cb_prod_final.getSelectedItem().toString();
        fila[1] = cb_insert_insumos.getSelectedItem().toString();
        fila[2] = tf_cantidad_insumos.getText();

        modelo.addRow(fila);

        tb_tabla_receta.setModel(modelo);
    }//GEN-LAST:event_bt_agregarActionPerformed

    private void tf_cantidad_insumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_cantidad_insumosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_cantidad_insumosActionPerformed

    private void bt_guardar_recetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardar_recetaActionPerformed
        insertarReceta();
    }//GEN-LAST:event_bt_guardar_recetaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_agregar;
    private javax.swing.JButton bt_eliminar_fila;
    private javax.swing.JButton bt_guardar_receta;
    private javax.swing.JComboBox<String> cb_insert_insumos;
    private javax.swing.JComboBox<String> cb_prod_final;
    private javax.swing.JComboBox<String> cb_unidad_medida;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.persistence.Query q_ingreso_insumo;
    private javax.persistence.Query q_nombre_producto;
    private javax.persistence.Query q_unidad_medida;
    private javax.swing.JTable tb_tabla_receta;
    private javax.swing.JTextField tf_cantidad_insumos;
    private javax.swing.JLabel txt_cantidad_insumos;
    private javax.swing.JLabel txt_ingreso_insumos;
    private javax.swing.JLabel txt_nombre_producto;
    // End of variables declaration//GEN-END:variables
}
