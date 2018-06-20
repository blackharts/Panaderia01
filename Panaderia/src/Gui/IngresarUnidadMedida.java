/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.UnidadMedidaJpaController;
import Controller.exceptions.NonexistentEntityException;
import Data.UnidadMedida;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisa
 */
public class IngresarUnidadMedida extends javax.swing.JInternalFrame {

    /**
     * Creates new form IngresarUnidadMedida
     */
    public IngresarUnidadMedida() {
        initComponents();
        this.mostrarTabla();
    }
    
    

    void limpiar() {
        tf_codigo.setText("");
        tf_descripcion.setText("");
    }

    void guardarUnidadMedida() {

        UnidadMedidaJpaController unid = new UnidadMedidaJpaController(entityManager1.getEntityManagerFactory());
        UnidadMedida uni = new UnidadMedida();

        try {
            if(tf_codigo.getText().length() == 0 &&tf_descripcion.getText().length() ==0 ){
                JOptionPane.showMessageDialog(null, "Campos vacios");
            
            }else{
            
            uni.setUnidCodigo(tf_codigo.getText());
            uni.setUnidDescripcion(tf_descripcion.getText());

            unid.create(uni);
            JOptionPane.showMessageDialog(null, "Datos Insertados");
            
            }   
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        limpiar();
        mostrarTabla();
    }

    public void mostrarTabla() {
        /* hace referencia a mostrar los ingreso diario en la tabla */
        try {

            String[] columnas = {"Id", "Codigo", "Descripcion"};
            Object[] obj = new Object[3];
            DefaultTableModel tabla = new DefaultTableModel(null, columnas);
            UnidadMedida u = new UnidadMedida();
            List<UnidadMedida> uni = query2.getResultList();
            for (int i = 0; i < uni.size(); i++) {
                u = (UnidadMedida) uni.get(i);
                obj[0] = u.getUnidId();
                obj[1] = u.getUnidCodigo();
                obj[2] = u.getUnidDescripcion();
                tabla.addRow(obj);
                
            }
            tb_unidadmedida.setModel(tabla);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
      
    void eliminarUnidadMedida() {
        try {
          
             if(tf_codigo.getText().length() == 0 &&tf_descripcion.getText().length() ==0 ){
                JOptionPane.showMessageDialog(null, "Campos  vacios");
            
            }else{
                 
                 Integer id = (Integer) tb_unidadmedida.getValueAt(tb_unidadmedida.getSelectedRow(), 0);
            UnidadMedidaJpaController unid = new UnidadMedidaJpaController(entityManager1.getEntityManagerFactory());
            

            int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                if (id != null) {
                    unid.destroy(id);
                    this.mostrarTabla();
                    JOptionPane.showMessageDialog(null, "Se ha eliminado el id: " + id);

                } else {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado un producto");
                }

            } else {
                limpiar();
            }
             
             
             }
            
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        limpiar();
        mostrarTabla();

    }
     void modificarProducto() {
        UnidadMedidaJpaController unid = new UnidadMedidaJpaController(entityManager1.getEntityManagerFactory());
        UnidadMedida uni = new UnidadMedida();
   
        try {
            
            if(tf_codigo.getText().length() == 0 &&tf_descripcion.getText().length() ==0 ){
                JOptionPane.showMessageDialog(null, "Campos  vacios");
            
            }else{
             uni= unid.findUnidadMedida(Integer.parseInt(txt_id.getText()));
            uni.setUnidCodigo(this.tf_codigo.getText().toString());
            uni.setUnidDescripcion(this.tf_descripcion.getText().toString());
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea modificar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {

                unid.edit(uni);              
                JOptionPane.showMessageDialog(this, "Datos modificados");
            } else {
                limpiar();
            }
            
            
            }
            
            
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se a selecionado elemento a modifcar");
            e.printStackTrace();
        }
        limpiar();
       mostrarTabla();
    }

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager1 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        query2 = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT U  FROM UnidadMedida U");
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_codigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_descripcion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        bt_insertar = new javax.swing.JButton();
        bt_modificar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_unidadmedida = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Unidad de Medida");

        jPanel2.setLayout(new java.awt.GridLayout(3, 2, 5, 5));

        jLabel1.setText("ID:");
        jPanel2.add(jLabel1);

        txt_id.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(txt_id);

        jLabel2.setText("Codigo:");
        jPanel2.add(jLabel2);
        jPanel2.add(tf_codigo);

        jLabel3.setText("Descripcion:");
        jPanel2.add(jLabel3);
        jPanel2.add(tf_descripcion);

        jPanel3.setLayout(new java.awt.GridLayout(3, 1, 5, 5));

        bt_insertar.setText("Insertar");
        bt_insertar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_insertarActionPerformed(evt);
            }
        });
        jPanel3.add(bt_insertar);

        bt_modificar.setText("Modificar");
        bt_modificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificarActionPerformed(evt);
            }
        });
        jPanel3.add(bt_modificar);

        bt_eliminar.setText("Elimnar");
        bt_eliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });
        jPanel3.add(bt_eliminar);

        tb_unidadmedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_unidadmedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_unidadmedidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_unidadmedida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_insertarActionPerformed
        // TODO add your handling code here:

        this.guardarUnidadMedida();
    }//GEN-LAST:event_bt_insertarActionPerformed

    private void bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificarActionPerformed
        // TODO add your handling code here:
           this.modificarProducto();
           mostrarTabla();
    }//GEN-LAST:event_bt_modificarActionPerformed

    private void tb_unidadmedidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_unidadmedidaMouseClicked
        // TODO add your handling code here:
        int fila = this.tb_unidadmedida.getSelectedRow();
        this.txt_id.setText(String.valueOf(this.tb_unidadmedida.getValueAt(fila, 0)));
        this.tf_codigo.setText(String.valueOf(this.tb_unidadmedida.getValueAt(fila, 1)));
        this.tf_descripcion.setText(String.valueOf(this.tb_unidadmedida.getValueAt(fila, 2)));
        
    }//GEN-LAST:event_tb_unidadmedidaMouseClicked

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        // TODO add your handling code here:
        this.eliminarUnidadMedida();
    }//GEN-LAST:event_bt_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_insertar;
    private javax.swing.JButton bt_modificar;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.persistence.Query query2;
    private javax.swing.JTable tb_unidadmedida;
    private javax.swing.JTextField tf_codigo;
    private javax.swing.JTextField tf_descripcion;
    private javax.swing.JLabel txt_id;
    // End of variables declaration//GEN-END:variables

}
