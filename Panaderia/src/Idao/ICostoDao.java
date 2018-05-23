/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Idao;

import Models.Costo;
import java.util.List;

/**
 *
 * @author kalbl
 */
public interface ICostoDao {
    public List<Costo> obtenerCosto();
	public Costo obtenerCosto(int id);
        public void insertarCosto(Costo costo);   
	public void actualizarCosto(Costo costo);
	public void eliminarCosto(Costo costo);
}
