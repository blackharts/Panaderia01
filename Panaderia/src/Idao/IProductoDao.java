/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;

import Models.Producto;
import java.util.List;

/**
 *
 * @author kalbl
 */
public interface IProductoDao {
     public List<Producto> obtenerCosto();
	public Producto obtenerCosto(int id);
        public void insertarCosto(Producto prod);   
	public void actualizarCosto(Producto prod);
	public void eliminarCosto(Producto prod);
}
