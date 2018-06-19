/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controller.UsuarioJpaController;
import Data.Usuario;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author luisa
 */
public class NuevoUsuario extends javax.swing.JFrame {

    /**
     * Creates new form CrearUsuario
     */
    public NuevoUsuario() {
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

        jToggleButton1 = new javax.swing.JToggleButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        entityManager1 = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("PanaderiaPU").createEntityManager();
        listadousuarios = java.beans.Beans.isDesignTime() ? null : entityManager1.createQuery("SELECT u FROM Usuario u");
        jl_crear_usu = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();
        jl_name_usu = new javax.swing.JLabel();
        tf_new_usuario = new javax.swing.JTextField();
        jl_tipo_usu = new javax.swing.JLabel();
        cbox_tipo_usuario = new javax.swing.JComboBox<>();
        jl_pass_usu = new javax.swing.JLabel();
        tf_pass_usuario = new javax.swing.JTextField();
        jl_pass_admin = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pf_pass_admin = new javax.swing.JPasswordField();

        jToggleButton1.setText("jToggleButton1");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_crear_usu.setText("Crear usuario nuevo");

        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        jl_name_usu.setText("Nombre de Usuario");

        tf_new_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_new_usuarioActionPerformed(evt);
            }
        });

        jl_tipo_usu.setText("Tipo de Usuario");

        cbox_tipo_usuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrativo", "Bodegero", "Gerente" }));
        cbox_tipo_usuario.setToolTipText("");
        cbox_tipo_usuario.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        cbox_tipo_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_tipo_usuarioActionPerformed(evt);
            }
        });

        jl_pass_usu.setText("Contraseña");

        tf_pass_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_pass_usuarioActionPerformed(evt);
            }
        });

        jl_pass_admin.setText("Contraseña de Administrador");

        pf_pass_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_pass_adminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_name_usu)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_tipo_usu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1))
                            .addComponent(jl_pass_usu)
                            .addComponent(jl_pass_admin))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_new_usuario)
                            .addComponent(cbox_tipo_usuario, 0, 152, Short.MAX_VALUE)
                            .addComponent(tf_pass_usuario)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pf_pass_admin)))
                        .addGap(6, 6, 6))
                    .addComponent(btn_guardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jl_crear_usu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jl_crear_usu)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_name_usu)
                    .addComponent(tf_new_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_tipo_usu)
                            .addComponent(cbox_tipo_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_pass_usu)
                    .addComponent(tf_pass_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_pass_admin)
                    .addComponent(pf_pass_admin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btn_guardar)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
       Guardar();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void tf_new_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_new_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_new_usuarioActionPerformed

    private void cbox_tipo_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_tipo_usuarioActionPerformed
                                       
    }//GEN-LAST:event_cbox_tipo_usuarioActionPerformed

    private void tf_pass_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_pass_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_pass_usuarioActionPerformed

    private void pf_pass_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pf_pass_adminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pf_pass_adminActionPerformed
    
    public void Guardar(){     
        Usuario usuario_registrado = new Usuario();
        Usuario usuario_admin = new Usuario(); 
        ArrayList<Usuario> usuarios = new ArrayList(listadousuarios.getResultList());  
        if(tf_new_usuario.getText().equals("") || tf_pass_usuario.getText().equals("") || pf_pass_admin.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Campos vacios"); 
        }
        else{          
            for(Usuario a:usuarios){            
                if(a.getUsuContrasena().equals(pf_pass_admin.getText()) && a.getTipoUsuario().equals("Administrativo")){
                    usuario_admin.setUsuId(a.getUsuId());
                    usuario_admin.setUsuNombre(a.getUsuNombre());
                    usuario_admin.setUsuContrasena(a.getUsuContrasena());
                    usuario_admin.setTipoUsuario(a.getTipoUsuario());
                }
            } 
            try{
                if (usuario_admin.getUsuContrasena().equals(pf_pass_admin.getText())){
                    UsuarioJpaController ujc;            
                    ujc = new UsuarioJpaController(entityManager1.getEntityManagerFactory());
                    Usuario usu = new Usuario();                               
                    usu.setUsuNombre(tf_new_usuario.getText());
                    usu.setUsuContrasena(tf_pass_usuario.getText());
                    String tipo_usu = (String)cbox_tipo_usuario.getSelectedItem();                
                    usu.setTipoUsuario(tipo_usu);
                    for(Usuario a:usuarios){            
                        if(a.getUsuNombre().equals(tf_new_usuario.getText())){
                            usuario_registrado.setUsuId(a.getUsuId());
                            usuario_registrado.setUsuNombre(a.getUsuNombre());
                            usuario_registrado.setUsuContrasena(a.getUsuContrasena());
                            usuario_registrado.setTipoUsuario(a.getTipoUsuario()); 
                        }
                    } 
                    if (tf_new_usuario.getText().equals(usuario_registrado.getUsuNombre())){
                        JOptionPane.showMessageDialog(null,"Usuario ya existe"); 
                        tf_new_usuario.setText("");
                    }
                    else if (!tf_new_usuario.getText().equals(usuario_registrado.getUsuNombre())){
                        ujc.create(usu);
                        JOptionPane.showMessageDialog(null,"Usuario Creado Exitosamente");
                        tf_new_usuario.setText("");
                        tf_pass_usuario.setText("");
                        pf_pass_admin.setText("");                    
                    }
                }
                else if (!usuario_registrado.getUsuContrasena().equals(pf_pass_admin.getText())){
                    JOptionPane.showMessageDialog(null,"Vuelva a Ingresar la Contraseña de Administrador"); 
                    pf_pass_admin.setText("");  
                }
            }
            catch(NullPointerException e){
                JOptionPane.showMessageDialog(null,"Vuelva a Ingresar la Contraseña de Administrador");                                                            
                pf_pass_admin.setText("");  
            }
        }            
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JComboBox<String> cbox_tipo_usuario;
    private javax.persistence.EntityManager entityManager1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel jl_crear_usu;
    private javax.swing.JLabel jl_name_usu;
    private javax.swing.JLabel jl_pass_admin;
    private javax.swing.JLabel jl_pass_usu;
    private javax.swing.JLabel jl_tipo_usu;
    private javax.persistence.Query listadousuarios;
    private javax.swing.JPasswordField pf_pass_admin;
    private javax.swing.JTextField tf_new_usuario;
    private javax.swing.JTextField tf_pass_usuario;
    // End of variables declaration//GEN-END:variables
}
