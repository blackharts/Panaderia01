/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.LineaJpaController;
import Controller.ProduccionPanJpaController;
import Controller.ProductoJpaController;
import Controller.UnidadMedidaJpaController;
import Data.Familia;
import Data.Linea;
import Data.ProduccionPan;
import Data.Producto;
import Data.UnidadMedida;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisa
 */
public class IngresarProduccion extends javax.swing.JInternalFrame {

    DefaultTableModel tt = new DefaultTableModel();

    void limpiar() {
        tf_produccion.setText("");
        cb_umedida.setSelectedIndex(0);
        cb_producto.setSelectedIndex(0);
    }

    /*se utilizar para borar info una vez guardada */
    public IngresarProduccion() {
        initComponents();
        this.mostrarTabla();

        try {
            List<Producto> p = query_producto.getResultList();
            List<UnidadMedida> u = query_unidadmedida.getResultList();

            cb_producto.removeAllItems();/*se limpia el combobox*/
            cb_umedida.removeAllItems();/*se limpia el combobox*/

            for (Iterator<UnidadMedida> it = u.iterator(); it.hasNext();) {
                UnidadMedida uni = it.next();
                cb_umedida.addItem(uni.getUnidDescripcion());/*se muestra en el combobox  */
            }
            for (Iterator<Producto> it = p.iterator(); it.hasNext();) {
                Producto pro = it.next();
                // se recorre
                cb_producto.addItem(pro.getProdNombre());/*se muestra en el combobox*/
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.mostrarTabla();    }

    void modificarProducto() {
        ProduccionPanJpaController pruduccion = new ProduccionPanJpaController(entityManager1.getEntityManagerFactory());

        ProduccionPan pp = new ProduccionPan();
        Producto p = new Producto();
        try {

            p.setProdId(cb_producto.getSelectedIndex() + 1);
            /*combobox de producto*/

            UnidadMedida u = new UnidadMedida();
            u.setUnidId(cb_umedida.getSelectedIndex() + 1);
            /*combobox de unidad medida */

            Date fecha = new Date();
            pp.setPpanId(Integer.parseInt(txt_id.getText()));
            pp.setPpanFechaIngreso(fecha);
            pp.setPpanProducto(p);
            pp.setPpanUnidadMedida(u);
            pp.setPpanProduccion(Double.parseDouble(tf_produccion.getText()));

            int SioNo = JOptionPane.showConfirmDialog(this, "Desea modificar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                pruduccion.edit(pp);

                JOptionPane.showMessageDialog(this, "Datos modificados");
                this.mostrarTabla();

            } else {
                limpiar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        limpiar();
        this.mostrarTabla();

    }

    void eliminarProducto() {
        try {
            Integer id = (Integer) tb_produccion.getValueAt(tb_produccion.getSelectedRow(), 0);
            ProduccionPanJpaController prd = new ProduccionPanJpaController(entityManager1.getEntityManagerFactory());
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                if (id != null) {
                    prd.destroy(id);
                    this.mostrarTabla();
                    JOptionPane.showMessageDialog(null, "Se ha eliminado el id: " + id);

                } else {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado un producto");
                }

            } else {
                limpiar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        limpiar();
        mostrarTabla();

    }

    public void mostrarTabla() {
        /* hace referencia a mostrar los ingreso diario en la tabla */
        try {

            String[] columnas = {"Id", "Producto", "Unidad Medida", "Cantidad", "Fecha"};
            Object[] obj = new Object[5];
            DefaultTableModel tabla = new DefaultTableModel(null, columnas);
            ProduccionPan p = new ProduccionPan();

            List<ProduccionPan> pro = query1.getResultList();
            /* select * from ProdudcionPan*/
            for (int i = 0; i < pro.size(); i++) {
                p = (ProduccionPan) pro.get(i);
                obj[0] = p.getPpanId();
                obj[1] = p.getPpanProducto().getProdNombre();
                obj[2] = p.getPpanUnidadMedida().getUnidDescripcion();
                obj[3] = p.getPpanProduccion();
                obj[4] = p.getPpanFechaIngreso();

                tabla.addRow(obj);
            }
            tb_produccion.setModel(tabla);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    void guardar() {

        ProduccionPanJpaController pruduccion = new ProduccionPanJpaController(entityManager1.getEntityManagerFactory());

        ProduccionPan pp = new ProduccionPan();
        Producto p = new Producto();
        Date fecha = new Date();
        try {

            p.setProdId(cb_producto.getSelectedIndex() + 1);
            /*combobox de producto*/

            UnidadMedida u = new UnidadMedida();
            u.setUnidId(cb_umedida.getSelectedIndex() + 1);
            /*combobox de unidad medida */

            pp.setPpanProduccion(Integer.parseInt(tf_produccion.getText()));
            pp.setPpanFechaIngreso(fecha);

            pp.setPpanProducto(p);
            pp.setPpanUnidadMedida(u);

            pruduccion.create(pp);
            JOptionPane.showMessageDialog(null, "Datos Insertados");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        limpiar();

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
        query_producto = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT p FROM Producto p");
        query_unidadmedida = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT u FROM UnidadMedida u");
        query1 = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT T FROM ProduccionPan T");
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_producto = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cb_umedida = new javax.swing.JComboBox<>();
        Cantidad = new javax.swing.JLabel();
        tf_produccion = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        bt_guardar = new javax.swing.JButton();
        bt_modificar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_produccion = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ingreso Produccion Diaria");

        jPanel3.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

        jLabel2.setText("Id");
        jPanel3.add(jLabel2);

        txt_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(txt_id);

        jLabel4.setText("Producto");
        jPanel3.add(jLabel4);

        jPanel3.add(cb_producto);

        jLabel5.setText("Unidad Medida");
        jPanel3.add(jLabel5);

        jPanel3.add(cb_umedida);

        Cantidad.setText("Cantidad");
        jPanel3.add(Cantidad);
        jPanel3.add(tf_produccion);

        jPanel6.setLayout(new java.awt.GridLayout(3, 1, 10, 10));

        bt_guardar.setText("Insertar");
        bt_guardar.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });
        jPanel6.add(bt_guardar);

        bt_modificar.setText("Modificar");
        bt_modificar.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificarActionPerformed(evt);
            }
        });
        jPanel6.add(bt_modificar);

        bt_eliminar.setText("Eliminar");
        bt_eliminar.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });
        jPanel6.add(bt_eliminar);

        tb_produccion.setBorder(new javax.swing.border.MatteBorder(null));
        tb_produccion.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_produccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_produccionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_produccion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_bt_guardarActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        this.eliminarProducto();
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificarActionPerformed
        this.modificarProducto();
    }//GEN-LAST:event_bt_modificarActionPerformed

    private void tb_produccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_produccionMouseClicked
        int fila = this.tb_produccion.getSelectedRow();
        this.txt_id.setText(String.valueOf(this.tb_produccion.getValueAt(fila, 0)));
        this.cb_producto.setSelectedItem(String.valueOf(this.tb_produccion.getValueAt(fila, 1)));
        this.cb_umedida.setSelectedItem(String.valueOf(this.tb_produccion.getValueAt(fila, 2)));
        this.tf_produccion.setText(String.valueOf(this.tb_produccion.getValueAt(fila, 3)));
    }//GEN-LAST:event_tb_produccionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cantidad;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_modificar;
    private javax.swing.JComboBox<String> cb_producto;
    private javax.swing.JComboBox<String> cb_umedida;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.persistence.Query query1;
    private javax.persistence.Query query_producto;
    private javax.persistence.Query query_unidadmedida;
    private javax.swing.JTable tb_produccion;
    private javax.swing.JTextField tf_produccion;
    private javax.swing.JLabel txt_id;
    // End of variables declaration//GEN-END:variables
}
