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
public class Costo {

    private Integer id = 0;
    private Date fecha;
    private Integer valor_costo = 0;
    private boolean actual; //Se refiere si el costo actualizado es actual o no...¿Realmente es necesario?
    private Date fecha_creacion;
    private Date fecha_modificacion;
    private Date fecha_eliminacion;
    private Producto producto;

    //Constructor sin parámetros
    public Costo() {
    }

    //Constructor con todos los parámetros
    public Costo(Date fecha, boolean actual, Date fecha_creacion, Date fecha_modificacion, Date fecha_eliminacion, Producto producto) {
        this();
        this.fecha = fecha;
        this.actual = actual;
        this.fecha_creacion = fecha_creacion;
        this.fecha_modificacion = fecha_modificacion;
        this.fecha_eliminacion = fecha_eliminacion;
        this.producto = producto;
    }
    //Setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setValor_costo(Integer valor_costo) {
        this.valor_costo = valor_costo;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public void setFecha_eliminacion(Date fecha_eliminacion) {
        this.fecha_eliminacion = fecha_eliminacion;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Integer getValor_costo() {
        return valor_costo;
    }

    public boolean isActual() {
        return actual;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public Date getFecha_eliminacion() {
        return fecha_eliminacion;
    }

    public Producto getProducto() {
        return producto;
    }

}
