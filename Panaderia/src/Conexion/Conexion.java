/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author kalbl
 */
public class Conexion {
     public String db="panaderia";
    public String url = "jdbc:mysql://localhost/"+db;
    public String user="root";
    public String pass="";
    
    public Connection Conectar(){

       Connection link = null;

       try{

           Class.forName("com.mysql.jdbc.Driver");

           link = DriverManager.getConnection(this.url, this.user, this.pass);
           JOptionPane.showMessageDialog(null,"Conexion establecida");

       }catch(ClassNotFoundException | SQLException ex){

           JOptionPane.showMessageDialog(null, ex);

       }


       return link;

   }
}
