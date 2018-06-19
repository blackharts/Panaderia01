package Gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controller.LineaJpaController;
import Controller.ProductoJpaController;
import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Data.Familia;
import Data.Linea;
import Data.Producto;
import Data.UnidadMedida;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisa
 */
public class IngresarProductos extends javax.swing.JInternalFrame {

    Producto prod = new Producto();
    
    void limpiar() {
        jLabel1.setText("");
        tf_nombre.setText("");
        cb_umedida.setSelectedIndex(0);
        tf_marca.setText("");
        tf_formato.setText("");
        cb_linea.setSelectedIndex(0);
        cb_familia.setSelectedIndex(0);
        this.mostrarProducto();
    }

    void insertarProductos() {
        ProductoJpaController prd = new ProductoJpaController(entityManager.getEntityManagerFactory());
        
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
        limpiar();
        mostrarProducto();
    }

    void mostrarProducto() {
        String[] columnas = {"Id", "Nombre", "U.Medida", "Marca", "Formato", "Linea", "Familia"};
        Object[] obj = new Object[7];
        Producto p = new Producto();

        DefaultTableModel tabla = new DefaultTableModel(null, columnas);

        List<Producto> prd = q_producto.getResultList();
        for (int i = 0; i < prd.size(); i++) {
            p = (Producto) prd.get(i);
            obj[0] = p.getProdId();
            obj[1] = p.getProdNombre();
            obj[2] = p.getProdUnidadmedida().getUnidDescripcion();
            obj[3] = p.getProdMarca();
            obj[4] = p.getProdFormato();
            obj[5] = p.getProdLinea().getLineNombre();
            obj[6] = p.getProdFamilia().getFamiNombre();

            tabla.addRow(obj);

        }
        tb_producto.setModel(tabla);

    }

    void eliminarProducto() {
        try {
            Integer id = (Integer) tb_producto.getValueAt(tb_producto.getSelectedRow(), 0);
            ProductoJpaController prd = new ProductoJpaController(entityManager.getEntityManagerFactory());
            int SioNo = JOptionPane.showConfirmDialog(this, "Desea eliminar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                if (id != null) {
                    try {
                        prd.destroy(id);
                    } catch (NonexistentEntityException | IllegalOrphanException ex) {
                        Logger.getLogger(IngresarProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.mostrarProducto();
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
        mostrarProducto();

    }

    void modificarProducto() {
        ProductoJpaController prd = new ProductoJpaController(entityManager.getEntityManagerFactory());
        Linea l = new Linea();
        try {
            l.setLineId(this.cb_linea.getSelectedIndex() + 1);

            UnidadMedida u = new UnidadMedida();
            u.setUnidId(this.cb_umedida.getSelectedIndex() + 1);

            Familia f = new Familia();
            f.setFamiId(this.cb_familia.getSelectedIndex() + 1);
            prod.setProdId(Integer.parseInt(this.jLabel1.getText()));
            prod.setProdNombre(this.tf_nombre.getText().toString());
            prod.setProdUnidadmedida(u);
            prod.setProdMarca(this.tf_marca.getText().toString());
            prod.setProdFormato(this.tf_formato.getText().toString());
            prod.setProdLinea(l);
            prod.setProdFamilia(f);

            int SioNo = JOptionPane.showConfirmDialog(this, "Desea modificar?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (SioNo == 0) {
                prd.edit(prod);
                JOptionPane.showMessageDialog(this, "Datos modificados");
                mostrarProducto();
            } else {
                limpiar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        limpiar();
        mostrarProducto();

    }

    public IngresarProductos() {
        initComponents();
        this.mostrarProducto();
        List<UnidadMedida> u = q_unidad_medida.getResultList();
        List<Familia> f = q_familia.getResultList();
        List<Linea> l = q_linea.getResultList();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        q_linea = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT l FROM Linea l");
        q_unidad_medida = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT u FROM UnidadMedida u");
        q_familia = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT f FROM Familia f");
        q_producto = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT p FROM Producto p");
        jPanel1 = new javax.swing.JPanel();
        txt_codigo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
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
        bt_modificar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_producto = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ingresar Productos");

        jPanel1.setLayout(new java.awt.GridLayout(7, 2, 10, 10));

        txt_codigo.setText("Id:");
        jPanel1.add(txt_codigo);

        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel1);

        txt_nombre.setText("Nombre:");
        jPanel1.add(txt_nombre);
        jPanel1.add(tf_nombre);

        txt_umedida.setText("Unidad de Medida:");
        jPanel1.add(txt_umedida);

        cb_umedida.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
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

        cb_linea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(cb_linea);

        txt_familia.setText("Familia:");
        jPanel1.add(txt_familia);

        cb_familia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(cb_familia);

        jPanel2.setLayout(new java.awt.GridLayout(3, 1, 10, 10));

        bt_insertar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_insertar.setText("Insertar");
        bt_insertar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_insertarActionPerformed(evt);
            }
        });
        jPanel2.add(bt_insertar);

        bt_modificar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_modificar.setText("Modificar");
        bt_modificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modificarActionPerformed(evt);
            }
        });
        jPanel2.add(bt_modificar);

        bt_eliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_eliminar.setText("Eliminar");
        bt_eliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });
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
        tb_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_productoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_producto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_umedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_umedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_umedidaActionPerformed

    private void bt_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_insertarActionPerformed
        try {
            this.insertarProductos();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_bt_insertarActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        this.eliminarProducto();
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void tb_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_productoMouseClicked
        int fila = this.tb_producto.getSelectedRow();
        this.jLabel1.setText(String.valueOf(this.tb_producto.getValueAt(fila, 0)));
        this.tf_nombre.setText(String.valueOf(this.tb_producto.getValueAt(fila, 1)));
        this.cb_umedida.setSelectedItem(String.valueOf(this.tb_producto.getValueAt(fila, 2)));
        this.tf_marca.setText(String.valueOf(this.tb_producto.getValueAt(fila, 3)));
        this.tf_formato.setText(String.valueOf(this.tb_producto.getValueAt(fila, 4)));
        this.cb_linea.setSelectedItem(String.valueOf(this.tb_producto.getValueAt(fila, 5)));
        this.cb_familia.setSelectedItem(String.valueOf(this.tb_producto.getValueAt(fila, 6)));

    }//GEN-LAST:event_tb_productoMouseClicked

    private void bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modificarActionPerformed
        this.modificarProducto();
    }//GEN-LAST:event_bt_modificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_insertar;
    private javax.swing.JButton bt_modificar;
    private javax.swing.JComboBox cb_familia;
    private javax.swing.JComboBox cb_linea;
    private javax.swing.JComboBox cb_umedida;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.persistence.Query q_familia;
    private javax.persistence.Query q_linea;
    private javax.persistence.Query q_producto;
    private javax.persistence.Query q_unidad_medida;
    private javax.swing.JTable tb_producto;
    private javax.swing.JTextField tf_formato;
    private javax.swing.JTextField tf_marca;
    private javax.swing.JTextField tf_nombre;
    private javax.swing.JLabel txt_codigo;
    private javax.swing.JLabel txt_familia;
    private javax.swing.JLabel txt_formato;
    private javax.swing.JLabel txt_linea;
    private javax.swing.JLabel txt_marca;
    private javax.swing.JLabel txt_nombre;
    private javax.swing.JLabel txt_umedida;
    // End of variables declaration//GEN-END:variables
}
