/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Idao.IFamiliaDao;
/**
 *
 * @author kalbl
 */
public class Familia implements IFamiliaDao{
    private int id;
    private String nombre;
    private Linea lineaproducto;

    public Linea getLineaproducto() {
        return lineaproducto;
    }

    public void setLineaproducto(Linea lineaproducto) {
        this.lineaproducto = lineaproducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}