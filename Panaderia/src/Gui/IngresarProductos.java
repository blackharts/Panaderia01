/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/**
 *
 * @author luisa
 */
public class IngresarProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrame
     */
    public IngresarProductos() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jt_codbarra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jc_umedida = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jt_marca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jt_formato = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jc_linea = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jc_familia = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        jPanel1.setLayout(new java.awt.GridLayout(7, 2, 10, 10));

        jLabel1.setText("Código barra:");
        jPanel1.add(jLabel1);
        jPanel1.add(jt_codbarra);

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2);
        jPanel1.add(jt_nombre);

        jLabel3.setText(" Unidad de Medida:");
        jPanel1.add(jLabel3);

        jc_umedida.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jc_umedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_umedidaActionPerformed(evt);
            }
        });
        jPanel1.add(jc_umedida);

        jLabel4.setText("Marca:");
        jPanel1.add(jLabel4);
        jPanel1.add(jt_marca);

        jLabel5.setText("Formato:");
        jPanel1.add(jLabel5);
        jPanel1.add(jt_formato);

        jLabel6.setText("Linea:");
        jPanel1.add(jLabel6);

        jc_linea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jc_linea);

        jLabel7.setText("Familia:");
        jPanel1.add(jLabel7);

        jc_familia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jc_familia);

        jButton1.setText("Guardar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jc_umedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_umedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jc_umedidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox jc_familia;
    private javax.swing.JComboBox jc_linea;
    private javax.swing.JComboBox jc_umedida;
    private javax.swing.JTextField jt_codbarra;
    private javax.swing.JTextField jt_formato;
    private javax.swing.JTextField jt_marca;
    private javax.swing.JTextField jt_nombre;
    // End of variables declaration//GEN-END:variables
}
