/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author kalbl
 */
public class Producto {
    
    private Integer id;
    private String nombre;
    private String marca;
    private Integer formato;
    private Integer familia_id;
    private Integer unidad_medida_id;
    private Date creado_el;
    private Date modificado_el;
    private Date eliminado_el;
    
    
    public Producto() {
    }

    public Producto(Integer id, String nombre, String marca, Integer formato, Integer familia_id, Integer unidad_medida_id, Date creado_el, Date modificado_el, Date eliminado_el) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.formato = formato;
        this.familia_id = familia_id;
        this.unidad_medida_id = unidad_medida_id;
        this.creado_el = creado_el;
        this.modificado_el = modificado_el;
        this.eliminado_el = eliminado_el;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public Integer getFormato() {
        return formato;
    }
    
    public void setFormato(Integer formato) {
        this.formato = formato;
    }
    
     public void setFormato(String formato) {
        this.formato = Integer.parseInt(formato);
    }   
    
    public Integer getFamilia_id() {
        return familia_id;
    }

    public void setFamilia_id(Integer familia_id) {
        this.familia_id = familia_id;
    }   
    
    public void setFamilia_id(String familia_id) {
        this.familia_id = Integer.parseInt(familia_id);
    } 
    
    public Integer getUnidad_medida_id() {
        return unidad_medida_id;
    }
    
    public void setUnidad_medida_id(Integer unidad_medida_id) {
        this.unidad_medida_id = unidad_medida_id;
    }
    
    public void setUnidad_medida_id(String unidad_medida_id) {
        this.unidad_medida_id = Integer.parseInt(unidad_medida_id);
    }
    
    public Date getCreado_el() {
        return creado_el;
    }
    
    public void setCreado_el(Date creado_el) {
        this.creado_el = creado_el;
    }

    public Date getModificado_el() {
        return modificado_el;
    }
    
    public void setModificado_el(Date modificado_el) {
        this.modificado_el = modificado_el;
    }

    public Date getEliminado_el() {
        return eliminado_el;
    }

    public void setEliminado_el(Date eliminado_el) {
        this.eliminado_el = eliminado_el;
    }


  
}
