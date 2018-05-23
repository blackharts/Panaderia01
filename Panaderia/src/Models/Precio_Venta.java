/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author KEVIN
 */
public class Precio_Venta {

    private Integer ID;
    private Date FECHA_VENTA;
    private Integer VALOR_VENTA;
    private Integer PRODUCTO_VENTA;

    /*Constructor*/
    public Precio_Venta(Integer ID, Date FECHA_VENTA, Integer VALOR_VENTA, Integer PRODUCTO_VENTA) {
        this.ID = ID;
        this.FECHA_VENTA = FECHA_VENTA;
        this.VALOR_VENTA = VALOR_VENTA;
        this.PRODUCTO_VENTA = PRODUCTO_VENTA;
    }

    /*Constructor vacio */
    public Precio_Venta() {

    }

    /**/
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setFECHA_VENTA(Date FECHA_VENTA) {
        this.FECHA_VENTA = FECHA_VENTA;
    }

    public void setVALOR_VENTA(Integer VALOR_VENTA) {
        this.VALOR_VENTA = VALOR_VENTA;
    }

    public void setPRODUCTO_VENTA(Integer PRODUCTO_VENTA) {
        this.PRODUCTO_VENTA = PRODUCTO_VENTA;
    }

    /**/
    public Integer getID() {
        return ID;
    }

    public Date getFECHA_VENTA() {
        return FECHA_VENTA;
    }

    public Integer getVALOR_VENTA() {
        return VALOR_VENTA;
    }

    public Integer getPRODUCTO_VENTA() {
        return PRODUCTO_VENTA;
    }

}
