/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.PrecioCostoJpaController;
import Controller.PrecioVentaJpaController;
import Data.PrecioCosto;
import Data.PrecioVenta;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luisa
 */
public class VisualizarCostosJornLabor extends javax.swing.JInternalFrame {

    /**
     * Creates new form VisualizarCostos
     */
    public VisualizarCostosJornLabor() {
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

        entityManagerJornada = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        q_consulta_costo = java.beans.Beans.isDesignTime() ? null : entityManagerJornada.createQuery("Select c from PrecioCosto c");
        q_consulta_costo_venta = java.beans.Beans.isDesignTime() ? null : entityManagerJornada.createQuery("Select c from PrecioVenta c");
        jPanel1 = new javax.swing.JPanel();
        jl_FechaInicio = new javax.swing.JLabel();
        jd_fecha_inicial = new com.toedter.calendar.JDateChooser();
        jl_FechaFinal = new javax.swing.JLabel();
        jd_fecha_final = new com.toedter.calendar.JDateChooser();
        bt_mostrar_report = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_costos_jornada = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Costos Jornada Laboral");

        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        jl_FechaInicio.setText("Fecha Inicial:");
        jPanel1.add(jl_FechaInicio);
        jPanel1.add(jd_fecha_inicial);

        jl_FechaFinal.setText("Fecha Final:");
        jPanel1.add(jl_FechaFinal);
        jPanel1.add(jd_fecha_final);

        bt_mostrar_report.setText("Mostrar reporte");
        bt_mostrar_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_mostrar_reportActionPerformed(evt);
            }
        });

        tb_costos_jornada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Precio Costo", "Precio venta", "Ren.Porcentaje", "Ren.Moneda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_costos_jornada);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(bt_mostrar_report)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(bt_mostrar_report)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_mostrar_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_mostrar_reportActionPerformed
        // TODO add your handling code here:
        crearReporte();
    }//GEN-LAST:event_bt_mostrar_reportActionPerformed
    private void mostrarTabla(){
    DefaultTableModel report = (DefaultTableModel) tb_costos_jornada.getModel();
    PrecioCosto costo;
    PrecioVenta venta;
    PrecioCostoJpaController jpa_costo = new PrecioCostoJpaController(entityManagerJornada.getEntityManagerFactory());
    PrecioVentaJpaController jpa_venta = new PrecioVentaJpaController(entityManagerJornada.getEntityManagerFactory());
     Date date = new Date();// se trajo la fecha del sistema
        Date fecha_inicio = jd_fecha_inicial.getDate();
        Date fecha_final = jd_fecha_final.getDate();
       // Date fecha_pro=(costo.getCostFechaIngreso());
    }
private void crearReporte(){
        DefaultTableModel reports = (DefaultTableModel) tb_costos_jornada.getModel();
        List<PrecioVenta> vts = q_consulta_costo_venta.getResultList(); // se obtienen los productos y almcenan en lista
        for (int i = 0; i < tb_costos_jornada.getRowCount(); i++) {
        reports.removeRow(i);
        i-=1;
        }
        
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bt_mostrar_report;
    private javax.persistence.EntityManager entityManagerJornada;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jd_fecha_final;
    private com.toedter.calendar.JDateChooser jd_fecha_inicial;
    private javax.swing.JLabel jl_FechaFinal;
    private javax.swing.JLabel jl_FechaInicio;
    private javax.persistence.Query q_consulta_costo;
    private javax.persistence.Query q_consulta_costo_venta;
    private javax.swing.JTable tb_costos_jornada;
    // End of variables declaration//GEN-END:variables
}
