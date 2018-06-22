/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.FamiliaJpaController;
import Controller.ProductoJpaController;
import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Data.Familia;
import Data.Linea;
import Data.Producto;
import Data.UnidadMedida;
import java.util.List;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author yo
 */
public class IngresarFamilia extends javax.swing.JInternalFrame {

    Familia f = new Familia();

    public IngresarFamilia() {
        initComponents();
        this.mostrarFamilia();
        tb_familia.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tb_familia.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(110);
        columnModel.getColumn(2).setPreferredWidth(110);

        List<Linea> productos = q_linea.getResultList(); // se obtienen los productos y almcenan en lista
        cb_codigo_linea.removeAllItems();//se limpia el combobox
        for (Iterator<Linea> it = productos.iterator(); it.hasNext();) {
            Linea p = it.next();
            // se recorre
            cb_codigo_linea.addItem(p.getLineNombre());//se muestra en el combobox  
        }

    }

    void limpiar() {
        this.txt_id.setText("");
        this.cb_codigo_linea.setSelectedIndex(0);
        this.tf_familia.setText("");
    }

    void insertarFamilia() {
        try {
            if (tf_familia.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Campos vacios");

            } else {
                FamiliaJpaController fa = new FamiliaJpaController(entityManager1.getEntityManagerFactory());
                Linea l = new Linea();
                l.setLineId(this.cb_codigo_linea.getSelectedIndex() + 1);
                f.setFamiLinea(l);
                f.setFamiNombre(this.tf_familia.getText().toString());
                fa.create(f);
                JOptionPane.showMessageDialog(null, "Datos insertados");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        mostrarFamilia();
        this.limpiar();
    }

    void mostrarFamilia() {
        String[] columnas = {"Id", "Nombre", "Linea"};
        Object[] obj = new Object[4];
        Familia fa = new Familia();

        DefaultTableModel tabla = new DefaultTableModel(null, columnas);

        List<Familia> f = q_familia.getResultList();
        for (int i = 0; i < f.size(); i++) {
            fa = (Familia) f.get(i);
            obj[0] = fa.getFamiId();
            obj[1] = fa.getFamiNombre();
            obj[2] = fa.getFamiLinea().getLineNombre();

            tabla.addRow(obj);

        }
        tb_familia.setModel(tabla);

    }

    void eliminarFamilia() {
        try {
            if (tf_familia.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Campos  vacios");

            } else {
                Integer id = (Integer) tb_familia.getValueAt(tb_familia.getSelectedRow(), 0);
                FamiliaJpaController fa = new FamiliaJpaController(entityManager1.getEntityManagerFactory());
                int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                if (SioNo == 0) {
                    if (id != null) {
                        try {
                            fa.destroy(id);
                        } catch (NonexistentEntityException | IllegalOrphanException ex) {
                            Logger.getLogger(IngresarProductos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        this.mostrarFamilia();
                        JOptionPane.showMessageDialog(null, "Se ha eliminado el id: " + id);

                    } else {
                        JOptionPane.showMessageDialog(null, "No ha seleccionado un producto");
                    }

                } else {
                    this.limpiar();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mostrarFamilia();

    }

    void editarFamilia() {
        Familia f = new FamiliaJpaController(entityManager1.getEntityManagerFactory()).findFamilia(Integer.parseInt(txt_id.getText()));
        Linea l = new Linea();
        try {
            if (tf_familia.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Campos vacios");

            } else {
                f.setFamiId(Integer.parseInt(this.txt_id.getText()));
                f.setFamiNombre(this.tf_familia.getText().toString());
                l.setLineId(this.cb_codigo_linea.getSelectedIndex() + 1);
                f.setFamiLinea(l);

                int SioNo = JOptionPane.showConfirmDialog(this, "Desea modificar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                if (SioNo == 0) {
                    FamiliaJpaController fa = new FamiliaJpaController(entityManager1.getEntityManagerFactory());
                    fa.edit(f);
                    JOptionPane.showMessageDialog(this, "Datos modificados");
                    mostrarFamilia();
                    this.limpiar();
                } else {
                    this.limpiar();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mostrarFamilia();

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
        q_linea = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("select c from Linea c");
        q_familia = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT f FROM Familia f");
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cb_codigo_linea = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();
        tf_familia = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        bt_insertar = new javax.swing.JButton();
        bt_editar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_familia = new javax.swing.JTable();
        bt_limpiar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Familia");

        jPanel1.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        jLabel3.setText("Id:");
        jPanel1.add(jLabel3);

        txt_id.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txt_id);

        jLabel1.setText("Seleccionar Linea:");
        jPanel1.add(jLabel1);

        cb_codigo_linea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(cb_codigo_linea);

        jLabel2.setText("Ingresar Familia:");
        jPanel1.add(jLabel2);

        tf_familia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_familiaActionPerformed(evt);
            }
        });
        jPanel1.add(tf_familia);

        jPanel2.setLayout(new java.awt.GridLayout(1, 3, 10, 10));

        bt_insertar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_insertar.setText("Insertar");
        bt_insertar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_insertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_insertarMouseClicked(evt);
            }
        });
        jPanel2.add(bt_insertar);

        bt_editar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_editar.setText("Modificar");
        bt_editar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_editarMouseClicked(evt);
            }
        });
        jPanel2.add(bt_editar);

        bt_eliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_eliminar.setText("Eliminar");
        bt_eliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_eliminarMouseClicked(evt);
            }
        });
        jPanel2.add(bt_eliminar);

        tb_familia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_familia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_familiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_familia);

        bt_limpiar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_limpiar.setText("Limpiar");
        bt_limpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_familiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_familiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_familiaActionPerformed

    private void bt_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_eliminarMouseClicked
        // TODO add your handling code here:
        this.eliminarFamilia();
    }//GEN-LAST:event_bt_eliminarMouseClicked

    private void bt_editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_editarMouseClicked
        // TODO add your handling code here:
        this.editarFamilia();
    }//GEN-LAST:event_bt_editarMouseClicked

    private void tb_familiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_familiaMouseClicked
        // TODO add your handling code here:
        int fila = this.tb_familia.getSelectedRow();
        this.txt_id.setText(String.valueOf(this.tb_familia.getValueAt(fila, 0)));
        this.tf_familia.setText(String.valueOf(this.tb_familia.getValueAt(fila, 1)));
        this.cb_codigo_linea.setSelectedItem(String.valueOf(this.tb_familia.getValueAt(fila, 2)));

    }//GEN-LAST:event_tb_familiaMouseClicked

    private void bt_insertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_insertarMouseClicked
        this.insertarFamilia();
    }//GEN-LAST:event_bt_insertarMouseClicked

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        this.limpiar();
    }//GEN-LAST:event_bt_limpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_insertar;
    private javax.swing.JButton bt_limpiar;
    private javax.swing.JComboBox<String> cb_codigo_linea;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.persistence.Query q_familia;
    private javax.persistence.Query q_linea;
    private javax.swing.JTable tb_familia;
    private javax.swing.JTextField tf_familia;
    private javax.swing.JLabel txt_id;
    // End of variables declaration//GEN-END:variables
}
