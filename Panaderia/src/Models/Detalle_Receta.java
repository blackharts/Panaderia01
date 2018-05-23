/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author kalbl
 */
public class Detalle_Receta {
    private Integer receta_id;
    private Integer producto_id;
    private Integer cantidad;
    
    public Detalle_Receta(Integer receta_id,Integer producto_id,Integer cantidad){
        this.receta_id=receta_id;
        this.producto_id=producto_id;
        this.cantidad=cantidad;
    }
    
    public Detalle_Receta(){
    }
    

    public int getReceta_id() {
        return receta_id;
    }

    public void setReceta_id(Integer receta_id) {
        this.receta_id = receta_id;
    }
    public void setReceta_id(String receta_id) {
        this.receta_id = Integer.parseInt(receta_id);
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Integer producto_id) {
        this.producto_id = producto_id;
    }
    public void setProducto_id(String producto_id) {
        this.producto_id = Integer.parseInt(producto_id);
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = Integer.parseInt(cantidad);
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
