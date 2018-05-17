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
public class Precio_Venta {

    private Integer id = 0;
    private Date fecha;
    private Integer valor = 0;
    private boolean es_actual;
    private Date creado_el;
    private Date modificado_el;
    private Date eliminado_el;
    private Producto producto;

    public Precio_Venta() {

    }

    public Precio_Venta(Date fecha, boolean es_actual, Date creado_el, Date modificado_el, Date eliminado_el, Producto producto) {
        this.fecha = fecha;
        this.es_actual = es_actual;
        this.creado_el = creado_el;
        this.modificado_el = modificado_el;
        this.eliminado_el = eliminado_el;
        this.producto = producto;
    }

    //set
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setEs_actual(boolean es_actual) {
        this.es_actual = es_actual;
    }

    public void setCreado_el(Date creado_el) {
        this.creado_el = creado_el;
    }

    public void setModificado_el(Date modificado_el) {
        this.modificado_el = modificado_el;
    }

    public void setEliminado_el(Date eliminado_el) {
        this.eliminado_el = eliminado_el;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    //get 
    public Integer getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Integer getValor() {
        return valor;
    }

    public boolean isEs_actual() {
        return es_actual;
    }

    public Date getCreado_el() {
        return creado_el;
    }

    public Date getModificado_el() {
        return modificado_el;
    }

    public Date getEliminado_el() {
        return eliminado_el;
    }

    public Producto getProducto() {
        return producto;
    }

}
