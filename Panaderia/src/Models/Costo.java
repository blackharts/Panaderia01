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
    private Producto producto_costo;

    //Constructor sin parámetros
    public Costo() {
        super();
    }

    //Constructor con todos los parámetros
    public Costo(Integer id, Date fecha, Integer valor_costo, Producto producto_costo) {
        this();
        this.id = id;
        this.fecha = fecha;
        this.valor_costo = valor_costo;
        this.producto_costo = producto_costo;
    }

    //Setters
    //Poliformismo ID
    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    //Poliformismo VALOR COSTO
    public void setValor_costo(String valor_costo) {
        this.valor_costo = Integer.parseInt(valor_costo);
    }
    public void setValor_costo(Integer valor_costo) {
        this.valor_costo = valor_costo;
    }

    public void setProducto_costo(Producto producto_costo) {
        this.producto_costo = producto_costo;
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

    public Producto getProducto_costo() {
        return producto_costo;
    }
}
