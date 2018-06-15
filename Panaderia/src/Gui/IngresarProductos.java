/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.LineaJpaController;
import Controller.ProductoJpaController;
import Data.Familia;
import Data.Linea;
import Data.Producto;
import Data.UnidadMedida;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisa
 */
public class IngresarProductos extends javax.swing.JInternalFrame {

    void limpiar() {
        tf_nombre.setText("");
        cb_umedida.setSelectedIndex(0);
        tf_marca.setText("");
        tf_formato.setText("");
        cb_linea.setSelectedIndex(0);
        cb_familia.setSelectedIndex(0);
        this.mostrarProducto();
    }

    public IngresarProductos() {
        initComponents();
        this.mostrarProducto();
        List<UnidadMedida> u = query_unidad_medida.getResultList();
        List<Familia> f = query_familia.getResultList();
        List<Linea> l = query_linea.getResultList();
        cb_familia.removeAllItems();//se limpia el combobox
        cb_linea.removeAllItems();//se limpia el combobox
        cb_umedida.removeAllItems();//se limpia el combobox
        for (Iterator<UnidadMedida> it = u.iterator(); it.hasNext();) {
            UnidadMedida uni = it.next();
            // se recorre
            cb_umedida.addItem(uni.getUnidCodigo());//se muestra en el combobox  
        }
        for (Iterator<Familia> it = f.iterator(); it.hasNext();) {
            Familia fam = it.next();
            // se recorre
            cb_familia.addItem(fam.getFamiNombre());//se muestra en el combobox  
        }
        for (Iterator<Linea> it = l.iterator(); it.hasNext();) {
            Linea lin = it.next();
            // se recorre
            cb_linea.addItem(lin.getLineNombre());//se muestra en el combobox  
        }
    }

    public void mostrarProducto() {
        String[] columnas = {"Id", "Nombre", "U.Medida", "Marca", "Formato", "Linea", "Familia"};
        Object[] obj = new Object[7];
        Producto p = new Producto();

        DefaultTableModel tabla = new DefaultTableModel(null, columnas);

        List<Producto> prd = query_producto.getResultList();
        for (int i = 0; i < prd.size(); i++) {
            p = (Producto) prd.get(i);
            obj[0] = p.getProdId();
            obj[1] = p.getProdNombre();
            obj[2] = p.getProdUnidadmedida().getUnidCodigo();
            obj[3] = p.getProdMarca();
            obj[4] = p.getProdFormato();
            obj[5] = p.getProdLinea().getLineNombre();
            obj[6] = p.getProdFamilia().getFamiNombre();

            tabla.addRow(obj);
 
        }
        tb_producto.setModel(tabla);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        query_linea = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT l FROM Linea l");
        query_unidad_medida = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT u FROM UnidadMedida u");
        query_familia = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT f FROM Familia f");
        query_producto = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Producto p");
        jPanel1 = new javax.swing.JPanel();
        txt_nombre = new javax.swing.JLabel();
        tf_nombre = new javax.swing.JTextField();
        txt_umedida = new javax.swing.JLabel();
        cb_umedida = new javax.swing.JComboBox();
        txt_marca = new javax.swing.JLabel();
        tf_marca = new javax.swing.JTextField();
        txt_formato = new javax.swing.JLabel();
        tf_formato = new javax.swing.JTextField();
        txt_linea = new javax.swing.JLabel();
        cb_linea = new javax.swing.JComboBox();
        txt_familia = new javax.swing.JLabel();
        cb_familia = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        bt_insertar = new javax.swing.JButton();
        bt_buscar = new javax.swing.JButton();
        bt_modificar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_producto = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ingresar Productos");

        jPanel1.setLayout(new java.awt.GridLayout(7, 2, 10, 10));

        txt_nombre.setText("Nombre:");
        jPanel1.add(txt_nombre);
        jPanel1.add(tf_nombre);

        txt_umedida.setText(" Unidad de Medida:");
        jPanel1.add(txt_umedida);

        cb_umedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_umedidaActionPerformed(evt);
            }
        });
        jPanel1.add(cb_umedida);

        txt_marca.setText("Marca:");
        jPanel1.add(txt_marca);
        jPanel1.add(tf_marca);

        txt_formato.setText("Formato:");
        jPanel1.add(txt_formato);
        jPanel1.add(tf_formato);

        txt_linea.setText("Linea:");
        jPanel1.add(txt_linea);

        jPanel1.add(cb_linea);

        txt_familia.setText("Familia:");
        jPanel1.add(txt_familia);

        jPanel1.add(cb_familia);

        jPanel2.setLayout(new java.awt.GridLayout(4, 1, 20, 20));

        bt_insertar.setText("Insertar");
        bt_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_insertarActionPerformed(evt);
            }
        });
        jPanel2.add(bt_insertar);

        bt_buscar.setText("Buscar");
        jPanel2.add(bt_buscar);

        bt_modificar.setText("Modificar");
        jPanel2.add(bt_modificar);

        bt_eliminar.setText("Eliminar");
        jPanel2.add(bt_eliminar);

        tb_producto.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_producto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_umedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_umedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_umedidaActionPerformed

    private void bt_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_insertarActionPerformed

        ProductoJpaController prd = new ProductoJpaController(entityManager.getEntityManagerFactory());

        Producto prod = new Producto();
        Linea l = new Linea();

        l.setLineId(cb_linea.getSelectedIndex() + 1);

        UnidadMedida u = new UnidadMedida();
        u.setUnidId(cb_umedida.getSelectedIndex() + 1);

        Familia f = new Familia();
        f.setFamiId(cb_familia.getSelectedIndex() + 1);

        prod.setProdNombre(tf_nombre.getText().toString());
        prod.setProdUnidadmedida(u);
        prod.setProdMarca(tf_marca.getText().toString());
        prod.setProdFormato(tf_formato.getText().toString());
        prod.setProdLinea(l);
        prod.setProdFamilia(f);
        prd.create(prod);
        JOptionPane.showMessageDialog(null, "Datos insertados");
        this.limpiar();


    }//GEN-LAST:event_bt_insertarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_insertar;
    private javax.swing.JButton bt_modificar;
    private javax.swing.JComboBox cb_familia;
    private javax.swing.JComboBox cb_linea;
    private javax.swing.JComboBox cb_umedida;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.persistence.Query query_familia;
    private javax.persistence.Query query_linea;
    private javax.persistence.Query query_producto;
    private javax.persistence.Query query_unidad_medida;
    private javax.swing.JTable tb_producto;
    private javax.swing.JTextField tf_formato;
    private javax.swing.JTextField tf_marca;
    private javax.swing.JTextField tf_nombre;
    private javax.swing.JLabel txt_familia;
    private javax.swing.JLabel txt_formato;
    private javax.swing.JLabel txt_linea;
    private javax.swing.JLabel txt_marca;
    private javax.swing.JLabel txt_nombre;
    private javax.swing.JLabel txt_umedida;
    // End of variables declaration//GEN-END:variables
}
