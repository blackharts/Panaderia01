/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/**
 *
 * @author Alex
 */
public class IngresoPrecioVenta extends javax.swing.JInternalFrame {

    /**
     * Creates new form IngresarCostos
     */
    public IngresoPrecioVenta() {
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
        lb_codigobarra = new javax.swing.JLabel();
        tf_codigobarra = new javax.swing.JTextField();
        bt_buscar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lb_nombre = new javax.swing.JLabel();
        tf_nombre = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lb_unimedida = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_formato = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lb_linea = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lb_familia = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lb_nuevocosto = new javax.swing.JLabel();
        tf_nuevocosto = new javax.swing.JTextField();
        bt_guardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_PrecioVenta = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Precio Venta");

        jPanel1.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        lb_codigobarra.setText("Codigo de Barra");
        jPanel1.add(lb_codigobarra);
        jPanel1.add(tf_codigobarra);

        bt_buscar.setText("Buscar");
        jPanel1.add(bt_buscar);

        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        lb_nombre.setText("Nombre");
        jPanel2.add(lb_nombre);
        jPanel2.add(tf_nombre);

        jPanel4.setLayout(new java.awt.GridLayout(5, 2, 20, 20));

        lb_unimedida.setText("Unidad de Medida");
        jPanel4.add(lb_unimedida);
        jPanel4.add(jLabel1);

        jLabel5.setText("Marca");
        jPanel4.add(jLabel5);
        jPanel4.add(jLabel7);

        lb_formato.setText("Formato");
        jPanel4.add(lb_formato);
        jPanel4.add(jLabel9);

        lb_linea.setText("Linea");
        jPanel4.add(lb_linea);
        jPanel4.add(jLabel11);

        lb_familia.setText("Familia");
        jPanel4.add(lb_familia);
        jPanel4.add(jLabel13);

        jPanel5.setLayout(new java.awt.GridLayout(1, 3, 10, 0));

        lb_nuevocosto.setText("Nuevo Costo");
        jPanel5.add(lb_nuevocosto);
        jPanel5.add(tf_nuevocosto);

        bt_guardar.setText("Guardar");
        jPanel5.add(bt_guardar);

        tabla_PrecioVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Fecha", "Precio costo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabla_PrecioVenta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_codigobarra;
    private javax.swing.JLabel lb_familia;
    private javax.swing.JLabel lb_formato;
    private javax.swing.JLabel lb_linea;
    private javax.swing.JLabel lb_nombre;
    private javax.swing.JLabel lb_nuevocosto;
    private javax.swing.JLabel lb_unimedida;
    private javax.swing.JTable tabla_PrecioVenta;
    private javax.swing.JTextField tf_codigobarra;
    private javax.swing.JTextField tf_nombre;
    private javax.swing.JTextField tf_nuevocosto;
    // End of variables declaration//GEN-END:variables
}
