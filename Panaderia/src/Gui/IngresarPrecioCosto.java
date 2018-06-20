/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.PrecioCostoJpaController;
import Controller.ProductoJpaController;
import Controller.exceptions.NonexistentEntityException;
import Data.*;
import Data.Producto;
import com.mysql.jdbc.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Alex
 */
public final class IngresarPrecioCosto extends javax.swing.JInternalFrame {

    /**
     * Creates new form IngresarCostos
     */
    public IngresarPrecioCosto() {
        initComponents();
        cargarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        em = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        em2 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        em3 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_costos = new javax.swing.JTable();
        bt_editar = new javax.swing.JButton();
        bt_borrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tf_costo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_nombre = new javax.swing.JTextField();
        tf_codigobarra = new javax.swing.JTextField();
        tf_nuevovalor = new javax.swing.JTextField();
        tf_marca = new javax.swing.JTextField();
        tf_formato = new javax.swing.JTextField();
        tf_unidadmedida = new javax.swing.JTextField();
        tf_linea = new javax.swing.JTextField();
        tf_familia = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        bt_buscar = new javax.swing.JButton();
        bt_guardar = new javax.swing.JButton();
        bt_aplicar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ingresar costo");

        tb_costos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tb_costos.setToolTipText("");
        jScrollPane1.setViewportView(tb_costos);

        bt_editar.setText("Editar");
        bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editarActionPerformed(evt);
            }
        });

        bt_borrar.setText("Borrar");
        bt_borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_borrarActionPerformed(evt);
            }
        });

        tf_costo.setText("No disp.");
        tf_costo.setEnabled(false);

        jLabel7.setText("Editando...");
        jLabel7.setEnabled(false);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Código barra");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel2.setText("Nombre");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 54, -1, -1));

        jLabel3.setText("Unidad de medida");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 88, -1, -1));

        jLabel4.setText("Marca                        ");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 122, -1, -1));

        jLabel6.setText("Formato");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 156, -1, -1));

        jLabel8.setText("Linea              ");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel10.setText("Familia              ");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 224, -1, -1));

        jLabel5.setText("Nuevo valor");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 258, -1, -1));

        tf_nombre.setEnabled(false);
        jPanel3.add(tf_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 100, -1));

        tf_codigobarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_codigobarraKeyTyped(evt);
            }
        });
        jPanel3.add(tf_codigobarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 60, -1));

        tf_nuevovalor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_nuevovalorKeyTyped(evt);
            }
        });
        jPanel3.add(tf_nuevovalor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 90, -1));

        tf_marca.setEnabled(false);
        jPanel3.add(tf_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 100, -1));

        tf_formato.setEnabled(false);
        jPanel3.add(tf_formato, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 60, -1));

        tf_unidadmedida.setEnabled(false);
        jPanel3.add(tf_unidadmedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 60, -1));

        tf_linea.setEnabled(false);
        jPanel3.add(tf_linea, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 60, -1));

        tf_familia.setEnabled(false);
        jPanel3.add(tf_familia, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 60, -1));

        bt_buscar.setText("Buscar p.");
        bt_buscar.setAlignmentX(0.1F);
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });

        bt_guardar.setText("Guardar");
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });

        bt_aplicar.setText("Aplicar");
        bt_aplicar.setEnabled(false);
        bt_aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_aplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bt_aplicar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(bt_aplicar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_guardar)
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7)
                .addGap(46, 46, 46)
                .addComponent(tf_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_costo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(286, 286, 286))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_borrar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_borrar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    DefaultTableModel modelo2;

    private void CrearModelo2(){
            try {
                modelo2 = (new DefaultTableModel(null, new String [] {"ID","Producto","Valor","Fecha"}){
                    Class[] types = new Class [] {java.lang.String.class,java.lang.String.class,java.lang.String.class,java.lang.String.class};
                    boolean[] canEdit = new boolean [] {false,false,false,false};
                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }
                    @Override
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return canEdit [colIndex];}
                });
                tb_costos.setModel(modelo2);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.toString()+" error2");
            }
        }
    
    public void cargarTabla(){
        CrearModelo2();
        try {
            System.out.println("Cargando tabla...");
            PrecioCostoJpaController costocontroller = new PrecioCostoJpaController(em.getEntityManagerFactory());
            Object A[]=null;
            List<PrecioCosto>Listacosto;
            Listacosto=costocontroller.findPrecioCostoEntities();
            tb_costos.getColumnModel().getColumn(0).setPreferredWidth(10);
            tb_costos.getColumnModel().getColumn(1).setPreferredWidth(40);
            tb_costos.getColumnModel().getColumn(2).setPreferredWidth(10);
            tb_costos.getColumnModel().getColumn(3).setPreferredWidth(70);
            for (int i = 0; i < Listacosto.size(); i++) {
                modelo2.addRow(A);
                modelo2.setValueAt(Listacosto.get(i).getCostId(), i, 0);
                modelo2.setValueAt(Listacosto.get(i).getCostProducto().getProdNombre(), i, 1);
                modelo2.setValueAt(Listacosto.get(i).getCostValor(), i, 2);
                modelo2.setValueAt(new SimpleDateFormat("dd-MM-yyyy'/'HH:mm").format(Listacosto.get(i).getCostFechaIngreso()), i, 3);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void cargarProducto(Producto p) throws ClassNotFoundException, SQLException{
        try {
            System.out.println("Cargando info producto...");
            Producto producto;
            ProductoJpaController pcjcontroller = new ProductoJpaController(em.getEntityManagerFactory());
            producto = pcjcontroller.findProducto(p.getProdId());
            if (producto != null){
                tf_codigobarra.setText(producto.getProdId().toString());
                tf_nombre.setText(producto.getProdNombre().toString());
                tf_unidadmedida.setText(producto.getProdUnidadmedida().getUnidDescripcion());
                tf_marca.setText(producto.getProdMarca().toString());
                tf_formato.setText(producto.getProdFormato());
                tf_linea.setText(producto.getProdLinea().getLineNombre());
                tf_familia.setText(producto.getProdFamilia().getFamiNombre());
            } else {
                JOptionPane.showMessageDialog(null, "Prod. ID no existe");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void cargarCosto(PrecioCosto costo) throws SQLException, ClassNotFoundException{
        try {
            PrecioCostoJpaController pccon = new PrecioCostoJpaController(em3.getEntityManagerFactory());
            PrecioCosto costocarg = new PrecioCosto();
            costocarg = pccon.findPrecioCosto(costo.getCostId());
            Producto producto = costocarg.getCostProducto();
            Integer cid = costocarg.getCostId();
            Integer cvalor = costocarg.getCostValor();
            cargarProducto(producto);
            tf_costo.setText(String.valueOf(cid));
            tf_nuevovalor.setText(String.valueOf(cvalor));
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void limpiar(){
        tf_costo.setText("No disp.");
        tf_codigobarra.setText("");
        tf_nombre.setText("");
        tf_unidadmedida.setText("");
        tf_marca.setText("");
        tf_formato.setText("");
        tf_linea.setText("");
        tf_familia.setText("");
        tf_nuevovalor.setText("");
    }
    
    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        if (tf_codigobarra.getText().length() != 0 && tf_nuevovalor.getText().length() != 0) {
            try {
                Producto prod = new Producto();
                prod.setProdId(Integer.parseInt(tf_codigobarra.getText()));
                int valor = Integer.parseInt(tf_nuevovalor.getText());
                PrecioCosto costo = new PrecioCosto();
                costo.setCostProducto(prod);
                costo.setCostValor(valor);
                Date fecha = new Date();
                costo.setCostFechaIngreso(fecha);
                PrecioCostoJpaController cjpacontroller = new PrecioCostoJpaController(em2.getEntityManagerFactory());
                cjpacontroller.create(costo);
                limpiar();
                cargarTabla();
                JOptionPane.showMessageDialog(this,"Registrado!");
            } catch (Exception e) {
                if (e.equals(e)) {
                    JOptionPane.showMessageDialog(this,"Prod. ID no existe!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Llenar todos los campos!");
        }
    }//GEN-LAST:event_bt_guardarActionPerformed

    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        if (tf_codigobarra.getText().length() != 0) {
            try {
                Integer id = Integer.parseInt(tf_codigobarra.getText());
                Producto prod = new Producto();
                prod.setProdId(id);
                cargarProducto(prod);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar id válido");
        }
    }//GEN-LAST:event_bt_buscarActionPerformed

    private void bt_borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_borrarActionPerformed
        try {    
            Integer id = (Integer) tb_costos.getValueAt(tb_costos.getSelectedRow(), 0);
            PrecioCostoJpaController pcjpa = new PrecioCostoJpaController(em.getEntityManagerFactory());
            pcjpa.destroy(id);
            cargarTabla();
            JOptionPane.showMessageDialog(null,"Eliminado! ID: " + id);
        } catch (Exception ex) {
            String resp = ex.getMessage();
            if (resp.equals(String.valueOf(-1))) {
                JOptionPane.showMessageDialog(null,"Seleccione correctamente!");
            } else {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
    }//GEN-LAST:event_bt_borrarActionPerformed

    private void bt_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editarActionPerformed
        try {
            Integer id = (Integer) tb_costos.getValueAt(tb_costos.getSelectedRow(), 0);
            PrecioCosto costo = new PrecioCosto();
            costo.setCostId(id);
            PrecioCostoJpaController pcjpa = new PrecioCostoJpaController(em.getEntityManagerFactory());
            cargarTabla();
            cargarCosto(costo);
            JOptionPane.showMessageDialog(null,"Cargando para editar... " + id);
            bt_aplicar.setEnabled(true);
            bt_guardar.setEnabled(false);
        } catch (Exception ex) {
            String resp = ex.getMessage();
            if (resp.equals(String.valueOf(-1))) {
                JOptionPane.showMessageDialog(null,"Seleccione correctamente!");
            } else {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
    }//GEN-LAST:event_bt_editarActionPerformed

    private void bt_aplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_aplicarActionPerformed
        if (tf_codigobarra.getText().length() != 0 && tf_nuevovalor.getText().length() != 0) {
            try {
                Producto prod = new Producto();
                prod.setProdId(Integer.parseInt(tf_codigobarra.getText()));
                int valor = Integer.parseInt(tf_nuevovalor.getText());
                PrecioCosto costo = new PrecioCosto();
                Date fecha = new Date();
                costo.setCostId(Integer.parseInt(tf_costo.getText()));
                costo.setCostProducto(prod);
                costo.setCostValor(valor);
                costo.setCostFechaIngreso(fecha);
                PrecioCostoJpaController cjpacontroller = new PrecioCostoJpaController(em2.getEntityManagerFactory());
                cjpacontroller.edit(costo);
                cargarTabla();
                limpiar();
                JOptionPane.showMessageDialog(this,"Editado costo ID "+costo.getCostId());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,e.getMessage());
            }
            bt_aplicar.setEnabled(false);
            bt_guardar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Llenar todos los campos!");
        }
    }//GEN-LAST:event_bt_aplicarActionPerformed

    private void tf_codigobarraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_codigobarraKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_tf_codigobarraKeyTyped

    private void tf_nuevovalorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nuevovalorKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_tf_nuevovalorKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_aplicar;
    private javax.swing.JButton bt_borrar;
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_guardar;
    private javax.persistence.EntityManager em;
    private javax.persistence.EntityManager em2;
    private javax.persistence.EntityManager em3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_costos;
    private javax.swing.JTextField tf_codigobarra;
    private javax.swing.JTextField tf_costo;
    private javax.swing.JTextField tf_familia;
    private javax.swing.JTextField tf_formato;
    private javax.swing.JTextField tf_linea;
    private javax.swing.JTextField tf_marca;
    private javax.swing.JTextField tf_nombre;
    private javax.swing.JTextField tf_nuevovalor;
    private javax.swing.JTextField tf_unidadmedida;
    // End of variables declaration//GEN-END:variables

    
}