/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.ProduccionPanJpaController;
import Controller.UnidadMedidaJpaController;
import Data.Familia;
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
public class IngresarProdPAn extends javax.swing.JInternalFrame {
    


   void limpiar()
   {
   tf_produccion.setText("");
   cb_umedida.setSelectedIndex(0);
   cb_producto.setSelectedIndex(0);
   }
    public IngresarProdPAn() {
    
        initComponents();
        List<Producto> p = query_producto.getResultList();
        List<UnidadMedida> u = query_unidadmedida.getResultList();

        cb_producto.removeAllItems();//se limpia el combobox
        cb_umedida.removeAllItems();//se limpia el combobox

        for (Iterator<UnidadMedida> it = u.iterator(); it.hasNext();) {
            UnidadMedida uni = it.next();
            cb_umedida.addItem(uni.getUnidDescripcion());//se muestra en el combobox  
        }
        for (Iterator<Producto> it = p.iterator(); it.hasNext();) {
            Producto pro = it.next();
            // se recorre
            cb_producto.addItem(pro.getProdNombre());//se muestra en el combobox  
        }
    }
    
    
    public void mostrarProducto() {
        
        String[] columnas = {"Id", "Nombre", "U.Medida", "Marca", "Formato", "Linea", "Familia"};
        Object[] obj = new Object[7];
        DefaultTableModel tabla = new DefaultTableModel(null, columnas);
        Producto p = new Producto();

        List<Producto> prd = query_producto.getResultList();
        for (int i = 0; i < prd.size(); i++) {
             p = (Producto) prd.get(i);
            obj[0] = p.getProdId();
            obj[1] = p.getProdNombre();
            obj[2] = p.getProdUnidadmedida();
            obj[3] = p.getProdMarca();
            obj[4] = p.getProdFormato();
            obj[5] = p.getProdLinea();
            obj[6] = p.getProdFamilia();
            tabla.addRow(obj);
        }
        tb_produccionppan.setModel(tabla);

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
        jPanel1 = new javax.swing.JPanel();
        txt_producto = new javax.swing.JLabel();
        txt_unidadmedida = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JLabel();
        cb_producto = new javax.swing.JComboBox();
        cb_umedida = new javax.swing.JComboBox<>();
        tf_produccion = new javax.swing.JTextField();
        bt_guardar = new javax.swing.JButton();
        bt_modificar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_produccionppan = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ingreso Produccion Diaria");

        jPanel1.setLayout(new java.awt.GridLayout(3, 3, 10, 10));

        txt_producto.setText("Producto ");
        jPanel1.add(txt_producto);

        txt_unidadmedida.setText("Unidad Medida");
        jPanel1.add(txt_unidadmedida);

        txt_cantidad.setText("Cantidad");
        jPanel1.add(txt_cantidad);

        cb_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_productoActionPerformed(evt);
            }
        });
        jPanel1.add(cb_producto);

        jPanel1.add(cb_umedida);
        jPanel1.add(tf_produccion);

        bt_guardar.setText("Guardar");
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });
        jPanel1.add(bt_guardar);

        bt_modificar.setText("Modificar");
        jPanel1.add(bt_modificar);

        bt_eliminar.setText("Eliminar");
        jPanel1.add(bt_eliminar);

        tb_produccionppan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Producto", "Unjidad Medida", "Producción", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_produccionppan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_productoActionPerformed

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed

        ProduccionPanJpaController pruduccion = new ProduccionPanJpaController(entityManager1.getEntityManagerFactory());
       

        ProduccionPan pp = new ProduccionPan();
        Producto p = new Producto();
        p.setProdId(cb_producto.getSelectedIndex() + 1);
      
        UnidadMedida u = new UnidadMedida();
        u.setUnidId(cb_umedida.getSelectedIndex() + 1);

        Date fecha = new Date();
        pp.setPpanFechaIngreso(fecha);
        pp.setPpanProducto(p);
        pp.setPpanUnidadMedida(u);
        pp.setPpanProduccion(Double.parseDouble(tf_produccion.getText()));
  
        pruduccion.create(pp);
        JOptionPane.showMessageDialog(null,"Se ha insertado datos");
        this.limpiar();


    }//GEN-LAST:event_bt_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_modificar;
    private javax.swing.JComboBox cb_producto;
    private javax.swing.JComboBox<String> cb_umedida;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.persistence.Query query_producto;
    private javax.persistence.Query query_unidadmedida;
    private javax.swing.JTable tb_produccionppan;
    private javax.swing.JTextField tf_produccion;
    private javax.swing.JLabel txt_cantidad;
    private javax.swing.JLabel txt_producto;
    private javax.swing.JLabel txt_unidadmedida;
    // End of variables declaration//GEN-END:variables
}
